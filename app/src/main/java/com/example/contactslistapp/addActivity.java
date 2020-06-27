package com.example.contactslistapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

public class addActivity extends AppCompatActivity  {
    public static final String TABLE_NAME = "contacts_table";
    public static final String EXTRA_NAME = "extra_name";
    public static final String EXTRA_PHONE_NO = "extra_phoneNo";
    public static final String EXTRA_EMAIL = "extra_task_priority";
    public static final String EXTRA_AGE = "extra_age";
    public static final String EXTRA_GENDER = "extra_gender";
    public static final String EXTRA_CITY = "extra_city";
    public static final String EXTRA_COLLEGE = "extra_college";

    public static final int GENDER_OTHERS = 0;
    public static final int GENDER_MALE = 1;
    public static final int GENDER_FEMALE = 2;

    private addViewModel viewModel;
    private int mGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final Bundle extras = getIntent().getExtras();

        viewModel = new ViewModelProvider(this).get(addViewModel.class);

        final EditText name = findViewById(R.id.newName);
        final TextView phoneText = findViewById(R.id.new_phone_text);
        final EditText phone = findViewById(R.id.newPhone);
        final EditText email = findViewById(R.id.new_email);
        final EditText age = findViewById(R.id.new_age);
        final EditText city = findViewById(R.id.new_city);
        final EditText college = findViewById(R.id.new_college);
        Button saveBtn = findViewById(R.id.new_save);
        //final int contactsAge = 0;
        RadioGroup radioGroup =  findViewById(R.id.new_gender);
        radioGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton radioButton =  group.findViewById(checkedId);
                    }
                });



            saveBtn.setText("UPDATE");

            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String Name = name.getText().toString();
                    String Phone = phone.getText().toString();
                    String Email = email.getText().toString();
                    //String Age = age.getText().toString();
                    int Age = Integer.parseInt(String.valueOf(age));
                    int selectedId = radioGroup.getCheckedRadioButtonId();
                    if (selectedId == -1) {
                        Toast.makeText(getApplicationContext(), "Missed an input", Toast.LENGTH_SHORT).show();
                    } else {
                        RadioButton radioButton = (RadioButton) radioGroup.findViewById(selectedId);
                    }
                    String City = city.getText().toString();
                    String College = college.getText().toString();
                    if(!Name.isEmpty() && !Phone.isEmpty() && !Email.isEmpty() && !City.isEmpty() && !College.isEmpty() )
                    if (extras != null) {
                        Contacts contacts = new Contacts(Name, Phone, Email, Age, selectedId, City, College);
                        viewModel.insertContacts(contacts);
                        String contactsName = extras.getString(EXTRA_NAME, "");
                        String contactsPhone = extras.getString(EXTRA_PHONE_NO);
                        String contactsEmail = extras.getString(EXTRA_EMAIL);
                        int contactsAge = extras.getInt(EXTRA_AGE);
                        String contactsGender = extras.getString(EXTRA_GENDER);
                        String contactsCity = extras.getString(EXTRA_CITY);
                        String contactsCollege = extras.getString(EXTRA_COLLEGE);

                        if (!contactsName.isEmpty()) {
                            name.setText(contactsName);
                        }

                        if (!contactsPhone.isEmpty()) {
                            phone.setText(contactsPhone);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(),"Missed an input",Toast.LENGTH_SHORT).show();
                    }

                    setResult(RESULT_OK);
                    finish();
                }
            });

        }
    }


   /* @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       String selection = (String)parent.getItemAtPosition(position);
       if(!TextUtils.isEmpty(selection)){
           if(selection.equals("Male")) {
               mGender = GENDER_MALE;
           } else  if(selection.equals("Female")){
               mGender = GENDER_FEMALE;
           } else {
               mGender = GENDER_OTHERS;
           }
       }
    }*/





   /* private  EditText mEditWordView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        mEditWordView = findViewById(R.id.edit_word);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditWordView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String word = mEditWordView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, word);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}*/








