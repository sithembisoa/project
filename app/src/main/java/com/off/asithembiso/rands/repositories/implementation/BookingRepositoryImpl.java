package com.off.asithembiso.rands.repositories.implementation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.off.asithembiso.rands.conf.databases.DBConstants;
import com.off.asithembiso.rands.domain.Booking;
import com.off.asithembiso.rands.repositories.interfaces.BookingRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by asithembiso on 2016/09/02.
 */
public class BookingRepositoryImpl extends SQLiteOpenHelper implements BookingRepository{
    
    public static final String TABLE_NAME = "bookings";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FULLNAME = "fullname";
    public static final String COLUMN_IDNUMBER = "id";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TABLESIZE = "tableSize";


    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_DATE + " TEXT NOT NULL, "
            + COLUMN_TABLESIZE + " TEXT NOT NULL, "
            + COLUMN_FULLNAME + " TEXT NOT NULL );";

    public BookingRepositoryImpl(Context context){

        super(context, DBConstants.DATABASE_NAME,null,DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Booking findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_DATE,
                        COLUMN_TABLESIZE,
                        COLUMN_FULLNAME,
                        },
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Booking Booking = new Booking.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .fullName(cursor.getString(cursor.getColumnIndex(COLUMN_FULLNAME)))
                    .tableSize(cursor.getString(cursor.getColumnIndex(COLUMN_TABLESIZE)))
                    .bookingDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)))
                    .build();

            return Booking;
        } else {
            return null;
        }
    }

    @Override
    public Booking save(Booking entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_FULLNAME, entity.getFullName());
        values.put(COLUMN_TABLESIZE, entity.getTableSize());
        values.put(COLUMN_DATE, entity.getBookingDate());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Booking insertedEntity = new Booking.Builder()
                .copy(entity)
                .id(id)
                .build();
        return insertedEntity;
    }

    @Override
    public Booking update(Booking entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_FULLNAME, entity.getFullName());
        values.put(COLUMN_IDNUMBER, entity.getFullName());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Booking delete(Booking entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Booking> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Booking> Bookings = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Booking Booking = new Booking.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .fullName(cursor.getString(cursor.getColumnIndex(COLUMN_FULLNAME)))
                        .tableSize(cursor.getString(cursor.getColumnIndex(COLUMN_TABLESIZE)))
                        .bookingDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)))
                        .build();
                Bookings.add(Booking);
            } while (cursor.moveToNext());
        }
        return Bookings;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}
