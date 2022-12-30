package english.englishgrammar.app.Dic.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import java.util.List;

import english.englishgrammar.app.Dic.data.model.wordsapi.Word;
import english.englishgrammar.app.Dic.data.repository.WordsRepo;
import english.englishgrammar.app.Dic.ui.main.Dic_MainActivity;
import english.englishgrammar.app.Dic.util.WordResponseListener;
import io.reactivex.rxjava3.core.Single;

public class MainViewModel extends ViewModel {
    private WordsRepo mRepo;
    private LiveData<List<Word>> mFavWords;
    public void init(@NonNull Application application){
        mRepo = WordsRepo.getInstance(application);
        mFavWords = mRepo.getFavWords();
    }
    public LiveData<List<Word>> getFavWords(){
        return this.mFavWords;
    }
    public void getWord(String w, WordResponseListener responseListener){
        mRepo.getWordFromApi(w, responseListener);
    }
    public void insertWord(Word w){
        mRepo.insertWord(w);
    }
    public void removeWord(Word w){
        mRepo.removeWord(w);
    }
    public void removeListofWords( List<Word> w, Dic_MainActivity.OnRemoveSelectedWords mListener){
        mRepo.removeListofWords(w, mListener);
    }
    public Single<Word> findWord(String w){
        return mRepo.findWord(w);
    }
}
