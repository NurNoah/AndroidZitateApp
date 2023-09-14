package com.example.firstapp;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
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

    private Button sendbutton;
    private TextView firsttext;
    private TextView errormsg;
    private TextView costumname;
    private View inputViewerror;
    private View dropdownerror;
    private EditText imputText;
    private Spinner dropdown;
    private String costumnametext;
    private int zaehler = 1;
    private boolean costumnamebo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Objekte verknüpfen
        sendbutton = (Button) findViewById(R.id.sendbutton);
        firsttext = (TextView) findViewById(R.id.firsttext);
        errormsg = (TextView) findViewById(R.id.errormsg);
        costumname = (TextView) findViewById(R.id.costumname);
        imputText = (EditText) findViewById(R.id.imputtext);
        dropdown = (Spinner) findViewById(R.id.dropdown);
        inputViewerror = (View) findViewById(R.id.inputerror);
        dropdownerror = (View) findViewById(R.id.dropdownerror);

        // firsttext.setMovementMethod(new ScrollingMovementMethod()); // test

        //Button klick method
        sendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String imputTextString = imputText.getText().toString();
                String dropdownString = dropdown.getSelectedItem().toString();

                    //show error masage
                if (imputTextString.trim().isEmpty() || imputTextString.equals("Zitat Eingeben") || dropdownString.equals("Name")) {

                    errormsg.setVisibility(View.VISIBLE);
                    dropdown.setVisibility(View.VISIBLE);
                    costumname.setVisibility(View.INVISIBLE);
                    if(dropdownString.equals("Andere")) {
                        dropdown.setSelection(0);
                    }
                    //hilight Imput fenster
                    if (imputTextString.trim().isEmpty() || imputTextString.equals("Zitat Eingeben")) {

                        inputViewerror.setVisibility(View.VISIBLE);

                    }
                    //hilight dropdownfenster
                    if (dropdownString.equals("Name")) {

                        dropdownerror.setVisibility(View.VISIBLE);

                    }

                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        public void run() {
                            errormsg.setVisibility(View.INVISIBLE);
                        }
                    }, 2000); // 2 Sekunden = 2000 Millisekunden
                    Timer timer2 = new Timer();
                    timer.schedule(new TimerTask() {
                        public void run() {
                            dropdownerror.setVisibility(View.INVISIBLE);
                        }
                    }, 2000); // 2 Sekunden = 2000 Millisekunden
                    Timer timer3 = new Timer();
                    timer.schedule(new TimerTask() {
                        public void run() {
                            inputViewerror.setVisibility(View.INVISIBLE);
                        }
                    }, 2000); // 2 Sekunden = 2000 Millisekunden

                //wenn knopf durch geht
                }else{
                    //Testet nach costum names
                    if (costumnamebo == true){
                        firsttext.append(costumnametext + ": " + imputTextString + " #" + zaehler + "\n");
                        dropdown.setVisibility(View.VISIBLE);
                        costumname.setVisibility(View.INVISIBLE);

                    }else {
                        firsttext.append(dropdownString + ": " + imputTextString + " #" + zaehler + "\n");
                    }
                    dropdown.setSelection(0);
                    errormsg.setVisibility(View.INVISIBLE);
                    zaehler++;
                    dropdown.setVisibility(View.VISIBLE);
                    costumname.setVisibility(View.INVISIBLE);
                    imputText.setText("");
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
                String dropdownString = dropdown.getSelectedItem().toString();
                if (dropdownString.equals("Andere")){

                    popout();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Hier können Sie die Aktionen definieren, die ausgeführt werden sollen, wenn kein Element ausgewählt ist
            }
        });
    }

    public void popout() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Name Eingeben");

        // Füge ein EditText-Feld hinzu
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                costumnametext = input.getText().toString();
                dropdown.setVisibility(View.INVISIBLE);
                costumname.setText(costumnametext);
                costumname.setVisibility(View.VISIBLE);
                costumnamebo = true;

                // Fehlermeldung Andere Empty

                if (costumnametext.trim().isEmpty()){
                    errormsg.setVisibility(View.VISIBLE);
                    dropdown.setVisibility(View.VISIBLE);
                    dropdown.setSelection(0);

                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        public void run() {
                            errormsg.setVisibility(View.INVISIBLE);
                        }
                    }, 2000); // 2 Sekunden = 2000 Millisekunden
                }

            }
        });
        //Error Message Andere
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}