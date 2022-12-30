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

public class PresentTense extends AppCompatActivity {
    Conversation_DBManager dbManager;
    ImageButton backBtn;
    ListView mainOptionsLV;
    String[] shorttxt = {"PT", "PT", "PT", "PT"};
    String[] mainOpionsUrdu = {"वर्तमान काल", "वर्तमान निरंतर क्रिया", "वर्ब प्रेजेंट परफेक्ट", "वर्त्तमान काल में जो काम अभी तक होता आ रहा है"};
    MainOptionsAdapter mainOptionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_present_tense);

        ///native ad
        LxiMyPreferenceManager myPreferenceManagerpics = new LxiMyPreferenceManager(this);
        NaxleApps adsGlobalClassEveryTimepics = new NaxleApps();
        adsGlobalClassEveryTimepics.showAndLoadGoogleNativelxi(PresentTense.this,
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
                    adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(PresentTense.this, new LxiadmobCloseEvent() {
                        @Override
                        public void setFailedlxi() {
                            SharedClass.tense_id = dbManager.getTenses().get(0).getId();
                            startActivity(new Intent(PresentTense.this,PresentIndefinateTense.class));
                        }

                        @Override
                        public void setSuccesslxi() {
                        }

                        @Override
                        public void setAdmobCloseEventlxi() {
                            SharedClass.tense_id = dbManager.getTenses().get(0).getId();
                            startActivity(new Intent(PresentTense.this,PresentIndefinateTense.class));
                        }
                    }, new LxiMyPreferenceManager(PresentTense.this).fIdlxi());

                }
                if (i == 1) {
                    NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
                    adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(PresentTense.this, new LxiadmobCloseEvent() {
                        @Override
                        public void setFailedlxi() {
                            SharedClass.tense_id = dbManager.getTenses().get(1).getId();
                            startActivity(new Intent(PresentTense.this,PresentContinuousTense.class));
                        }

                        @Override
                        public void setSuccesslxi() {
                        }

                        @Override
                        public void setAdmobCloseEventlxi() {
                            SharedClass.tense_id = dbManager.getTenses().get(1).getId();
                            startActivity(new Intent(PresentTense.this,PresentContinuousTense.class));
                        }
                    }, new LxiMyPreferenceManager(PresentTense.this).fIdlxi());

                }
                if (i == 2) {
                    NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
                    adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(PresentTense.this, new LxiadmobCloseEvent() {
                        @Override
                        public void setFailedlxi() {
                            SharedClass.tense_id = dbManager.getTenses().get(2).getId();
                            startActivity(new Intent(PresentTense.this,PresentPerfectTense.class));
                        }

                        @Override
                        public void setSuccesslxi() {
                        }

                        @Override
                        public void setAdmobCloseEventlxi() {
                            SharedClass.tense_id = dbManager.getTenses().get(2).getId();
                            startActivity(new Intent(PresentTense.this,PresentPerfectTense.class));
                        }
                    }, new LxiMyPreferenceManager(PresentTense.this).fIdlxi());

                }
                if (i == 3) {
                    NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
                    adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(PresentTense.this, new LxiadmobCloseEvent() {
                        @Override
                        public void setFailedlxi() {
                            SharedClass.tense_id = dbManager.getTenses().get(3).getId();
                            startActivity(new Intent(PresentTense.this,PresentPerfectContinuousTense.class));
                        }

                        @Override
                        public void setSuccesslxi() {
                        }

                        @Override
                        public void setAdmobCloseEventlxi() {
                            SharedClass.tense_id = dbManager.getTenses().get(3).getId();
                            startActivity(new Intent(PresentTense.this,PresentPerfectContinuousTense.class));
                        }
                    }, new LxiMyPreferenceManager(PresentTense.this).fIdlxi());

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
            View rowView = View.inflate(PresentTense.this, R.layout.tenses_cell, null);

            LinearLayout cell = rowView.findViewById(R.id.cell);
            CardView shortTxtCard = rowView.findViewById(R.id.shortTxtCard);
            RelativeLayout mainCard = rowView.findViewById(R.id.mainCard);
            TextView shortTxt = rowView.findViewById(R.id.shortTxt);
            TextView optionname = rowView.findViewById(R.id.optionname);
            TextView opurdu = rowView.findViewById(R.id.opurdu);
            opurdu.setText(mainOpionsUrdu[i]);
            optionname.setText(dbManager.getTensesNames(SharedClass.mainOption).get(i).getName());
            optionname.setTextColor(PresentTense.this.getResources().getColor(R.color.white));
            shortTxt.setText(shorttxt[i]);
            shortTxt.setTextColor(PresentTense.this.getResources().getColor(R.color.maincolor));
            shortTxtCard.setCardBackgroundColor(PresentTense.this.getResources().getColor(R.color.white));
//            mainCard.setCardBackgroundColor(PresentTense.this.getResources().getColor(R.color.maincolor));
            cell.setBackgroundColor(PresentTense.this.getResources().getColor(R.color.maincolor));

            return rowView;
        }
    }

    public void favbtn(View view){
        NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
        adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(PresentTense.this, new LxiadmobCloseEvent() {
            @Override
            public void setFailedlxi() {
                startActivity(new Intent(PresentTense.this,FavouriteMessages.class));

            }

            @Override
            public void setSuccesslxi() {
            }

            @Override
            public void setAdmobCloseEventlxi() {
                startActivity(new Intent(PresentTense.this,FavouriteMessages.class));

            }
        }, new LxiMyPreferenceManager(PresentTense.this).fIdlxi());

    }


    @Override
    protected void onResume() {
        super.onResume();
        NaxleApps.setAdShowDialogslxi(PresentTense.this);
    }


}
