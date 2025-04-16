package com.example.advice_of_the_day;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.advice_of_the_day.FactItem;
import com.example.advice_of_the_day.R;

import java.util.List;

public class FactsAdapter extends RecyclerView.Adapter<FactsAdapter.FactViewHolder> {
    private List<FactItem> facts;

    public FactsAdapter(List<FactItem> facts) {
        this.facts = facts;
    }

    @NonNull
    @Override
    public FactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fact, parent, false);
        return new FactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FactViewHolder holder, int position) {
        FactItem fact = facts.get(position);
        holder.factText.setText(fact.getFactText());


        if (fact.isHistoryFact()) {
            holder.factText.setTextSize(18);
            holder.factText.setBackgroundResource(R.color.history_fact_bg);
        } else {
            holder.factText.setTextSize(16);
            holder.factText.setBackgroundResource(R.color.normal_fact_bg);
        }
    }

    @Override
    public int getItemCount() {
        return facts.size();
    }

    public static class FactViewHolder extends RecyclerView.ViewHolder {
        TextView factText;

        public FactViewHolder(@NonNull View itemView) {
            super(itemView);
            factText = itemView.findViewById(R.id.fact_text);
        }
    }
}