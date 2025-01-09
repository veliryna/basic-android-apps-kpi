package com.example.helloworld;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;


public class FragmentChoice extends Fragment {
    YearClicked mCallback;
    public interface YearClicked{
        void sendData(String year);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Activity activity;
        try {
            activity = (Activity) context;
            mCallback = (YearClicked) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement YearClicked interface.");
        }
    }

    @Override
    public void onDetach() {
        mCallback = null;
        super.onDetach();
    }


    ExpandableListView bookList;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableTitleList;
    HashMap<String, List<String>> expandableDetailList;
    RadioGroup radioGrp;
    Button filterbutton;
    Button openbutton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment_choice, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // creating and populating list of books
        bookList = view.findViewById(R.id.expandableListViewOfBooks);
        expandableDetailList = ExpandableListDataItems.getData();
        expandableTitleList = new ArrayList<>(expandableDetailList.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(view.getContext(), expandableTitleList, expandableDetailList);
        bookList.setAdapter(expandableListAdapter);


        // creating and populating radio buttons with year labels
        radioGrp = view.findViewById(R.id.radio_group);
        HashMap<String,String> bookYears = BooksAndYearsDataItems.getData();
        ArrayList<String> year_list = new ArrayList<>(bookYears.values());
        Collections.sort(year_list);
        LinkedHashSet<String> years = new LinkedHashSet<>(year_list);
        int i = 1;
        for (String year : years) {
            RadioButton radioButton = new RadioButton(view.getContext());
            radioButton.setText(year);
            radioButton.setId(i);
            radioGrp.addView(radioButton);
            i++;
        }

        filterbutton = view.findViewById(R.id.filter_button);
        filterbutton.setOnClickListener(v -> {
            int checked = radioGrp.getCheckedRadioButtonId();
            if(checked == -1){
                Toast.makeText(v.getContext(), "Choose year. Then click OK",Toast.LENGTH_SHORT).show();
            } else {
                RadioButton chosen = requireView().findViewById(checked);
                String year = (String) chosen.getText();
                mCallback.sendData(year);
            }
        });

        openbutton = view.findViewById(R.id.open_button);
        openbutton.setOnClickListener(v -> {
            Intent intent = new Intent((Activity)getContext(), StorageActivity.class);
            startActivity(intent);
        });
    }
}