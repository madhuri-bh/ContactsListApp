package com.example.contactslistapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactsDao {

    @Insert
    void insert (Contacts contacts);

    @Query("DELETE FROM contacts_table")
    void deleteAll();

    @Query("SELECT * from contacts_table ORDER BY Name ASC")
    LiveData<List<Contacts>> getAllContacts();
}
