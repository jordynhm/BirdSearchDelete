package com.example.birdsearch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SearchPage extends AppCompatActivity implements View.OnClickListener {

    EditText editTextFindZipcode;
    Button buttonSearch, buttonGoToSubmit;
    TextView textViewBirdName, textViewPersonName;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchpage);

        editTextFindZipcode = findViewById(R.id.editTextSearchZipcode);
        buttonSearch = findViewById(R.id.buttonSearch);
        buttonGoToSubmit = findViewById(R.id.buttonGoToSubmit);
        textViewBirdName = findViewById(R.id.textViewBirdName);
        textViewPersonName = findViewById(R.id.textViewPersonName);

        buttonGoToSubmit.setOnClickListener(this);
        buttonSearch.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Bird");

        if (v == buttonGoToSubmit) {
            Intent submitPage = new Intent(this, MainActivity.class);
            startActivity(submitPage);
        } else if (v == buttonSearch) {
            int findZipCode = Integer.parseInt(editTextFindZipcode.getText().toString());


            myRef.orderByChild("zipcode").equalTo(findZipCode).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Bird foundBird = dataSnapshot.getValue(Bird.class);
                    String findPersoName = foundBird.personname;
                    String findBirdName = foundBird.birdname;

                    textViewBirdName.setText(findBirdName);
                    textViewPersonName.setText(findPersoName);


                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
    }

}
