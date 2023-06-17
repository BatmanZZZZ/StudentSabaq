package com.example.studentsabaq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);

        // LinearLayoutManager or GridLayoutManager
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        // Convert images to byte arrays
        int[] imageResourceIds = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d};
        List<byte[]> imageByteArrayList = convertImagesToByteArrays(imageResourceIds);

        // Create Student objects
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(0, imageByteArrayList.get(0), "Hamza"));
        studentList.add(new Student(1, imageByteArrayList.get(1), "Ali"));
        studentList.add(new Student(2, imageByteArrayList.get(2), "Ahmad"));
        studentList.add(new Student(3, imageByteArrayList.get(3), "Farzad"));
        studentList.add(new Student(4, imageByteArrayList.get(0), "Hamza"));
        studentList.add(new Student(5, imageByteArrayList.get(1), "Ali"));
        studentList.add(new Student(6, imageByteArrayList.get(2), "Ahmad"));
        studentList.add(new Student(7, imageByteArrayList.get(3), "Farzad"));
        studentList.add(new Student(8, imageByteArrayList.get(0), "Hamza"));
        studentList.add(new Student(9, imageByteArrayList.get(1), "Ali"));

        refreshgrid(studentList);



        // Set up the RecyclerView

    }

    private List<byte[]> convertImagesToByteArrays(int[] imageResourceIds) {
        List<byte[]> byteArrayList = new ArrayList<>();
        for (int resourceId : imageResourceIds) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resourceId);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] imageBytes = stream.toByteArray();
            byteArrayList.add(imageBytes);
        }
        return byteArrayList;
    }
    public void refreshgrid(List<Student> studentList){
        db=new DatabaseHelper(this);
        db.insertStudents(studentList);
        List<Student> studentList1 = db.getAllStudents();
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new studenViewHolder(studentList1);
        recyclerView.setAdapter(adapter);

    }
}
