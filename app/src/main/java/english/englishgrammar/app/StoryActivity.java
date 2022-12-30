package english.englishgrammar.app;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import english.englishgrammar.app.SharedData.SharedClass;
import english.englishgrammar.app.jaky_lxi.interfaces_lxi.LxiadmobCloseEvent;
import english.englishgrammar.app.jaky_lxi.mains_lxi.NaxleApps;
import english.englishgrammar.app.jaky_lxi.utils_lxi.LxiMyPreferenceManager;

public class StoryActivity extends AppCompatActivity {
    private AdView mAdView;
    Typeface alvi_Nastaleeq_Lahori;
    ImageButton backBtn;
    ImageView defbtnon, defbtnoff, methodbtnon, methodbtnoff;
    TextView txtDesc, txtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        txtDesc = findViewById(R.id.txtDesc);
        txtTitle = findViewById(R.id.txtTitle);

        txtTitle.setText(SharedClass.storyTitle);
        txtDesc.setText(SharedClass.storyDesc);


        //Banner AD
        LxiMyPreferenceManager myPreferenceManagerdude = new LxiMyPreferenceManager(StoryActivity.this);
        NaxleApps adsGlobalClassEveryTimedude = new NaxleApps();

        adsGlobalClassEveryTimedude.showBannerAdlxi(StoryActivity.this, new LxiadmobCloseEvent() {
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
        alvi_Nastaleeq_Lahori = Typeface.createFromAsset(this.getAssets(), "fonts/alvi_Nastaleeq_Lahori.ttf");
        backBtn = findViewById(R.id.backBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }



    public void favbtn(View view){
        NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
        adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(StoryActivity.this, new LxiadmobCloseEvent() {
            @Override
            public void setFailedlxi() {
                startActivity(new Intent(StoryActivity.this,FavouriteMessages.class));

            }

            @Override
            public void setSuccesslxi() {
            }

            @Override
            public void setAdmobCloseEventlxi() {
                startActivity(new Intent(StoryActivity.this,FavouriteMessages.class));

            }
        }, new LxiMyPreferenceManager(StoryActivity.this).fIdlxi());
    }
    @Override
    public void onBackPressed() {
//        onBackPressed();
        finish();
    }

//    public void favbtn(View view) {
//        startActivity(new Intent(getApplicationContext(), FavouriteMessages.class));
//    }

}
