package com.example.helloworld;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BooksAndYearsDataItems {
    public static HashMap<String,String> getData() {
        HashMap<String,String> expandableListDetail = new HashMap<>();

        List<String> books = new ArrayList<>();
        books.add("The Great Gatsby by F. Scott Fitzgerald");
        books.add("Mrs. Dalloway by Virginia Woolf");
        books.add("To Kill a Mockingbird by Harper Lee");
        books.add("One Hundred Years of Solitude by Gabriel Garcia Marquez");
        books.add("Beloved by Toni Morrison");
        books.add("The Silence of the Lambs by Thomas Harris");
        books.add("The Road by Cormac McCarthy");
        books.add("The Hunger Games by Suzanne Collins");
        books.add("The Help by Kathryn Stockett");
        books.add("Gone Girl by Gillian Flynn");

        List<String> years = new ArrayList<>();
        years.add("1925");
        years.add("1925");
        years.add("1960");
        years.add("1967");
        years.add("1988");
        years.add("1988");
        years.add("2006");
        years.add("2008");
        years.add("2008");
        years.add("2012");

        for(int i = 0; i < books.size(); i++){
            expandableListDetail.put(books.get(i), years.get(i));
        }
        return expandableListDetail;
    }
}
