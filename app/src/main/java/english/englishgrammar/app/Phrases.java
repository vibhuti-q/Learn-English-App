package english.englishgrammar.app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
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
import english.englishgrammar.app.databinding.PhrasesCellBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import english.englishgrammar.app.Manager.Conversation_DBManager;
import english.englishgrammar.app.jaky_lxi.interfaces_lxi.LxiadmobCloseEvent;
import english.englishgrammar.app.jaky_lxi.mains_lxi.NaxleApps;
import english.englishgrammar.app.jaky_lxi.utils_lxi.LxiMyPreferenceManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Phrases extends AppCompatActivity {

    private AdView mAdView;
    List<PhrasesViewModel> phrasesData = new ArrayList<>();
    Typeface alvi_Nastaleeq_Lahori;
    Conversation_DBManager dbManager;
    ImageButton backBtn;       ImageView defbtnon, defbtnoff, methodbtnon, methodbtnoff;
    RecyclerView phrasesRV;
    MainOptionsAdapter mainOptionsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);

        //Banner AD
        LxiMyPreferenceManager myPreferenceManagerdude = new LxiMyPreferenceManager(Phrases.this);
        NaxleApps adsGlobalClassEveryTimedude = new NaxleApps();

        adsGlobalClassEveryTimedude.showBannerAdlxi(Phrases.this, new LxiadmobCloseEvent() {
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

        dbManager = new Conversation_DBManager(this);
        dbManager.open();
        try {
            dbManager.copyDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        phrasesData = dbManager.getPhrases();

        alvi_Nastaleeq_Lahori = Typeface.createFromAsset(this.getAssets(), "fonts/alvi_Nastaleeq_Lahori.ttf");
        backBtn = findViewById(R.id.backBtn);
        phrasesRV = findViewById(R.id.phrasesRV);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        phrasesRV.setLayoutManager(linearLayoutManager);
        mainOptionsAdapter = new MainOptionsAdapter(Phrases.this, phrasesData);
        phrasesRV.setAdapter(mainOptionsAdapter);
        phrasesRV.setNestedScrollingEnabled(false);

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
        List<PhrasesViewModel> phrasesData;
        private LayoutInflater inflater;

        public MainOptionsAdapter(Context context, List<PhrasesViewModel> phrasesData) {
            this.context = context;
            this.phrasesData = phrasesData;
        }

        @NonNull
        @Override
        public MyAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            if (inflater == null) {
                inflater = LayoutInflater.from(viewGroup.getContext());
            }
            PhrasesCellBinding phrasesCellBinding = PhrasesCellBinding.inflate(inflater, viewGroup, false);
            return new MyAdapter(phrasesCellBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter myAdapter, int i) {
            myAdapter.bind(phrasesData.get(i));
        }

        @Override
        public int getItemCount() {
            return phrasesData.size();
        }

        public class MyAdapter extends RecyclerView.ViewHolder {
            private PhrasesCellBinding phrasesCellBinding;

            public MyAdapter(PhrasesCellBinding phrasesCellBinding) {
                super(phrasesCellBinding.getRoot());
                this.phrasesCellBinding = phrasesCellBinding;
            }

            public void bind(PhrasesViewModel phrasesViewModel) {
                this.phrasesCellBinding.setPhrasesViewModel(phrasesViewModel);
            }

            public PhrasesCellBinding getPhrasesCellBinding() {
                return phrasesCellBinding;
            }
        }
    }


    public void favbtn(View view){
        NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
        adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(Phrases.this, new LxiadmobCloseEvent() {
            @Override
            public void setFailedlxi() {
                startActivity(new Intent(Phrases.this,FavouriteMessages.class));

            }

            @Override
            public void setSuccesslxi() {
            }

            @Override
            public void setAdmobCloseEventlxi() {
                startActivity(new Intent(Phrases.this,FavouriteMessages.class));

            }
        }, new LxiMyPreferenceManager(Phrases.this).fIdlxi());
    }

}
