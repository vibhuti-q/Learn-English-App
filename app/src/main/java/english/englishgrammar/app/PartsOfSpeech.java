package english.englishgrammar.app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import english.englishgrammar.app.Manager.Conversation_DBManager;
import english.englishgrammar.app.SharedData.SharedClass;
import english.englishgrammar.app.jaky_lxi.interfaces_lxi.LxiadmobCloseEvent;
import english.englishgrammar.app.jaky_lxi.mains_lxi.NaxleApps;
import english.englishgrammar.app.jaky_lxi.utils_lxi.LxiMyPreferenceManager;

import java.io.IOException;

public class PartsOfSpeech extends AppCompatActivity {

    private AdView mAdView;
    ImageButton backBtn;       ImageView defbtnon, defbtnoff, methodbtnon, methodbtnoff;
    ListView mainOptionsLV;
    Conversation_DBManager dbManager;
    String[] shorttxt = {"ET", "EG"};
    MainOptionsAdapter mainOptionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parts_speech);



        //Banner AD


        LxiMyPreferenceManager myPreferenceManagerdude = new LxiMyPreferenceManager(PartsOfSpeech.this);
        NaxleApps adsGlobalClassEveryTimedude = new NaxleApps();


        adsGlobalClassEveryTimedude.showBannerAdlxi(PartsOfSpeech.this, new LxiadmobCloseEvent() {
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

        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        backBtn = findViewById(R.id.backBtn);
        mainOptionsLV = findViewById(R.id.mainOptionsLV);
        mainOptionsAdapter = new MainOptionsAdapter();
        mainOptionsLV.setAdapter(mainOptionsAdapter);
        mainOptionsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
                adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(PartsOfSpeech.this, new LxiadmobCloseEvent() {
                    @Override
                    public void setFailedlxi() {
                        SharedClass.tense_id = dbManager.getPartsOfSpeech().get(i).getId();
                        startActivity(new Intent(PartsOfSpeech.this,PartOfSpeech_Detail.class));

                    }

                    @Override
                    public void setSuccesslxi() {
                    }

                    @Override
                    public void setAdmobCloseEventlxi() {
                        SharedClass.tense_id = dbManager.getPartsOfSpeech().get(i).getId();
                        startActivity(new Intent(PartsOfSpeech.this,PartOfSpeech_Detail.class));

                    }
                }, new LxiMyPreferenceManager(PartsOfSpeech.this).fIdlxi());

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

    public class MainOptionsAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return dbManager.getPartsOfSpeech().size();
        }

        @Override
        public Object getItem(int i) {
            return dbManager.getPartsOfSpeech().get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View rowView = View.inflate(PartsOfSpeech.this, R.layout.pos_cell, null);

            LinearLayout cell = rowView.findViewById(R.id.cell);
            CardView shortTxtCard = rowView.findViewById(R.id.shortTxtCard);
            RelativeLayout mainCard = rowView.findViewById(R.id.mainCard);
            TextView shortTxt = rowView.findViewById(R.id.shortTxt);
            TextView optionname = rowView.findViewById(R.id.optionname);
            TextView optionnameurdu = rowView.findViewById(R.id.optionnameurdu);
            optionname.setText(dbManager.getPartsOfSpeech().get(i).getName_eng());
            optionnameurdu.setText(dbManager.getPartsOfSpeech().get(i).getName_urdu());
            optionname.setTextColor(PartsOfSpeech.this.getResources().getColor(R.color.white));
            optionnameurdu.setTextColor(PartsOfSpeech.this.getResources().getColor(R.color.white));
            shortTxt.setText(shorttxt[0]);
            shortTxt.setTextColor(PartsOfSpeech.this.getResources().getColor(R.color.white));
            shortTxtCard.setCardBackgroundColor(PartsOfSpeech.this.getResources().getColor(R.color.maincolor));
//            mainCard.setCardBackgroundColor(PartsOfSpeech.this.getResources().getColor(R.color.white));
//            cell.setBackgroundColor(PartsOfSpeech.this.getResources().getColor(R.color.white));

            return rowView;
        }
    }

    public void favbtn(View view) {
        NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
        adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(PartsOfSpeech.this, new LxiadmobCloseEvent() {
            @Override
            public void setFailedlxi() {
                startActivity(new Intent(PartsOfSpeech.this,FavouriteMessages.class));

            }

            @Override
            public void setSuccesslxi() {
            }

            @Override
            public void setAdmobCloseEventlxi() {
                startActivity(new Intent(PartsOfSpeech.this,FavouriteMessages.class));

            }
        }, new LxiMyPreferenceManager(PartsOfSpeech.this).fIdlxi());


    }


    @Override
    protected void onResume() {
        super.onResume();
        NaxleApps.setAdShowDialogslxi(PartsOfSpeech.this);
    }

}
