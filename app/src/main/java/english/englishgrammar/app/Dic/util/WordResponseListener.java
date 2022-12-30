package english.englishgrammar.app.Dic.util;


import english.englishgrammar.app.Dic.data.model.wordsapi.Word;

public interface WordResponseListener {
    public void onSuccess(Word word);
    public void onFail(String e);
    public void onError(Throwable t);
}
