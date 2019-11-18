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


public class AddExpenseFragment extends Fragment {

    private Communicator communicator;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View addExpenseView = inflater.inflate(R.layout.fragment_add_expense, container, false);
        communicator = (Communicator) getActivity();
        final EditText editGiderAd = addExpenseView.findViewById(R.id.editGiderAd);
        final EditText editGiderTutar = addExpenseView.findViewById(R.id.editGiderTutar);


        final EditText editGiderTarih = addExpenseView.findViewById(R.id.editGiderTarih);
        editGiderTarih.setOnClickListener(new View.OnClickListener() {
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
                                editGiderTarih.setText(dayOfMonth + "/" + month + "/" + year);
                            }
                        }, yil, ay, gun);

                dpd.setButton(DatePickerDialog.BUTTON_POSITIVE, "Seç", dpd);
                dpd.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", dpd);
                dpd.show();
            }
        });
        editGiderTarih.setInputType(InputType.TYPE_NULL);

        Button giderKaydet = addExpenseView.findViewById(R.id.giderKaydet);
        giderKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editGiderAd.getText().toString().equals("") || editGiderTarih.getText().toString().equals("") || editGiderTutar.getText().toString().equals("")) {
                    if (editGiderAd.getText().toString().equals("")) {
                        editGiderAd.setError("Lutfen bos birakmayin!");
                    }
                    if (editGiderTarih.getText().toString().equals("")) {
                        editGiderTarih.setError("Lutfen bos birakmayin!");
                    }
                    if (editGiderTutar.getText().toString().equals("")) {
                        editGiderTutar.setError("Lutfen bos birakmayin!");
                    }
                    return;
                } else {
                    try{
                        Budget budget = new Budget(editGiderAd.getText().toString(), editGiderTarih.getText().toString(), Double.parseDouble(editGiderTutar.getText().toString()), false);
                        communicator.sendData(budget);
                        addExpenseView.setVisibility(View.GONE);
                        communicator.visibilty(addExpenseView.getVisibility());
                    }catch (NumberFormatException e){
                        editGiderTutar.setError("Lutfen sayi giriniz!");
                    }

                }
            }
        });
        return addExpenseView;
    }
}
