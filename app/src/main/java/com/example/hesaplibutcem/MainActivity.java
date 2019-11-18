package com.example.hesaplibutcem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView toplamGelirler;
    TextView toplamGiderler;
    TextView netPara;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        toplamGelirler  = findViewById(R.id.toplamGelirler);
        toplamGiderler = findViewById(R.id.toplamGiderler);
        netPara = findViewById(R.id.netPara);

        toplamGelirler.setText(getString(R.string.incomes, String.valueOf(0.0)));
        toplamGiderler.setText(getString(R.string.expenses, String.valueOf(0.0)));
        netPara.setText(getString(R.string.money_amount, String.valueOf(0.0)));


        Button btn = findViewById(R.id.butcemiGoster);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BudgetActivity.class);
                startActivityForResult(intent,100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100 && resultCode==RESULT_OK){
            double gelirler = data.getDoubleExtra("gelirler",0);
            toplamGelirler.setText(getString(R.string.incomes, String.valueOf(gelirler)));
            double giderler = data.getDoubleExtra("giderler",0);
            toplamGiderler.setText(getString(R.string.expenses, String.valueOf(giderler)));
            double net = data.getDoubleExtra("net",0);
            netPara.setText(getString(R.string.money_amount, String.valueOf(net)));
        }
    }
}





//klavyeye focus yapma