package com.limra.jaipurilohar.dao;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User implements Parcelable {

        @NonNull
        @PrimaryKey(autoGenerate = true)
        private int uid;

        @ColumnInfo(name = "first_name")
        private String firstName;

        @ColumnInfo(name = "last_name")
        private String lastName;

        @ColumnInfo(name = "user_name")
        private String userName;

        @ColumnInfo(name = "password")
        private String password;

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

    public User(String firstName, String lastName,String userName,String password, String phoneNumber, String gotra, String address, String city, String state, String occupation, int imageUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.gotra = gotra;
        this.address = address;
        this.city = city;
        this.state = state;
        this.occupation = occupation;
        this.imageUrl = imageUrl;
        this.userName = userName;
        this.password = password;
    }

    protected User(Parcel in) {
        uid = in.readInt();
        firstName = in.readString();
        lastName = in.readString();
        phoneNumber = in.readString();
        gotra = in.readString();
        address = in.readString();
        city = in.readString();
        state = in.readString();
        occupation = in.readString();
        imageUrl = in.readInt();
        userName = in.readString();
        password = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(uid);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(phoneNumber);
        dest.writeString(gotra);
        dest.writeString(address);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(occupation);
        dest.writeInt(imageUrl);
        dest.writeString(userName);
        dest.writeString(password);
    }
}
