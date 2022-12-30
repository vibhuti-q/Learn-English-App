#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_english_englishgrammar_app_ad_1module_jaky_1lxi_core_1lxi_LxiSplashTESActivity_stringFromC(
        JNIEnv *env,
        jobject thiz) {
    std::string hello = "https://naxle-db-default-rtdb.asia-southeast1.firebasedatabase.app/apps/";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_english_englishgrammar_app_ad_1module_contactus_1lxi_config_1lxi_LxiRythm_pKeyU(JNIEnv *env,
                                                                              jclass clazz,
                                                                              jstring m_key) {
    const char *mKey = env->GetStringUTFChars(m_key, NULL);
    std::string hello = mKey + std::string("shaktiman");
    return env->NewStringUTF(hello.c_str());
}