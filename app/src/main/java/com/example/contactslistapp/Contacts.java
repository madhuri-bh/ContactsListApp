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

    public Contacts(String name, String phoneNo, String email, @NonNull int age, @NonNull int gender, @NonNull String city, @NonNull String college) {
        this.mName = name;
        this.mPhoneNo = phoneNo;
        this.mEmail = email;
        this.mAge = age;
        this.mGender = gender;
        this.mCity = city;
        this.mCollege = college;
    }

    public String getName() {
        return this.mName;
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
