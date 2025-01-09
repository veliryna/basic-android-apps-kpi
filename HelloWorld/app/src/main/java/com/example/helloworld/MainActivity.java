package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    ExpandableListView bookList;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableTitleList;
    HashMap<String, List<String>> expandableDetailList;
    RadioGroup radioGrp;
    Button filterbutton;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // creating and populating list of books
        bookList = findViewById(R.id.expandableListViewOfBooks);
        expandableDetailList = ExpandableListDataItems.getData();
        expandableTitleList = new ArrayList<>(expandableDetailList.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableTitleList, expandableDetailList);
        bookList.setAdapter(expandableListAdapter);


        // creating and populating radio buttons with year labels
        radioGrp = findViewById(R.id.radio_group);
        HashMap<String,String> bookYears = BooksAndYearsDataItems.getData();
        ArrayList<String> year_list = new ArrayList<>(bookYears.values());
        Collections.sort(year_list);
        LinkedHashSet<String> years = new LinkedHashSet<>(year_list);
        int i = 1;
        for (String year : years) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(year);
            radioButton.setId(i);
            radioGrp.addView(radioButton);
            i++;
        }

        //filtering books by year and displaying results in text view
        filterbutton = findViewById(R.id.filter_button);
        filterbutton.setOnClickListener(v -> {
            int checked = radioGrp.getCheckedRadioButtonId();
            if(checked == -1){
                Toast.makeText(getApplicationContext(),"Choose year. Then click OK",Toast.LENGTH_SHORT).show();
            } else {
                RadioButton chosen = findViewById(checked);
                String year = (String) chosen.getText();
                HashSet<String> books = getKeysByValue(bookYears, year);
                result = findViewById(R.id.filtered_books);
                result.setText("");
                for(String book : books){
                    result.append(book);
                    result.append("\n");
                }
            }
        });
    }

    public static <T, E> HashSet<T> getKeysByValue(HashMap<T, E> map, E value) {
        HashSet<T> keys = new HashSet<>();
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

}