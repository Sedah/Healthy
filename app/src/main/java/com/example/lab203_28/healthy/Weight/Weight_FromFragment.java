package com.example.lab203_28.healthy.Weight;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab203_28.healthy.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Weight_FromFragment extends Fragment {
    FirebaseFirestore mdb = FirebaseFirestore.getInstance();
    FirebaseAuth mauth = FirebaseAuth.getInstance();
    Button buttonSave;
    Button buttonBack;
    EditText dateInput;
    EditText weightInput;
    ArrayList<Weight> weights = new ArrayList<>();
    Calendar calendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            updateLabel();
        }
    };
    private void updateLabel() {
        EditText weighDateForm = getView().findViewById(R.id.weight_from_date);
        SimpleDateFormat fmt = new SimpleDateFormat("dd MMM yyyy", Locale.US);
        weighDateForm.setText(fmt.format(calendar.getTime()));
    }



    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight_from, container, false);
    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initBackBtn();
        initSaveBtn();
        initDatePicker();

    }
    void initBackBtn(){
        TextView _backBtn = (TextView) getView().findViewById(R.id.weight_from_backBnt);
        _backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("USER", "GOTO WEIGHT");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new WeightFragment()).commit();
            }
        });
    }
    void initSaveBtn() {
        Button _saveBtn = getView().findViewById(R.id.weight_from_saveBnt);
        _saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("WEIGHT FORM", "SAVED");
                addObj();
            }
        });
    }
    void initDatePicker() {
        EditText weighDateForm = getView().findViewById(R.id.weight_from_date);
        weighDateForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), date,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
    void addObj() {
        EditText _date = getView().findViewById(R.id.weight_from_date);
        EditText _weight = getView().findViewById(R.id.weight_from_weight);
        String _dateStr = _date.getText().toString();
        Log.d("WEIGHT FORM", _dateStr);
        int _weightInt = Integer.parseInt(_weight.getText().toString());
        Weight _weightObj = new Weight(_dateStr, _weightInt);
        mdb.collection("myfitness")
                .document(mauth.getCurrentUser().getUid())
                .collection("weight")
                .document(_dateStr)
                .set(_weightObj)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(
                                getActivity(),"Saved",Toast.LENGTH_SHORT
                        ).show();
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new WeightFragment()).commit();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("WEIGHT FORM", e.getMessage());
                Toast.makeText(
                        getActivity(),"Failed",Toast.LENGTH_SHORT
                ).show();
            }
        });
    }



}
