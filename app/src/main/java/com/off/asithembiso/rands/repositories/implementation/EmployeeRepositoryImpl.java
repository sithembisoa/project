package com.off.asithembiso.rands.repositories.implementation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.off.asithembiso.rands.conf.databases.DBConstants;
import com.off.asithembiso.rands.domain.Employee;
import com.off.asithembiso.rands.repositories.interfaces.EmployeeRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by asithembiso on 2016/10/31.
 */

public class EmployeeRepositoryImpl extends SQLiteOpenHelper implements EmployeeRepository {

    public static final String TABLE_NAME="employee";
    public static final String COLUMN_ID="id";
    private SQLiteDatabase db;
    public static final String COLUMN_NAME="name";
    public static final String COLUMN_SURNAME="SurName";
    public static final String COLUMN_JOB="job";
    public static final String COLUMN_RATE="rate";
    public static final String COLUMN_HOURS="hours";
    public static final String COLUMN_SALARY="salary";

    private static final String DATABASE_CREATE=" CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT NOT NULL, "
            + COLUMN_SURNAME + " TEXT NOT NULL, "
            + COLUMN_JOB + " TEXT NOT NULL,"
            + COLUMN_RATE + " TEXT NOT NULL, "
            + COLUMN_HOURS +" TEXT NOT NULL, "
            + COLUMN_SALARY +" TEXT NOT NULL );";

    public EmployeeRepositoryImpl(Context context){

        super(context, DBConstants.DATABASE_NAME,null,DBConstants.DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
        this.db = db;
    }
    @Override
    public Employee findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_ID,
                        COLUMN_NAME,
                        COLUMN_SURNAME,
                        COLUMN_JOB,
                        COLUMN_RATE,
                        COLUMN_HOURS,
                        COLUMN_SALARY},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Employee employee= new Employee.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .surname(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)))
                    .jobTitle(cursor.getString(cursor.getColumnIndex(COLUMN_JOB)))
                    .rate(cursor.getDouble(cursor.getColumnIndex(COLUMN_RATE)))
                    .hoursWorked(cursor.getInt(cursor.getColumnIndex(COLUMN_HOURS)))
                    .salary()
                    .build();
            return employee;
        } else {
            return null;
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    @Override
    public Employee save(Employee entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,entity.getName());
        values.put(COLUMN_SURNAME,entity.getSurname());
        values.put(COLUMN_JOB,entity.getJobTitle());
        values.put(COLUMN_RATE,entity.getRate());
        values.put(COLUMN_HOURS,entity.getHours());
        values.put(COLUMN_RATE, entity.getRate());
        values.put(COLUMN_SALARY,entity.getSalary());
        Long id=db.insertOrThrow(TABLE_NAME,null,values);
        Employee createdEntity =new Employee.Builder()
                .copy(entity)
                .id(id)
                .build();

        return createdEntity;
    }

    @Override
    public Employee update(Employee entity) {
        open();
        ContentValues values= new ContentValues();
        values.put(COLUMN_ID,entity.getId());
        values.put(COLUMN_NAME,entity.getName());
        values.put(COLUMN_SURNAME,entity.getSurname());
        values.put(COLUMN_JOB,entity.getJobTitle());
        values.put(COLUMN_RATE,entity.getRate());
        values.put(COLUMN_HOURS,entity.getHours());
        values.put(COLUMN_SALARY, entity.getSalary());
        db.update(TABLE_NAME,values,COLUMN_ID + " =? " , new String[]
                {String.valueOf(entity.getId())});


        return entity;
    }

    @Override
    public Cursor getAll() {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql="Select * from employee";
        return (db.rawQuery(sql,null));

    }

    @Override
    public Employee delete(Employee entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }

    @Override
    public Set<Employee> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Employee> employees = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Employee employee = new Employee.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .surname(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)))
                        .jobTitle(cursor.getString(cursor.getColumnIndex(COLUMN_JOB)))
                        .rate(cursor.getDouble(cursor.getColumnIndex(COLUMN_RATE)))
                        .hoursWorked(cursor.getInt(cursor.getColumnIndex(COLUMN_HOURS)))
                        .salary()
                        .build();
                employees.add(employee);
            } while (cursor.moveToNext());
        }
        return employees;
    }

}
