package com.example.studentsabaq;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "student_database";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_STUDENT = "students";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_SABAQ_START = "sabaq_start";
    private static final String COLUMN_SABAQ_END = "sabaq_end";
    private static final String COLUMN_SABQI = "sabqi";
    private static final String COLUMN_MANZIL_RANGE = "manzil_range";
    private static final String COLUMN_NAME = "name";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the student table
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_STUDENT + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_IMAGE + " INTEGER, " +
                COLUMN_SABAQ_START + " INTEGER, " +
                COLUMN_SABAQ_END + " INTEGER, " +
                COLUMN_SABQI + " INTEGER, " +
                COLUMN_MANZIL_RANGE + " INTEGER, " +
                COLUMN_NAME + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop and recreate the student table on database upgrade
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_STUDENT;
        db.execSQL(dropTableQuery);
        onCreate(db);
    }

    // Insert a student record with image and name
    public void insertStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_IMAGE, student.getImage());
        values.put(COLUMN_SABAQ_START, student.getSabaq_start());
        values.put(COLUMN_SABAQ_END, student.getSabaq_end());
        values.put(COLUMN_SABQI, student.getSabqi());
        values.put(COLUMN_MANZIL_RANGE, student.getManzil_range());
        values.put(COLUMN_NAME, student.getName());
        db.insert(TABLE_STUDENT, null, values);
        db.close();
    }

    public void insertStudents(List<Student> students) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();

        try {
            for (Student student : students) {
                ContentValues values = new ContentValues();
                values.put(COLUMN_IMAGE, student.getImage());
                values.put(COLUMN_SABAQ_START, student.getSabaq_start());
                values.put(COLUMN_SABAQ_END, student.getSabaq_end());
                values.put(COLUMN_SABQI, student.getSabqi());
                values.put(COLUMN_MANZIL_RANGE, student.getSabaq_end());
                values.put(COLUMN_NAME, student.getName());
                db.insert(TABLE_STUDENT, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    // Retrieve a student record by ID
    public Student getStudentById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_STUDENT, new String[]{COLUMN_ID, COLUMN_IMAGE, COLUMN_SABAQ_START, COLUMN_SABAQ_END, COLUMN_SABQI, COLUMN_MANZIL_RANGE, COLUMN_NAME},
                COLUMN_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(COLUMN_ID);
            int imageIndex = cursor.getColumnIndex(COLUMN_IMAGE);
            int sabaqStartIndex = cursor.getColumnIndex(COLUMN_SABAQ_START);
            int sabaqEndIndex = cursor.getColumnIndex(COLUMN_SABAQ_END);
            int sabqiIndex = cursor.getColumnIndex(COLUMN_SABQI);
            int manzilRangeIndex = cursor.getColumnIndex(COLUMN_MANZIL_RANGE);
            int nameIndex = cursor.getColumnIndex(COLUMN_NAME);

            // Check if the column indexes are valid
            if (idIndex >= 0 && imageIndex >= 0 && sabaqStartIndex >= 0 && sabaqEndIndex >= 0 && sabqiIndex >= 0 && manzilRangeIndex >= 0 && nameIndex >= 0) {
                int image = cursor.getInt(imageIndex);
                int sabaqStart = cursor.getInt(sabaqStartIndex);
                int sabaqEnd = cursor.getInt(sabaqEndIndex);
                int sabqi = cursor.getInt(sabqiIndex);
                int manzilRange = cursor.getInt(manzilRangeIndex);
                String name = cursor.getString(nameIndex);
                cursor.close();
                return new Student(id, image, sabaqStart, sabaqEnd, sabqi, manzilRange, name);
            } else {
                cursor.close();
                return null;
            }
        }

        return null;
    }

    // Retrieve all student records
    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_STUDENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null && cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(COLUMN_ID);
            int imageIndex = cursor.getColumnIndex(COLUMN_IMAGE);
            int sabaqStartIndex = cursor.getColumnIndex(COLUMN_SABAQ_START);
            int sabaqEndIndex = cursor.getColumnIndex(COLUMN_SABAQ_END);
            int sabqiIndex = cursor.getColumnIndex(COLUMN_SABQI);
            int manzilRangeIndex = cursor.getColumnIndex(COLUMN_MANZIL_RANGE);
            int nameIndex = cursor.getColumnIndex(COLUMN_NAME);

            // Check if the column indexes are valid
            if (idIndex >= 0 && imageIndex >= 0 && sabaqStartIndex >= 0 && sabaqEndIndex >= 0 && sabqiIndex >= 0 && manzilRangeIndex >= 0 && nameIndex >= 0) {
                do {
                    int id = cursor.getInt(idIndex);
                    int image = cursor.getInt(imageIndex);
                    int sabaqStart = cursor.getInt(sabaqStartIndex);
                    int sabaqEnd = cursor.getInt(sabaqEndIndex);
                    int sabqi = cursor.getInt(sabqiIndex);
                    int manzilRange = cursor.getInt(manzilRangeIndex);
                    String name = cursor.getString(nameIndex);

                    Student student = new Student(id, image, sabaqStart, sabaqEnd, sabqi, manzilRange, name);
                    studentList.add(student);
                } while (cursor.moveToNext());

                cursor.close();
            } else {
                cursor.close();
            }
        }

        return studentList;
    }
}
