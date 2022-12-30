package english.englishgrammar.app.ad_module.permission_lxi;

public interface PermissionResult {

    void permissionGranted();

    void permissionDenied();

    void permissionForeverDenied();
}
