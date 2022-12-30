package english.englishgrammar.app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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

public class ConversationTopics extends AppCompatActivity {

    Conversation_DBManager dbManager;
    ImageButton backBtn;
    ImageView defbtnon, defbtnoff, methodbtnon, methodbtnoff;
    TextView titleTxtView;
    ListView mainOptionsLV;
    MainOptionsAdapter mainOptionsAdapter;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversations_topics);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        //Banner AD

        LxiMyPreferenceManager myPreferenceManagerdude = new LxiMyPreferenceManager(ConversationTopics.this);
        NaxleApps adsGlobalClassEveryTimedude = new NaxleApps();

        adsGlobalClassEveryTimedude.showBannerAdlxi(ConversationTopics.this, new LxiadmobCloseEvent() {
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

        titleTxtView = findViewById(R.id.titleTxtView);
        titleTxtView.setText(SharedClass.conversationtitle);
        backBtn = findViewById(R.id.backBtn);
        mainOptionsLV = findViewById(R.id.mainOptionsLV);
        mainOptionsAdapter = new MainOptionsAdapter();
        mainOptionsLV.setAdapter(mainOptionsAdapter);
        mainOptionsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
                adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(ConversationTopics.this, new LxiadmobCloseEvent() {
                    @Override
                    public void setFailedlxi() {
                        SharedClass.conversationdialogtitle = dbManager.getConversationTypes(SharedClass.conversationId).get(i).getType();
                        SharedClass.conversationType_id = dbManager.getConversationTypes(SharedClass.conversationId).get(i).getId();
                        Intent intent = new Intent(ConversationTopics.this, ConversationDialogActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void setSuccesslxi() {
                    }

                    @Override
                    public void setAdmobCloseEventlxi() {
                        SharedClass.conversationdialogtitle = dbManager.getConversationTypes(SharedClass.conversationId).get(i).getType();
                        SharedClass.conversationType_id = dbManager.getConversationTypes(SharedClass.conversationId).get(i).getId();
                        Intent intent = new Intent(ConversationTopics.this, ConversationDialogActivity.class);
                        startActivity(intent);

                    }
                }, new LxiMyPreferenceManager(ConversationTopics.this).fIdlxi());
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onBackPressed();
            }
        });

    }
    public void favbtn(View view){
        NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
        adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(ConversationTopics.this, new LxiadmobCloseEvent() {
            @Override
            public void setFailedlxi() {
                startActivity(new Intent(ConversationTopics.this,FavouriteMessages.class));

            }

            @Override
            public void setSuccesslxi() {
            }

            @Override
            public void setAdmobCloseEventlxi() {
                startActivity(new Intent(ConversationTopics.this,FavouriteMessages.class));
            }

        }, new LxiMyPreferenceManager(ConversationTopics.this).fIdlxi());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    public class MainOptionsAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return dbManager.getConversationTypes(SharedClass.conversationId).size();
        }

        @Override
        public Object getItem(int i) {
            return dbManager.getConversationTypes(SharedClass.conversationId).get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View rowView = View.inflate(ConversationTopics.this, R.layout.conversation_categories_cell, null);

            TextView shortTxt = rowView.findViewById(R.id.shortTxt);
            TextView optionname = rowView.findViewById(R.id.optionname);

            optionname.setText(dbManager.getConversationTypes(SharedClass.conversationId).get(i).getType());
            shortTxt.setText(String.valueOf(i + 1));

            return rowView;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        NaxleApps.setAdShowDialogslxi(ConversationTopics.this);
    }

}
