package com.example.assignment3;

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
import android.widget.CompoundButton;
import android.widget.DatePicker;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class Edit extends AppCompatActivity {
    EditText etbname, etaname;
    RadioGroup rdogrpfic;
    RadioButton rdofic, rdonfic;
    Button btndate;
    CheckBox chkage1, chkage2, chkage3, chkage4;
    AppCompatButton btnadd;
    Spinner spngenre;

    Toolbar toolbar;
    NavigationView navview;
    DrawerLayout drawlay;
    ActionBarDrawerToggle toggle;
    Context ctx=this;

    List<String> bgenre;
    String bname,aname,fic,genre,ldate,age1,age2,age3,age4;
    String bookgenre;
    int DefaultDate,DefaultMonth,DefaultYear;
    int position;

    ArrayList<BookData> bookData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        position = getIntent().getIntExtra("POSITION",position);
        
        bookData = Add.bookDataList;
        bname = bookData.get(position).bookName;
        aname = bookData.get(position).authorName;
        genre = bookData.get(position).bookGenre;
        fic = bookData.get(position).bookType;
        ldate = bookData.get(position).launchDate;
        age1 = bookData.get(position).ageGrpOne;
        age2 = bookData.get(position).ageGrpTwo;
        age3 = bookData.get(position).ageGrpThree;
        age4 = bookData.get(position).ageGrpFour;
        AllocateMemory();
        menuItem();
        spinner();
        DisplayExistingData();
        SetEvent();
    }

    private void DisplayExistingData() {
        btnadd.setText(R.string.edit_book_details);
        etbname.setText(bname);
        etaname.setText(aname);
        btndate.setText(ldate);
        switch (genre) {
            case "Novel":
                spngenre.setSelection(0);
                break;
            case "Mystery":
                spngenre.setSelection(1);
                break;
            case "Thriller":
                spngenre.setSelection(2);
                break;
            case "Historical":
                spngenre.setSelection(3);
                break;
            case "Poetry":
                spngenre.setSelection(4);
                break;
        }

        if (age1 != null){
            chkage1.setChecked(true);
        }
        if (age2 != null){
            chkage2.setChecked(true);
        }
        if (age3 != null){
            chkage3.setChecked(true);
        }
        if (age4 != null){
            chkage4.setChecked(true);
        }
        if (fic.equals("Fiction")){
            rdofic.setChecked(true);
        }
        if (fic.equals("Non-fiction")){
            rdonfic.setChecked(true);
        }
    }

    private void SetEvent() {

        btndate.setOnClickListener(view -> {
            GregorianCalendar gc = new GregorianCalendar();
            DefaultDate = gc.get(Calendar.DAY_OF_MONTH);
            DefaultMonth = gc.get(Calendar.MONTH);
            DefaultYear = gc.get(Calendar.YEAR);
            DatePickerDialog dpd = new DatePickerDialog(ctx, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    ldate = day + "/" + (month+1) + "/" + year;
                    btndate.setText(ldate);
                }
            },DefaultYear,DefaultMonth,DefaultDate);
            dpd.show();

        });

        btnadd.setOnClickListener(view -> {
            if(isValidate()){
                ArrayList<BookData> bookData = Add.bookDataList;
                bookData.get(position).bookName = etbname.getText().toString();
                bookData.get(position).authorName = etaname.getText().toString();
                bookData.get(position).launchDate = btndate.getText().toString();
                bookData.get(position).bookGenre = bookgenre;
                bookData.get(position).ageGrpOne = age1;
                bookData.get(position).ageGrpTwo = age2;
                bookData.get(position).ageGrpThree = age3;
                bookData.get(position).ageGrpFour = age4;
                bookData.get(position).bookGenre = genre;
                bookData.get(position).bookType = fic;
                Add.bookDataList = bookData;
                Intent intent = new Intent(Edit.this, MainActivity.class);
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

    private void spinner() {
        bgenre = new ArrayList<>();
        bgenre.add("Novel");
        bgenre.add("Mystery");
        bgenre.add("Thriller");
        bgenre.add("Historical");
        bgenre.add("Poetry");

        spngenre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position1, long l) {
                bookgenre = adapterView.getItemAtPosition(position1).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,bgenre);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spngenre.setAdapter(adapter);

    }

    private void menuItem() {
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
                        Intent intent = new Intent(Edit.this, MainActivity.class);
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
    }
}
