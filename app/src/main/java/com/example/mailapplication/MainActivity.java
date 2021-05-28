package com.example.mailapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    Toolbar toolbar;
    BottomNavigationView bottomNav;
    ExtendedFloatingActionButton fab;
    RecyclerView recyclerView;
    MassageAdapter adapter;

    FirebaseDatabase database ;
    DatabaseReference myRef ;
    List<MassageModel> modelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab = findViewById(R.id.fab_button);
        bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        toolbar.setTitle("    Postalarda Arama Yapmak için Tiklayiniz");
      //  toolbar.setSubtitle("alt baslik");
        toolbar.setLogo(R.drawable.menu_icon);
        toolbar.setTitleTextColor(getResources().getColor(R.color.red));

        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });

        pull();



    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId())
                    {
                        case R.id.nav_bottom_mail:
                            Toast.makeText(MainActivity.this,"POSTA",Toast.LENGTH_SHORT);
                            System.out.println("POSTA");
                            break;
                        case R.id.nav_bottom_meeting:
                            Toast.makeText(MainActivity.this,"TOPLANTI",Toast.LENGTH_SHORT);
                            System.out.println("TOPLANTI");
                            break;
                    }
                    return true;
                }
            };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;

    }

    @Override
    public boolean onQueryTextChange(String newText) {
       adapter.getFilter().filter(newText);
        return true;
    }

    public void pull()
    {
        modelList = new ArrayList<>();
        myRef = FirebaseDatabase.getInstance().getReference("users");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                modelList.clear();
               for (DataSnapshot modelSnapshot : snapshot.getChildren())
               {
                   MassageModel model = modelSnapshot.getValue(MassageModel.class);
                   modelList.add(model);
                   System.out.println(model.gonderen);
               }

                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
               adapter = new MassageAdapter(MainActivity.this,modelList);
               recyclerView.setAdapter(adapter);
               adapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}