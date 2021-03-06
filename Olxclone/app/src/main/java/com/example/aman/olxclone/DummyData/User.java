package com.example.aman.olxclone.DummyData;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
   String profilename;
   String firstname;
   String lastname;
   String phoneno;
    String profilephoto;
    String user_id;
    List<String> ad_id;
    List<String> wish_id;
    String adsViews;
    String createdOn;
    String isActive;
    String userType;
    String verfied;
    String adsNo;
    String refercode;
    String referCash;
    String earnedCash;
    String password;





    public User(String profilename, String phoneno, String profilephoto, String user_id, List<String> ad_id,List<String> wish_id) {
        this.profilename = profilename;
        this.phoneno = phoneno;
        this.profilephoto = profilephoto;
        this.user_id = user_id;
        this.ad_id = ad_id;
        this.wish_id=wish_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRefercode() {
        return refercode;
    }

    public void setRefercode(String refercode) {
        this.refercode = refercode;
    }

    public String getReferCash() {
        return referCash;
    }

    public void setReferCash(String referCash) {
        this.referCash = referCash;
    }

    public String getEarnedCash() {
        return earnedCash;
    }

    public void setEarnedCash(String earnedCash) {
        this.earnedCash = earnedCash;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAdsNo() {
        return adsNo;
    }

    public void setAdsNo(String adsNo) {
        this.adsNo = adsNo;
    }

    public String getVerfied() {
        return verfied;
    }

    public void setVerfied(String verfied) {
        this.verfied = verfied;
    }

    public List<String> getAd_id() {
        return ad_id;
    }

    public List<String> getWish_id() {
        return wish_id;
    }

    public void setWish_id(List<String> wish_id) {
        this.wish_id = wish_id;
    }

    public String getProfilename() {
        return profilename;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public String getProfilephoto() {
        return profilephoto;
    }

    public String getUser_id() {
        return user_id;
    }

    public List<String> getAdd_id() {
        return ad_id;
    }

    public void setProfilename(String profilename) {
        this.profilename = profilename;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public void setProfilephoto(String profilephoto) {
        this.profilephoto = profilephoto;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setAd_id(List<String> add_id) {
        this.ad_id = add_id;
    }
    public void addAd_id(String newadd_id) {
        this.ad_id.add(newadd_id);
    }

    public void addwish_id(String newwish_id) {
        this.ad_id.add(newwish_id);
    }
    public String getAdsViews() {
        return adsViews;
    }

    public void setAdsViews(String adsViews) {
        this.adsViews = adsViews;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }


    @Override
    public String toString() {
        return "User{" +
                "profilename='" + profilename + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phoneno='" + phoneno + '\'' +
                ", profilephoto='" + profilephoto + '\'' +
                ", user_id='" + user_id + '\'' +
                ", ad_id=" + ad_id +
                ", wish_id=" + wish_id +
                ", adsViews='" + adsViews + '\'' +
                ", createdOn='" + createdOn + '\'' +
                ", isActive='" + isActive + '\'' +
                ", userType='" + userType + '\'' +
                ", verfied='" + verfied + '\'' +
                ", adsNo='" + adsNo + '\'' +
                ", refercode='" + refercode + '\'' +
                ", referCash='" + referCash + '\'' +
                ", earnedCash='" + earnedCash + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
