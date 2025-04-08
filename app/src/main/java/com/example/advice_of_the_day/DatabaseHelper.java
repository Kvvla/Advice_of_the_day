package com.example.advice_of_the_day;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "AdviceOfTheDay.db";
    private static final int DATABASE_VERSION = 1;

    // Таблица "День в истории"
    public static final String TABLE_DATE_IN_HISTORY = "Dateinhistory";
    public static final String COLUMN_DATE_ID = "id";
    public static final String COLUMN_DATE = "Date";
    public static final String COLUMN_DATE_FACT = "Fact";

    // Таблица обычных фактов
    public static final String TABLE_NORMAL_FACTS = "Normalfacts";
    public static final String COLUMN_NORMAL_ID = "Id";
    public static final String COLUMN_NORMAL_FACT = "Fact";

    // Таблица уведомлений
    public static final String TABLE_NOTIF = "Notif";
    public static final String COLUMN_NOTIF_ID = "Id";
    public static final String COLUMN_UPPER_TEXT = "UpperText";
    public static final String COLUMN_DOWN_TEXT = "DownText";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Создание таблиц
        String createDateTable = "CREATE TABLE " + TABLE_DATE_IN_HISTORY + " (" +
                COLUMN_DATE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DATE + " TEXT NOT NULL, " +
                COLUMN_DATE_FACT + " TEXT NOT NULL)";
        db.execSQL(createDateTable);

        String createNormalTable = "CREATE TABLE " + TABLE_NORMAL_FACTS + " (" +
                COLUMN_NORMAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NORMAL_FACT + " TEXT NOT NULL)";
        db.execSQL(createNormalTable);

        String createNotifTable = "CREATE TABLE " + TABLE_NOTIF + " (" +
                COLUMN_NOTIF_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_UPPER_TEXT + " TEXT NOT NULL, " +
                COLUMN_DOWN_TEXT + " TEXT NOT NULL)";
        db.execSQL(createNotifTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATE_IN_HISTORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NORMAL_FACTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIF);
        onCreate(db);
    }
}