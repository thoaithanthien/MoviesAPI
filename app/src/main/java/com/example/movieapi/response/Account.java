package com.example.movieapi.response;

import java.io.Serializable;

public class Account implements Serializable {
    private int PIDProfile;
    private String NameProfile;
    private String EmailProfile;
    private String PhoneProfile;
    private String DiaChiProfile;
    private String FbProfile;

    public Account(int PIDProfile, String nameProfile, String emailProfile, String phoneProfile, String diaChiProfile, String fbProfile) {
        this.PIDProfile = PIDProfile;
        NameProfile = nameProfile;
        EmailProfile = emailProfile;
        PhoneProfile = phoneProfile;
        DiaChiProfile = diaChiProfile;
        FbProfile = fbProfile;
    }

    public int getPIDProfile() {
        return PIDProfile;
    }

    public void setPIDProfile(int PIDProfile) {
        this.PIDProfile = PIDProfile;
    }

    public String getNameProfile() {
        return NameProfile;
    }

    public void setNameProfile(String nameProfile) {
        NameProfile = nameProfile;
    }

    public String getEmailProfile() {
        return EmailProfile;
    }

    public void setEmailProfile(String emailProfile) {
        EmailProfile = emailProfile;
    }

    public String getPhoneProfile() {
        return PhoneProfile;
    }

    public void setPhoneProfile(String phoneProfile) {
        PhoneProfile = phoneProfile;
    }

    public String getDiaChiProfile() {
        return DiaChiProfile;
    }

    public void setDiaChiProfile(String diaChiProfile) {
        DiaChiProfile = diaChiProfile;
    }

    public String getFbProfile() {
        return FbProfile;
    }

    public void setFbProfile(String fbProfile) {
        FbProfile = fbProfile;
    }
}
