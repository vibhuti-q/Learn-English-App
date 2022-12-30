package english.englishgrammar.app;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.Calendar;

import english.englishgrammar.app.Dic.ui.main.Dic_MainActivity;
import english.englishgrammar.app.Manager.Quotes_DBManager;
import english.englishgrammar.app.Receiver.Notification_receiver;
import english.englishgrammar.app.jaky_lxi.interfaces_lxi.LxiNativeAdListener;
import english.englishgrammar.app.jaky_lxi.interfaces_lxi.LxiadmobCloseEvent;
import english.englishgrammar.app.jaky_lxi.mains_lxi.NaxleApps;
import english.englishgrammar.app.jaky_lxi.utils_lxi.LxiMyPreferenceManager;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.On_TWS_ClickPathListener {
    LinearLayout d, el, md, qd, ms, ee;
    ImageView fm;
    //    SharedPreferences preferences;
    int flag = 0;
    private int PICK_REQUEST_CODElxi = 1;
    private DrawerLayout drawerLayoutlxi;
    SharedPreferences preferences;
    int alarm_status = 0;
    FragmentDrawer mDrawerLayoutHelperlxi;
    Quotes_DBManager quotes_dbManager;

    public FragmentDrawer getDrawerLayoutHelperlxi() {
        return mDrawerLayoutHelperlxi;
    }

    @Override
    public void onStop() {
        mDrawerLayoutHelperlxi.closePanelslxi();
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GetQuotePreference();
        LxiMyPreferenceManager myPreferenceManagerdude = new LxiMyPreferenceManager(MainActivity.this);
        NaxleApps adsGlobalClassEveryTimedude = new NaxleApps();

//        adViewone = findViewById(R.id.adViewone);
//        AdRequest adRequest1 = new AdRequest.Builder().build();
//        adViewone.loadAd(adRequest1);

//ad
        new NaxleApps().showAdInHomeScreen(this, new LxiadmobCloseEvent() {
            @Override
            public void setFailedlxi() {
            }

            @Override
            public void setSuccesslxi() {
            }

            @Override
            public void setAdmobCloseEventlxi() {
            }

        }, new LxiMyPreferenceManager(this).fIdlxi());

        //Banner AD
        adsGlobalClassEveryTimedude.showBannerAdlxi(MainActivity.this, new LxiadmobCloseEvent() {
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
        }, myPreferenceManagerdude.getGBannerIDlxi(), findViewById(R.id.adViewLayoutlxi));

        initViewlxi();

        ImageView drawerButton = findViewById(R.id.drawerButton);
        drawerButton.setVisibility(View.VISIBLE);
        drawerButton.setOnClickListener(v -> {
            if (mDrawerLayoutHelperlxi.isPanelOpenslxi()) {
                mDrawerLayoutHelperlxi.closePanelslxi();
            } else {
                mDrawerLayoutHelperlxi.openPanellxi();
            }
        });

        d = findViewById(R.id.d);
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
                adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(MainActivity.this, new LxiadmobCloseEvent() {
                    @Override
                    public void setFailedlxi() {
                        startActivity(new Intent(MainActivity.this, Dic_MainActivity.class));
                    }

                    @Override
                    public void setSuccesslxi() {
                    }

                    @Override
                    public void setAdmobCloseEventlxi() {
                        startActivity(new Intent(MainActivity.this, Dic_MainActivity.class));
                    }
                }, new LxiMyPreferenceManager(MainActivity.this).fIdlxi());
            }
        });

        el = findViewById(R.id.el);
        el.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
                adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(MainActivity.this, new LxiadmobCloseEvent() {
                    @Override
                    public void setFailedlxi() {
                        startActivity(new Intent(MainActivity.this, EnglishLessons.class));
                    }

                    @Override
                    public void setSuccesslxi() {
                    }

                    @Override
                    public void setAdmobCloseEventlxi() {
                        startActivity(new Intent(MainActivity.this, EnglishLessons.class));

                    }
                }, new LxiMyPreferenceManager(MainActivity.this).fIdlxi());
            }
        });

        md = findViewById(R.id.md);
        md.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
                adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(MainActivity.this, new LxiadmobCloseEvent() {
                    @Override
                    public void setFailedlxi() {
                        startActivity(new Intent(MainActivity.this, MessageOfTheDay.class));
                    }

                    @Override
                    public void setSuccesslxi() {
                    }

                    @Override
                    public void setAdmobCloseEventlxi() {
                        startActivity(new Intent(MainActivity.this, MessageOfTheDay.class));
                    }
                }, new LxiMyPreferenceManager(MainActivity.this).fIdlxi());
            }
        });

        qd = findViewById(R.id.qd);
        qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
                adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(MainActivity.this, new LxiadmobCloseEvent() {
                    @Override
                    public void setFailedlxi() {
                        startActivity(new Intent(MainActivity.this, QuizOfTheDay.class));
                    }

                    @Override
                    public void setSuccesslxi() {
                    }

                    @Override
                    public void setAdmobCloseEventlxi() {
                        startActivity(new Intent(MainActivity.this, QuizOfTheDay.class));
                    }
                }, new LxiMyPreferenceManager(MainActivity.this).fIdlxi());
            }
        });

        ms = findViewById(R.id.ms);
        ms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
                adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(MainActivity.this, new LxiadmobCloseEvent() {
                    @Override
                    public void setFailedlxi() {
                        startActivity(new Intent(MainActivity.this, MoralStories.class));
                    }

                    @Override
                    public void setSuccesslxi() {
                    }

                    @Override
                    public void setAdmobCloseEventlxi() {
                        startActivity(new Intent(MainActivity.this, MoralStories.class));
                    }
                }, new LxiMyPreferenceManager(MainActivity.this).fIdlxi());
            }
        });

        ee = findViewById(R.id.ee);
        ee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
                adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(MainActivity.this, new LxiadmobCloseEvent() {
                    @Override
                    public void setFailedlxi() {
                        startActivity(new Intent(MainActivity.this, EnglishExercise.class));
                    }

                    @Override
                    public void setSuccesslxi() {
                    }

                    @Override
                    public void setAdmobCloseEventlxi() {
                        startActivity(new Intent(MainActivity.this, EnglishExercise.class));
                    }
                }, new LxiMyPreferenceManager(MainActivity.this).fIdlxi());
            }
        });

        fm = findViewById(R.id.fm);
        fm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NaxleApps adsGlobalClassEveryTimeslxi = new NaxleApps();
                adsGlobalClassEveryTimeslxi.showIntrestrialAdslxi(MainActivity.this, new LxiadmobCloseEvent() {
                    @Override
                    public void setFailedlxi() {
                        Intent intent = new Intent(MainActivity.this, FavouriteMessages.class);
                        startActivity(intent);
                    }

                    @Override
                    public void setSuccesslxi() {
                    }

                    @Override
                    public void setAdmobCloseEventlxi() {
                        Intent intent = new Intent(MainActivity.this, FavouriteMessages.class);
                        startActivity(intent);
                    }
                }, new LxiMyPreferenceManager(MainActivity.this).fIdlxi());
            }
        });


        quotes_dbManager = new Quotes_DBManager(this);
        quotes_dbManager.open();
        try {
            if (flag == 0) {
                quotes_dbManager.copyDataBase();
                flag = 1;
                QuotePreference();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        getAlarmStatus();

        if (alarm_status == 0) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 10);
            calendar.set(Calendar.MINUTE, 10);
            calendar.set(Calendar.SECOND, 10);
            Intent intent = new Intent(getApplicationContext(), Notification_receiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_IMMUTABLE);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
            alarm_status = 1;
            saveAlarmStatus();
        } else {
            Log.d("msg", "already scheduled");
        }

        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    private void startMainActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
//        finish();
    }

    private void initViewlxi() {
        drawerLayoutlxi = (DrawerLayout) findViewById(R.id.drawer_layoutlxi);
    }

    @Override
    public void onClickPath() {
//        Intent i = new Intent(LxiMainActivity.this, FilePickerActivity.class);
//        i.putExtra(FilePickerActivity.EXTRA_ALLOW_MULTIPLE, false);
//        i.putExtra(FilePickerActivity.EXTRA_ALLOW_CREATE_DIR, false);
//        i.putExtra(FilePickerActivity.EXTRA_MODE, FilePickerActivity.MODE_FILE);
//        i.putExtra(FilePickerActivity.EXTRA_START_PATH, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath());
//        startActivityForResult(i, PICK_REQUEST_CODElxi);

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, PICK_REQUEST_CODElxi);
        } else {
            Toast.makeText(MainActivity.this, "Please Select Valid Code", Toast.LENGTH_SHORT).show();
        }
    }

    public void QuotePreference() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("flag", flag);
        editor.apply();
        editor.commit();
    }

    public void GetQuotePreference() {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        flag = preferences.getInt("flag", 0);
    }

    public void getAlarmStatus() {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        alarm_status = preferences.getInt("alarm_status", 0);
    }

    public void saveAlarmStatus() {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("alarm_status", alarm_status);
        editor.apply();
        editor.commit();
    }

    public void showNotification(Context context, String title, String body, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        int notificationId = 1;
        String channelId = "channel-01";
        String channelName = "Channel Name";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.edu)
                .setContentTitle(title)
                .setContentText(body);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntent(intent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        mBuilder.setContentIntent(resultPendingIntent);

        notificationManager.notify(notificationId, mBuilder.build());
    }

//    public void favbtn(View view) {
//        showandstart(english_FavouriteMessages.class);
//    }

    private void rateapp() {
        closedrawer();

        StringBuilder sb = new StringBuilder();
        sb.append("market://details?id=");
        sb.append(getPackageName());
        String str = "android.intent.action.VIEW";
        Intent intent = new Intent(str, Uri.parse(sb.toString()));
        intent.addFlags(1208483840);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("http://play.google.com/store/apps/details?id=");
            sb2.append(getPackageName());
            startActivity(new Intent(str, Uri.parse(sb2.toString())));
        }
    }

    private void shareApp(Context context) {
        closedrawer();
        Intent intentSharelxi = new Intent(Intent.ACTION_SEND);
        intentSharelxi.putExtra(Intent.EXTRA_TEXT, getString(R.string.sharetxt) + " https://play.google.com/store/apps/details?id=" + getPackageName());
        intentSharelxi.setType("text/plain");
        startActivity(Intent.createChooser(intentSharelxi, getString(R.string.settings_share)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        NaxleApps.setAdShowDialogslxi(MainActivity.this);
    }

    public void moreapps() {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:Order+IT+Services")));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/developer?id=English+Learning+App")));
        }
    }

    public void IsFinish(String alertmessage) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        android.os.Process.killProcess(android.os.Process.myPid());
                        // This above line close correctly
                        //finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(alertmessage)
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    public void closedrawer() {
        if (this.drawerLayoutlxi.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayoutlxi.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDrawerLayoutHelperlxi = new FragmentDrawer(this, this, drawerLayoutlxi, MainActivity.this);
    }

    @Override
    public void onBackPressed() {
        if (this.drawerLayoutlxi.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayoutlxi.closeDrawer(GravityCompat.START);
        } else {
            Dialog dialogpics = new Dialog(this);
            dialogpics.setContentView(R.layout.dialog_fullexit_lxi);
            dialogpics.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialogpics.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.gravity = Gravity.CENTER;

            dialogpics.getWindow().setAttributes(lp);

            LxiMyPreferenceManager myPreferenceManagerpics = new LxiMyPreferenceManager(this);
            NaxleApps adsGlobalClassEveryTimepics = new NaxleApps();
            adsGlobalClassEveryTimepics.showAndLoadGoogleNativelxi(MainActivity.this,
                    myPreferenceManagerpics.nadIdlxi(),
                    dialogpics.findViewById(R.id.containerlxi),
                    true, 1,
                    new LxiNativeAdListener() {
                        @Override
                        public void setNativeFailedlxi() {
                            dialogpics.findViewById(R.id.nadViewlxi).setVisibility(View.GONE);
                        }

                        @Override
                        public void setNativeSuccesslxi() {
                        }
                    });

            TextView txt_nopics = dialogpics.findViewById(R.id.txt_nohetu);
            TextView txt_yespics = dialogpics.findViewById(R.id.txt_yeshetu);

            txt_yespics.setOnClickListener(v -> {
                dialogpics.dismiss();
                finishAffinity();
            });

            txt_nopics.setOnClickListener(v -> dialogpics.dismiss());
            dialogpics.show();
        }
    }
}
