package com.example.contactslistapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final static int NEW_DATA_REQUEST_CODE = 1;
    private final static int UPDATE_DATA_REQUEST_CODE = 2;

    public static final String EXTRA_NAME = "extra_name";
    public static final String EXTRA_PHONE_NO = "extra_phoneNo";
    public static final String EXTRA_EMAIL = "extra_task_priority";
    public static final String EXTRA_AGE = "extra_age";
    public static final String EXTRA_GENDER = "extra_gender";
    public static final String EXTRA_CITY = "extra_city";
    public static final String EXTRA_COLLEGE = "extra_college";


    private ContactsViewModel mViewModel;
    private Contacts contacts;

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
                Intent intent = new Intent(MainActivity.this, addActivity.class);
                startActivityForResult(intent, NEW_DATA_REQUEST_CODE);
            }

        });


        mViewModel = new ViewModelProvider(this).get(ContactsViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ContactsListAdapter contactsListPagingAdapter = new ContactsListAdapter();
        recyclerView.setAdapter(contactsListPagingAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        mViewModel.pagedListLiveData.observe(this, new Observer<PagedList<Contacts>>() {
            @Override
            public void onChanged(@Nullable final PagedList<Contacts> contacts) {
                // Update the cached copy of the words in the adapter.
                contactsListPagingAdapter.setContacts(contacts);
            }
        });

        /*ConstraintLayout constraint = findViewById(R.id.activity_main);
        final Snackbar snackbar = Snackbar.make(constraint,"Task Deleted", BaseTransientBottomBar.LENGTH_SHORT)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewModel.insert(contacts);
                    }
                });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition();
                contacts = contactsListPagingAdapter.getContactsAtPosition(pos);
                mViewModel.delete(contacts);
                snackbar.show();
            }
        });

        itemTouchHelper.attachToRecyclerView(recyclerView);*/

        contactsListPagingAdapter.setOnItemClickListener(new ContactsListAdapter.ClickListener() {

            @Override
            public void itemClick(int position, View view) {
                Contacts currentContacts = contactsListPagingAdapter.getContactsAtPosition(position);
                launchUpdateTaskActivity(currentContacts);
            }
        });
    }

    private void launchUpdateTaskActivity(Contacts currentContacts) {
        Intent intent = new Intent(this,addActivity.class);
        intent.putExtra(EXTRA_NAME,currentContacts.getName());
        intent.putExtra(EXTRA_PHONE_NO,currentContacts.getPhoneNo());
        intent.putExtra(EXTRA_EMAIL,currentContacts.getEmail());
        intent.putExtra(EXTRA_AGE,currentContacts.getAge());
        intent.putExtra(EXTRA_GENDER,currentContacts.getGender());
        intent.putExtra(EXTRA_CITY,currentContacts.getCity());
        intent.putExtra(EXTRA_COLLEGE,currentContacts.getCollege());
        startActivityForResult(intent,UPDATE_DATA_REQUEST_CODE);
    }


        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == NEW_DATA_REQUEST_CODE)
            {
                if (resultCode == Activity.RESULT_OK) {
                    Toast.makeText(this, "Added Contact", Toast.LENGTH_SHORT).show();
                }
                if (resultCode == Activity.RESULT_CANCELED) {
                    Toast.makeText(this, "Cancelled Added Contact", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }


