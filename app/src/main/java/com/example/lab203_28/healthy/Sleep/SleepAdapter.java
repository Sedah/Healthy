package com.example.lab203_28.healthy.Sleep;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lab203_28.healthy.R;



import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SleepAdapter extends ArrayAdapter {

    List<Sleep> sleeps;
    Context context;

    public SleepAdapter(Context context, int resource, List<Sleep> objects)
    {
        super(context, resource, objects);
        this.sleeps = objects;
        this.context = context;
    }

    @Nonnull
    @Override
    public View getView(int position, @Nullable View convertView, @Nonnull ViewGroup parent) {
        View sleepItem = LayoutInflater.from(context).inflate(R.layout.sleep, parent, false);
        TextView date = sleepItem.findViewById(R.id.sleep_list_item_date);
        TextView time = sleepItem.findViewById(R.id.sleep_list_item_time);
        TextView sleepTime = sleepItem.findViewById(R.id.sleep_list_item_sleep_time);
        Sleep sleep = sleeps.get(position);
        date.setText(sleep.getDate());
        time.setText(sleep.getToBedTime() + " - " + sleep.getAwakeTime());
        sleepTime.setText(sleep.getSleepTime());
        return sleepItem;
    }
}
