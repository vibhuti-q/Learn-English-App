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

import english.englishgrammar.app.jaky_lxi.interfaces_lxi.LxiNativeAdListener;
import english.englishgrammar.app.jaky_lxi.interfaces_lxi.LxiadmobCloseEvent;
import english.englishgrammar.app.jaky_lxi.mains_lxi.NaxleApps;
import english.englishgrammar.app.jaky_lxi.utils_lxi.LxiMyPreferenceManager;

public class EnglishExercise extends AppCompatActivity {
    ImageButton backBtn;
    ListView mainOptionsLV;
    String[] shorttxt = {"ET", "EG"};
    String[] mainOpions = {"English Tenses", "English Grammer"};
    String[] mainOpionsurdu = {"अंग्रेजी काल", "अंग्रेजी व्याकरण"};
    MainOptionsAdapter mainOptionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        ///native ad
        LxiMyPreferenceManager myPreferenceManagerpics = new LxiMyPreferenceManager(this);
        NaxleApps adsGlobalClassEveryTimepics = new NaxleApps();
        adsGlobalClassEveryTimepics.showAndLoadGoogleNativelxi(EnglishExercise.this,
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

        backBtn = findViewById(R.id.backBtn);
        mainOptionsLV = findViewById(R.id.mainOptionsLV);
        mainOptionsAdapter = new MainOptionsAdapter();
        mainOptionsLV.setAdapter(mainOptionsAdapter);
        mainOptionsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
                    adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(EnglishExercise.this, new LxiadmobCloseEvent() {
                        @Override
                        public void setFailedlxi() {
                            startActivity(new Intent(EnglishExercise.this, Tenses.class));
                        }

                        @Override
                        public void setSuccesslxi() {
                        }

                        @Override
                        public void setAdmobCloseEventlxi() {
                            startActivity(new Intent(EnglishExercise.this, Tenses.class));
                        }

                    }, new LxiMyPreferenceManager(EnglishExercise.this).fIdlxi());
                }
                if (i == 1) {
                    NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
                    adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(EnglishExercise.this, new LxiadmobCloseEvent() {

                        @Override
                        public void setFailedlxi() {
                            startActivity(new Intent(EnglishExercise.this, PartsOfSpeech.class));
                        }

                        @Override
                        public void setSuccesslxi() {
                        }

                        @Override
                        public void setAdmobCloseEventlxi() {
                            startActivity(new Intent(EnglishExercise.this, PartsOfSpeech.class));
                        }

                    }, new LxiMyPreferenceManager(EnglishExercise.this).fIdlxi());
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
            View rowView = View.inflate(EnglishExercise.this, R.layout.exercise_categories_cell, null);

            LinearLayout cell = rowView.findViewById(R.id.cell);
            CardView shortTxtCard = rowView.findViewById(R.id.shortTxtCard);
            RelativeLayout mainCard = rowView.findViewById(R.id.mainCard);
            TextView shortTxt = rowView.findViewById(R.id.shortTxt);
            TextView optionname = rowView.findViewById(R.id.optionname);
            TextView optionnameurdu = rowView.findViewById(R.id.optionnameurdu);
            optionname.setText(mainOpions[i]);
            optionnameurdu.setText(mainOpionsurdu[i]);
            shortTxt.setText(shorttxt[i]);
            shortTxt.setTextColor(EnglishExercise.this.getResources().getColor(R.color.maincolor));
            shortTxtCard.setCardBackgroundColor(EnglishExercise.this.getResources().getColor(R.color.white));
//            mainCard.setCardBackgroundColor(EnglishExercise.this.getResources().getColor(R.color.maincolor));
//            cell.setBackgroundColor(EnglishExercise.this.getResources().getColor(R.color.maincolor));

            return rowView;
        }
    }

    public void favbtn(View view) {
        NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
        adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(EnglishExercise.this, new LxiadmobCloseEvent() {
            @Override
            public void setFailedlxi() {
                startActivity(new Intent(EnglishExercise.this, FavouriteMessages.class));

            }

            @Override
            public void setSuccesslxi() {
            }

            @Override
            public void setAdmobCloseEventlxi() {
                startActivity(new Intent(EnglishExercise.this, FavouriteMessages.class));

            }
        }, new LxiMyPreferenceManager(EnglishExercise.this).fIdlxi());

    }

    @Override
    protected void onResume() {
        super.onResume();
        NaxleApps.setAdShowDialogslxi(EnglishExercise.this);
    }

    private void startMainActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
        finish();
    }
}
