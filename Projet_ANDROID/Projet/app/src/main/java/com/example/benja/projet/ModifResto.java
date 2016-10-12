package com.example.benja.projet;

import android.content.Intent;
import android.os.Bundle;
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

import java.lang.reflect.Array;

public class ModifResto extends AppCompatActivity {
    EditText[] editTexts = null;
    Spinner spinner_infos = null;
    Spinner spinner_horraires = null;
    Button valider = null;
    AccessBase bd = new AccessBase();
    Intent i = null;
    int id_resto;
    String info;
    String horraire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_resto);
        spinner_infos = (Spinner) findViewById(R.id.spinner_infos);
        spinner_horraires = (Spinner) findViewById(R.id.spinner_horraires);
        editTexts = new EditText[6];
        editTexts[0] = (EditText) findViewById(R.id.edit_infos);
        editTexts[1] = (EditText) findViewById(R.id.edit_horraires);
        editTexts[2] = (EditText) findViewById(R.id.edit_photo_principale);
        editTexts[3] = (EditText) findViewById(R.id.edit_photo_1);
        editTexts[4] = (EditText) findViewById(R.id.edit_photo_2);
        editTexts[5] = (EditText) findViewById(R.id.edit_photo_3);
        valider = (Button) findViewById(R.id.valider);
        i = getIntent();
        id_resto = Integer.parseInt(i.getStringExtra("id_resto"));
        ArrayAdapter<CharSequence> adapter_infos
                = ArrayAdapter.createFromResource(this,R.array.criteres,android.R.layout.simple_spinner_item);
        adapter_infos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_infos.setAdapter(adapter_infos);
        ArrayAdapter<CharSequence> adapter_horraires
                = ArrayAdapter.createFromResource(this,R.array.semaine,android.R.layout.simple_spinner_item);
        adapter_horraires.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_horraires.setAdapter(adapter_horraires);

        spinner_infos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                info = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                info = parent.getItemAtPosition(0).toString();
            }
        });

        spinner_horraires.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                horraire = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                horraire = info = parent.getItemAtPosition(0).toString();
            }
        });

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inf = editTexts[0].getText().toString();
                String hor = editTexts[1].getText().toString();
                String begin_path = "/storage/emulated/0/Pictures/PhotosProjet/";
                String photo_principale = editTexts[2].getText().toString();
                String photo1 = editTexts[3].getText().toString();
                String photo2 = editTexts[4].getText().toString();
                String photo3 = editTexts[5].getText().toString();
                if (!inf.equals("")) {
                    if (info.equals("nom")) bd.modifString(getContentResolver(),"nom_resto",inf,id_resto);
                    if (info.equals("cuisine")) bd.modifString(getContentResolver(),"type_cuisine",inf,id_resto);
                    if (info.equals("note")) bd.modifDouble(getContentResolver(), "note_resto", Double.parseDouble(inf), id_resto);
                    if (info.equals("cout")) bd.modifDouble(getContentResolver(), "cout_moyen", Double.parseDouble(inf), id_resto);
                    if (info.equals("numero")) bd.modifDouble(getContentResolver(), "numero_resto", Double.parseDouble(inf), id_resto);
                }
                if (!hor.equals("")){
                    bd.modifHorraire(getContentResolver(),horraire,hor,id_resto);
                }
                if (!photo_principale.equals("")){
                    bd.modifString(getContentResolver(), "path", begin_path + photo_principale, id_resto);
                }
                if (!photo1.equals("")){
                    System.out.println(begin_path+photo1);
                    bd.modifPhoto(getContentResolver(), begin_path + photo1, 1,id_resto);
                }
                if (!photo2.equals("")){
                    bd.modifPhoto(getContentResolver(), begin_path + photo2, 2,id_resto);
                }
                if (!photo3.equals("")){
                    bd.modifPhoto(getContentResolver(),begin_path + photo3,3,id_resto);
                }
                setResult(RESULT_OK,i);
                finish();
            }
        });
    }
}
