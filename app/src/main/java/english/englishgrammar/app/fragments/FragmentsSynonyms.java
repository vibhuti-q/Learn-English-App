package english.englishgrammar.app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import english.englishgrammar.app.R;
import english.englishgrammar.app.WordMeaningActivity;


public class FragmentsSynonyms extends Fragment {


    public FragmentsSynonyms() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_definition,container, false);//Inflate Layout

        Context context=getActivity();

        TextView text = (TextView) view.findViewById(R.id.textview_d);//Find textView Id

        String example= ((WordMeaningActivity)context).synonyms;
        text.setText(example);

        if(example==null)
        {
            text.setText("No Example found");
        }


        return view;


    }
}
