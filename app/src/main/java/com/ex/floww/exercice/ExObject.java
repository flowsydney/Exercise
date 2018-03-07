package com.ex.floww.exercice;

import com.ex.floww.exercice.Objects.Rows;
import com.ex.floww.exercice.Objects.Title;

import java.util.ArrayList;

/**
 * Created by Floww on 07/03/2018.
 */

class ExObject {
   private String title;
   private ArrayList<Rows> rows;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Rows> getRows() {
        return rows;
    }

    public void setRows(ArrayList<Rows> rows) {
        this.rows = rows;
    }
}
