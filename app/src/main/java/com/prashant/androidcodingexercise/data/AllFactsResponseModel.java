package com.prashant.androidcodingexercise.data;

import java.util.ArrayList;

public class AllFactsResponseModel {
    private String title;
    private ArrayList<FactModel> rows;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<FactModel> getRows() {
        return rows;
    }

    public void setRows(ArrayList<FactModel> rows) {
        this.rows = rows;
    }
}
