package com.example.firstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Button changebutton;
    private TextView firsttext;
    private TextView errormsg;
    private EditText imputText;
    private Spinner dropdown;
    private int zaehler = 1;
    private boolean allesgut = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changebutton = (Button) findViewById(R.id.sendbutton);
        firsttext = (TextView) findViewById(R.id.firsttext);
        errormsg = (TextView) findViewById(R.id.errormsg);
        imputText = (EditText) findViewById(R.id.imputtext);
        dropdown = (Spinner) findViewById(R.id.dropdown);

        firsttext.setMovementMethod(new ScrollingMovementMethod());


        //Button klick method
        changebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String imputTextString = imputText.getText().toString();
                String dropdownString = dropdown.getSelectedItem().toString();

                if (imputTextString.equals("")){
                    imputText.setText("Zitat Eingeben");
                }

                    //shor error masage
                if (imputTextString.equals("Zitat Eingeben") || dropdownString.equals("Name")) {

                    errormsg.setVisibility(View.VISIBLE);

                    //hilight Imput fenster
                    if (imputTextString.equals("Zitat Eingeben")) {
                        imputText.setTextColor(0xFF0000);
                    }
                    //hilight dropdownfenster
                    if (dropdownString.equals("Name")) {
                        dropdown.setBackgroundColor(0xFF0000);
                    }

                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        public void run() {
                            errormsg.setVisibility(View.INVISIBLE);
                        }
                    }, 2000); // 5 Sekunden = 5000 Millisekunden

                }else{
                    errormsg.setVisibility(View.INVISIBLE);
                    firsttext.append(dropdownString + ": " + imputTextString + " #" + zaehler + "\n");
                    zaehler++;
                }
            }
        });

        //Button Info Text Deletion
        imputText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    imputText.setText("");
                }
            }
        });

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Hier können Sie die Aktionen definieren, die bei Auswahländerungen ausgeführt werden sollen
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Hier können Sie die Aktionen definieren, die ausgeführt werden sollen, wenn kein Element ausgewählt ist
            }
        });

    }
}