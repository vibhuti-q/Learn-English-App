package english.englishgrammar.app.Dic.data.remote.wordsapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

import english.englishgrammar.app.Dic.data.model.wordsapi.Word;
import english.englishgrammar.app.Dic.util.Constants;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WordsApiService {
    private WordsApi api;
    public WordsApiService(){
        api = new Retrofit.Builder()
                .baseUrl(Constants.WORDSAPI_BASE_URL)
                .addConverterFactory(createGsonConverter(Word.class, new GetWordDetailsDeserializer()))
                .build()
                .create(WordsApi.class);
    }
    private static Converter.Factory createGsonConverter(Type type, Object typeAdapter) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(type, typeAdapter);
        Gson gson = gsonBuilder.create();
        return GsonConverterFactory.create(gson);
    }
    public Call<Word> getWord(String w){
        return api.getWord(w);
    }
}
