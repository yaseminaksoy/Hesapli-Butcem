package com.example.hesaplibutcem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class BudgetActivity extends AppCompatActivity implements Communicator {
    List<Budget> list = new ArrayList<>();
    ListView listView;
    //ArrayAdapter<String> arrayAdapter;
    BudgetAdapter adapter;
    Button btn_gelirEkle;
    Button btn_giderEkle;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);


        listView = findViewById(R.id.listView);
        //arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        adapter = new BudgetAdapter(this, list);

        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.listview_header, listView, false);
        listView.addHeaderView(header);
        listView.setAdapter(adapter);

        btn_gelirEkle = findViewById(R.id.gelirEkle);
        btn_giderEkle = findViewById(R.id.giderEkle);
        btn_gelirEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment gelirFragment = new AddIncomeFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayout, gelirFragment);
                transaction.commit();
                btn_gelirEkle.setVisibility(View.INVISIBLE);
                btn_giderEkle.setVisibility(View.INVISIBLE);
            }
        });

        btn_giderEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment giderFragment = new AddExpenseFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayout, giderFragment);
                transaction.commit();
                btn_giderEkle.setVisibility(View.INVISIBLE);
                btn_gelirEkle.setVisibility(View.INVISIBLE);
            }
        });

    }


    @Override
    public void sendData(Budget budget) {
        textView = findViewById(R.id.netParaniz);
        double total = Double.parseDouble(textView.getText().toString());

        if (budget.isIncome()) {
            total += budget.getCost();
        } else {
            total -= budget.getCost();
        }
        list.add(budget);
        textView.setText(total + "");
        adapter.notifyDataSetChanged();
    }


    @Override
    public void visibilty(int deger) {
        if (deger == 8) {
            btn_gelirEkle.setVisibility(View.VISIBLE);
            btn_giderEkle.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        double budgets = budgets();
        double incomes = incomes();
        double net = budgets - incomes;
        Bundle bundle = new Bundle();
        Intent i = new Intent();
        bundle.putDouble("gelirler", budgets);
        bundle.putDouble("giderler", incomes);
        bundle.putDouble("net", net);
        i.putExtras(bundle);
        if (getParent() == null) {
            setResult(Activity.RESULT_OK, i);
        } else {
            getParent().setResult(Activity.RESULT_OK, i);
        }
        super.onBackPressed();
    }
    public double budgets() {
        double budgets = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isIncome())
                budgets += list.get(i).getCost();
        }
        return budgets;
    }
    public double incomes() {
        double incomes = 0;
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).isIncome())
                incomes += list.get(i).getCost();
        }
        return incomes;
    }
}

// startactivityForResult
// sharedPreferences - kayit etme icin