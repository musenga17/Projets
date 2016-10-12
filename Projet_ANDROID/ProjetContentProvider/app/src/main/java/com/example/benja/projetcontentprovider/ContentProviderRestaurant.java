package com.example.benja.projetcontentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import java.util.List;


//Le fournisseur de contenu de l'application.
//A faire tourner comme une application normale, elle n'a juste pas d'activité.
//Elle contient la base de donnée de l'application
//Pour les explications se référer au TP6
public class ContentProviderRestaurant extends ContentProvider {
    private Base helper;
    private SQLiteDatabase bd;

    public static final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    public static final String AUTHORITY = "provider";
            //"com.example.benja.contentproviderrestaurant";

    public static final Uri CONTENT_URI_RESTO =
            Uri.parse("content://" + AUTHORITY + "/restaurant");
    public static final Uri CONTENT_URI_HORRAIRES =
            Uri.parse("content://" + AUTHORITY + "/horraires");
    public static final Uri CONTENT_URI_PHOTOS =
            Uri.parse("content://" + AUTHORITY + "/photos");

    private static final int RESTO_WITH_ID = 0; //Toute les informations de la table restaurant
    private static final int PARTIAL_INFOS = 1; //Les premières informations qui apparaissent dans lors de l'affichage de tous les restaurants
    private static final int INFO_WITH_CRITERE = 6;
    private static final int NB = 2;
    private static final int PHOTOS_WITH_ID = 3;
    private static final int HORRAIRES_WITH_ID = 4;
    private static final int MODIF = 5;
    private static final int RESTO = 100;
    private static final int HORRAIRES = 200;
    private static final int PHOTOS = 300;
    private static final int PHOTOS_WITH_2_ID= 400;

    //Chaque URI va être ici associé à un flag (entier)
    static{
        matcher.addURI(AUTHORITY,"restaurant",RESTO);
        matcher.addURI(AUTHORITY,"restaurant/#",RESTO_WITH_ID);
        matcher.addURI(AUTHORITY,"restaurant/nbresto",NB);
        matcher.addURI(AUTHORITY,"restaurant/partial_infos",PARTIAL_INFOS);
        matcher.addURI(AUTHORITY,"restaurant/*",INFO_WITH_CRITERE);
        matcher.addURI(AUTHORITY,"photos",PHOTOS);
        matcher.addURI(AUTHORITY,"photos/#",PHOTOS_WITH_ID);
        matcher.addURI(AUTHORITY,"photos/#/#",PHOTOS_WITH_2_ID);
        matcher.addURI(AUTHORITY, "horraires", HORRAIRES);
        matcher.addURI(AUTHORITY,"horraires/#",HORRAIRES_WITH_ID);
    }

    public ContentProviderRestaurant() {
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    //Comme chaque URI est associée à un flag on va pouvoir faire du cas par cas et effectué un traitement spécifique à chacune d'entre elles
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        bd = helper.getWritableDatabase();
        long id;
        switch (matcher.match(uri)) {
            case RESTO:
                id = bd.insert(Base.TABLE_RESTO, null, values);
                return ContentUris.withAppendedId(CONTENT_URI_RESTO,id);
            case PHOTOS:
                id = bd.insert(Base.TABLE_PHOTOS, null, values);
                return ContentUris.withAppendedId(CONTENT_URI_PHOTOS,id);
            case HORRAIRES:
                id = bd.insert(Base.TABLE_HORRAIRES, null, values);
                return ContentUris.withAppendedId(CONTENT_URI_HORRAIRES,id);
            default:
                return null;
        }
    }

    @Override
    public boolean onCreate() {
        try{
            helper = new Base(getContext());
        }catch(Exception e){
            return false;
        }
        return true;
    }

    //Ici l'idée est la même que pour insert mais on fait des demandes, pas des insertions
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        bd = helper.getReadableDatabase();
        Cursor cursor = null;
        switch (matcher.match(uri)) {
            case RESTO_WITH_ID:
                String path = uri.getLastPathSegment();
                cursor = bd.query(Base.TABLE_RESTO,
                        new String[]{
                                Base.NOM_RESTO,
                                Base.ADRESSE_RESTO,
                                Base.NUMERO_RESTO,
                                Base.ADRESSE_WEB,
                                Base.NOTE_RESTO,
                                Base.COUT_MOYEN,
                                Base.TYPE_CUISINE,
                                Base.PATH_PHOTO,
                                Base.COORDONNEES_GPS,
                                Base.URL_MENU
                        },Base.ID_RESTO + " = " + path
                        , null, null, null, null);
                break;
            case INFO_WITH_CRITERE:
                path = uri.getLastPathSegment();
                cursor = bd.query(Base.TABLE_RESTO,
                        new String[]{
                                Base.PATH_PHOTO,
                                Base.NOM_RESTO,
                                Base.NOTE_RESTO,
                                Base.TYPE_CUISINE,
                                "rowid as _id"
                        },selection,selectionArgs,null,null,null);
                break;
            case NB :
                cursor = bd.query(Base.TABLE_RESTO,
                        new String[]{Base.ID_RESTO},
                        null,null,null,null,null);
                break;
            case PARTIAL_INFOS:
                cursor = bd.query(Base.TABLE_RESTO,
                        new String[]{
                                Base.PATH_PHOTO,
                                Base.NOM_RESTO,
                                Base.NOTE_RESTO,
                                Base.TYPE_CUISINE,
                                "rowid as _id"
                        },
                        null, null, null, null, null);
                break;
            case PHOTOS_WITH_ID:
                path = uri.getLastPathSegment();
                cursor = bd.query(Base.TABLE_PHOTOS,
                        new String[]{Base.PATH_PHOTO},
                        Base.ID_RESTO + " = " + path,
                        null, null, null, null);
                break;
            case PHOTOS_WITH_2_ID :
                List<String> listpath = uri.getPathSegments();
                cursor = bd.query(Base.TABLE_PHOTOS,
                        new String[]{Base.ID_PHOTO,Base.PATH_PHOTO},
                        Base.ID_RESTO + " = " + listpath.get(1) + " and " + Base.ID_PHOTO + " = " + listpath.get(2),
                        null,null,null,null);
                break;
            case HORRAIRES_WITH_ID:
                path = uri.getLastPathSegment();
                cursor = bd.query(Base.TABLE_HORRAIRES,
                        new String[]{Base.JOUR, Base.HORRAIRE},
                        Base.ID_RESTO + " = " + path,null, null, null, null);
                break;
            default:
                return null;
        }
        return cursor;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs){
        bd = helper.getWritableDatabase();
        switch(matcher.match(uri)) {
            case RESTO:
                return bd.delete(Base.TABLE_RESTO, selection, selectionArgs);
            case HORRAIRES:
                return bd.delete(Base.TABLE_HORRAIRES, selection, selectionArgs);
            case PHOTOS:
                return bd.delete(Base.TABLE_PHOTOS, selection, selectionArgs);
            default:
                return -1;
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        bd = helper.getWritableDatabase();
        switch(matcher.match(uri)) {
            case RESTO:
                return bd.update(Base.TABLE_RESTO, values, selection, selectionArgs);
            case HORRAIRES:
                return bd.update(Base.TABLE_HORRAIRES, values, selection, selectionArgs);
            case PHOTOS:
                return bd.update(Base.TABLE_PHOTOS, values, selection, selectionArgs);
            default:
                return -1;
        }
    }
}
