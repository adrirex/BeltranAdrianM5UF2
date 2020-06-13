package com.example.beltranadrianm5uf2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    CheckBox checkBox;
    ArrayAdapter adp;
    EditText editText;
    TextView textView;
    Spinner spinner;
    Button buton;
    String bonificacion;
    Boolean fct = false;
    Integer descuento, calculoFinal, ufs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        textView = (TextView) findViewById(R.id.textView5);
        spinner = (Spinner) findViewById(R.id.spinner);
        buton = (Button) findViewById(R.id.button);

        final ArrayList<String> bonificacio = new ArrayList<>();

        bonificacio.add("0");
        bonificacio.add("50");
        bonificacio.add("100");

        adp = new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, bonificacio);

        spinner.setAdapter(adp);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bonificacion = (String) spinner.getAdapter().getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().equals("0") || editText.getText().toString().equals("1")){
                    if(checkBox.isChecked()){
                        calculoFinal = 0;
                        fct = true;
                    }else{
                        fct = false;
                    }
                }else{
                    checkBox.setChecked(false);
                    Toast.makeText(getApplicationContext(),
                            "Opcio disponible nomes amb 0 o 1 UF!!!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( editText.getText().toString().length() == 0 ) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Numero UF's requerido!", Toast.LENGTH_LONG);
                    toast.show();
                }
                else {
                    ufs = Integer.parseInt(editText.getText().toString());

                    if (checkBox.isChecked() && ufs > 1) {
                        checkBox.setChecked(false);
                        Toast.makeText(getApplicationContext(),
                                "Opcio disponible nomes amb 0 o 1 UF!!!", Toast.LENGTH_SHORT).show();
                    }
                    if (!fct && ufs >= 0 || ufs > 1) {
                        calculoFinal = precionConDescuento(bonificacion, ufs);
                    }
                    textView.setText("Preu: " + Integer.toString(calculoFinal));
                }
            }
        });


    }

    public Integer precionConDescuento(String bonificacion, Integer ufs) {
        Integer calculo = ufs * 30;
        //La matricula no puede superior a 360, entiendo que hace referencia a antes del descuento. En el caso contrario seria cambiar calculo por calculoFinal, esbribirlo al final y devolver el nuevo calculoFinal.
        if (calculo > 360) {
            calculo = 360;
        }
        if (bonificacion.equals("0")) {
            descuento = 0;
        }
        if (bonificacion.equals("50")) {
            descuento = calculo / 2;
        }
        if (bonificacion.equals("100")) {
            descuento = calculo;
        }
        return calculoFinal = calculo - descuento;
    }
}
