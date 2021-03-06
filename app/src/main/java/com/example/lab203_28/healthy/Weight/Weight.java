package com.example.lab203_28.healthy.Weight;

import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Weight implements Comparable<Weight> {
    String date;
    int weight;
    private String status;

    public Weight() {}

    @Override
    public int compareTo(@NonNull Weight weight) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd MMM yyyy");
        long otherEpoch, thisEpoch;
        try {
            otherEpoch = fmt.parse(weight.date).getTime();
            thisEpoch = fmt.parse(this.date).getTime();
            return -Long.compare(thisEpoch, otherEpoch);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Weight(String date, int weight){
        this.date = date;
        this.weight = weight;
        this.status = "UP";
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}