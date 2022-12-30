package english.englishgrammar.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import english.englishgrammar.app.jaky_lxi.interfaces_lxi.LxiadmobCloseEvent;
import english.englishgrammar.app.jaky_lxi.mains_lxi.NaxleApps;
import english.englishgrammar.app.jaky_lxi.utils_lxi.LxiMyPreferenceManager;

public class VocabularyWords extends AppCompatActivity {
    private AdView mAdView;
    ImageButton backBtn;       ImageView defbtnon, defbtnoff, methodbtnon, methodbtnoff;
    RecyclerView phrasesRV;
    MainOptionsAdapter mainOptionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_words);

        //Banner AD


        LxiMyPreferenceManager myPreferenceManagerdude = new LxiMyPreferenceManager(VocabularyWords.this);
        NaxleApps adsGlobalClassEveryTimedude = new NaxleApps();

        adsGlobalClassEveryTimedude.showBannerAdlxi(VocabularyWords.this, new LxiadmobCloseEvent() {
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
        phrasesRV = findViewById(R.id.phrasesRV);
        phrasesRV.setLayoutManager(new LinearLayoutManager(this));
        mainOptionsAdapter = new MainOptionsAdapter(VocabularyWords.this);
        phrasesRV.setAdapter(mainOptionsAdapter);

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

    public class MainOptionsAdapter extends RecyclerView.Adapter<MainOptionsAdapter.MyAdapter> {

        Context context;
        private LayoutInflater inflater;

        public MainOptionsAdapter(Context context) {
            this.context = context;
        }

        @NonNull
        @Override
        public MyAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            if (inflater == null) {
                inflater = LayoutInflater.from(viewGroup.getContext());
            }
            View view = inflater.inflate(R.layout.phrases_cell, viewGroup, false);
            return new MyAdapter(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter myAdapter, int i) {

        }

        @Override
        public int getItemCount() {
            return 5;
        }

        public class MyAdapter extends RecyclerView.ViewHolder {
            public MyAdapter(@NonNull View itemView) {
                super(itemView);
            }
        }
    }


    public void favbtn(View view){
        NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
        adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(VocabularyWords.this, new LxiadmobCloseEvent() {
            @Override
            public void setFailedlxi() {
                startActivity(new Intent(VocabularyWords.this,FavouriteMessages.class));

            }

            @Override
            public void setSuccesslxi() {
            }

            @Override
            public void setAdmobCloseEventlxi() {
                startActivity(new Intent(VocabularyWords.this,FavouriteMessages.class));

            }
        }, new LxiMyPreferenceManager(VocabularyWords.this).fIdlxi());
    }


}
