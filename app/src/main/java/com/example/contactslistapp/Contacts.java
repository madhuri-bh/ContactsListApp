package com.example.contactslistapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contacts_table")
public class Contacts {
    @NonNull
    @ColumnInfo(name = "Name")
    private String mName;
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "Phone")
    private String mPhoneNo;
    @NonNull
    @ColumnInfo(name = "Email")
    private String mEmail;
    @ColumnInfo(name = "Age")
    private int mAge;
    @ColumnInfo(name = "Gender")
    private int mGender;
    @ColumnInfo(name = "City")
    private String mCity;
    @ColumnInfo(name = "College")
    private String mCollege;

    public Contacts(@NonNull String name,@NonNull String phoneNo,@NonNull String email, int age, @NonNull int gender, @NonNull String city, @NonNull String college) {
        this.mName = name;
        this.mPhoneNo = phoneNo;
        this.mEmail = email;
        this.mAge = age;
        this.mGender = gender;
        this.mCity = city;
        this.mCollege = college;
    }

    public Contacts() {

    }

    public String getName() {
        return this.mName;
    }

    public void setName(@NonNull String mName) {
        this.mName = mName;
    }

    public void setPhoneNo(@NonNull String mPhoneNo) {
        this.mPhoneNo = mPhoneNo;
    }

    public void setEmail(@NonNull String mEmail) {
        this.mEmail = mEmail;
    }

    public void setAge(int mAge) {
        this.mAge = mAge;
    }

    public void setGender(int mGender) {
        this.mGender = mGender;
    }

    public void setCity(String mCity) {
        this.mCity = mCity;
    }

    public void setCollege(String mCollege) {
        this.mCollege = mCollege;
    }

    public String getPhoneNo() {
        return this.mPhoneNo;
    }

    public String getEmail() {
        return this.mEmail;
    }

    public int getAge() {
        return this.mAge;
    }

    public int getGender() {
        return this.mGender;
    }

    public String getCity() {
        return this.mCity;
    }

    public String getCollege() {
        return this.mCollege;
    }
}
