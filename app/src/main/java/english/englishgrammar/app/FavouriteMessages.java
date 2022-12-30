package english.englishgrammar.app;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import english.englishgrammar.app.Dic.ui.main.Dic_MainActivity;
import english.englishgrammar.app.Manager.Quotes_DBManager;
import english.englishgrammar.app.Models.QuotesModel;
import english.englishgrammar.app.jaky_lxi.interfaces_lxi.LxiadmobCloseEvent;
import english.englishgrammar.app.jaky_lxi.mains_lxi.NaxleApps;
import english.englishgrammar.app.jaky_lxi.utils_lxi.LxiMyPreferenceManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FavouriteMessages extends AppCompatActivity {
    List<QuotesModel> quotesData = new ArrayList<>();
    Quotes_DBManager dbManager;
    ImageButton backBtn;
    RecyclerView messageRV;
    ExampleAdapter exampleAdapter;
    private TextToSpeech textToSpeech;
    TextView titletxt, noRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_of_day1);

        LxiMyPreferenceManager myPreferenceManagerdude = new LxiMyPreferenceManager(FavouriteMessages.this);
        NaxleApps adsGlobalClassEveryTimedude = new NaxleApps();

        adsGlobalClassEveryTimedude.showBannerAdlxi(FavouriteMessages.this, new LxiadmobCloseEvent() {
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
        quotesData = dbManager.getFavQuote();

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
        titletxt = findViewById(R.id.titletxt);
        noRecord = findViewById(R.id.noRecord);
        backBtn = findViewById(R.id.backBtn);
        messageRV = findViewById(R.id.messageRV);
        messageRV.setLayoutManager(new LinearLayoutManager(this));
        titletxt.setText("Favourite Messsages");
        exampleAdapter = new ExampleAdapter(FavouriteMessages.this);
        messageRV.setAdapter(exampleAdapter);

        if (dbManager.getFavQuote().size() == 0) {
            noRecord.setVisibility(View.VISIBLE);
            messageRV.setVisibility(View.GONE);
        }

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
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
            myAdapter.msgTxt.setText(quotesData.get(i).getEng_quote());
            myAdapter.urduMeaningTxt.setText(quotesData.get(i).getAuthor());
            myAdapter.favouriteBtn.setBackgroundResource(R.drawable.favorite_heart_button);
        }

        @Override
        public int getItemCount() {
            return quotesData.size();
        }

        public class MyAdapter extends RecyclerView.ViewHolder {
            TextView dateTxt, msgTxt, urduMeaningTxt;
            ImageButton favouriteBtn, shareBtn, copy;
            ImageView defbtnon, defbtnoff, methodbtnon, methodbtnoff;

            public MyAdapter(@NonNull View itemView) {
                super(itemView);

                dateTxt = itemView.findViewById(R.id.dateTxt);
                msgTxt = itemView.findViewById(R.id.msgTxt);
                urduMeaningTxt = itemView.findViewById(R.id.urduMeaningTxt);
                favouriteBtn = itemView.findViewById(R.id.favouriteBtn);
                copy = itemView.findViewById(R.id.copy);

                defbtnon = itemView.findViewById(R.id.defbtnon);
                defbtnoff = itemView.findViewById(R.id.defbtnoff);
                methodbtnon = itemView.findViewById(R.id.methodbtnon);
                methodbtnoff = itemView.findViewById(R.id.methodbtnoff);

                shareBtn = itemView.findViewById(R.id.shareBtn);

                favouriteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        favouriteBtn.setBackgroundResource(R.drawable.favorite);
                        dbManager.updateMessageFav(quotesData.get(getAdapterPosition()).getId(), "0");
                        quotesData = dbManager.getFavQuote();
                        notifyDataSetChanged();
                        if (dbManager.getFavQuote().size() == 0) {
                            noRecord.setVisibility(View.VISIBLE);
                            messageRV.setVisibility(View.GONE);
                        }
                    }
                });
                copy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("vv", quotesData.get(getAdapterPosition()).getEng_quote());
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(FavouriteMessages.this, "Text copied !", Toast.LENGTH_SHORT).show();
                    }
                });
                shareBtn.setOnClickListener(new SingleClickListener() {
                    @Override
                    public void performClick(View v) {
                        try {
                            Intent shareIntent = new Intent(Intent.ACTION_SEND);
                            shareIntent.setType("text/plain");
                            String shareMessage = quotesData.get(getAdapterPosition()).getEng_quote() + "\n\n" + dbManager.getQuote().get(getAdapterPosition()).getAuthor();
                            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                            startActivity(Intent.createChooser(shareIntent, "choose one"));
                        } catch (Exception e) {
                        }
                    }
                });

                defbtnon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (!textToSpeech.isSpeaking()) {
                            textToSpeech.speak(quotesData.get(getAdapterPosition()).getEng_quote(), TextToSpeech.QUEUE_FLUSH, null);
                            defbtnoff.setVisibility(View.GONE);
                            defbtnon.setVisibility(View.VISIBLE);
                        } else {
                            textToSpeech.speak(quotesData.get(getAdapterPosition()).getEng_quote(), TextToSpeech.QUEUE_FLUSH, null);
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
                            textToSpeech.speak(quotesData.get(getAdapterPosition()).getEng_quote(), TextToSpeech.QUEUE_FLUSH, null);
                            defbtnoff.setVisibility(View.GONE);
                            defbtnon.setVisibility(View.VISIBLE);
                        } else {
                            textToSpeech.speak(quotesData.get(getAdapterPosition()).getEng_quote(), TextToSpeech.QUEUE_FLUSH, null);
                            textToSpeech.stop();
                            defbtnoff.setVisibility(View.VISIBLE);
                            defbtnon.setVisibility(View.GONE);
                        }
                    }
                });


                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(FavouriteMessages.this, quotesData.get(getAdapterPosition()).getFav() + "", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }


    public void favbtn(View view) {
        NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
        adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(FavouriteMessages.this, new LxiadmobCloseEvent() {
            @Override
            public void setFailedlxi() {
                startActivity(new Intent(FavouriteMessages.this, FavouriteMessages.class));

            }

            @Override
            public void setSuccesslxi() {
            }

            @Override
            public void setAdmobCloseEventlxi() {
                startActivity(new Intent(FavouriteMessages.this, FavouriteMessages.class));

            }
        }, new LxiMyPreferenceManager(FavouriteMessages.this).fIdlxi());
    }
    @Override
    protected void onResume() {
        super.onResume();
        NaxleApps.setAdShowDialogslxi(FavouriteMessages.this);
    }

}
