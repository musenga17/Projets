package com.example.benja.projet;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class RechercheResto extends AppCompatActivity {
    Spinner spinner;
    EditText critere;
    String str_critere;
    Button rechercher;
    ListView listView;
    ListeRestoCursorAdapter listAdapter;
    AccessBase bd = new AccessBase();

    public static final int INFO_RESTO_REQUEST = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_resto);
        spinner = (Spinner)findViewById(R.id.spinner2);
        critere = (EditText)findViewById(R.id.EditText);
        rechercher = (Button)findViewById(R.id.rechercher);
        listView = (ListView)findViewById(R.id.listView2);
        ArrayAdapter<CharSequence> adapter
                = ArrayAdapter.createFromResource(this,R.array.criteres,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_critere = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                str_critere = parent.getItemAtPosition(0).toString();
            }
        });

        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (critere.getText().toString().equals("")) {
                    Toast.makeText(RechercheResto.this, "critère absent", Toast.LENGTH_SHORT).show();
                } else {
                    Cursor c = bd.listeWithCritere(getContentResolver(), str_critere, critere.getText().toString());
                    listAdapter = new ListeRestoCursorAdapter(RechercheResto.this, c, 0);
                    listView.setAdapter(listAdapter);
                }
            }
        });

        final Intent i1 = new Intent(RechercheResto.this,InfosResto.class);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                i1.putExtra("numero_resto", Integer.toString((int) listAdapter.getItemId(position)));
                startActivityForResult(i1, INFO_RESTO_REQUEST);
            }
        });
    }


    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if (resultCode == RESULT_OK) {
            Cursor c = bd.listeRestaurant(getContentResolver());
            listAdapter = new ListeRestoCursorAdapter(this, c, 0);
            listView.setAdapter(listAdapter);
        }
    }
}
