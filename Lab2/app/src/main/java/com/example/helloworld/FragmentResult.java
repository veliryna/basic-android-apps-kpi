package com.example.helloworld;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

public class FragmentResult extends Fragment {
    TextView result;
    HashMap<String,String> bookYears;
    View viewResult;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewResult = view;
    }

    public void showResult(String year){
        bookYears = BooksAndYearsDataItems.getData();
        HashSet<String> books = getKeysByValue(bookYears, year);
        result = viewResult.findViewById(R.id.filtered_books);
        result.setText("");
        for(String book : books){
            result.append(book);
            result.append("\n");
        }
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
