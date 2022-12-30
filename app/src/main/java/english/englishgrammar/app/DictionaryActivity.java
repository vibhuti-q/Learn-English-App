package english.englishgrammar.app;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import english.englishgrammar.app.db.DatabaseHelper;
import english.englishgrammar.app.db.LoadDatabaseAsync;

@SuppressLint("Range")
public class DictionaryActivity extends AppCompatActivity {
    SearchView search;
    static DatabaseHelper databaseHelper;
    static boolean databaseOpenned = false;
    SimpleCursorAdapter simpleCursorAdapter;
    ArrayList<History> historyList;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter historyAdapter;
    RelativeLayout emptyHistory;
    Cursor cursorHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        // Version için destekleyici Toolbar eklenmesi işlemi.
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        search = findViewById(R.id.search_view);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search.setIconified(false);
            }
        });

        databaseHelper = new DatabaseHelper(this);

        if (databaseHelper.checkDatabase()) {
            openDatabase();
        } else {

            LoadDatabaseAsync loadDatabaseAsync = new LoadDatabaseAsync(DictionaryActivity.this);
            loadDatabaseAsync.execute();

        }

        final String[] from = new String[]{"en_word"};
        final int[] to = new int[]{R.id.suggestion_text};


        simpleCursorAdapter = new SimpleCursorAdapter(DictionaryActivity.this,
                R.layout.suggestion_row, null, from, to, 0
        ) {
            @Override
            public void changeCursor(Cursor cursor) {
                super.changeCursor(cursor);
            }
        };


        search.setSuggestionsAdapter(simpleCursorAdapter);

        search.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionClick(int position) {
                try {// Add clicked text to search box
                    CursorAdapter ca = search.getSuggestionsAdapter();
                    Cursor cursor = ca.getCursor();
                    cursor.moveToPosition(position);
                    String clicked_word = cursor.getString(cursor.getColumnIndex("en_word"));
                    search.setQuery(clicked_word, false);

                    //search.setQuery("",false);

                    search.clearFocus();
                    search.setFocusable(false);

                    Intent intent = new Intent(DictionaryActivity.this, WordMeaningActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("en_word", clicked_word);
                    intent.putExtras(bundle);
                    startActivity(intent);

                } catch (Exception e) {

                    Toast.makeText(DictionaryActivity.this, "not available", Toast.LENGTH_SHORT).show();
                }
                return true;

            }

            @Override
            public boolean onSuggestionSelect(int position) {
                // Your code here
                return true;
            }
        });


        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String text = search.getQuery().toString();
                Cursor c = databaseHelper.getMeaning(text);

                if (c.getCount() == 0) {
                    search.setQuery("", false);

                    AlertDialog.Builder builder = new AlertDialog.Builder(DictionaryActivity.this, R.style.MyDialogTheme);
                    builder.setTitle("Word Not Found");
                    builder.setMessage("Please search again");

                    String positiveText = getString(android.R.string.ok);
                    builder.setPositiveButton(positiveText,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // positive button logic
                                }
                            });

                    String negativeText = getString(android.R.string.cancel);
                    builder.setNegativeButton(negativeText,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    search.clearFocus();
                                }
                            });

                    AlertDialog dialog = builder.create();
                    // display dialog
                    dialog.show();
                } else {
                    //search.setQuery("",false);
                    search.clearFocus();
                    search.setFocusable(false);

                    Intent intent = new Intent(DictionaryActivity.this, WordMeaningActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("en_word", text);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }

                return false;
            }


            @Override
            public boolean onQueryTextChange(final String s) {

                search.setIconifiedByDefault(false); //Give Suggestion list margins
                Cursor cursorSuggestion = databaseHelper.getSuggestions(s);
                simpleCursorAdapter.changeCursor(cursorSuggestion);

                return false;
            }

        });


        emptyHistory = (RelativeLayout) findViewById(R.id.empty_history);

        //recycler View
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_history);
        layoutManager = new LinearLayoutManager(DictionaryActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        fetch_history();

    }

    @SuppressLint("Range")
    private void fetch_history() {
        historyList = new ArrayList<>();
        historyAdapter = new RecyclerViewAdapterHistory(this, historyList);
        recyclerView.setAdapter(historyAdapter);

        History h;

        if (databaseOpenned) {
            cursorHistory = databaseHelper.getHistory();
            if (cursorHistory.moveToFirst()) {
                do {
                    h = new History(cursorHistory.getString(cursorHistory.getColumnIndex("word")),
                            cursorHistory.getString(cursorHistory.getColumnIndex("en_definition")));
                    historyList.add(h);
                }
                while (cursorHistory.moveToNext());
            }

            historyAdapter.notifyDataSetChanged();
        }


        if (historyAdapter.getItemCount() == 0) {
            emptyHistory.setVisibility(View.VISIBLE);
        } else {
            emptyHistory.setVisibility(View.GONE);
        }
    }


    public static void openDatabase() {
        try {
            databaseHelper.openDatabase();
            databaseOpenned = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // İlgili menu xml dosyasını şişiriyoruz.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // İlgili menü seçeneklerinin seçilmesi durumunda gerçekleşecek işlemler.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(DictionaryActivity.this, SettingsActivity.class);
            startActivity(intent);
            return true;

        }

        if (id == R.id.action_exit) {
            System.exit(0);
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        fetch_history();
    }
}
