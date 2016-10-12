package com.example.benja.projet;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by benja on 02/12/2015.
 */

//Pour mettre des choses dans ma listView j'ai besoin d'autre chose qu'un simple adapter
//En effet un item dans la liste n'est pas un simple TextView mais un Layout contenant un photo et un autre layout content des textViews
//On crée donc un adapter personnalisé qui va être adéquat à notre problème
public class ListeRestoCursorAdapter extends CursorAdapter {
    private LayoutInflater cursorInflater;

    public ListeRestoCursorAdapter(Context context, Cursor c, int flags){
        super(context, c, flags);
        cursorInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void bindView(View view, Context context, Cursor cursor){
        ImageView view_photo = (ImageView)view.findViewById(R.id.imageView);
        TextView view_nom = (TextView)view.findViewById(R.id.nom);
        TextView view_note = (TextView)view.findViewById(R.id.note);
        TextView view_type = (TextView)view.findViewById(R.id.type_cuisine);
        Bitmap bmp = MainActivity.fileToBitmap(cursor.getString(cursor.getColumnIndex("path")));
        String nom = cursor.getString(cursor.getColumnIndex("nom_resto"));
        float note = cursor.getFloat(cursor.getColumnIndex("note_resto"));
        String type = cursor.getString(cursor.getColumnIndex("type_cuisine"));
        view_photo.setImageBitmap(bmp);
        view_nom.setText(nom);
        view_note.setText(Float.toString(note));
        view_type.setText(type);
    }

    public View newView(Context context, Cursor cursor, ViewGroup parent){
        return cursorInflater.inflate(R.layout.resto_item,parent,false);
    }

}
