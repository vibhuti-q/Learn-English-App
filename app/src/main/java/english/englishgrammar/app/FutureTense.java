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
import english.englishgrammar.app.jaky_lxi.interfaces_lxi.LxiNativeAdListener;
import english.englishgrammar.app.jaky_lxi.interfaces_lxi.LxiadmobCloseEvent;
import english.englishgrammar.app.jaky_lxi.mains_lxi.NaxleApps;
import english.englishgrammar.app.jaky_lxi.utils_lxi.LxiMyPreferenceManager;

import java.io.IOException;

public class FutureTense extends AppCompatActivity {

    private AdView mAdView;
    Conversation_DBManager dbManager;
    ImageButton backBtn;       ImageView defbtnon, defbtnoff, methodbtnon, methodbtnoff;
    ListView mainOptionsLV;
    String[] shorttxt = {"FT", "FT", "FT", "FT"};
    String[] mainOpionsUrdu = {"क्रिया भविष्य", "क्रिया भविष्य सतत", "वर्ब फ्यूचर परफेक्ट", "वर्ब फ्यूचर परफेक्ट कंटीन्यूअस"};
    MainOptionsAdapter mainOptionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future_tense);

        ///native ad
        LxiMyPreferenceManager myPreferenceManagerpics = new LxiMyPreferenceManager(this);
        NaxleApps adsGlobalClassEveryTimepics = new NaxleApps();
        adsGlobalClassEveryTimepics.showAndLoadGoogleNativelxi(FutureTense.this,
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

        dbManager = new Conversation_DBManager(this);
        dbManager.open();
        try {
            dbManager.copyDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        backBtn = findViewById(R.id.backBtn);
        mainOptionsLV = findViewById(R.id.mainOptionsLV);
        mainOptionsAdapter = new MainOptionsAdapter();
        mainOptionsLV.setAdapter(mainOptionsAdapter);
        mainOptionsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
                    adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(FutureTense.this, new LxiadmobCloseEvent() {
                        @Override
                        public void setFailedlxi() {
                            SharedClass.tense_id = dbManager.getTenses().get(8).getId();
                            startActivity(new Intent(FutureTense.this,FutureIndefinateTense.class));

                        }

                        @Override
                        public void setSuccesslxi() {
                        }

                        @Override
                        public void setAdmobCloseEventlxi() {
                            SharedClass.tense_id = dbManager.getTenses().get(8).getId();
                            startActivity(new Intent(FutureTense.this,FutureIndefinateTense.class));

                        }
                    }, new LxiMyPreferenceManager(FutureTense.this).fIdlxi());


                }
                if (i == 1) {
                    NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
                    adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(FutureTense.this, new LxiadmobCloseEvent() {
                        @Override
                        public void setFailedlxi() {
                            SharedClass.tense_id = dbManager.getTenses().get(9).getId();
                            startActivity(new Intent(FutureTense.this,FutureContinuousTense.class));
                        }

                        @Override
                        public void setSuccesslxi() {
                        }

                        @Override
                        public void setAdmobCloseEventlxi() {
                            SharedClass.tense_id = dbManager.getTenses().get(9).getId();
                            startActivity(new Intent(FutureTense.this,FutureContinuousTense.class));
                        }
                    }, new LxiMyPreferenceManager(FutureTense.this).fIdlxi());


                }
                if (i == 2) {
                    NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
                    adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(FutureTense.this, new LxiadmobCloseEvent() {
                        @Override
                        public void setFailedlxi() {
                            SharedClass.tense_id = dbManager.getTenses().get(10).getId();
                            startActivity(new Intent(FutureTense.this,FuturePerfectTense.class));
                        }

                        @Override
                        public void setSuccesslxi() {
                        }

                        @Override
                        public void setAdmobCloseEventlxi() {
                            SharedClass.tense_id = dbManager.getTenses().get(10).getId();
                            startActivity(new Intent(FutureTense.this,FuturePerfectTense.class));
                        }
                    }, new LxiMyPreferenceManager(FutureTense.this).fIdlxi());


                }
                if (i == 3) {
                    NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
                    adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(FutureTense.this, new LxiadmobCloseEvent() {
                        @Override
                        public void setFailedlxi() {
                            SharedClass.tense_id = dbManager.getTenses().get(11).getId();
                            startActivity(new Intent(FutureTense.this,FuturePerfectContinuousTense.class));
                        }

                        @Override
                        public void setSuccesslxi() {
                        }

                        @Override
                        public void setAdmobCloseEventlxi() {
                            SharedClass.tense_id = dbManager.getTenses().get(11).getId();
                            startActivity(new Intent(FutureTense.this,FuturePerfectContinuousTense.class));
                        }
                    }, new LxiMyPreferenceManager(FutureTense.this).fIdlxi());


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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public class MainOptionsAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return dbManager.getTensesNames(SharedClass.mainOption).size();
        }

        @Override
        public Object getItem(int i) {
            return dbManager.getTensesNames(SharedClass.mainOption).get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View rowView = View.inflate(FutureTense.this, R.layout.tenses_cell, null);

            LinearLayout cell = rowView.findViewById(R.id.cell);
            CardView shortTxtCard = rowView.findViewById(R.id.shortTxtCard);
            RelativeLayout mainCard = rowView.findViewById(R.id.mainCard);
            TextView shortTxt = rowView.findViewById(R.id.shortTxt);
            TextView optionname = rowView.findViewById(R.id.optionname);
            TextView opurdu = rowView.findViewById(R.id.opurdu);
            opurdu.setText(mainOpionsUrdu[i]);
            optionname.setText(dbManager.getTensesNames(SharedClass.mainOption).get(i).getName());
            optionname.setTextColor(FutureTense.this.getResources().getColor(R.color.white));
            shortTxt.setText(shorttxt[i]);
            shortTxt.setTextColor(FutureTense.this.getResources().getColor(R.color.maincolor));
            shortTxtCard.setCardBackgroundColor(FutureTense.this.getResources().getColor(R.color.white));
//            mainCard.setCardBackgroundColor(FutureTense.this.getResources().getColor(R.color.maincolor));
            cell.setBackgroundColor(FutureTense.this.getResources().getColor(R.color.maincolor));

            return rowView;
        }
    }

    public void favbtn(View view){
        NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
        adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(FutureTense.this, new LxiadmobCloseEvent() {
            @Override
            public void setFailedlxi() {
                startActivity(new Intent(FutureTense.this,FavouriteMessages.class));

            }

            @Override
            public void setSuccesslxi() {
            }

            @Override
            public void setAdmobCloseEventlxi() {
                startActivity(new Intent(FutureTense.this,FavouriteMessages.class));

            }
        }, new LxiMyPreferenceManager(FutureTense.this).fIdlxi());


    }

    @Override
    protected void onResume() {
        super.onResume();
        NaxleApps.setAdShowDialogslxi(FutureTense.this);
    }


}
