package com.example.weightsdata;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapterWeight extends BaseAdapter {
    private Context context;
    private ArrayList<WeightModel> weightsModelArrayList;

    public CustomAdapterWeight(Context context, ArrayList<WeightModel> weightsModelArrayList) {
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

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        CustomAdapterWeight.ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.model_weights, null, true);

            holder.tvdate = (TextView) convertView.findViewById(R.id.weights_date);
            holder.tvmax = (TextView) convertView.findViewById(R.id.weights_max);
            holder.tvmin = (TextView) convertView.findViewById(R.id.weights_min);
            holder.tvdiff = (TextView) convertView.findViewById(R.id.weights_diff);

            convertView.setTag(holder);
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (CustomAdapterWeight.ViewHolder)convertView.getTag();
        }

        double max = weightsModelArrayList.get(position).getMax();
        double min = weightsModelArrayList.get(position).getMin();
        double diff = max - min;
        holder.tvdate.setText("Date: " + weightsModelArrayList.get(position).getDate());
        holder.tvmax.setText("Max: " + max);
        holder.tvmin.setText("Min: " + min);
        holder.tvdiff.setText("Diff: " + diff);

        return convertView;
    }

    private static class ViewHolder {
        protected TextView tvdate, tvmax, tvmin, tvdiff;
    }
}
