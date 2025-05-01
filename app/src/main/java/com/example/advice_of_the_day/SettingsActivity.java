package com.example.advice_of_the_day;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class SettingsActivity extends AppCompatActivity {
    private SharedPreferences prefs;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        findViewById(R.id.set_notification_time).setOnClickListener(v -> showTimePicker());
    }

    private void showTimePicker() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePicker = new TimePickerDialog(this, this::onTimeSet, hour, minute, true);
        timePicker.show();
    }

    private void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Сохраняем время
        prefs.edit()
                .putInt("notification_hour", hourOfDay)
                .putInt("notification_minute", minute)
                .apply();

        // Перезапускаем уведомления с новым временем
        NotificationScheduler.scheduleNotification(this, hourOfDay, minute);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Показываем текущее время уведомлений
        int hour = prefs.getInt("notification_hour", 7);
        int minute = prefs.getInt("notification_minute", 0);
        // Можно обновить TextView с текущим временем
    }
}