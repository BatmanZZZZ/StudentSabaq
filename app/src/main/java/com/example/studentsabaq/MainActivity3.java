package com.example.studentsabaq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    TextView sabaqtextview;
    Button sabaqdone;
    Button sabaqrepeat;
    Button assignsabaq;


    TextView sabqitextview;


    TextView manziltextview;
    Button manzildone;
    Button manzilrepeat;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        sabaqtextview=findViewById(R.id.SabaqRange);
        sabaqdone=findViewById(R.id.SabaqDoneButton);
        sabaqrepeat=findViewById(R.id.SabaqRepeatButton);
        assignsabaq=findViewById(R.id.AssignSabaq);

        sabqitextview=findViewById(R.id.SabqiParaNo);

        manziltextview=findViewById(R.id.ManzilParaNo);
        manzildone=findViewById(R.id.ManzilDone);
        manzilrepeat=findViewById(R.id.ManzilRepeat);

        Intent a = getIntent();
        int id = a.getIntExtra("studentId",0);

        db = new DatabaseHelper(this);
        Student student=db.getStudentById(id);
        sabaqtextview.setText(student.getName());










    }
}