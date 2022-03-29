package com.example.converter_lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    double number_meters = 0;
    double coefficient = 1;

    TextView meter_num;
    TextView centimeter_num;
    TextView kilometer_num;
    TextView inch_num;
    TextView foot_num;
    TextView mile_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        meter_num = findViewById(R.id.meter_num);
        centimeter_num = findViewById(R.id.centimeter_num);
        kilometer_num = findViewById(R.id.kilometer_num);
        mile_num = findViewById(R.id.mile_num);
        foot_num = findViewById(R.id.foot_num);
        inch_num = findViewById(R.id.inch_num);

        Spinner spinner = findViewById(R.id.spinner);
        String[] languages = getResources().getStringArray(R.array.languages);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, languages);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);

                Locale locale;
                if (item.equals(languages[0])) {
                    locale = new Locale("ru");
                } else {
                    locale = new Locale("en");
                }
                changeLocale(locale);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);

        editText = findViewById(R.id.enter);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().equals("")) {
                    number_meters = 0;
                } else {
                    number_meters = Double.parseDouble(editable.toString()) * coefficient;
                }
                setResults();
            }
        });
    }

    private void changeLocale(Locale locale) {
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources()
                .updateConfiguration(configuration,
                        getBaseContext()
                                .getResources()
                                .getDisplayMetrics());
        setTitle(R.string.app_name);

        TextView lang = findViewById(R.id.language);
        lang.setText(R.string.language);

        RadioButton meters = findViewById(R.id.meter);
        meters.setText(R.string.meter);

        RadioButton centimeters = findViewById(R.id.centimeter);
        centimeters.setText(R.string.centimeter);

        RadioButton kilometers = findViewById(R.id.kilometer);
        kilometers.setText(R.string.kilometer);

        RadioButton feet = findViewById(R.id.foot);
        feet.setText(R.string.foot);

        RadioButton inches = findViewById(R.id.inch);
        inches.setText(R.string.inch);

        RadioButton miles = findViewById(R.id.mile);
        miles.setText(R.string.mile);

        EditText text = findViewById(R.id.enter);
        text.setHint(R.string.enter);

        TextView meter_res = findViewById(R.id.meter_res);
        meter_res.setText(R.string.meter);

        TextView centimeter_res = findViewById(R.id.centimeter_res);
        centimeter_res.setText(R.string.centimeter);

        TextView foot_res = findViewById(R.id.foot_res);
        foot_res.setText(R.string.foot);

        TextView kilometer_res = findViewById(R.id.kilometer_res);
        kilometer_res.setText(R.string.kilometer);

        TextView inch_res = findViewById(R.id.inch_res);
        inch_res.setText(R.string.inch);

        TextView mile_res = findViewById(R.id.mile_res);
        mile_res.setText(R.string.mile);
    }

    @SuppressLint("NonConstantResourceId")
    public void onRadioButtonClicked(View view) {
        switch (view.getId()) {
            case R.id.meter:
                coefficient = Double.parseDouble(getResources().getString(R.string.meter_to_meter));
                break;
            case R.id.centimeter:
                coefficient = Double.parseDouble(getResources().getString(R.string.centimeter_to_meter));
                break;
            case R.id.kilometer:
                coefficient = Double.parseDouble(getResources().getString(R.string.kilometer_to_meter));
                break;
            case R.id.foot:
                coefficient = Double.parseDouble(getResources().getString(R.string.foot_to_meter));
                break;
            case R.id.inch:
                coefficient = Double.parseDouble(getResources().getString(R.string.inch_to_meter));
                break;
            case R.id.mile:
                coefficient = Double.parseDouble(getResources().getString(R.string.mile_to_meter));
                break;
            default:
                break;
        }
        String string = editText.getText().toString();

        if (string.equals("")) {
            number_meters = 0;
        } else {
            number_meters = Double.parseDouble(string) * coefficient;
        }

        setResults();
    }

    private void setResults() {
        meter_num.setText(getStringResult(R.string.meter_to_meter));
        centimeter_num.setText(getStringResult(R.string.meter_to_centimeter));
        kilometer_num.setText(getStringResult(R.string.meter_to_kilometer));
        foot_num.setText(getStringResult(R.string.meter_to_foot));
        inch_num.setText(getStringResult(R.string.meter_to_inch));
        mile_num.setText(getStringResult(R.string.meter_to_mile));
    }

    private String getStringResult(int coefficient) {
        NumberFormat nf = new DecimalFormat("#.####");
        return nf.format(number_meters * Double.parseDouble(getResources().getString(coefficient)));
    }
}