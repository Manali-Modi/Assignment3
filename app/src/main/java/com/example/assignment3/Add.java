package com.example.assignment3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.assignment3.model.BookData;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Add extends AppCompatActivity {

    EditText etbname, etaname;
    RadioGroup rdogrpfic;
    RadioButton rdofic, rdonfic;
    Button btndate;
    CheckBox chkage1, chkage2, chkage3, chkage4;
    AppCompatButton btnadd;
    Spinner spngenre;

    String bname,aname,fic,genre,ldate,age1,age2,age3,age4;
    List<String> bgenre;

    Toolbar toolbar;
    NavigationView navview;
    DrawerLayout drawlay;
    ActionBarDrawerToggle toggle;
    Intent intent;
    public static ArrayList<BookData> bookDataList = new ArrayList<>();

    int DefaultDate,DefaultMonth,DefaultYear;

    Context ctx=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        AllocateMemory();
        menuitem();
        spinner();
        SetEvent();
    }

    private void SetEvent() {

        btndate.setOnClickListener(view -> {
           GregorianCalendar gc = new GregorianCalendar();
            DefaultDate = gc.get(Calendar.DAY_OF_MONTH);
            DefaultMonth = gc.get(Calendar.MONTH);
            DefaultYear = gc.get(Calendar.YEAR);
            DatePickerDialog dpd = new DatePickerDialog(ctx, (datePicker, year, month, day) -> {
                ldate = day + "/" + (month+1) + "/" + year;
                btndate.setText(ldate);
            },DefaultYear,DefaultMonth,DefaultDate);
            dpd.show();

        });

        btnadd.setOnClickListener(view -> {
            if(isValidate()){
                BookData bookData = new BookData(bname,aname,genre,fic,ldate,age1,age2,age3,age4);
                bookDataList.add(bookData);
                intent = new Intent(Add.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean isValidate() {
        boolean isValid = true;
        bname = etbname.getText().toString();
        aname = etaname.getText().toString();

        if(bname.length()==0){
            Toast.makeText(this,"Enter book name",Toast.LENGTH_LONG).show();
            isValid = false;
        }
        if(aname.length()==0){
            Toast.makeText(this,"Enter author name",Toast.LENGTH_LONG).show();
            isValid = false;
        }

        if(rdofic.isChecked()){
            fic = "Fiction";
        }
        if(rdonfic.isChecked()){
            fic = "Non-fiction";
        }
        if (chkage1.isChecked()){
            age1 = "0-18";
        }
        if (chkage2.isChecked()){
            age2 = "19-35";
        }
        if (chkage3.isChecked()){
            age3 = "35-58";
        }
        if (chkage4.isChecked()){
            age4 = "58 and above";
        }

        return isValid;
    }

    private void AllocateMemory() {
        etbname = findViewById(R.id.etbname);
        etaname = findViewById(R.id.etaname);
        rdogrpfic = findViewById(R.id.rdogrpfic);
        rdofic = findViewById(R.id.rdofic);
        rdonfic = findViewById(R.id.rdonfic);
        btndate = findViewById(R.id.btndate);
        chkage1 = findViewById(R.id.chkage1);
        chkage2 = findViewById(R.id.chkage2);
        chkage3 = findViewById(R.id.chkage3);
        chkage4 = findViewById(R.id.chkage4);
        btnadd = findViewById(R.id.btnadd);

        toolbar = findViewById(R.id.toolbar2);
        navview = findViewById(R.id.navview2);
        drawlay = findViewById(R.id.drawlay2);

        spngenre = findViewById(R.id.spngenre);
        btnadd.setText(getString(R.string.add));
    }

    private void menuitem() {
        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this,drawlay,toolbar,R.string.open,R.string.close);
        drawlay.addDrawerListener(toggle);
        toggle.syncState();

        navview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mnulist:
                        intent = new Intent(Add.this, MainActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.mnuadd:
                        Toast.makeText(getApplicationContext(),"You are already here",Toast.LENGTH_LONG).show();
                        break;
                }
                drawlay.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void spinner() {
        bgenre = new ArrayList<>();
        bgenre.add("Novel");
        bgenre.add("Mystery");
        bgenre.add("Thriller");
        bgenre.add("Historical");
        bgenre.add("Poetry");

        spngenre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                genre = adapterView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,bgenre);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spngenre.setAdapter(adapter);

    }

}