package com.example.mailapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class AddActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText gonderen,alici,konu,ileti;
    Button btn;
    FirebaseDatabase database ;
    DatabaseReference myRef ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add);
        toolbar = findViewById(R.id.toolbar_add);
        toolbar.setTitle("Oluştur");
        toolbar.setTitleTextColor(getResources().getColor(R.color.red));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        gonderen = findViewById(R.id.gonderen);
        alici = findViewById(R.id.alici);
        konu = findViewById(R.id.konu);
        ileti = findViewById(R.id.ileti);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar_add,menu);
        MenuItem item = menu.findItem(R.id.action_send);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_send:
              send();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void send()
    {
        MassageModel model = new MassageModel(gonderen.getText().toString(),alici.getText().toString(),gonderen.getText().toString(),ileti.getText().toString());
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users").child(model.gonderen);
        myRef.setValue(model);
        Intent i = new Intent(AddActivity.this,MainActivity.class);
        startActivity(i);
        finish();
    }


}