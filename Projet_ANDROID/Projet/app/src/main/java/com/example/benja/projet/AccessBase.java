package com.example.benja.projet;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


//Cette classe est comme une interface entre le fournisseur et l'appli
public class AccessBase {

    private static final String AUTHORITY = "provider";
            //"com.example.benja.contentproviderrestaurant";

    public static final Uri CONTENT_URI_RESTO =
            Uri.parse("content://" + AUTHORITY + "/restaurant");
    public static final Uri CONTENT_URI_HORRAIRES =
            Uri.parse("content://" + AUTHORITY + "/horraires");
    public static final Uri CONTENT_URI_PHOTOS =
            Uri.parse("content://" + AUTHORITY + "/photos");

    public void ajouterRestaurant(ContentResolver resolver,Restaurant r){
        String[] tab = r.getInfos();
        Map<String,String> map_horraire = r.getHorraires();
        Set<String> set_horraire = map_horraire.keySet();
        ContentValues[] row = new ContentValues[3];
        //row[0] est le contentValue que l'on va utiliser pour inserer dans la table resto
        row[0] = new ContentValues();
        row[0].put("_ID", r.getId_resto());
        row[0].put("nom_resto", tab[0]);
        row[0].put("adresse_resto", tab[1]);
        row[0].put("numero_resto", tab[2]);
        row[0].put("adresse_web", tab[3]);
        row[0].put("note_resto", r.getNote());
        row[0].put("cout_moyen", r.getCout());
        row[0].put("type_cuisine", tab[4]);
        row[0].put("path", tab[5]);
        row[0].put("coordonnees_gps", tab[6]);
        row[0].put("url_menu",tab[7]);
        resolver.insert(CONTENT_URI_RESTO, row[0]);
        //row[1] est le contentValue que l'on va utiliser pour inserer dans la table horaire
        row[1] = new ContentValues();
        for (String str : set_horraire) {
            row[1].put("_ID", r.getId_resto());
            row[1].put("jour", str);
            row[1].put("horraire", map_horraire.get(str));
            resolver.insert(CONTENT_URI_HORRAIRES, row[1]);
        }
        //row[2] est le contentValue que l'on va utiliser pour inserer dans la table photos
        row[2] = new ContentValues();
        row[2].put("_ID", r.getId_resto());
        row[2].put("id_photo",1);
        row[2].put("path",r.getPhoto1());
        resolver.insert(CONTENT_URI_PHOTOS,row[2]);
        row[2].put("_ID", r.getId_resto());
        row[2].put("id_photo",2);
        row[2].put("path",r.getPhoto2());
        resolver.insert(CONTENT_URI_PHOTOS,row[2]);
        row[2].put("_ID", r.getId_resto());
        row[2].put("id_photo",3);
        row[2].put("path",r.getPhoto3());
        resolver.insert(CONTENT_URI_PHOTOS,row[2]);
    }

    public void ajouterPhotoPrincipale(ContentResolver resolver, String path,int id) {
        ContentValues row = new ContentValues();
        row.put("path",path);
        Cursor c = resolver.query(Uri.withAppendedPath(CONTENT_URI_RESTO,"/"+id),new String[]{"path"},"_ID = ?",new String[]{""+id},null,null);
        if(c.getCount()==0){
            resolver.insert(CONTENT_URI_RESTO,row);
        }else{
            resolver.update(CONTENT_URI_RESTO,row,"_ID = ?",new String[]{""+id});
        }
    }

    //
    public void modifString(ContentResolver resolver, String champs, String info, int id){
        ContentValues row = new ContentValues();
        row.put(champs, info);
        resolver.update(CONTENT_URI_RESTO,row,"_ID = ?",new String[]{""+id});
    }

    public void modifDouble(ContentResolver resolver, String champs, Double info, int id){
        ContentValues row = new ContentValues();
        row.put(champs,info);
        resolver.update(CONTENT_URI_RESTO, row, "_ID = ?", new String[]{"" + id});
    }

    public void modifHorraire(ContentResolver resolver, String jour, String horraire, int id){
        ContentValues row = new ContentValues();
        row.put("jour",jour);
        row.put("horraire",horraire);
        resolver.update(CONTENT_URI_HORRAIRES, row, "_ID = ? and jour = ?", new String[]{"" + id, "" + jour});
    }

    public void modifPhoto(ContentResolver resolver, String photo, int id_photo, int id_resto){
        ContentValues row = new ContentValues();
        row.put("id_photo",id_photo);
        row.put("path",photo);
        Cursor c = photoRestoWithId(resolver,id_photo,id_resto);
        if (c.getCount()==0){
            resolver.insert(CONTENT_URI_PHOTOS,row);
        }else {
            resolver.update(CONTENT_URI_PHOTOS, row, "_ID = ? and id_photo = ?", new String[]{"" + id_resto, "" + id_photo});
        }
    }

    public void init(ContentResolver resolver) {
        if(getNbResto(resolver)==0) {
            String begin_path = "/storage/emulated/0/Pictures/PhotosProjet/";
            Restaurant resto1;
            Restaurant resto2;
            String[] infos = new String[]{"Restaurant Bon", "25 Rue de la Pompe, 75116 Paris", "0140727000", "restaurantbonparis.fr", "asiatique", begin_path + "restaurantBon.jpg", "48.86015,2.27491","http://www.restaurantbon.fr/fr/restaurantbon_lassiette_midi.php"};
            double note = 4.3;
            double cout = 8.9;
            Map<String, String> horraires = new HashMap<>();
            horraires.put("lundi", "10:00-19:00");
            horraires.put("mardi", "10:00-19:00");
            horraires.put("mercredi", "10:00-19:00");
            horraires.put("jeudi", "10:00-19:00");
            horraires.put("vendredi", "10:00-19:00");
            horraires.put("samedi", "10:00-19:00");
            horraires.put("dimanche", "10:00-19:00");
            resto1 = new Restaurant(1, infos, horraires, note, cout);
            resto1.setPhoto1(begin_path + "restaurantBon1.jpg");
            resto1.setPhoto2(begin_path + "restaurantBon2.jpg");
            resto1.setPhoto3(begin_path + "restaurantBon3.jpg");
            ajouterRestaurant(resolver, resto1);
            infos = new String[]{"La Crète", "85 Rue Mouffetard, 75005 Paris", "0143313047", "", "Grec", "", "48.841814,2.349861",""};
            note = 4.5;
            cout = 25;
            horraires = new HashMap<>();
            horraires.put("lundi", "12:00-00:00");
            horraires.put("mardi", "12:00-00:00");
            horraires.put("mercredi", "12:00-00:00");
            resto2 = new Restaurant(2, infos, horraires, note, cout);
            ajouterRestaurant(resolver, resto2);
        }
    }

    public int getNbResto(ContentResolver resolver) {
        int n = 0;
        Cursor c = resolver.query(Uri.withAppendedPath(CONTENT_URI_RESTO,"/nbresto"),new String[]{"_ID"},null,null,null,null);
        while(c.moveToNext()) n = c.getInt(0);
        return n;
    }

    public Cursor listeRestaurant(ContentResolver resolver){
        Cursor c = resolver.query(Uri.withAppendedPath(CONTENT_URI_RESTO,"/partial_infos"),new String[]{"path","nom_resto","note_resto","type_cuisine"},null,null,null,null);
        return c;
    }

    public Cursor listeWithCritere(ContentResolver resolver,String critere, String value){
        Cursor c = null;
        if(critere.equals("nom"))
            c = resolver.query(Uri.withAppendedPath(CONTENT_URI_RESTO,"/"+critere),new String[]{"path","nom_resto","note_resto","type_cuisine"},"nom_resto = ?",new String[]{""+value},null,null);
        if(critere.equals("cuisine"))
            c = resolver.query(Uri.withAppendedPath(CONTENT_URI_RESTO,"/"+critere),new String[]{"path","nom_resto","note_resto","type_cuisine"},"type_cuisine = ?",new String[]{""+value},null,null);
        if(critere.equals("note"))
            c = resolver.query(Uri.withAppendedPath(CONTENT_URI_RESTO, "/" + critere), new String[]{"path", "nom_resto", "note_resto", "type_cuisine"},"note_resto >=  ?",new String[]{""+value},null,null);
        if(critere.equals("cout"))
            c = resolver.query(Uri.withAppendedPath(CONTENT_URI_RESTO, "/" + critere), new String[]{"path", "nom_resto", "note_resto", "type_cuisine"},"cout_moyen >= ?",new String[]{""+value},null,null);
        return c;
    }

    public Cursor infosResto(ContentResolver resolver,int id_resto){
        Cursor c = resolver.query(Uri.withAppendedPath(CONTENT_URI_RESTO,"/"+id_resto),new String[]{"nom_resto","adresse_resto","numero_resto","adresse_web","note_resto","cout_moyen","type_cuisine","coordonnees_gps","url_menu"},"_ID = ?",new String[]{""+id_resto},null);
        return c;
    }

    public Cursor horrairesResto(ContentResolver resolver, int id_resto){
        Cursor c = resolver.query(Uri.withAppendedPath(CONTENT_URI_HORRAIRES,"/" + id_resto),new String[]{"jour","horraire"},"_ID = ?",new String[]{""+id_resto},null);
        return c;
    }

    public Cursor photosResto(ContentResolver resolver, int id_resto){
        Cursor c = resolver.query(Uri.withAppendedPath(CONTENT_URI_PHOTOS,"/" + id_resto),new String[]{"path"},"_ID = ?",new String[]{""+id_resto},null);
        return c;
    }

    public Cursor photoRestoWithId(ContentResolver resolver, int id_resto, int id_photo){
        Cursor c = resolver.query(Uri.withAppendedPath(CONTENT_URI_PHOTOS,"/" + id_resto + "/" + id_photo), new String[]{"id_photo","path"},"_ID = ? and id_photo = ?",new String[]{""+id_resto,""+id_photo},null);
        return c;
    }

    public int supprimerRestaurant(ContentResolver resolver,int id){
        return resolver.delete(CONTENT_URI_RESTO,"_ID = ?",new String[]{""+id});
    }

    public int supprimerHorraire(ContentResolver resolver, int id){
        return resolver.delete(CONTENT_URI_HORRAIRES,"_ID = ?",new String[]{""+id});
    }

    public int supprimerPhotos(ContentResolver resolver,int id){
        return resolver.delete(CONTENT_URI_PHOTOS,"_ID = ?",new String[]{""+id});
    }
}
