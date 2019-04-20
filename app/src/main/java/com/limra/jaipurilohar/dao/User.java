package com.limra.jaipurilohar.dao;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {

        @NonNull
        @PrimaryKey
        private int uid;

        @ColumnInfo(name = "first_name")
        private String firstName;

        @ColumnInfo(name = "last_name")
        private String lastName;

        @ColumnInfo(name = "phone_number")
        private String phoneNumber;

        @ColumnInfo(name = "gotra")
        private String gotra;

        @ColumnInfo(name = "address")
        private String address;

        @ColumnInfo(name = "city")
        private String city;

        @ColumnInfo(name = "state")
        private String state;

        @ColumnInfo(name = "occupation")
        private String occupation;

        @ColumnInfo(name = "image_url")
        private int imageUrl;

    public User(@NonNull int uid, String firstName, String lastName, String phoneNumber, String gotra, String address, String city, String state, String occupation, int imageUrl) {
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.gotra = gotra;
        this.address = address;
        this.city = city;
        this.state = state;
        this.occupation = occupation;
        this.imageUrl = imageUrl;
    }

    public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getGotra() {
            return gotra;
        }

        public void setGotra(String gotra) {
            this.gotra = gotra;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public String getOccupation() {
            return occupation;
        }

        public void setOccupation(String occupation) {
            this.occupation = occupation;
        }

        public int getImageUrl() {
            return imageUrl;
        }
}
