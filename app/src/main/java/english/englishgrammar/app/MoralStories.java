package english.englishgrammar.app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import english.englishgrammar.app.Customization.AutoResizeTextView;
import english.englishgrammar.app.Manager.Stories_DBManager;
import english.englishgrammar.app.Models.StoriesModel;
import english.englishgrammar.app.SharedData.SharedClass;
import english.englishgrammar.app.jaky_lxi.interfaces_lxi.LxiadmobCloseEvent;
import english.englishgrammar.app.jaky_lxi.mains_lxi.NaxleApps;
import english.englishgrammar.app.jaky_lxi.utils_lxi.LxiMyPreferenceManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoralStories extends AppCompatActivity {

    List<StoriesModel> phrasesData = new ArrayList<>();
    Typeface alvi_Nastaleeq_Lahori;
    Stories_DBManager dbManager;
    ImageButton backBtn;
    RecyclerView phrasesRV;
    StoriesAdapter mainOptionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moral_stories);

        LxiMyPreferenceManager myPreferenceManagerdude = new LxiMyPreferenceManager(MoralStories.this);
        NaxleApps adsGlobalClassEveryTimedude = new NaxleApps();

        adsGlobalClassEveryTimedude.showBannerAdlxi(MoralStories.this, new LxiadmobCloseEvent() {
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

        dbManager = new Stories_DBManager(this);
        dbManager.open();
        try {
            dbManager.copyDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        phrasesData = dbManager.getAllStories();
        alvi_Nastaleeq_Lahori = Typeface.createFromAsset(this.getAssets(), "fonts/alvi_Nastaleeq_Lahori.ttf");
        backBtn = findViewById(R.id.backBtn);
        phrasesRV = findViewById(R.id.phrasesRV);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        phrasesRV.setLayoutManager(linearLayoutManager);
        mainOptionsAdapter = new StoriesAdapter(MoralStories.this, phrasesData);
        phrasesRV.setAdapter(mainOptionsAdapter);
        phrasesRV.setNestedScrollingEnabled(false);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    public void favbtn(View view) {
        NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
        adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(MoralStories.this, new LxiadmobCloseEvent() {
            @Override
            public void setFailedlxi() {
                startActivity(new Intent(MoralStories.this, FavouriteMessages.class));
            }

            @Override
            public void setSuccesslxi() {
            }

            @Override
            public void setAdmobCloseEventlxi() {
                startActivity(new Intent(MoralStories.this, FavouriteMessages.class));

            }
        }, new LxiMyPreferenceManager(MoralStories.this).fIdlxi());
    }


    public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.MyAdapter> {

        Context context;
        List<StoriesModel> phrasesData;
        private LayoutInflater inflater;

        public StoriesAdapter(Context context, List<StoriesModel> phrasesData) {
            this.context = context;
            this.phrasesData = phrasesData;
        }

        @NonNull
        @Override
        public MyAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (inflater == null) {
                inflater = LayoutInflater.from(parent.getContext());
            }
            View view = inflater.inflate(R.layout.stories_cell, parent, false);
            return new MyAdapter(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter holder, int position) {
            holder.optionname.setText(phrasesData.get(position).getTitle());
        }

        @Override
        public int getItemCount() {
            return phrasesData.size();
        }

        public class MyAdapter extends RecyclerView.ViewHolder {
            AutoResizeTextView optionname;

            public MyAdapter(@NonNull View itemView) {
                super(itemView);
                optionname = itemView.findViewById(R.id.optionname);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
                        adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(MoralStories.this, new LxiadmobCloseEvent() {
                            @Override
                            public void setFailedlxi() {
                                SharedClass.storyTitle = phrasesData.get(getAdapterPosition()).getTitle();
                                SharedClass.storyDesc = phrasesData.get(getAdapterPosition()).getStory();
                                context.startActivity(new Intent(context, StoryActivity.class));
                            }

                            @Override
                            public void setSuccesslxi() {
                            }

                            @Override
                            public void setAdmobCloseEventlxi() {
                                SharedClass.storyTitle = phrasesData.get(getAdapterPosition()).getTitle();
                                SharedClass.storyDesc = phrasesData.get(getAdapterPosition()).getStory();
                                context.startActivity(new Intent(context, StoryActivity.class));
                            }
                        }, new LxiMyPreferenceManager(MoralStories.this).fIdlxi());
                    }
                });
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

//    public void favbtn(View view){
//        startActivity(new Intent(getApplicationContext(), FavouriteMessages.class));
//    }

    @Override
    protected void onResume() {
        super.onResume();
        NaxleApps.setAdShowDialogslxi(MoralStories.this);
    }
}
