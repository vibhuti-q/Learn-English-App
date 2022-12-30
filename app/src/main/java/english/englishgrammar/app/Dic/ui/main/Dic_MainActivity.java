package english.englishgrammar.app.Dic.ui.main;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.gson.Gson;

import java.util.List;

import english.englishgrammar.app.Dic.data.model.wordsapi.Word;
import english.englishgrammar.app.Dic.ui.adapter.FavouritesAdapter;
import english.englishgrammar.app.Dic.ui.viewmodel.MainViewModel;
import english.englishgrammar.app.Dic.util.WordResponseListener;
import english.englishgrammar.app.EnglishLessons;
import english.englishgrammar.app.FavouriteMessages;
import english.englishgrammar.app.R;
import english.englishgrammar.app.databinding.DicActivityMainBinding;
import english.englishgrammar.app.jaky_lxi.interfaces_lxi.LxiNativeAdListener;
import english.englishgrammar.app.jaky_lxi.interfaces_lxi.LxiadmobCloseEvent;
import english.englishgrammar.app.jaky_lxi.mains_lxi.NaxleApps;
import english.englishgrammar.app.jaky_lxi.utils_lxi.LxiMyPreferenceManager;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Dic_MainActivity extends AppCompatActivity {
    private DicActivityMainBinding binding;
    private static final String TAG = "MainActivity";
    private MainViewModel mViewModel;
    //    public List<Word> listFavWords = new ArrayList<>();
    public static TextView notxt;
    public static LinearLayout ll;
    private FavouritesAdapter mFavAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DicActivityMainBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);

        notxt = (findViewById(R.id.notxt));
        ll = (findViewById(R.id.ll));

        ///native ad
        LxiMyPreferenceManager myPreferenceManagerpics = new LxiMyPreferenceManager(this);
        NaxleApps adsGlobalClassEveryTimepics = new NaxleApps();
        adsGlobalClassEveryTimepics.showAndLoadGoogleNativelxi(Dic_MainActivity.this,
                myPreferenceManagerpics.nadIdlxi(),
                findViewById(R.id.containerlxi),
                true, 1,
                new LxiNativeAdListener() {
                    @Override
                    public void setNativeFailedlxi() {
                        findViewById(R.id.nadViewlxi).setVisibility(View.GONE);
                    }

                    @Override
                    public void setNativeSuccesslxi() {
                    }
                });

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        try {
            initValues();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    public void favbtn(View view) {
        NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
        adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(Dic_MainActivity.this, new LxiadmobCloseEvent() {
            @Override
            public void setFailedlxi() {
                startActivity(new Intent(Dic_MainActivity.this, FavouriteMessages.class));
            }

            @Override
            public void setSuccesslxi() {
            }

            @Override
            public void setAdmobCloseEventlxi() {
                startActivity(new Intent(Dic_MainActivity.this, FavouriteMessages.class));
            }
        }, new LxiMyPreferenceManager(Dic_MainActivity.this).fIdlxi());
    }

    private void initValues() {
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mViewModel.init(getApplication());
        createSearchBarEvent();
        createFavouritesRV();
        binding.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Word> listW = mFavAdapter.getSelectedWords();
                if (listW.size() > 0) {
                    mViewModel.removeListofWords(listW, new OnRemoveSelectedWords() {
                        @Override
                        public void clear(boolean result) {
                            if (result) {
                                mFavAdapter.clearSelectedWords();
//                                Toast.makeText(getApplicationContext(), "Please long press the word from favourite you want to remove", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                } else {
//                    Toast.makeText(getApplicationContext(), "no data", Toast.LENGTH_LONG).show();

//                    notxt.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    void createFavouritesRV() {
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getApplicationContext());
        binding.rvFavourites.setLayoutManager(layoutManager);
        mFavAdapter = new FavouritesAdapter(getApplicationContext(),
                new FavouritesAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Word item) {
                        openDetailActivity(item, true);
                    }
                });

        binding.rvFavourites.setAdapter(mFavAdapter);
        mViewModel.getFavWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                if (words != null) {
                    mFavAdapter.setFavWordsList(words);
//                    notxt.setVisibility(View.GONE);
//                    ll.setVisibility(View.GONE);
                    mFavAdapter.notifyDataSetChanged();
                } else {
                    Log.d(TAG, "words == null");
//                    notxt.setVisibility(View.VISIBLE);
//                    ll.setVisibility(View.VISIBLE);
                    mFavAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    void createSearchBarEvent() {
        binding.svSearchbar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Single<Word> w = mViewModel.findWord(query);
                w.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<Word>() {
                            @Override
                            public void onSuccess(@NonNull Word word) {
                                if (word != null) {
                                    openDetailActivity(word, true);
                                } else {
                                    Log.e(TAG, "onSuccess " + query);
                                }
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                if (isConnected()) {
                                    getWordFromApi(query);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Please check your internet connection and try again", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mFavAdapter.filter(newText);
                return true;
            }
        });
    }

    void openDetailActivity(Word word, Boolean isFav) {
        NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
        adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(Dic_MainActivity.this, new LxiadmobCloseEvent() {
            @Override
            public void setFailedlxi() {
                Gson gson = new Gson();
                String myJson = gson.toJson(word);
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("wordItem", myJson);
                if (isFav) {
                    intent.putExtra("favourite", true);
                }
                startActivity(intent);
            }

            @Override
            public void setSuccesslxi() {
            }

            @Override
            public void setAdmobCloseEventlxi() {
                Gson gson = new Gson();
                String myJson = gson.toJson(word);
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("wordItem", myJson);
                if (isFav) {
                    intent.putExtra("favourite", true);
                }
                startActivity(intent);
            }
        }, new LxiMyPreferenceManager(Dic_MainActivity.this).fIdlxi());
    }

    void getWordFromApi(String text) {
        mViewModel.getWord(text, new WordResponseListener() {
            @Override
            public void onSuccess(Word word) {
                if (word.getResults() != null) {
                    try {
                        openDetailActivity(word, false);
                    } catch (Exception e) {
                        Log.e(TAG, e.toString());
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Word not found", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFail(String s) {
                Log.d(TAG, s);
                AlertDialog.Builder builder1 = new AlertDialog.Builder(Dic_MainActivity.this);
                builder1.setMessage("Word not found");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

//                builder1.setNegativeButton(
//                        "No",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                dialog.cancel();
//                            }
//                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
                hideKeyboard(Dic_MainActivity.this);
//                Toast.makeText(getApplicationContext(), "Word not founddddd", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Throwable t) {
                Log.e(TAG, t.getMessage());
                Toast.makeText(getApplicationContext(), "Error " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public interface OnRemoveSelectedWords {
        void clear(boolean result);
    }
    @Override
    protected void onResume() {
        super.onResume();
        NaxleApps.setAdShowDialogslxi(Dic_MainActivity.this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}