package com.example.lab203_28.healthy.Weight;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lab203_28.healthy.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WeightAdaptor extends ArrayAdapter {

    ArrayList<Weight> weights = new ArrayList<Weight>();
    Context context;

    public WeightAdaptor (Context context, int resource, List<Weight> object){
        super(context, resource, object);
        this.weights = (ArrayList<Weight>) object;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View _row = LayoutInflater.from(context).inflate(R.layout.weight_,parent,false);
        TextView _dateview = (TextView) _row.findViewById(R.id.Weight_dateviewobj);
        TextView _weightview = (TextView) _row.findViewById(R.id.Weight_weightviewobj);
        TextView _statusview = (TextView) _row.findViewById(R.id.Weight_statusviewobj);

        Weight _w = this.weights.get(position);

        _dateview.setText(_w.getDate());
        _weightview.setText(_w.getWeight()+"");
        _statusview.setText(_w.getStatus());
        return _row;
    }
}
