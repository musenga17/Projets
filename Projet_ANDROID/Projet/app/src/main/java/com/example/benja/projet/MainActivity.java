package com.example.benja.projet;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button ajouter;
    Button rechercher;
    ListeRestoCursorAdapter adapter = null;
    ContentResolver client = null;
    AccessBase accessBase = null;
    Cursor c = null;
    ListView listView = null;

    public static final int INFO_RESTO_REQUEST = 0;
    public static final int AJOUT_RESTO_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ajouter = (Button)findViewById(R.id.ajouter);
        rechercher = (Button)findViewById(R.id.recherche);
        client = getContentResolver();
        accessBase = new AccessBase();
        accessBase.init(client);
        c = accessBase.listeRestaurant(client);
        adapter = new ListeRestoCursorAdapter(this,c,0);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        final Intent i1 = new Intent(MainActivity.this,InfosResto.class);
        final Intent i2 = new Intent(MainActivity.this,AjoutResto.class);
        final Intent i3 = new Intent(MainActivity.this,RechercheResto.class);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                i1.putExtra("numero_resto",Integer.toString((int)adapter.getItemId(position)));
                startActivityForResult(i1, INFO_RESTO_REQUEST);
            }
        });
        System.out.println(ajouter);
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(i2, AJOUT_RESTO_REQUEST);
            }
        });

        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i3);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if (resultCode == RESULT_OK) {
            c = accessBase.listeRestaurant(client);
            adapter = new ListeRestoCursorAdapter(this, c, 0);
            listView.setAdapter(adapter);
        }
    }

    public static Bitmap fileToBitmap(String file){
        FileInputStream fis = null;
        Bitmap bmp = null;
        try {
            fis = new FileInputStream(new File(file));
            bmp = BitmapFactory.decodeStream(fis);
        } catch(IOException e){
            e.printStackTrace();
        }
        return bmp;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
