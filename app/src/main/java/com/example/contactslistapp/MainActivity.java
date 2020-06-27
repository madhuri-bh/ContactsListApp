package com.example.contactslistapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final static int NEW_DATA_REQUEST_CODE =1;

    private ContactsViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton addButton = findViewById(R.id.fab);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this,addActivity.class);
               startActivityForResult(intent,NEW_DATA_REQUEST_CODE);
            }
        });
        mViewModel = new ViewModelProvider(this).get(ContactsViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ContactsListAdapter contactsListPagingAdapter = new ContactsListAdapter();
        recyclerView.setAdapter(contactsListPagingAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mViewModel.pagedListLiveData.observe(this, new Observer<PagedList<Contacts>>() {
            @Override
            public void onChanged(@Nullable final PagedList<Contacts> contacts) {
                // Update the cached copy of the words in the adapter.
                contactsListPagingAdapter.setContacts(contacts);
            }
        });
        
    }
}
