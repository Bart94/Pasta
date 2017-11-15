package com.example.bcoccaro.pasta;

import android.os.Parcel;
import android.os.Parcelable;


public class PastaObject implements Parcelable {

    String name;
    String cooking_time;

    public PastaObject(){
    }

    public PastaObject(String name, String cooking_time){
        this.name = name;
        this.cooking_time = cooking_time;
    }

    public String getCooking_time() {
        return cooking_time;
    }

    public String getName() {
        return name;
    }

    public void setCooking_time(String cooking_time) {
        this.cooking_time = cooking_time;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Pasta: " + name + ", Tempo di Cottura: " + cooking_time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.cooking_time);
    }

    protected PastaObject(Parcel in) {
        this.name = in.readString();
        this.cooking_time = in.readString();
    }

    public static final Parcelable.Creator<PastaObject> CREATOR = new Parcelable.Creator<PastaObject>() {
        @Override
        public PastaObject createFromParcel(Parcel source) {
            return new PastaObject(source);
        }

        @Override
        public PastaObject[] newArray(int size) {
            return new PastaObject[size];
        }
    };
}
