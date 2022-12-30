package english.englishgrammar.app;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import english.englishgrammar.app.jaky_lxi.interfaces_lxi.LxiNativeAdListener;
import english.englishgrammar.app.jaky_lxi.interfaces_lxi.LxiadmobCloseEvent;
import english.englishgrammar.app.jaky_lxi.mains_lxi.NaxleApps;
import english.englishgrammar.app.jaky_lxi.utils_lxi.LxiMyPreferenceManager;

public class EnglishLessons extends AppCompatActivity {

    private AdView mAdView;
    ImageButton backBtn;       ImageView defbtnon, defbtnoff, methodbtnon, methodbtnoff;
    RecyclerView mainOptionsLV;
    String[] shorttxt = {"EC", "EP"};
    String[] mainOpions = {"English Conversation", "English Phrases"};
    String[] mainOpionsurdu = {"अंग्रेजी वार्तालाप", "अंग्रेजी वाक्यांश"};
    MainOptionsAdapter mainOptionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        ///native ad
        LxiMyPreferenceManager myPreferenceManagerpics = new LxiMyPreferenceManager(this);
        NaxleApps adsGlobalClassEveryTimepics = new NaxleApps();
        adsGlobalClassEveryTimepics.showAndLoadGoogleNativelxi(EnglishLessons.this,
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
        mainOptionsLV.setLayoutManager(new LinearLayoutManager(this));
        mainOptionsAdapter = new MainOptionsAdapter();
        mainOptionsLV.setAdapter(mainOptionsAdapter);

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

    public void favbtn(View view){
        NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
        adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(EnglishLessons.this, new LxiadmobCloseEvent() {
            @Override
            public void setFailedlxi() {
                startActivity(new Intent(EnglishLessons.this,FavouriteMessages.class));

            }

            @Override
            public void setSuccesslxi() {
            }

            @Override
            public void setAdmobCloseEventlxi() {
                startActivity(new Intent(EnglishLessons.this,FavouriteMessages.class));

            }
        }, new LxiMyPreferenceManager(EnglishLessons.this).fIdlxi());
    }


    public class MainOptionsAdapter extends RecyclerView.Adapter<MainOptionsAdapter.MyAdapter> {

        private LayoutInflater inflater;

        @NonNull
        @Override
        public MyAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (inflater == null) {
                inflater = LayoutInflater.from(parent.getContext());
            }
            View view = inflater.inflate(R.layout.categories_celltwo, parent, false);
            return new MyAdapter(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter holder, int position) {
            holder.optionname.setText(mainOpions[position]);
            holder.optionnameurdu.setText(mainOpionsurdu[position]);
            holder.shortTxt.setText(shorttxt[position]);
            holder.shortTxtCard.setCardBackgroundColor(EnglishLessons.this.getResources().getColor(R.color.white));
        }

        @Override
        public int getItemCount() {
            return mainOpions.length;
        }

        public class MyAdapter extends RecyclerView.ViewHolder {
            LinearLayout cell;
            CardView shortTxtCard, mainCard;
            TextView shortTxt, optionname, optionnameurdu;

            public MyAdapter(@NonNull View itemView) {
                super(itemView);

                cell = itemView.findViewById(R.id.cell);
                shortTxtCard = itemView.findViewById(R.id.shortTxtCard);
                mainCard = itemView.findViewById(R.id.mainCard);
                shortTxt = itemView.findViewById(R.id.shortTxt);
                optionname = itemView.findViewById(R.id.optionname);
                optionnameurdu = itemView.findViewById(R.id.optionnameurdu);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (getAdapterPosition() == 0) {

                            NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
                            adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(EnglishLessons.this, new LxiadmobCloseEvent() {
                                @Override
                                public void setFailedlxi() {
                                    Intent intent = new Intent(EnglishLessons.this, ConversationsList.class);
                                    startActivity(intent);

                                }

                                @Override
                                public void setSuccesslxi() {
                                }

                                @Override
                                public void setAdmobCloseEventlxi() {
                                    Intent intent = new Intent(EnglishLessons.this, ConversationsList.class);
                                    startActivity(intent);

                                }
                            }, new LxiMyPreferenceManager(EnglishLessons.this).fIdlxi());

                        } else if (getAdapterPosition() == 1) {

                            NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
                            adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(EnglishLessons.this, new LxiadmobCloseEvent() {
                                @Override
                                public void setFailedlxi() {
                                    Intent intent = new Intent(EnglishLessons.this, Phrases.class);
                                    startActivity(intent);

                                }

                                @Override
                                public void setSuccesslxi() {
                                }

                                @Override
                                public void setAdmobCloseEventlxi() {
                                    Intent intent = new Intent(EnglishLessons.this, Phrases.class);
                                    startActivity(intent);

                                }
                            }, new LxiMyPreferenceManager(EnglishLessons.this).fIdlxi());

                        } else if (getAdapterPosition() == 2) {

                            NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
                            adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(EnglishLessons.this, new LxiadmobCloseEvent() {
                                @Override
                                public void setFailedlxi() {
                                    Intent intent = new Intent(EnglishLessons.this, VocabularyWords.class);
                                    startActivity(intent);
                                    finish();
                                }

                                @Override
                                public void setSuccesslxi() {
                                }

                                @Override
                                public void setAdmobCloseEventlxi() {
                                    Intent intent = new Intent(EnglishLessons.this, VocabularyWords.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }, new LxiMyPreferenceManager(EnglishLessons.this).fIdlxi());

                        }
                    }
                });

            }
        }
    }
//    }

    @Override
    protected void onResume() {
        super.onResume();
        NaxleApps.setAdShowDialogslxi(EnglishLessons.this);
    }


}
