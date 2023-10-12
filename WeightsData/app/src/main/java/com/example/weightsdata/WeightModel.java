package com.example.weightsdata;

import android.os.Parcel;
import android.os.Parcelable;

public class WeightModel implements Parcelable {
    private String date;
    private double min;
    private double max;
    private int id;

    // Default constructor
    public WeightModel() {
    }

    // Constructor with all fields
    public WeightModel(int id, String date, double min, double max) {
        this.id = id;
        this.date = date;
        this.min = min;
        this.max = max;
    }

    // Parcelable constructor
    protected WeightModel(Parcel in) {
        id = in.readInt();
        date = in.readString();
        min = in.readDouble();
        max = in.readDouble();
    }

    public static final Creator<WeightModel> CREATOR = new Creator<WeightModel>() {
        @Override
        public WeightModel createFromParcel(Parcel in) {
            return new WeightModel(in);
        }

        @Override
        public WeightModel[] newArray(int size) {
            return new WeightModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(date);
        dest.writeDouble(min);
        dest.writeDouble(max);
    }
}
