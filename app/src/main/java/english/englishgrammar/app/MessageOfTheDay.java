package english.englishgrammar.app;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdView;

import java.io.IOException;
import java.util.Locale;

import english.englishgrammar.app.Manager.Quotes_DBManager;
import english.englishgrammar.app.jaky_lxi.interfaces_lxi.LxiadmobCloseEvent;
import english.englishgrammar.app.jaky_lxi.mains_lxi.NaxleApps;
import english.englishgrammar.app.jaky_lxi.utils_lxi.LxiMyPreferenceManager;

public class MessageOfTheDay extends AppCompatActivity {

    private AdView mAdView;
    Quotes_DBManager dbManager;
    ImageButton backBtn;
    ImageView defbtnon, defbtnoff, methodbtnon, methodbtnoff;
    RecyclerView messageRV;
    ExampleAdapter exampleAdapter;
    private TextToSpeech textToSpeech;
    int fav_check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_of_day);


        LxiMyPreferenceManager myPreferenceManagerdude = new LxiMyPreferenceManager(MessageOfTheDay.this);
        NaxleApps adsGlobalClassEveryTimedude = new NaxleApps();


        adsGlobalClassEveryTimedude.showBannerAdlxi(MessageOfTheDay.this, new LxiadmobCloseEvent() {
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


        dbManager = new Quotes_DBManager(this);
        dbManager.open();
        try {
            dbManager.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
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

        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        backBtn = findViewById(R.id.backBtn);
        messageRV = findViewById(R.id.messageRV);
        messageRV.setLayoutManager(new LinearLayoutManager(this));
        exampleAdapter = new ExampleAdapter(MessageOfTheDay.this);
        messageRV.setAdapter(exampleAdapter);
        exampleAdapter.notifyDataSetChanged();
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        exampleAdapter.notifyDataSetChanged();

    }

    public void favbtn(View view) {
        NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
        adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(MessageOfTheDay.this, new LxiadmobCloseEvent() {
            @Override
            public void setFailedlxi() {
                startActivity(new Intent(MessageOfTheDay.this, FavouriteMessages.class));

            }

            @Override
            public void setSuccesslxi() {
            }

            @Override
            public void setAdmobCloseEventlxi() {
                startActivity(new Intent(MessageOfTheDay.this, FavouriteMessages.class));


            }
        }, new LxiMyPreferenceManager(MessageOfTheDay.this).fIdlxi());
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.MyAdapter> {
        ImageView defbtnon, defbtnoff, methodbtnon, methodbtnoff;

        Context context;
        private LayoutInflater inflater;

        public ExampleAdapter(Context context) {
            this.context = context;
        }

        @NonNull
        @Override
        public MyAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            if (inflater == null) {
                inflater = LayoutInflater.from(viewGroup.getContext());
            }
            View view = inflater.inflate(R.layout.message_custom_cell, viewGroup, false);
            return new MyAdapter(view);
        }

        int i;

        @Override
        public void onBindViewHolder(@NonNull MyAdapter myAdapter, int i) {
            myAdapter.msgTxt.setText(dbManager.getQuote().get(i).getEng_quote());
            myAdapter.urduMeaningTxt.setText(dbManager.getQuote().get(i).getAuthor());
            if (dbManager.getQuote().get(i).getFav().equals("1")) {
                myAdapter.favouriteBtn.setBackgroundResource(R.drawable.favorite_heart_button);
            } else {
                myAdapter.favouriteBtn.setBackgroundResource(R.drawable.favorite);
            }
        }

        @Override
        public int getItemCount() {
            return dbManager.getQuote().size();
        }

        public class MyAdapter extends RecyclerView.ViewHolder {
            TextView dateTxt, msgTxt, urduMeaningTxt;
            ImageButton favouriteBtn, shareBtn, copy;
            LinearLayout favLayout;
            ImageView defbtnon, defbtnoff, methodbtnon, methodbtnoff;

            public MyAdapter(@NonNull View itemView) {
                super(itemView);
                dateTxt = itemView.findViewById(R.id.dateTxt);
                msgTxt = itemView.findViewById(R.id.msgTxt);
                urduMeaningTxt = itemView.findViewById(R.id.urduMeaningTxt);
                favouriteBtn = itemView.findViewById(R.id.favouriteBtn);
                favLayout = itemView.findViewById(R.id.favLayout);


                defbtnon = itemView.findViewById(R.id.defbtnon);
                defbtnoff = itemView.findViewById(R.id.defbtnoff);

                methodbtnon = itemView.findViewById(R.id.methodbtnon);
                methodbtnoff = itemView.findViewById(R.id.methodbtnoff);

                copy = itemView.findViewById(R.id.copy);
                copy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("vv",


                                dbManager.getQuote().get(getAdapterPosition()).getEng_quote());
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(MessageOfTheDay.this, "Text copied !", Toast.LENGTH_SHORT).show();
                    }
                });
                shareBtn = itemView.findViewById(R.id.shareBtn);

                shareBtn.setOnClickListener(new SingleClickListener() {

                    @Override
                    public void performClick(View v) {
                        try {
                            Intent shareIntent = new Intent(Intent.ACTION_SEND);
                            shareIntent.setType("text/plain");
                            String shareMessage = dbManager.getQuote().get(getAdapterPosition()).getEng_quote() + "\n\n" + dbManager.getQuote().get(getAdapterPosition()).getAuthor();
                            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                            startActivity(Intent.createChooser(shareIntent, "choose one"));
                        } catch (Exception e) {
                        }
                    }
                });

                favouriteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (fav_check == 0) {
                            favouriteBtn.setBackgroundResource(R.drawable.favorite_heart_button);
                            fav_check = 1;
                            dbManager.updateMessageFav(dbManager.getQuote().get(getAdapterPosition()).getId(), "1");
                        } else {
                            favouriteBtn.setBackgroundResource(R.drawable.favorite);
                            fav_check = 0;
                            dbManager.updateMessageFav(dbManager.getQuote().get(getAdapterPosition()).getId(), "0");
                        }
                    }
                });


                defbtnon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (!textToSpeech.isSpeaking()) {
                            textToSpeech.speak(dbManager.getQuote().get(getAdapterPosition()).getEng_quote(), TextToSpeech.QUEUE_FLUSH, null);
                            defbtnoff.setVisibility(View.GONE);
                            defbtnon.setVisibility(View.VISIBLE);
                        } else {
                            textToSpeech.speak(dbManager.getQuote().get(getAdapterPosition()).getEng_quote(), TextToSpeech.QUEUE_FLUSH, null);

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
                            textToSpeech.speak(dbManager.getQuote().get(getAdapterPosition()).getEng_quote(), TextToSpeech.QUEUE_FLUSH, null);
                            defbtnoff.setVisibility(View.GONE);
                            defbtnon.setVisibility(View.VISIBLE);
                        } else {
                            textToSpeech.speak(dbManager.getQuote().get(getAdapterPosition()).getEng_quote(), TextToSpeech.QUEUE_FLUSH, null);

                            textToSpeech.stop();

                            defbtnoff.setVisibility(View.VISIBLE);
                            defbtnon.setVisibility(View.GONE);
                        }
                    }
                });
            }
        }
    }

//    public void favbtn(View view){
//        startActivity(new Intent(getApplicationContext(), FavouriteMessages.class));
//    }

}
