package com.example.hesaplibutcem;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import java.util.Calendar;


public class AddIncomeFragment extends Fragment {
    private Communicator communicator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View addBudgetView = inflater.inflate(R.layout.fragment_add_income, container, false);
        communicator = (Communicator) getActivity();
        final EditText editAd = addBudgetView.findViewById(R.id.editAd);
        final EditText editTutar = addBudgetView.findViewById(R.id.editTutar);
        final EditText editTarih = addBudgetView.findViewById(R.id.editTarih);



        editTarih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar takvim = Calendar.getInstance();
                int yil = takvim.get(Calendar.YEAR);
                int ay = takvim.get(Calendar.MONTH);
                int gun = takvim.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                month += 1;
                                editTarih.setText(dayOfMonth + "/" + month + "/" + year);
                            }
                        }, yil, ay, gun);

                dpd.setButton(DatePickerDialog.BUTTON_POSITIVE, "Seç", dpd);
                dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", dpd);
                dpd.show();
            }
        });
        editTarih.setInputType(InputType.TYPE_NULL);


        Button gelirKaydet = addBudgetView.findViewById(R.id.gelirKaydet);
        gelirKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editAd.getText().toString().equals("") || editTarih.getText().toString().equals("")
                        || editTutar.getText().toString().equals("")) {
                    if (editAd.getText().toString().equals("")) {
                        editAd.setError("Lutfen bos birakmayin!");
                    }
                    if (editTutar.getText().toString().equals("")) {
                        editTutar.setError("Lutfen bos birakmayin!");
                    }
                    if (editTarih.getText().toString().equals("")) {
                        editTarih.setError("Lutfen bos birakmayin!");
                    }
                    return;
                } else {
                    try {
                        Budget budget = new Budget(editAd.getText().toString(), editTarih.getText().toString(),
                                Double.parseDouble(editTutar.getText().toString()), true);

                        communicator.sendData(budget);
                        addBudgetView.setVisibility(View.GONE);
                        communicator.visibilty(addBudgetView.getVisibility());
                    } catch (NumberFormatException e) {
                        editTutar.setError("Lutfen sayi giriniz!");
                    }
                }
            }
        });
        return addBudgetView;
    }
}
