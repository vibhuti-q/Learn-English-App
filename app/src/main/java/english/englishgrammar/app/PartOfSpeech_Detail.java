package english.englishgrammar.app;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import english.englishgrammar.app.Manager.Conversation_DBManager;
import english.englishgrammar.app.Models.ExampleModel;
import english.englishgrammar.app.SharedData.SharedClass;
import english.englishgrammar.app.jaky_lxi.interfaces_lxi.LxiadmobCloseEvent;
import english.englishgrammar.app.jaky_lxi.mains_lxi.NaxleApps;
import english.englishgrammar.app.jaky_lxi.utils_lxi.LxiMyPreferenceManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PartOfSpeech_Detail extends AppCompatActivity {

    private TextToSpeech textToSpeech;
    List<ExampleModel> exampleData = new ArrayList<>();
    ImageButton backBtn;
    ImageView defbtnon, defbtnoff, methodbtnon, methodbtnoff;
    TextView titleTxt;
    Conversation_DBManager dbManager;
    Typeface alvi_Nastaleeq_Lahori;
    TextView engDeftxt, urduDeftxt, methodEnglish, methodUrdu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.part_of_speech_detail);


        //Banner AD


        LxiMyPreferenceManager myPreferenceManagerdude = new LxiMyPreferenceManager(PartOfSpeech_Detail.this);
        NaxleApps adsGlobalClassEveryTimedude = new NaxleApps();

        adsGlobalClassEveryTimedude.showBannerAdlxi(PartOfSpeech_Detail.this, new LxiadmobCloseEvent() {
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

        initExample();

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


        defbtnon = findViewById(R.id.defbtnon);
        defbtnoff = findViewById(R.id.defbtnoff);

        methodbtnon = findViewById(R.id.methodbtnon);
        methodbtnoff = findViewById(R.id.methodbtnoff);

        alvi_Nastaleeq_Lahori = Typeface.createFromAsset(this.getAssets(), "fonts/alvi_Nastaleeq_Lahori.ttf");
        backBtn = findViewById(R.id.backBtn);

        dbManager = new Conversation_DBManager(this);
        dbManager.open();
        try {
            dbManager.copyDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        titleTxt = findViewById(R.id.titleTxt);
        methodUrdu = findViewById(R.id.methodUrdu);
        methodEnglish = findViewById(R.id.methodEnglish);
        engDeftxt = findViewById(R.id.engDeftxt);
        urduDeftxt = findViewById(R.id.urduDeftxt);
        urduDeftxt.setTypeface(alvi_Nastaleeq_Lahori);
        engDeftxt.setText(dbManager.getPartsOfSpeech(SharedClass.tense_id).get(0).getDef_eng());
        urduDeftxt.setText(dbManager.getPartsOfSpeech(SharedClass.tense_id).get(0).getDef_urdu());
        methodEnglish.setText(dbManager.getPartsOfSpeech(SharedClass.tense_id).get(0).getEg_eng());
        titleTxt.setText(dbManager.getPartsOfSpeech(SharedClass.tense_id).get(0).getName_eng());

        defbtnon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!textToSpeech.isSpeaking()) {
                    textToSpeech.speak(dbManager.getPartsOfSpeech(SharedClass.tense_id).get(0).getDef_eng(), TextToSpeech.QUEUE_FLUSH, null);
                    defbtnoff.setVisibility(View.GONE);
                    defbtnon.setVisibility(View.VISIBLE);
                } else {
                    textToSpeech.speak(dbManager.getPartsOfSpeech(SharedClass.tense_id).get(0).getDef_eng(), TextToSpeech.QUEUE_FLUSH, null);

                    textToSpeech.stop();

                    defbtnoff.setVisibility(View.VISIBLE);
                    defbtnon.setVisibility(View.GONE);
                }
            }
        });
        defbtnoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!textToSpeech.isSpeaking()) {
                    textToSpeech.speak(dbManager.getPartsOfSpeech(SharedClass.tense_id).get(0).getDef_eng(), TextToSpeech.QUEUE_FLUSH, null);
                    defbtnoff.setVisibility(View.GONE);
                    defbtnon.setVisibility(View.VISIBLE);
                } else {
                    textToSpeech.speak(dbManager.getPartsOfSpeech(SharedClass.tense_id).get(0).getDef_eng(), TextToSpeech.QUEUE_FLUSH, null);

                    textToSpeech.stop();

                    defbtnoff.setVisibility(View.VISIBLE);
                    defbtnon.setVisibility(View.GONE);
                }
            }
        });

        methodbtnon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!textToSpeech.isSpeaking()) {
                    textToSpeech.speak(dbManager.getPartsOfSpeech(SharedClass.tense_id).get(0).getEg_eng(), TextToSpeech.QUEUE_FLUSH, null);
                    methodbtnoff.setVisibility(View.GONE);
                    methodbtnon.setVisibility(View.VISIBLE);
                } else {
                    textToSpeech.speak(dbManager.getPartsOfSpeech(SharedClass.tense_id).get(0).getEg_eng(), TextToSpeech.QUEUE_FLUSH, null);

                    textToSpeech.stop();

                    methodbtnoff.setVisibility(View.VISIBLE);
                    methodbtnon.setVisibility(View.GONE);
                }
            }
        });
        methodbtnoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!textToSpeech.isSpeaking()) {
                    textToSpeech.speak(dbManager.getPartsOfSpeech(SharedClass.tense_id).get(0).getEg_eng(), TextToSpeech.QUEUE_FLUSH, null);
                    methodbtnoff.setVisibility(View.GONE);
                    methodbtnon.setVisibility(View.VISIBLE);
                } else {
                    textToSpeech.speak(dbManager.getPartsOfSpeech(SharedClass.tense_id).get(0).getEg_eng(), TextToSpeech.QUEUE_FLUSH, null);

                    textToSpeech.stop();

                    methodbtnoff.setVisibility(View.VISIBLE);
                    methodbtnon.setVisibility(View.GONE);
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

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

    private void initExample() {
        ExampleModel exampleModel = new ExampleModel("Example", "Urdu Meaning");
        exampleData.add(exampleModel);
        ExampleModel exampleModel1 = new ExampleModel("Example", "Urdu Meaning");
        exampleData.add(exampleModel1);
        ExampleModel exampleModel2 = new ExampleModel("Example", "Urdu Meaning");
        exampleData.add(exampleModel2);
        ExampleModel exampleModel3 = new ExampleModel("Example", "Urdu Meaning");
        exampleData.add(exampleModel3);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public class ExampleAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return exampleData.size();
        }

        @Override
        public Object getItem(int i) {
            return exampleData.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View rowView = View.inflate(PartOfSpeech_Detail.this, R.layout.example_cell, null);

            TextView exampleTxt = rowView.findViewById(R.id.exampleTxt);
            TextView meaningTxt = rowView.findViewById(R.id.meaningTxt);

            exampleTxt.setText(exampleData.get(i).getExample());
            meaningTxt.setText(exampleData.get(i).getUrduMeaning());

            return rowView;
        }
    }

    public void favbtn(View view) {
        NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
        adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(PartOfSpeech_Detail.this, new LxiadmobCloseEvent() {
            @Override
            public void setFailedlxi() {
                startActivity(new Intent(getApplicationContext(), FavouriteMessages.class));

            }

            @Override
            public void setSuccesslxi() {
            }

            @Override
            public void setAdmobCloseEventlxi() {
                startActivity(new Intent(getApplicationContext(), FavouriteMessages.class));

            }
        }, new LxiMyPreferenceManager(PartOfSpeech_Detail.this).fIdlxi());


    }

    @Override
    protected void onResume() {
        super.onResume();
        NaxleApps.setAdShowDialogslxi(PartOfSpeech_Detail.this);
    }
}
