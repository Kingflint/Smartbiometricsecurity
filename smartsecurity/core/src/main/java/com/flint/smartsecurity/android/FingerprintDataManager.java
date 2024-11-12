package com.flint.smartsecurity.android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class FingerprintDataManager {
    private DatabaseHelper dbHelper;

    public FingerprintDataManager(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void addAccessRecord(String userId, long timestamp, long duration) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_USER_ID, userId);
        values.put(DatabaseHelper.COLUMN_TIMESTAMP, timestamp);
        values.put(DatabaseHelper.COLUMN_DURATION, duration);
        db.insert(DatabaseHelper.TABLE_NAME, null, values);
        db.close();
    }

    public Cursor getAccessRecords() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return db.query(DatabaseHelper.TABLE_NAME, null, null, null, null, null, null);
    }
}
