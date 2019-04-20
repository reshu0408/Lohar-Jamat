package com.limra.jaipurilohar.dao;

import android.os.Parcel;
import android.os.Parcelable;

public class SearchModel implements Parcelable {

    private String name;
    private String gotra;
    private String city;
    private String state;

    public SearchModel(String name, String gotra, String city) {
        this.name = name;
        this.gotra = gotra;
        this.city = city;
    }

    public SearchModel(Parcel in){
        String[] data = new String[3];
        in.readStringArray(data);
        this.name = data[0];
        this.gotra = data[1];
        this.city = data[2];
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGotra() {
        return gotra;
    }

    public void setGotra(String gotra) {
        this.gotra = gotra;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.name,
                this.gotra,
                this.city});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public SearchModel createFromParcel(Parcel in) {
            return new SearchModel(in);
        }

        public SearchModel[] newArray(int size) {
            return new SearchModel[size];
        }
    };
}
