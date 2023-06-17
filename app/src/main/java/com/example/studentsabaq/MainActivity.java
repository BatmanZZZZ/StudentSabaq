package com.example.studentsabaq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.appcompat.widget.SearchView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    studentViewHolder adapter;
    RecyclerView.LayoutManager layoutManager;

    DatabaseHelper db;
    List<Student> studentList;
    List<Student> filteredStudentList;

    EditText editTextSearch;
    Button buttonSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);

        // LinearLayoutManager or GridLayoutManager
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        editTextSearch = findViewById(R.id.editTextTextPersonName);
        buttonSearch = findViewById(R.id.search);

        // Create Student objects
        studentList = new ArrayList<>();
        studentList.add(new Student(0, R.drawable.b, 5, 10, 15, 20, "Hamza"));
        studentList.add(new Student(1, R.drawable.c, 7, 12, 18, 25, "Ali"));
        studentList.add(new Student(2, R.drawable.d, 3, 20, 10, 30, "Ahmad"));
        studentList.add(new Student(3, R.drawable.a, 8, 22, 5, 12, "Farzad"));
        studentList.add(new Student(4, R.drawable.d, 15, 25, 9, 21, "Hamza"));
        studentList.add(new Student(5, R.drawable.a, 11, 7, 27, 8, "Ali"));
        studentList.add(new Student(6, R.drawable.c, 16, 3, 14, 29, "Ahmad"));
        studentList.add(new Student(7, R.drawable.a, 9, 18, 4, 17, "Farzad"));
        studentList.add(new Student(8, R.drawable.c, 21, 30, 6, 23, "Hamza"));
        studentList.add(new Student(9, R.drawable.d, 2, 11, 26, 1, "Ali"));

        refreshGrid(studentList);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = editTextSearch.getText().toString().trim();
                filterStudentList(query,studentList);
            }
        });
    }



    private void filterStudentList(String query,List<Student> studentList) {
        filteredStudentList = new ArrayList<>();

        if (query.isEmpty()) {
            filteredStudentList=db.getAllStudents();
        } else {
            query = query.toLowerCase();
            for (Student student : studentList) {
                if (student.getName().toLowerCase().contains(query)) {
                    filteredStudentList.add(student);
                }
            }
            layoutManager = new LinearLayoutManager(MainActivity.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);
            adapter = new studentViewHolder(filteredStudentList);
            recyclerView.setAdapter(adapter);
        }
        adapter.notifyDataSetChanged();
    }
    public void refreshGrid(List<Student> studentList) {
        db = new DatabaseHelper(this);
        db.insertStudents(studentList);
        List<Student> studentList1 = db.getAllStudents();
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new studentViewHolder(studentList1);
        recyclerView.setAdapter(adapter);
    }
}
