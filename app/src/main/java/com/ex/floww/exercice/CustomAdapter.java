package com.ex.floww.exercice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ex.floww.exercice.Objects.Rows;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Floww on 07/03/2018.
 */
 class CustomAdapter extends ArrayAdapter<Rows>  {


    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView title;
        TextView description;
        ImageView img;
    }

     CustomAdapter(ArrayList<Rows> data, Context context) {
        super(context, R.layout.object_row, data);
        this.mContext=context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Rows dataModel = getItem(position);

        ViewHolder viewHolder; // view lookup cache stored in tag


        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.object_row, parent, false);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.description = (TextView) convertView.findViewById(R.id.description);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.url);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (dataModel != null) {
            viewHolder.title.setText(dataModel.getTitle());
            viewHolder.description.setText(dataModel.getDescription());
            Picasso.with(convertView.getContext().getApplicationContext()).load(dataModel.getUrl()).into(viewHolder.img);
        }

        // Return the completed view to render on screen
        return convertView;
    }


}