package english.englishgrammar.app;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
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
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import english.englishgrammar.app.Manager.Conversation_DBManager;
import english.englishgrammar.app.Models.ChatModel;
import english.englishgrammar.app.SharedData.SharedClass;
import english.englishgrammar.app.jaky_lxi.interfaces_lxi.LxiadmobCloseEvent;
import english.englishgrammar.app.jaky_lxi.mains_lxi.NaxleApps;
import english.englishgrammar.app.jaky_lxi.utils_lxi.LxiMyPreferenceManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConversationDialogActivity extends AppCompatActivity {
    Conversation_DBManager dbManager;
    List<ChatModel> chatData = new ArrayList<>();
    ImageButton backBtn;
    TextView titleTxtView;
    ListView mainOptionsLV;
    MainOptionsAdapter mainOptionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversations_dialog);

        //Banner AD
        LxiMyPreferenceManager myPreferenceManagerdude = new LxiMyPreferenceManager(ConversationDialogActivity.this);
        NaxleApps adsGlobalClassEveryTimedude = new NaxleApps();

        adsGlobalClassEveryTimedude.showBannerAdlxi(ConversationDialogActivity.this, new LxiadmobCloseEvent() {
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

        tempData();

        titleTxtView = findViewById(R.id.titleTxtView);
        titleTxtView.setText(SharedClass.conversationdialogtitle);
        backBtn = findViewById(R.id.backBtn);
        mainOptionsLV = findViewById(R.id.mainOptionsLV);
        mainOptionsAdapter = new MainOptionsAdapter();
        mainOptionsLV.setAdapter(mainOptionsAdapter);
        mainOptionsLV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("chat", chatData.get(i).getDialog());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(ConversationDialogActivity.this, "copied to clipboard", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onBackPressed();
            }
        });
    }

    private void tempData() {
        ChatModel chatModel = new ChatModel("1", "Hi, how are you doing?", "A");
        chatData.add(chatModel);
        ChatModel chatModel1 = new ChatModel("1", "I'm fine. How about yourself?", "B");
        chatData.add(chatModel1);
        ChatModel chatModel2 = new ChatModel("1", "I'm pretty good. Thanks for asking.", "A");
        chatData.add(chatModel2);
        ChatModel chatModel3 = new ChatModel("1", "No problem. So how have you been?", "B");
        chatData.add(chatModel3);
        ChatModel chatModel4 = new ChatModel("1", "I've been great. What about you?", "A");
        chatData.add(chatModel4);
    }


    public void favbtn(View view){
        NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
        adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(ConversationDialogActivity.this, new LxiadmobCloseEvent() {
            @Override
            public void setFailedlxi() {
                startActivity(new Intent(ConversationDialogActivity.this,FavouriteMessages.class));

            }

            @Override
            public void setSuccesslxi() {
            }

            @Override
            public void setAdmobCloseEventlxi() {
                startActivity(new Intent(ConversationDialogActivity.this,FavouriteMessages.class));

            }
        }, new LxiMyPreferenceManager(ConversationDialogActivity.this).fIdlxi());
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public class MainOptionsAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return dbManager.getDialogues(SharedClass.conversationType_id).size();
        }

        @Override
        public Object getItem(int i) {
            return dbManager.getDialogues(SharedClass.conversationType_id).get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View rowView = null;
            TextView messageTxt;

            if (dbManager.getDialogues(SharedClass.conversationType_id).get(i).getPerson_name().equals("A")) {
                rowView = View.inflate(ConversationDialogActivity.this, R.layout.sender_dialog_cell, null);
                messageTxt = rowView.findViewById(R.id.messageTxt);
                messageTxt.setText(dbManager.getDialogues(SharedClass.conversationType_id).get(i).getDialog());
            } else {
                rowView = View.inflate(ConversationDialogActivity.this, R.layout.receiver_dialog_cell, null);
                messageTxt = rowView.findViewById(R.id.messageTxt);
                messageTxt.setText(dbManager.getDialogues(SharedClass.conversationType_id).get(i).getDialog());
            }
            return rowView;
        }
    }
}
