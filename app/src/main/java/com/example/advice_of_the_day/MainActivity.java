package com.example.advice_of_the_day;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.advice_of_the_day.FactItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseManager dbManager;
    private RecyclerView factsRecyclerView;
    private FactsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotificationScheduler.scheduleNotification(this);        dbManager = new DatabaseManager(this);


        dbManager.open();

        factsRecyclerView = findViewById(R.id.facts_recycler_view);
        setupRecyclerView();
        displayFacts();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView() {
        factsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration divider = new DividerItemDecoration(
                factsRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL
        );
        factsRecyclerView.addItemDecoration(divider);
        adapter = new FactsAdapter(new ArrayList<>());
        factsRecyclerView.setAdapter(adapter);
    }

    private void displayFacts() {
        List<FactItem> facts = new ArrayList<>();
        facts.add(new FactItem("Сегодня в истории:\n" + dbManager.getTodayInHistoryFact(), true));

        for (String fact : dbManager.getRandomNormalFacts(4)) {
            facts.add(new FactItem(fact, false));
        }

        adapter = new FactsAdapter(facts);
        factsRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.close();
    }
}