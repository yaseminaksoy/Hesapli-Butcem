package com.example.hesaplibutcem;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class BudgetAdapter extends BaseAdapter {
    private LayoutInflater budgetInflater;
    private List<Budget> budgetList;

    public BudgetAdapter(Activity activity, List<Budget> budgetList) {
        budgetInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.budgetList = budgetList;
    }

    @Override
    public int getCount() {
        return budgetList.size();
    }

    @Override
    public Object getItem(int i) {
        return budgetList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View lineView;
        lineView = budgetInflater.inflate(R.layout.budget_layout, null);
        TextView textViewName = (TextView) lineView.findViewById(R.id.textViewName);
        TextView textViewDate = (TextView) lineView.findViewById(R.id.textViewDate);
        TextView textViewCost = (TextView) lineView.findViewById(R.id.textViewCost);

        Budget budget = budgetList.get(i);
        textViewName.setText(budget.getName());
        textViewDate.setText(budget.getDate());
        textViewCost.setText(budget.getCost() + "");
        return lineView;
    }
}








