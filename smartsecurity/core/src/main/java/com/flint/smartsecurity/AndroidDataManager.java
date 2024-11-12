package com.flint.smartsecurity;

import com.flint.smartsecurity.android.DatabaseHelper;
import com.flint.smartsecurity.android.FingerprintDataManager;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class AndroidDataManager implements IDataManager {
    private FingerprintDataManager fingerprintDataManager;

    public AndroidDataManager(Context context) {
        fingerprintDataManager = new FingerprintDataManager(context);
    }

    @Override
    public List<AccessRecord> getAccessRecords() {
        List<AccessRecord> records = new ArrayList<>();
        Cursor cursor = fingerprintDataManager.getAccessRecords();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String userId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_ID));
                long timestamp = cursor.getLong(cursor.getColumnIndex(DatabaseHelper.COLUMN_TIMESTAMP));
                long duration = cursor.getLong(cursor.getColumnIndex(DatabaseHelper.COLUMN_DURATION));
                records.add(new AccessRecord(userId, timestamp, duration));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return records;
    }

    @Override
    public void close() {
        // Add any cleanup code here
    }
}
