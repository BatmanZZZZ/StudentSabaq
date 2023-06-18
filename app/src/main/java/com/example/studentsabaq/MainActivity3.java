package com.example.studentsabaq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {
    TextView first;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent a = getIntent();
        String b = a.getStringExtra("student");
        first = findViewById(R.id.textView);
        first.setText("hi"+b);
    }
}