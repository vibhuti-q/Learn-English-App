package english.englishgrammar.app.Dic.data.remote.wordsapi;


import english.englishgrammar.app.Dic.data.model.wordsapi.Word;
import english.englishgrammar.app.Dic.util.Constants;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface WordsApi {
    @Headers({
            "X-RapidAPI-Host: " + Constants.WORDS_X_RAPID_API_HOST,
            "X-RapidAPI-Key: " + Constants.X_RAPID_API_KEY,
    })
    @GET("{word}")
    Call<Word> getWord(@Path("word") String word);
}
