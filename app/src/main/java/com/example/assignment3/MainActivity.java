package com.example.assignment3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.assignment3.model.BookData;
import com.google.android.material.navigation.NavigationView;

import java.util.Collections;
import java.util.Comparator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    NavigationView navview;
    DrawerLayout drawlay;
    ActionBarDrawerToggle toggle;
    Intent intent;
    ImageButton imgsort;

    SearchView srhbook;
    RecyclerView recbook;

    BookAdapter adapter;
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recbook = findViewById(R.id.recbooks);
        srhbook = findViewById(R.id.srhbook);
        imgsort = findViewById(R.id.imgsort);

        menu_item();

        SetEvents();
    }

    private void SetEvents() {

        adapter = new BookAdapter(ctx, Add.bookDataList);

        recbook.setAdapter(adapter);
        recbook.setLayoutManager(new LinearLayoutManager(this));

        srhbook.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //if(Add.bookDataList.contains(s)){
                adapter.getFilter().filter(s);
                //}
                return false;
            }
        });

    }

    private void menu_item() {
        toolbar = findViewById(R.id.toolbar);
        navview = findViewById(R.id.navview);
        drawlay = findViewById(R.id.drawlay);

        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this, drawlay, toolbar, R.string.open, R.string.close);
        drawlay.addDrawerListener(toggle);
        toggle.syncState();

        navview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.mnulist:
                        Toast.makeText(getApplicationContext(), "You are already here", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.mnuadd:
                        intent = new Intent(MainActivity.this, Add.class);
                        startActivity(intent);
                        navview.setCheckedItem(R.id.mnuadd);
                        break;
                }
                drawlay.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        imgsort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, imgsort);
                popupMenu.getMenuInflater().inflate(R.menu.sort_book, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @SuppressLint("NonConstantResourceId")
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.mnusortname:
                                Collections.sort(Add.bookDataList, new Comparator<BookData>() {
                                    @Override
                                    public int compare(BookData b1, BookData b2) {
                                        return b1.getBookName().compareTo(b2.getBookName());
                                    }
                                });
                                adapter.notifyDataSetChanged();
                                break;
                            case R.id.mnusortdate:
                                Collections.sort(Add.bookDataList, new Comparator<BookData>() {
                                    @Override
                                    public int compare(BookData d1, BookData d2) {
                                        try {
                                            return d1.getLaunchDate().compareTo(d2.getLaunchDate());
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            return 0;
                                        }
                                    }
                                });
                                adapter.notifyDataSetChanged();
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }
}