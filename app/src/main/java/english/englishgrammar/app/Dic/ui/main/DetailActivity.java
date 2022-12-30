package english.englishgrammar.app.Dic.ui.main;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import english.englishgrammar.app.Dic.data.model.wordsapi.Word;
import english.englishgrammar.app.Dic.ui.adapter.ResultsAdapter;
import english.englishgrammar.app.Dic.ui.viewmodel.MainViewModel;
import english.englishgrammar.app.R;
import english.englishgrammar.app.databinding.DicActivityDetailBinding;
import english.englishgrammar.app.jaky_lxi.interfaces_lxi.LxiadmobCloseEvent;
import english.englishgrammar.app.jaky_lxi.mains_lxi.NaxleApps;
import english.englishgrammar.app.jaky_lxi.utils_lxi.LxiMyPreferenceManager;

public class DetailActivity extends AppCompatActivity {
    private DicActivityDetailBinding binding;
    private final static String TAG = "DetailActivity";
    private ResultsAdapter mAdapter;
    private Word mWord;
    private MainViewModel mViewModel;
    private Boolean isFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DicActivityDetailBinding.inflate(getLayoutInflater());
        View viewRoot = binding.getRoot();
        setContentView(viewRoot);
        LxiMyPreferenceManager myPreferenceManagerdude = new LxiMyPreferenceManager(DetailActivity.this);
        NaxleApps adsGlobalClassEveryTimedude = new NaxleApps();

        //Banner AD
        adsGlobalClassEveryTimedude.showBannerAdlxi(DetailActivity.this, new LxiadmobCloseEvent() {
            @Override
            public void setFailedlxi() {
                findViewById(R.id.adViewlxi).setVisibility(View.GONE);
            }

            @Override
            public void setSuccesslxi() {
            }

            @Override
            public void setAdmobCloseEventlxi() {
            }
        }, myPreferenceManagerdude.getGBannerIDlxi(), findViewById(R.id.adViewLayoutlxi));
        try {
            initValues();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    void initValues() {
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mViewModel.init(getApplication());
        Gson gson = new Gson();
        Intent i = getIntent();
        mWord = gson.fromJson(i.getStringExtra("wordItem"), Word.class);
        isFav = i.getBooleanExtra("favourite", false);
        mAdapter = new ResultsAdapter(getApplicationContext());
        setData(mWord);
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        final Boolean[] isfav = {isFav};
        binding.ivStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isfav[0]) {
                    binding.ivStar.setImageResource(R.drawable.ic_baseline_star_36);
                    mViewModel.insertWord(mWord);
                    isfav[0] = true;
                } else {
                    binding.ivStar.setImageResource(R.drawable.ic_baseline_star_outline_36);
                    mViewModel.removeWord(mWord);
                    isfav[0] = false;
                }
            }
        });

    }

    void setData(Word w) {
        binding.tvWord.setText(w.getWord());
        if (isFav) {
            binding.ivStar.setImageResource(R.drawable.ic_baseline_star_36);
        } else {
            binding.ivStar.setImageResource(R.drawable.ic_baseline_star_outline_36);
        }
        if (w.getPronunciation().getAll() != null) {
            TextView tv = createPronTextView("all:" + " /" + w.getPronunciation().getAll() + "/");
            binding.llPronunciation.addView(tv);
        }
        if (w.getPronunciation().getNoun() != null) {
            TextView tv = createPronTextView("noun:" + " /" + w.getPronunciation().getNoun() + "/");
            binding.llPronunciation.addView(tv);
        }
        if (w.getPronunciation().getVerb() != null) {
            TextView tv = createPronTextView("verb:" + " /" + w.getPronunciation().getVerb() + "/");
            binding.llPronunciation.addView(tv);
        }
        RecyclerView.LayoutManager linearLayout = new LinearLayoutManager(getApplicationContext());
        binding.rvResults.setLayoutManager(linearLayout);
        binding.rvResults.setAdapter(mAdapter);
        mAdapter.setResultsList(mWord.getResults());
    }

    TextView createPronTextView(String text) {
        TextView tv = new TextView(getApplicationContext());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tv.setTextAppearance(R.style.PronunciationTextStyle);
        } else {
            tv.setTextAppearance(getApplicationContext(), R.style.PronunciationTextStyle);
        }
        tv.setPadding(10, 5, 10, 5);
        tv.setText(text);
        return tv;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}