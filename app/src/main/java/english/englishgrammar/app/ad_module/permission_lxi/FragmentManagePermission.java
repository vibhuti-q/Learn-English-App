package english.englishgrammar.app.ad_module.permission_lxi;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//@SuppressWarnings({"MissingPermission"})
public class FragmentManagePermission extends Fragment {
    private static final int KEY_PERMISSION = 200;
    private PermissionResult permissionResult;
    private String[] permissionsAsk;

    @Override
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setRetainInstance(false);
    }

    /**
     * @param context current Context
     * @param permission String permission to ask
     * @return boolean true/false
     */
    public boolean isPermissionGranted(Context context, String permission) {
        return (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) || (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED);
    }

    /**
     * @param context current Context
     * @param permissions String[] permission to ask
     * @return boolean true/false
     */
    public boolean isPermissionsGranted(Context context, String[] permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return true;

        boolean granted = true;

        for (String permission : permissions) {
            if (!(ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED))
                granted = false;
        }

        return granted;
    }

    private void internalRequestPermission(String[] permissionAsk) {
        String[] arrayPermissionNotGranted;
        ArrayList<String> permissionsNotGranted = new ArrayList<>();

        for (String s : permissionAsk) {
            if (!isPermissionGranted(getActivity(), s)) {
                permissionsNotGranted.add(s);
            }
        }

        if (permissionsNotGranted.isEmpty()) {
            if (permissionResult != null)
                permissionResult.permissionGranted();

        } else {
            arrayPermissionNotGranted = new String[permissionsNotGranted.size()];
            arrayPermissionNotGranted = permissionsNotGranted.toArray(arrayPermissionNotGranted);
            requestPermissions(arrayPermissionNotGranted, KEY_PERMISSION);
        }
    }

    // we need to add super method call in onRequestPermissionsResult to get PermissionResult callbacks on child fragment
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode != KEY_PERMISSION) {
            return;
        }
        boolean granted = true;
        List<String> permissionDenied = new LinkedList<>();

        for (int i = 0; i < grantResults.length; i++) {
            if (!(grantResults.length > 0 && grantResults[i] == PackageManager.PERMISSION_GRANTED)) {
                granted = false;
                permissionDenied.add(permissions[i]);
            }
        }

        if (permissionResult != null) {
            if (granted) {
                permissionResult.permissionGranted();
            } else {
                for (String s : permissionDenied) {
                    if (!shouldShowRequestPermissionRationale(s)) {
                        permissionResult.permissionForeverDenied();
                        return;
                    }
                }
                permissionResult.permissionDenied();
            }
        }
    }

    /**
     * @param permission String permission ask
     * @param permissionResult callback PermissionResult
     */
    public void askCompactPermission(String permission, PermissionResult permissionResult) {
        permissionsAsk = new String[]{permission};
        this.permissionResult = permissionResult;
        internalRequestPermission(permissionsAsk);
    }

    /**
     * @param permissions String[] permissions to ask
     * @param permissionResult callback PermissionResult
     */
    public void askCompactPermissions(String[] permissions, PermissionResult permissionResult) {
        permissionsAsk = permissions;
        this.permissionResult = permissionResult;
        internalRequestPermission(permissionsAsk);
    }

    public void openSettingsApp(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            startActivity(intent);
        }
    }
}
