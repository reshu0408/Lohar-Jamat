package com.limra.jaipurilohar.contacts;

public class ContactModel {

    private String name;
    private String gotra;
    private String phoneNo;
    private String Address;

    //TODO change url to string
    private int imageUrl;

    public ContactModel(String name, String gotra, String phoneNo, String address, int imageUrl) {
        this.name = name;
        this.gotra = gotra;
        this.phoneNo = phoneNo;
        this.Address = address;
        this.imageUrl = imageUrl;
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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }

}
