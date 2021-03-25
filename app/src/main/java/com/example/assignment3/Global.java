package com.example.assignment3;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Global {
    private List<String> booknm;
    private List<String> authornm;

    public Global(){
        booknm = new ArrayList<String>();
        authornm = new ArrayList<String>();
    }

    public List<String> getBooknm() {
        Log.d("msg",booknm + " get");
        return booknm;
    }

    public void setBooknm(String book) {
        booknm.add(book);
        Log.d("msg",booknm.toString());
    }

    public List<String> getAuthornm() {
        Log.d("msg",authornm + " get");
        return authornm;
    }

    public void setAuthornm(String author) {
        authornm.add(author);
        Log.d("msg",authornm.toString());
    }
}
