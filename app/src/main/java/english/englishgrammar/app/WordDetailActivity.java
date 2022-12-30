package english.englishgrammar.app;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.Locale;

import english.englishgrammar.app.jaky_lxi.interfaces_lxi.LxiNativeAdListener;
import english.englishgrammar.app.jaky_lxi.interfaces_lxi.LxiadmobCloseEvent;
import english.englishgrammar.app.jaky_lxi.mains_lxi.NaxleApps;
import english.englishgrammar.app.jaky_lxi.utils_lxi.LxiMyPreferenceManager;

public class WordDetailActivity extends AppCompatActivity {

    private AdView mAdView;
    private TextToSpeech textToSpeech;
    Typeface alvi_Nastaleeq_Lahori;
    TextView wordTxt, meaningTxt, searchedWordTxt;
    String[] words = {"quizeck", "asotori", "metabolism", "qurectallina", "polimorphism"};
    ImageButton backBtn, speechBtn;
    ExampleAdapter exampleAdapter;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_detail);


      

        ///native ad
        LxiMyPreferenceManager myPreferenceManagerpics = new LxiMyPreferenceManager(this);
        NaxleApps adsGlobalClassEveryTimepics = new NaxleApps();
        adsGlobalClassEveryTimepics.showAndLoadGoogleNativelxi(WordDetailActivity.this,
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

        intent = getIntent();
        alvi_Nastaleeq_Lahori = Typeface.createFromAsset(this.getAssets(), "fonts/alvi_Nastaleeq_Lahori.ttf");
        init();
        exampleAdapter = new ExampleAdapter();

        wordTxt.setText(intent.getStringExtra("word"));
        searchedWordTxt.setText(intent.getStringExtra("word"));
        meaningTxt.setTypeface(alvi_Nastaleeq_Lahori);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            meaningTxt.setText(Html.fromHtml(intent.getStringExtra("meaning"), Html.FROM_HTML_MODE_COMPACT));
        } else {
            meaningTxt.setText(Html.fromHtml(intent.getStringExtra("meaning")));
        }

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int ttsLang = textToSpeech.setLanguage(Locale.US);

                    if (ttsLang == TextToSpeech.LANG_MISSING_DATA
                            || ttsLang == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "The Language is not supported!");
                    } else {
                        Log.i("TTS", "Language Supported.");
                    }
                    Log.i("TTS", "Initialization success.");
                } else {
                    Log.d("msg", "failed");
                }
            }
        });

        speechBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = wordTxt.getText().toString();
                textToSpeech.speak(data, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {onBackPressed();
            }
        });

    }

    public void favbtn(View view){
        NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
        adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(WordDetailActivity.this, new LxiadmobCloseEvent() {
            @Override
            public void setFailedlxi() {
                startActivity(new Intent(WordDetailActivity.this,FavouriteMessages.class));

            }

            @Override
            public void setSuccesslxi() {
            }

            @Override
            public void setAdmobCloseEventlxi() {
                startActivity(new Intent(WordDetailActivity.this,FavouriteMessages.class));

            }
        }, new LxiMyPreferenceManager(WordDetailActivity.this).fIdlxi());
    }

    private void init() {
        wordTxt = findViewById(R.id.wordTxt);
        searchedWordTxt = findViewById(R.id.searchedWordTxt);
        speechBtn = findViewById(R.id.speechBtn);
        meaningTxt = findViewById(R.id.meaningTxt);
        backBtn = findViewById(R.id.backBtn);
    }


    //  WHEN ACTIVITY IS PAUSED
    @Override
    protected void onPause() {
        if (textToSpeech != null) {
            textToSpeech.stop();
        }
        super.onPause();
    }

    //  WHEN ACTIVITY IS DESTROYED
    @Override
    public void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
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
            View rowView = View.inflate(WordDetailActivity.this, R.layout.words_cell, null);

            TextView wordsTxt = rowView.findViewById(R.id.wordsTxt);

            wordsTxt.setText(words[i]);

            return rowView;
        }
    }

//    public void favbtn(View view) {
//        startActivity(new Intent(getApplicationContext(), FavouriteMessages.class));
//    }

}
