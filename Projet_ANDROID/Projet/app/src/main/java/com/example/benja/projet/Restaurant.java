package com.example.benja.projet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Restaurant {
    private int id_resto;
    private String[] infos = null;
    private String photo1, photo2, photo3;
    private Map<String,String> horraires;
    private double note;
    private double cout;

    public Restaurant(int id,String[] inf, Map<String,String> hor, double n, double c) {
        id_resto = id;
        infos = inf;
        horraires = hor;
        note = n;
        cout = c;
    }

    public int getId_resto(){return id_resto;}
    public String[] getInfos(){return infos;}
    public Map<String,String> getHorraires(){return horraires;}
    public double getNote(){return note;}
    public double getCout(){return cout;}
    public String getPhoto1(){return photo1;}
    public String getPhoto2(){return photo2;}
    public String getPhoto3(){return photo3;}
    public void setPhoto1(String p1){photo1=p1;}
    public void setPhoto2(String p2){photo2=p2;}
    public void setPhoto3(String p3){photo3=p3;}
}

