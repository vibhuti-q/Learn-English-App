package english.englishgrammar.app;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import english.englishgrammar.app.SharedData.SharedClass;
import english.englishgrammar.app.jaky_lxi.interfaces_lxi.LxiadmobCloseEvent;
import english.englishgrammar.app.jaky_lxi.mains_lxi.NaxleApps;
import english.englishgrammar.app.jaky_lxi.utils_lxi.LxiMyPreferenceManager;

public class WordsActivity extends AppCompatActivity {

    String[] words = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
            "Y", "Z"};
    ImageButton backBtn;       ImageView defbtnon, defbtnoff, methodbtnon, methodbtnoff;
    ListView exampleLV;
    ExampleAdapter exampleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);



      

        //Banner AD


        LxiMyPreferenceManager myPreferenceManagerdude = new LxiMyPreferenceManager(WordsActivity.this);
        NaxleApps adsGlobalClassEveryTimedude = new NaxleApps();




        adsGlobalClassEveryTimedude.showBannerAdlxi(WordsActivity.this, new LxiadmobCloseEvent() {
                    @Override
                    public void setFailedlxi() {
                        findViewById(R.id.adViewlxi).setVisibility(View.GONE);
                    }

                    @Override
                    public void setSuccesslxi() {
                    }

                    @Override
                    public void setAdmobCloseEventlxi() {

                    }
                },


                myPreferenceManagerdude.getGBannerIDlxi(), findViewById(R.id.adViewLayoutlxi));
        backBtn = findViewById(R.id.backBtn);
        exampleLV = findViewById(R.id.exampleLV);
        exampleAdapter = new ExampleAdapter();
        exampleLV.setAdapter(exampleAdapter);
        exampleLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SharedClass.alphabet = words[i];
                startActivity(new Intent(getApplicationContext(), DictionaryActivity.class));
                finish();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public class ExampleAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return words.length;
        }

        @Override
        public Object getItem(int i) {
            return words[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View rowView = View.inflate(WordsActivity.this, R.layout.words_cell, null);

            TextView wordsTxt = rowView.findViewById(R.id.wordsTxt);

            wordsTxt.setText(words[i]);

            return rowView;
        }
    }


    public void favbtn(View view){
        NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
        adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(WordsActivity.this, new LxiadmobCloseEvent() {
            @Override
            public void setFailedlxi() {
                startActivity(new Intent(WordsActivity.this,FavouriteMessages.class));

            }

            @Override
            public void setSuccesslxi() {
            }

            @Override
            public void setAdmobCloseEventlxi() {
                startActivity(new Intent(WordsActivity.this,FavouriteMessages.class));

            }
        }, new LxiMyPreferenceManager(WordsActivity.this).fIdlxi());
    }

}
