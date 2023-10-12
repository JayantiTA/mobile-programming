package com.example.weightsdata;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomMod extends BaseAdapter {
    private final Context context;
    private final ArrayList<WeightModel> weightsModelArrayList;

    public CustomMod(Context context, ArrayList<WeightModel> weightsModelArrayList) {
        this.context = context;
        this.weightsModelArrayList = weightsModelArrayList;
    }


    @Override
    public int getCount() {
        return weightsModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return weightsModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint({"SetTextI18n", "InflateParams"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.model_weight_mod, null, true);

            holder.tvdate = (TextView) convertView.findViewById(R.id.weights_date);
            holder.tvmax = (TextView) convertView.findViewById(R.id.weights_max);
            holder.tvmin = (TextView) convertView.findViewById(R.id.weights_min);

            convertView.setTag(holder);
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        double max = weightsModelArrayList.get(position).getMax();
        double min = weightsModelArrayList.get(position).getMin();
        holder.tvdate.setText("Date: " + weightsModelArrayList.get(position).getDate());
        holder.tvmax.setText("Max: " + max);
        holder.tvmin.setText("Min: " + min);

        return convertView;
    }

    private static class ViewHolder {
        protected TextView tvdate, tvmax, tvmin;
    }
}
