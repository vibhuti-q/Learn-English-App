package english.englishgrammar.app.jaky_lxi

import com.google.gson.annotations.SerializedName

class LxiSettingsModel {
    @SerializedName("v")
    val versionlxi: Int = 0

    @SerializedName("vm")
    var versionMessagelxi: String? = null

    @SerializedName("iMnc")
    var isMaintaincelxi: Boolean = false

    @SerializedName("mM")
    var maintainceMessagelxi: String? = null

    @SerializedName("iMo")
    var isMovedlxi: Boolean = false

    @SerializedName("mU")
    var movedURLlxi: String? = null

    @SerializedName("mD")
    var movedDescriptionlxi: String? = null

    @SerializedName("gB")
    var googleBannerlxi: String? = null

    @SerializedName("gI")
    var googleInterstitiallxi: String? = null

    @SerializedName("gN")
    var googleNativelxi: String? = null

    @SerializedName("gR")
    var googleRewardedlxi: String? = null

    @SerializedName("gO")
    var googleOpenAdIdlxi: String? = null

    @SerializedName("isS")
    val isStartScreenlxi: Boolean = true

    @SerializedName("pP")
    var privacyPolicylxi: String?="http://www.google.com"

    @SerializedName("tC")
    var termsandconditionlxi: String?="http://www.google.com"

    @SerializedName("mA")
    var moreAppslxi: String?="https://play.google.com/store/apps/developer?id="

    @SerializedName("sC")
    val showCount: Int = 0

    @SerializedName("qaz")
    val qaz: Int = 2

    @SerializedName("wsx")
    var wsx: String?=""

    @SerializedName("edc")
    var edc: String?=""

    @SerializedName("rfv")
    var rfv: String?=""

    @SerializedName("tgb")
    var tgb: String?=""

    @SerializedName("yhn")
    var yhn: String?=""

    @SerializedName("xzv")
    var xzv: String?=""

    @SerializedName("mnc")
    var mnc: String?=""

    @SerializedName("dMSW")
    var dynamicShows:Boolean = false

    @SerializedName("dMDY")
    val dynamicDays: Int = 0

    @SerializedName("haOO")
    var homeAdShow:Boolean = false

    //qaz, wsx, edc, rfv, tgb, yhn, xzv, mnc
}