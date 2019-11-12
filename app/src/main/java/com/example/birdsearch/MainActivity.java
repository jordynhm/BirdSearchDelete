package com.example.birdsearch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextBirdName, editTextPersonName,editTextZipcode;
    Button buttonSubmit, buttonGoToSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextBirdName = findViewById(R.id.editTextBirdName);
        editTextPersonName = findViewById(R.id.editTextPersonName);
        editTextZipcode = findViewById(R.id.editTextZipcode);
        buttonGoToSearch = findViewById(R.id.buttonGoToSearch);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(this);
        buttonGoToSearch.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Bird");

        if (v == buttonSubmit) {

            String birdName = editTextBirdName.getText().toString();
            String personName = editTextPersonName.getText().toString();
            int zipCode = Integer.parseInt(editTextZipcode.getText().toString());

            Bird myBird = new Bird(birdName, zipCode, personName);

            myRef.push().setValue(myBird);

        }
        else if (v == buttonGoToSearch){
            Intent searchPage = new Intent(this, SearchPage.class);
            startActivity(searchPage);


        }

    }
}
