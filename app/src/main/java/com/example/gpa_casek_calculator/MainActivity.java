package com.example.gpa_casek_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;


import java.io.Console;

public class MainActivity extends AppCompatActivity {

    EditText classOne;
    EditText classTwo;
    EditText classThree;
    EditText classFour;
    EditText classFive;

    Switch one;
    Switch two;
    Switch three;
    Switch four;
    Switch five;

    TextView total;

    Button btn;

    boolean isCompelete;
    double avg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        classOne = findViewById(R.id.edit_class1);
        classTwo = findViewById(R.id.edit_class2);
        classThree = findViewById(R.id.edit_class3);
        classFour = findViewById(R.id.edit_class4);
        classFive = findViewById(R.id.edit_class5);

        classOne.setOnFocusChangeListener(this::didStartTyping);
        classTwo.setOnFocusChangeListener(this::didStartTyping);
        classThree.setOnFocusChangeListener(this::didStartTyping);
        classFour.setOnFocusChangeListener(this::didStartTyping);
        classFive.setOnFocusChangeListener(this::didStartTyping);


        one = findViewById(R.id.switch_class1);
        one.setChecked(savedInstanceState == null ? true : savedInstanceState.getBoolean("sw_one"));

        two = findViewById(R.id.switch_class2);
        two.setChecked(savedInstanceState == null ? true : savedInstanceState.getBoolean("sw_two"));

        three = findViewById(R.id.switch_class3);
        three.setChecked(savedInstanceState == null ? true : savedInstanceState.getBoolean("sw_three"));

        four = findViewById(R.id.switch_class4);
        four.setChecked(savedInstanceState == null ? true : savedInstanceState.getBoolean("sw_four"));

        five = findViewById(R.id.switch_class5);
        five.setChecked(savedInstanceState == null ? true : savedInstanceState.getBoolean("sw_five"));

        classOne.setEnabled(one.isChecked());
        classTwo.setEnabled(two.isChecked());
        classThree.setEnabled(three.isChecked());
        classFour.setEnabled(four.isChecked());
        classFive.setEnabled(five.isChecked());

        total = findViewById(R.id.textView_total);

        btn = findViewById(R.id.button_main);




        this.isCompelete = savedInstanceState == null ? false : savedInstanceState.getBoolean("com");
        this.avg = savedInstanceState == null ? Double.NaN : savedInstanceState.getDouble("avg");

        if (!isCompelete){
            btn.setText(R.string.btn_calc);
            this.getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.white, null));
        }

        if (isCompelete) {
            this.getWindow().getDecorView().setBackgroundColor(avg >= 80 ? getResources().getColor(R.color.green, null) : avg > 60 ? getResources().getColor(R.color.yellow, null) : getResources().getColor(R.color.red, null ));
            btn.setText(R.string.btn_clear);
            this.total.setText(Double.toString(avg));
        }




    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){


        savedInstanceState.putBoolean("com", isCompelete);
        savedInstanceState.putDouble("avg", avg);
        savedInstanceState.putBoolean("sw_one", one.isChecked());
        savedInstanceState.putBoolean("sw_two", two.isChecked());
        savedInstanceState.putBoolean("sw_three", three.isChecked());
        savedInstanceState.putBoolean("sw_four", four.isChecked());
        savedInstanceState.putBoolean("sw_five", five.isChecked());

    super.onSaveInstanceState(savedInstanceState);

    }


    public void calcTotal(View view) {

        classOne.clearFocus();
        classTwo.clearFocus();
        classThree.clearFocus();
        classFour.clearFocus();
        classFive.clearFocus();

        if (!isCompelete) {

            try {
                double one =  this.one.isChecked() ? Double.parseDouble(classOne.getText().toString()) : Double.NaN;
                double two = this.two.isChecked() ? Double.parseDouble(classTwo.getText().toString()) : Double.NaN;
                double three = this.three.isChecked() ? Double.parseDouble(classThree.getText().toString()) : Double.NaN;
                double four = this.four.isChecked() ? Double.parseDouble(classFour.getText().toString()) : Double.NaN;
                double five = this.five.isChecked() ? Double.parseDouble(classFive.getText().toString()) : Double.NaN;

                double total = (Double.isNaN(one) ? 0 : one) + (Double.isNaN(two) ? 0 : two ) + (Double.isNaN(three) ? 0 : three) + (Double.isNaN(four) ? 0 : four) + (Double.isNaN(five) ? 0 : five);

                avg = total / ((this.one.isChecked() ? 1 : 0) + (this.two.isChecked() ? 1 : 0 ) + (this.three.isChecked() ? 1 : 0) + (this.four.isChecked() ? 1 : 0) + (this.five.isChecked() ? 1 : 0));
                if (Double.isNaN(avg) || Double.isInfinite(avg)) {throw new NumberFormatException();}

                this.total.setText(Double.toString(avg));

                this.getWindow().getDecorView().setBackgroundColor(avg >= 80 ? getResources().getColor(R.color.green, null) : avg > 60 ? getResources().getColor(R.color.yellow, null) : getResources().getColor(R.color.red, null ));

                btn.setText(R.string.btn_clear);

                isCompelete = !isCompelete;

            } catch (NumberFormatException e) {
                this.total.setText(R.string.result_error);
            }
        }

        else {
            this.getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.white, null));

            this.classOne.setText(R.string.empty);
            this.classTwo.setText(R.string.empty);
            this.classThree.setText(R.string.empty);
            this.classFour.setText(R.string.empty);
            this.classFive.setText(R.string.empty);

            this.total.setText(R.string.empty);
            btn.setText(R.string.btn_calc);

            avg = Double.NaN;

            isCompelete = !isCompelete;

        }
    }

    public void switchChanged(View view){
        Switch x =  (Switch)view;
        if (x.equals(one)) {classOne.setEnabled(one.isChecked()); classOne.setBackgroundColor(Color.TRANSPARENT);}
        if (x.equals(two)) {classTwo.setEnabled(two.isChecked()); classTwo.setBackgroundColor(Color.TRANSPARENT);}
        if (x.equals(three)) {classThree.setEnabled(three.isChecked()); classThree.setBackgroundColor(Color.TRANSPARENT);}
        if (x.equals(four)) {classFour.setEnabled(four.isChecked()); classFour.setBackgroundColor(Color.TRANSPARENT);}
        if (x.equals(five)) {classFive.setEnabled(five.isChecked()); classFive.setBackgroundColor(Color.TRANSPARENT);}

        if (this.isCompelete == true) {
            this.getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.white, null));
            this.total.setText(R.string.empty);
            btn.setText(R.string.btn_calc);

            avg = Double.NaN;

            isCompelete = !isCompelete;
        }


    }

    public void didStartTyping(View view, boolean hasFocus){
        if (!hasFocus) {

            EditText x = (EditText) view;

            try {
                double q = Double.parseDouble(x.getText().toString());
                x.setBackgroundColor(Color.TRANSPARENT);
            } catch (Exception e) {
                x.setBackgroundColor(getColor(R.color.red));
            }
                }

        if (this.isCompelete == true) {
            this.getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.white, null));
            this.total.setText(R.string.empty);
            btn.setText(R.string.btn_calc);

            avg = Double.NaN;

            isCompelete = !isCompelete;
        }

    }

}





