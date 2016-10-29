package com.off.asithembiso.rands.repositories.implementation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.off.asithembiso.rands.conf.databases.DBConstants;
import com.off.asithembiso.rands.domain.Customer;
import com.off.asithembiso.rands.repositories.interfaces.CustomerRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by asithembiso on 2016/09/02.
 */
public class CustomerRepositoryImpl  extends SQLiteOpenHelper implements CustomerRepository{

    public static final String TABLE_NAME = "customers";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FULLNAME = "fullname";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";


    // Database creation sql statement
    private static final String DATABASE_CREATE = "CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, "
            + COLUMN_EMAIL + " TEXT NOT NULL, "
            + COLUMN_PASSWORD + " TEXT NOT NULL, "
            + COLUMN_FULLNAME + " TEXT NOT NULL);";

    public CustomerRepositoryImpl(Context context){

        super(context, DBConstants.DATABASE_NAME,null,DBConstants.DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DATABASE_CREATE);
        this.db = db;
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
    public String findUser(String email){
        db = this.getReadableDatabase();
        String sqlQuery = "SELECT email, password FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(sqlQuery, null);
        String t_email="", t_password="not found";
        if (cursor.moveToFirst()){
            do {
                t_email = cursor.getString(0);
                if (t_email.equals(email)){
                    t_password = cursor.getString(1);
                    break;
                }
            }while(cursor.moveToNext());
        }
        close();
        return t_password;
    }

    @Override
    public Customer save(Customer entity) {
        open();
        String sqlQuery = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor = db.rawQuery(sqlQuery, null);
        int count = cursor.getCount();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, count);
        values.put(COLUMN_FULLNAME, entity.getFullName());
        values.put(COLUMN_EMAIL, entity.getEmail());
        values.put(COLUMN_PASSWORD, entity.getPassword());
        db.insert(TABLE_NAME, null, values);
        close();
        Customer insertedEntity = new Customer.Builder()
                .copy(entity)
                .id(count)
                .build();
        return insertedEntity;
    }

    @Override
    public Customer update(Customer entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_FULLNAME, entity.getFullName());
        values.put(COLUMN_EMAIL, entity.getFullName());
        values.put(COLUMN_PASSWORD, entity.getPassword());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Customer delete(Customer entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }
}
