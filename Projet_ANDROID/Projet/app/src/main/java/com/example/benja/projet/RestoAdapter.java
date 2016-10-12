/*package com.example.benja.projet;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


//Pas la peine de t'attarder là dessus ca sera supprimé
public class RestoAdapter extends BaseAdapter {
    private Activity context;
    private LayoutInflater layoutInflater = null;
    private List<Restaurant> list;

    static class ViewHolder{
        public ImageView imageView;
        public TextView nom;
        public TextView note;
        public TextView type_cuisine;
    }

    public RestoAdapter(Activity context, List<Restaurant> list) {
        this.context = context;
        this.list = list;
        this.layoutInflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount(){
        return this.list.size();
    }

    @Override
    public Object getItem(int pos){
        return this.list.get(pos);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder = null;

        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.resto_item,null);
            holder = new ViewHolder();
            //holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            holder.nom = (TextView) convertView.findViewById(R.id.nom);
            holder.note = (TextView) convertView.findViewById(R.id.note);
            holder.type_cuisine = (TextView) convertView.findViewById(R.id.type_cuisine);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Restaurant restaurant = (Restaurant)getItem(position);

        if(restaurant != null) {
            holder.imageView.setImageResource(this.context.getResources().getIdentifier(restaurant.getPath(),"drawable","res"));
            holder.nom.setText(restaurant.getNom());
            holder.note.setText(Float.toString(restaurant.getNote()));
            holder.type_cuisine.setText(restaurant.getTypeCuisine());
        }

        return convertView;
    }
}
*/