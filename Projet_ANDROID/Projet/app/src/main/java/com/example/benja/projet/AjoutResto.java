package com.example.benja.projet;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class AjoutResto extends AppCompatActivity {

    //NOUBLIE d'ajouter dans le AndroidMAnifest.xml
    //uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"
    //uses-permission android:name="android.permission.INTERNET"
    Map<String,String > map;
    EditText[] list;
    Spinner spinner;
    Button ajout_resto;
    Button ok;
    Button pic;
    AccessBase bd = new AccessBase();
    String jour = null;
    String path_begin ="";
    static int numphoto = 0;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_resto);
        id = bd.getNbResto(getContentResolver()) + 1;
        map = new HashMap<>();
        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter
                = ArrayAdapter.createFromResource(this,R.array.semaine,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        list = new EditText[10];
        list[0] = (EditText) findViewById(R.id.nom);
        list[1] = (EditText) findViewById(R.id.adresse);
        list[2] = (EditText) findViewById(R.id.numero);
        list[3] = (EditText) findViewById(R.id.siteWeb);
        list[4] = (EditText) findViewById(R.id.note);
        list[5]  = (EditText) findViewById(R.id.cout);
        list[6] = (EditText) findViewById(R.id.cuisine);
        list[7] = (EditText) findViewById(R.id.photo);
        list[8] = (EditText) findViewById(R.id.jour);
        list[9] = (EditText) findViewById(R.id.menu);
        ajout_resto = (Button) findViewById(R.id.ajoutResto);
        ok = (Button) findViewById(R.id.ok);
        pic = (Button) findViewById(R.id.pic);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                jour = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                jour = parent.getItemAtPosition(0).toString();
            }
        });

        ajout_resto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emptieseditTexts(list)) {
                    Toast.makeText(AjoutResto.this, "Un des champs est vide", Toast.LENGTH_SHORT).show();
                } else {
                    ajouterResto();
                    setResult(RESULT_OK, null);
                    finish();
                }
            }
        });

        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (photoIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(photoIntent, 1234);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        Bundle extras = data.getExtras();
        Bitmap bmp = null;
        if (resultCode==RESULT_OK){
            bmp = (Bitmap)extras.get("data");
            String name = list[0].getText().toString()+(numphoto++)+".jpg";
            try {
                saveToInternalStorage(bmp,name);
            } catch (IOException e) {
                e.printStackTrace();
            }
            list[7].setText(name);
            bd.ajouterPhotoPrincipale(getContentResolver(),name,id);
        }
    }

    public void ok(View view){
        if (list[8].getText().toString().equals("")){
            Toast.makeText(this,"Entrez un horraire",Toast.LENGTH_SHORT).show();
        } else {
            map.put(jour, list[8].getText().toString());
        }
    }

    public void ajouterResto(){
        String gps = trouverGps(list[1].getText().toString()); //list[1] est l'adresse
        String[] info_resto = new String[9];
        info_resto[0] = list[0].getText().toString();
        info_resto[1] = list[1].getText().toString();
        info_resto[2] = list[2].getText().toString();
        info_resto[3] = list[3].getText().toString();
        info_resto[4] = list[6].getText().toString();
        info_resto[5] = "/storage/emulated/0/Pictures/PhotosProjet/" + list[7].getText().toString();
        info_resto[6] = list[8].getText().toString();
        info_resto[7] = list[9].getText().toString();
        /*for(int i=0;i<info_resto.length-1;i++){
            if(i==7) {
                System.out.print("photo");
                path_begin = "/storage/emulated/0/Pictures/PhotosProjet/";
            }
            info_resto[i]=path_begin+list[i].getText().toString();//on ajoute chaque info dans la liste info_resto
            System.out.println(info_resto[i]);
        }*/
        //info_resto[info_resto.length-1]=gps; //la derniere case sera le résultat de la fonction trouverGps();
        double note = Double.parseDouble(list[4].getText().toString());
        double cout = Double.parseDouble(list[5].getText().toString());
        Restaurant r = new Restaurant(id,info_resto,map,note,cout);
        bd.ajouterRestaurant(getContentResolver(),r); //ajouter le restaurant dans la base à partir du content provider
    }

    private String trouverGps(String adresse) { //trouver la position à partir d'une adresse
        //String adresse = list[1].getText().toString();
        String latLongString=null;
        try {
            Geocoder g = new Geocoder(this, Locale.FRANCE);
            List<Address> list_adress;


            list_adress = g.getFromLocationName(adresse, 5);
            if (list_adress == null) {
                System.out.println("NULL");
            }
            Address location = list_adress.get(0);
            if(location!=null) {
                double lat = location.getLatitude();
                double lng = location.getLongitude();
                String valeur_lat = String.valueOf(lat);
                String valeur_long = String.valueOf(lng);
                latLongString = valeur_lat+","+valeur_long;
            }
            /*else{
                Toast.makeText(this, "erreur adresse", Toast.LENGTH_LONG).show();
            }*/
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return latLongString;
    }

    private boolean emptieseditTexts(EditText[] tab){
        for(int i=0; i<tab.length; i++)
            if(tab[i].getText().toString().equals("") && (i != 3 && i != 9))
                return false;
        return true;
    }

    private void saveToInternalStorage(Bitmap bitmapImage,String namepic) throws IOException {
        File file=new File(new File("/storage/emulated/0/Pictures/PhotosProjet"),namepic);
        file.createNewFile();

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

