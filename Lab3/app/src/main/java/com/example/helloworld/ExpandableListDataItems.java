package com.example.helloworld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataItems {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableDetailList = new HashMap<>();

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

        expandableDetailList.put("LIST OF BOOKS", books);
        return expandableDetailList;
    }
}

