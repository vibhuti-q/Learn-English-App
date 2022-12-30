package english.englishgrammar.app.jaky_lxi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface LxiApiUtils {
    @GET("{fullUrl}")
    fun getPowerslxi(@Path("fullUrl") fullUrllxi:String): Call<LxiSettingsModel>
}