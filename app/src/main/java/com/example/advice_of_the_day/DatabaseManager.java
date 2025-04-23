package com.example.advice_of_the_day;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DatabaseManager {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseManager(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public String getTodayInHistoryFact() {
        String currentDate = new SimpleDateFormat("dd.MM", Locale.getDefault()).format(new Date());

        Cursor cursor = database.query(
                DatabaseHelper.TABLE_DATE_IN_HISTORY,
                new String[]{DatabaseHelper.COLUMN_DATE_FACT},
                DatabaseHelper.COLUMN_DATE + " = ?",
                new String[]{currentDate},
                null, null, null
        );

        if (cursor != null && cursor.moveToFirst()) {
            String fact = cursor.getString(0);
            cursor.close();
            return fact;
        }

        if (cursor != null) {
            cursor.close();
        }
        return "Сегодня нет исторических событий в базе данных";
    }

    public List<String> getRandomNormalFacts(int count) {
        List<String> facts = new ArrayList<>();

        Cursor cursor = database.rawQuery(
                "SELECT " + DatabaseHelper.COLUMN_NORMAL_FACT +
                        " FROM " + DatabaseHelper.TABLE_NORMAL_FACTS +
                        " ORDER BY RANDOM() LIMIT " + count, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                facts.add(cursor.getString(0));
            } while (cursor.moveToNext());
            cursor.close();
        }

        return facts;
    }

    public String[] getRandomNotification() {
        Cursor cursor = database.rawQuery(
                "SELECT " + DatabaseHelper.COLUMN_UPPER_TEXT + ", " + DatabaseHelper.COLUMN_DOWN_TEXT +
                        " FROM " + DatabaseHelper.TABLE_NOTIF +
                        " ORDER BY RANDOM() LIMIT 1", null);

        if (cursor != null && cursor.moveToFirst()) {
            String[] notification = new String[]{
                    cursor.getString(0),
                    cursor.getString(1)
            };
            cursor.close();
            return notification;
        }

        if (cursor != null) {
            cursor.close();
        }
        return new String[]{"Уведомление", "Нет данных для уведомления"};
    }
}