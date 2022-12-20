package com.example.firstapp;

import android.content.DialogInterface;
import android.graphics.Color;
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

        firsttext.setMovementMethod(new ScrollingMovementMethod());


        //Button klick method
        sendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String imputTextString = imputText.getText().toString();
                String dropdownString = dropdown.getSelectedItem().toString();


                    //show error masage
                if (imputTextString.trim().isEmpty() || imputTextString.equals("Zitat Eingeben") || dropdownString.equals("Name")) {

                    errormsg.setVisibility(View.VISIBLE);

                    //hilight Imput fenster
                    if (imputTextString.trim().isEmpty() || imputTextString.equals("Zitat Eingeben")) {
                        //Wird ausgeführt wenn Zitat nich stimmt
                        imputText.setBackgroundColor(Color.YELLOW);
                    }
                    //hilight dropdownfenster
                    if (dropdownString.equals("Name")) {
                        //Wird ausgeführt wenn Dropbox nicht stimmt
                        dropdown.setBackgroundColor(Color.YELLOW);
                    }

                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        public void run() {
                            errormsg.setVisibility(View.INVISIBLE);
                        }
                    }, 2000); // 5 Sekunden = 5000 Millisekunden

                //wenn knopf durch geht
                }else{
                    //Testet nach costum names
                    if (costumnamebo == true){
                        firsttext.append(costumnametext + ": " + imputTextString + " #" + zaehler + "\n");
                        dropdown.setVisibility(View.VISIBLE);
                        costumname.setVisibility(View.INVISIBLE);
                        dropdown.setSelection(0);
                    }else {
                        firsttext.append(dropdownString + ": " + imputTextString + " #" + zaehler + "\n");
                    }
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
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}