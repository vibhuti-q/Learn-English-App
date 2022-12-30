package english.englishgrammar.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;


public class FragmentDrawer implements View.OnClickListener {
    private static final int PERMISSION_REQUEST_CODE = 100;
    private Context mContextslxi;
    private Activity activityContext;
    private DrawerLayout mDrawerLayoutslxi;
    private NavigationView mNavigationViewslxi;
    private LinearLayout navigationViewHeaderslxi;
    private LinearLayout navigationViewContainerslxi;
    public static On_TWS_ClickPathListener mOnClickslxi;

    public interface On_TWS_ClickPathListener {
        void onClickPath();
    }

    public FragmentDrawer(Context context, Activity activity, DrawerLayout drawerLayout, On_TWS_ClickPathListener onClick) {
        mContextslxi = context;
        activityContext = activity;
        mDrawerLayoutslxi = drawerLayout;
        mOnClickslxi = onClick;
        initViewslxi();
        setViewStyleslxi();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_apphomelxi:
                closePanelslxi();
//                ((LxiMainActivity) mContextslxi).showFragmentlxiDrawer(LxiFragmentMain.newInstanceslxi(LxiSharedPreferencesUtils.isAutoFocus(), LxiSharedPreferencesUtils.isSqarelxi()));
                break;

            case R.id.btn_sharelxi:
                closePanelslxi();
                Intent intentSharelxi = new Intent(Intent.ACTION_SEND);
                intentSharelxi.putExtra(Intent.EXTRA_TEXT, mContextslxi.getString(R.string.shareapp) + " https://play.google.com/store/apps/details?id=" + mContextslxi.getPackageName());
                intentSharelxi.setType("text/plain");
                mContextslxi.startActivity(Intent.createChooser(intentSharelxi, mContextslxi.getString(R.string.settings_share)));
                break;
            case R.id.btn_rate_uslxi:
                closePanelslxi();
                Intent intentRateUslxi = new Intent(Intent.ACTION_VIEW);
                intentRateUslxi.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + mContextslxi.getPackageName()));
                mContextslxi.startActivity(intentRateUslxi);
                break;


            case R.id.btn_privacy_policylxi:
                closePanelslxi();
//                try {
//                    Uri urilxi = Uri.parse(new LxiMyPreferenceManager(mContextslxi).getPrivacyPolicylxi());
//                    Intent inlxi = new Intent(Intent.ACTION_VIEW, urilxi);
//                    mContextslxi.startActivity(inlxi);
//                } catch (ActivityNotFoundException e) {
//                    e.printStackTrace();
//                }
                break;
            case R.id.btn_more_applxi:
                closePanelslxi();
                try {
                    activityContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:Order+IT+Services")));
                } catch (android.content.ActivityNotFoundException anfe) {
                    activityContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/developer?id=English+Learning+App")));
                }
                break;
        }
    }

    public void openPanellxi() {
        mDrawerLayoutslxi.openDrawer(mNavigationViewslxi);
    }

    public void closePanelslxi() {
        if (mDrawerLayoutslxi != null && mNavigationViewslxi != null && mDrawerLayoutslxi.isDrawerOpen(mNavigationViewslxi))
            mDrawerLayoutslxi.closeDrawer(mNavigationViewslxi);
    }

    public boolean isPanelOpenslxi() {
        return mDrawerLayoutslxi.isDrawerOpen(mNavigationViewslxi);
    }

    private void fastInitItemslxi(int btnId, int imgId, int txtId) {
        LinearLayout btnslxi = (LinearLayout) mNavigationViewslxi.findViewById(btnId);
        if (btnslxi != null) {
            btnslxi.setOnClickListener(this);
            btnslxi.setBackgroundResource(R.drawable.btn_transparent_dark_lxi);
        }

        TextView txtslxi = (TextView) mNavigationViewslxi.findViewById(txtId);
        if (txtslxi != null)
            txtslxi.setTextColor(mContextslxi.getResources().getColor(R.color.nv_text_theme1));
    }

    private void setViewStyleslxi() {
        if (navigationViewContainerslxi != null)
            navigationViewContainerslxi.setBackgroundColor(mContextslxi.getResources().getColor(R.color.white));
        if (navigationViewHeaderslxi != null)

            fastInitItemslxi(R.id.btn_apphomelxi, R.id.img_apphomeslxi, R.id.txt_apphomelxi);
        fastInitItemslxi(R.id.btn_rate_uslxi, R.id.img_rate_uslxi, R.id.txt_rate_uslxi);
        fastInitItemslxi(R.id.btn_sharelxi, R.id.img_sharelxi, R.id.txt_rate_uslxi);
        fastInitItemslxi(R.id.btn_privacy_policylxi, R.id.img_privacy_policylxi, R.id.txt_privacy_policyslxi);
        fastInitItemslxi(R.id.btn_more_applxi, R.id.img_more_applxi, R.id.txt_more_applxi);
    }

    private void initViewslxi() {
        mNavigationViewslxi = (NavigationView) mDrawerLayoutslxi.findViewById(R.id.navigation_viewlxi);
        if (mNavigationViewslxi != null) {
            navigationViewHeaderslxi = (LinearLayout) mNavigationViewslxi.findViewById(R.id.navigation_view_headerlxi);
            navigationViewContainerslxi = (LinearLayout) mNavigationViewslxi.findViewById(R.id.navigation_view_containervlxi);
        }
    }


}
