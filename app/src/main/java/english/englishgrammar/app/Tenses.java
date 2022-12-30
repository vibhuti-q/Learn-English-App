package english.englishgrammar.app;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.view.LayoutInflater;
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
import english.englishgrammar.app.Customization.AutoResizeTextView;
import english.englishgrammar.app.Manager.Conversation_DBManager;
import english.englishgrammar.app.SharedData.SharedClass;
import english.englishgrammar.app.jaky_lxi.interfaces_lxi.LxiNativeAdListener;
import english.englishgrammar.app.jaky_lxi.interfaces_lxi.LxiadmobCloseEvent;
import english.englishgrammar.app.jaky_lxi.mains_lxi.NaxleApps;
import english.englishgrammar.app.jaky_lxi.utils_lxi.LxiMyPreferenceManager;

import java.io.IOException;

public class Tenses extends AppCompatActivity {
    private AdView mAdView;
    ImageButton backBtn;
    ImageView defbtnon, defbtnoff, methodbtnon, methodbtnoff;
    ListView mainOptionsLV;
    String[] shorttxt = {"PT", "PT", "FT"};
    String[] mainOpions = {"Present Tense", "Past Tense", "Future Tense"};
    String[] mainOpionsUrdu = {"वर्तमान काल", "भूत काल", "भविष्यकाल"};
    Integer[] colors = {R.color.white, R.color.white, R.color.white};
    MainOptionsAdapter mainOptionsAdapter;
    Conversation_DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenses);

        ///native ad
        LxiMyPreferenceManager myPreferenceManagerpics = new LxiMyPreferenceManager(this);
        NaxleApps adsGlobalClassEveryTimepics = new NaxleApps();
        adsGlobalClassEveryTimepics.showAndLoadGoogleNativelxi(Tenses.this,
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
                SharedClass.mainOption = mainOpions[i];
                if (i == 0) {
                    NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
                    adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(Tenses.this, new LxiadmobCloseEvent() {
                        @Override
                        public void setFailedlxi() {
                            startActivity(new Intent(Tenses.this,PresentTense.class));

                        }

                        @Override
                        public void setSuccesslxi() {
                        }

                        @Override
                        public void setAdmobCloseEventlxi() {
                            startActivity(new Intent(Tenses.this,PresentTense.class));

                        }
                    }, new LxiMyPreferenceManager(Tenses.this).fIdlxi());

                }
                if (i == 1) {
                    NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
                    adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(Tenses.this, new LxiadmobCloseEvent() {
                        @Override
                        public void setFailedlxi() {
                            startActivity(new Intent(Tenses.this,PastTense.class));

                        }

                        @Override
                        public void setSuccesslxi() {
                        }

                        @Override
                        public void setAdmobCloseEventlxi() {
                            startActivity(new Intent(Tenses.this,PastTense.class));

                        }
                    }, new LxiMyPreferenceManager(Tenses.this).fIdlxi());
                }
                if (i == 2) {
                    NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
                    adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(Tenses.this, new LxiadmobCloseEvent() {
                        @Override
                        public void setFailedlxi() {
                            startActivity(new Intent(Tenses.this,FutureTense.class));
                        }

                        @Override
                        public void setSuccesslxi() {
                        }

                        @Override
                        public void setAdmobCloseEventlxi() {
                            startActivity(new Intent(Tenses.this,FutureTense.class));
                        }
                    }, new LxiMyPreferenceManager(Tenses.this).fIdlxi());
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
            return mainOpions.length;
        }

        @Override
        public Object getItem(int i) {
            return mainOpions[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            View rowView = inflater.inflate(R.layout.tenses_cell, viewGroup, false);

            LinearLayout cell = rowView.findViewById(R.id.cell);
            CardView shortTxtCard = rowView.findViewById(R.id.shortTxtCard);
            RelativeLayout mainCard = rowView.findViewById(R.id.mainCard);
            TextView shortTxt = rowView.findViewById(R.id.shortTxt);
            AutoResizeTextView optionname = rowView.findViewById(R.id.optionname);
            TextView opurdu = rowView.findViewById(R.id.opurdu);
            opurdu.setText(mainOpionsUrdu[i]);
            optionname.setText(mainOpions[i]);
            optionname.setTextColor(Tenses.this.getResources().getColor(R.color.white));
            shortTxt.setText(shorttxt[i]);
            shortTxt.setTextColor(Tenses.this.getResources().getColor(R.color.maincolor));
            shortTxtCard.setCardBackgroundColor(Tenses.this.getResources().getColor(colors[i]));
//            mainCard.setCardBackgroundColor(Tenses.this.getResources().getColor(R.color.maincolor));
            cell.setBackgroundColor(Tenses.this.getResources().getColor(R.color.maincolor));

            return rowView;
        }
    }

    public void favbtn(View view){
        NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
        adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(Tenses.this, new LxiadmobCloseEvent() {
            @Override
            public void setFailedlxi() {
                startActivity(new Intent(Tenses.this,FavouriteMessages.class));

            }

            @Override
            public void setSuccesslxi() {
            }

            @Override
            public void setAdmobCloseEventlxi() {
                startActivity(new Intent(Tenses.this,FavouriteMessages.class));

            }
        }, new LxiMyPreferenceManager(Tenses.this).fIdlxi());
    }

    @Override
    protected void onResume() {
        super.onResume();
        NaxleApps.setAdShowDialogslxi(Tenses.this);
    }
}
