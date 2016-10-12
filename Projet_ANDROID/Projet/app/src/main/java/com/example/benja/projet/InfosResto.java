package com.example.benja.projet;

import android.Manifest;
import android.Manifest.permission;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class InfosResto extends AppCompatActivity {
    ImageView[] photos;
    TextView nom, adresse, note, cout, cuisine;
    Button site = null;
    Button googleMap = null;
    Button telephone = null;
    TextView[] jours;
    TextView[] horraires;
    Button modifier = null;
    Button delete = null;
    Button retour = null;
    Button menu = null;
    Button ajouterContact = null;
    Button ajoutEven = null;
    Button inviterContact = null;
    final AccessBase bd = new AccessBase();
    Intent i;
    int id;
    Cursor c1, c2, c3;
    String url_site, numero, gps, url_menu;
    Calendar cal;


    public static final String NOTE = "note";

    private void remplir(int id) {//accède à la base de données et remplir les infos du restaurant (dans l'interface graphique)
        int k = 0;
        c1 = bd.infosResto(getContentResolver(), id);
        System.out.println(c1.getColumnCount());
        c1.moveToNext();
        nom.setText(" : " + c1.getString(0));
        adresse.setText(" : " + c1.getString(1));
        numero = c1.getString(2);
        url_site = c1.getString(3);
        note.setText(" : " + Double.toString(c1.getDouble(4)));
        cout.setText(" : " + Double.toString(c1.getDouble(5)));
        cuisine.setText(" : " + c1.getString(6));
        gps = c1.getString(8);
        url_menu = c1.getString(9);
        c2 = bd.horrairesResto(getContentResolver(), id);
        while (c2.moveToNext()) {
            jours[k].setText(c2.getString(0) + " : ");
            horraires[k].setText(c2.getString(1));
            k++;
        }
        k = 0;
        c3 = bd.photosResto(getContentResolver(), id);
        while (c3.moveToNext()) {
            if(c3.getString(0) != null)
                photos[k].setImageBitmap(MainActivity.fileToBitmap(c3.getString(0)));
            k++;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infos_resto);

        cal = Calendar.getInstance();//récupération du calendrier

        modifier = (Button) findViewById(R.id.modifier);
        site = (Button) findViewById(R.id.siteWeb);
        googleMap = (Button) findViewById(R.id.googleMap);
        telephone = (Button) findViewById(R.id.telephone);
        delete = (Button) findViewById(R.id.delete);
        photos = new ImageView[4];
        jours = new TextView[7];
        horraires = new TextView[7];
        photos[0] = (ImageView) findViewById(R.id.imageView2);
        photos[1] = (ImageView) findViewById(R.id.imageView3);
        photos[2] = (ImageView) findViewById(R.id.imageView4);
        nom = (TextView) findViewById(R.id.nom);
        adresse = (TextView) findViewById(R.id.adresse);
        site = (Button) findViewById(R.id.siteWeb);
        menu = (Button) findViewById(R.id.menu);
        note = (TextView) findViewById(R.id.note);
        cout = (TextView) findViewById(R.id.cout);
        cuisine = (TextView) findViewById(R.id.cuisine);
        jours[0] = (TextView) findViewById(R.id.jour1);
        jours[1] = (TextView) findViewById(R.id.jour2);
        jours[2] = (TextView) findViewById(R.id.jour3);
        jours[3] = (TextView) findViewById(R.id.jour4);
        jours[4] = (TextView) findViewById(R.id.jour5);
        jours[5] = (TextView) findViewById(R.id.jour6);
        jours[6] = (TextView) findViewById(R.id.jour7);
        horraires[0] = (TextView) findViewById(R.id.horraire1);
        horraires[1] = (TextView) findViewById(R.id.horraire2);
        horraires[2] = (TextView) findViewById(R.id.horraire3);
        horraires[3] = (TextView) findViewById(R.id.horraire4);
        horraires[4] = (TextView) findViewById(R.id.horraire5);
        horraires[5] = (TextView) findViewById(R.id.horraire6);
        horraires[6] = (TextView) findViewById(R.id.horraire7);
        retour = (Button) findViewById(R.id.retour);
        retour.setEnabled(false);
        ajouterContact = (Button) findViewById(R.id.ajoutContact);
        ajoutEven = (Button) findViewById(R.id.ajoutEvenement);
        inviterContact = (Button) findViewById(R.id.invitContact);
        i = getIntent();
        id = Integer.parseInt(i.getStringExtra("numero_resto"));
        remplir(id);
        if (url_site.equals("")) { //si il n'existe pas de site pour le restaurant
            site.setEnabled(false); //on désactive le bouton du site web
        }
        if (url_menu.equals("")) {
            menu.setEnabled(false);
        }
        final Intent i = new Intent(InfosResto.this, ModifResto.class);

        site.setOnClickListener(new View.OnClickListener() {//bouton GOOGLE MAP
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://" + url_site));
                startActivity(i);
            }
        });

        googleMap.setOnClickListener(new View.OnClickListener() {//bouton GOOGLE MAP
            @Override
            public void onClick(View v) {
                String coordGeographique = gps; //on récupère le string contenant latitude,longitude
                String adresseMap = c1.getString(1);//on récupère le string contenant l'adresse
                Uri uri = Uri.parse("geo:" + coordGeographique + "?q=" + Uri.encode(adresseMap));
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                i.setPackage("com.google.android.apps.maps"); //spécifier le package de cette intent
                startActivity(i); //démarrer ce nouvel intent => démarrage de Google Map pour le lieu "1st & Pike, Seattle"
            }
        });

        telephone.setOnClickListener(new View.OnClickListener() {//lorsque je clique sur le bouton téléphoner
            @Override
            public void onClick(View v) {
                String appel = "tel:0" + numero;
                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse(appel));
                startActivity(i);
            }
        });

        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.putExtra("id_resto", Integer.toString(id));
                startActivityForResult(i, 0);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bd.supprimerRestaurant(getContentResolver(), id);
                bd.supprimerHorraire(getContentResolver(), id);
                bd.supprimerPhotos(getContentResolver(), id);
                setResult(RESULT_OK, null);
                finish();
            }
        });

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK,i);
                finish();
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InfosResto.this,MenuResto.class);
                i.putExtra("menu",url_menu);
                startActivity(i);
            }
        });

        ajouterContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomResto = c1.getString(0);
                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setType(ContactsContract.Contacts.CONTENT_TYPE);

                //Pré-remplir les champs "NOM" et "N° de téléphone"
                intent.putExtra(ContactsContract.Intents.Insert.NAME, nomResto);
                intent.putExtra(ContactsContract.Intents.Insert.PHONE, "0" + numero);

                startActivity(intent);
            }
        });

        ajoutEven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomResto = c1.getString(0);
                String lieuEvenement = c1.getString(1);
                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setType("vnd.android.cursor.item/event");
                intent.putExtra(CalendarContract.Events.TITLE, "Invitation au restaurant : " + nomResto);
                intent.putExtra(CalendarContract.Events.EVENT_LOCATION, lieuEvenement);
                intent.putExtra(CalendarContract.Events.DESCRIPTION, "Invitation");

                 // date du jour
                int annee = cal.get(Calendar.YEAR);
                int mois = cal.get(Calendar.MONTH);
                int jour =  cal.get(Calendar.DAY_OF_MONTH);

                // Modification de la date
                GregorianCalendar calDate = new GregorianCalendar(annee, mois, jour);
                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, calDate.getTimeInMillis());
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, calDate.getTimeInMillis());

                // faire un événement d'une journée complète
                intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

                startActivity(intent);

            }
        });

        inviterContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
                pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE); // Montrer seulement les contacts avec numéro de téléphone
                startActivityForResult(pickContactIntent, 1001);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == RESULT_OK) {
            remplir(id);
            retour.setEnabled(true);
        }
        if (requestCode == 1001) {
            // Etre sur que la demande est un succès
            if (resultCode == Activity.RESULT_OK) {
                //Obtenir l'URI qui pointe vers le contact sélectionné
                Uri contactUri = data.getData();

                // On a juste besoin de la colonne du numéro "NUMBER" et de celle du nom du contact "DISPLAY_NAME"
                String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME};

                Cursor cursor = this.getContentResolver().query(contactUri, projection, null, null, null);
                cursor.moveToFirst();

                //récupération du jour,du mois et de l'année actuelle
                int annee = cal.get(Calendar.YEAR);
                int mois = cal.get(Calendar.MONTH)+1;
                int jour =  cal.get(Calendar.DAY_OF_MONTH);

                // Récupérer le numéro de téléphone de la colonne NUMBER
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                //Récupérer le nom du contact de la colonne DISPLAY_NAME
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

                /*String message = "Salut "+name+", je souhaite t'inviter dans ce restaurant : \n" +
                                 c1.getString(0)+"\n"+
                                 "basé à l'adresse suivante : \n" +
                                 c1.getString(1)+"\n" +
                                 "Si c'est possible, ça serait dans la journée du "+jour+"/"+mois+"/"+annee+"\n" +
                                 "Il est vraiment très bon :)";*/
                String message ="Salut "+name+".\n"+
                                "Je t'invite au restaurant : \n"+
                                c1.getString(0)+"\n"+
                                c1.getString(1)+"\n"+
                                "Si possible le "+jour+"/"+mois+"/"+annee+"\n"+
                                "Il est vraiment bon :)";

                Toast.makeText(this,"Choix du contact : "+name, Toast.LENGTH_LONG).show();
                Intent i = new Intent(this,EnvoiInvitation.class);
                i.putExtra("nomContact",name);
                i.putExtra("numeroContact",number);
                i.putExtra("messageInvitation",message);
                startActivity(i);
            }
        }

    }
}
