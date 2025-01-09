package com.example.helloworld;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

public class FragmentResult extends Fragment {
    TextView result;
    HashMap<String,String> bookYears;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    public void showResult(String year){
        String filename = new Date().getTime() + "choice_of_books.txt";
        bookYears = BooksAndYearsDataItems.getData();
        HashSet<String> books = getKeysByValue(bookYears, year);
        result = requireView().findViewById(R.id.filtered_books);
        result.setText("");
        for(String book : books){
            result.append(book);
            result.append("\n");
        }
        File dir = requireContext().getFilesDir();
        try {
            File file = new File(dir, filename);
            FileWriter writer = new FileWriter(file);
            String text = result.getText().toString();
            writer.append(text);
            writer.flush();
            writer.close();
            Toast.makeText(requireContext(), "Your data was saved successfully.",Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            e.printStackTrace();
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
