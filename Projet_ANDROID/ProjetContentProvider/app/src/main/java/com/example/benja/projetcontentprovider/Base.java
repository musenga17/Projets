package com.example.benja.projetcontentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.net.URL;


//Base de données de l'application
//Contient 3 Tables :
//  - Celle du restaurant
//  - Celle des horraires
//  - Celle qui enregistre les photos (on mettera des url vers des sites je pense)
public class Base extends SQLiteOpenHelper {
    public static final int VERSION = 1;

    public static final String BD_RESTAURANT = "bd_restaurant";

    public static final String TABLE_RESTO = "RESTAURANT";
    public static final String TABLE_HORRAIRES = "HORRAIRES";
    public static final String TABLE_PHOTOS = "PHOTOS";

    /*Colonnes de la table RESTAURANT*/
    public static final String ID_RESTO = "_ID";
    public static final String NOM_RESTO = "nom_resto";
    public static final String ADRESSE_RESTO = "adresse_resto";
    public static final String NUMERO_RESTO = "numero_resto";
    public static final String ADRESSE_WEB = "adresse_web";
    public static final String NOTE_RESTO = "note_resto";
    public static final String COUT_MOYEN = "cout_moyen";
    public static final String TYPE_CUISINE = "type_cuisine";
    public static final String PATH_PHOTO = "path";
    public static final String COORDONNEES_GPS = "coordonnees_gps";
    public static final String URL_MENU = "url_menu";

    /*Colonnes de la table HORRAIRES*/
    /* ID_RESTO déjà défini*/
    public static final String JOUR = "jour";
    public static final String HORRAIRE = "horraire";

    /*Colonnes de la table PHOTOS*/
   /*PATH_PHOTO est déjà défini*/
    /* ID_RESTO déjà défini */
    public static final String ID_PHOTO = "id_photo";

    /*Creation de la table RESTAURANT*/
    public static final String CREATE_TABLE_RESTO =
            " create table " + TABLE_RESTO + " ( " +
                    ID_RESTO + " integer primary key autoincrement, " +
                    NOM_RESTO + " string not null, " +
                    ADRESSE_RESTO + " string not null, " +
                    NUMERO_RESTO + " string not null, " +
                    ADRESSE_WEB + " string not null, " +
                    NOTE_RESTO + " float default 0, " +
                    COUT_MOYEN + " float, " +
                    TYPE_CUISINE + " string not null, " +
                    PATH_PHOTO + " string not null, "  +
                    COORDONNEES_GPS + " string not null, " +
                    URL_MENU + " string not null);";

    /*Creation de la table HORRAIRES*/
    public static final String CREATE_TABLE_HORRAIRES =
            " create table " + TABLE_HORRAIRES + " ( " +
                    ID_RESTO + " integer references " + TABLE_RESTO + ", " +
                    JOUR + " string not null, " +
                    HORRAIRE + " string not null);";

    /*Creation de la table PHOTOS */
    public static final String CREATE_TABLE_PHOTOS =
            " create table " + TABLE_PHOTOS + " ( " +
                    ID_RESTO + " integer references " + TABLE_RESTO + ", " +
                    ID_PHOTO + " integer, " +
                    PATH_PHOTO + " string);";

    public Base(Context context) {
        super(context, BD_RESTAURANT, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) { //On execute les créations de tables
        bd.execSQL(CREATE_TABLE_RESTO);
        bd.execSQL(CREATE_TABLE_HORRAIRES);
        bd.execSQL(CREATE_TABLE_PHOTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            bd.execSQL("drop table if exists " + TABLE_RESTO);
            bd.execSQL("drop table if exists " + TABLE_HORRAIRES);
            bd.execSQL("drop table if exists " + TABLE_PHOTOS);
            onCreate(bd);
        }
    }
}
