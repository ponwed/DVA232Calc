package se.pontus.calc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.FloatProperty;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class calc extends AppCompatActivity {

    int[] noIdArr = {R.id.btnSeven, R.id.btnEight, R.id.btnNine, R.id.btnFour, R.id.btnFive,
            R.id.btnSix, R.id.btnOne, R.id.btnTwo, R.id.btnThree, R.id.btnZero};
    int[] opIdArr = {R.id.btnPlus, R.id.btnMinus, R.id.btnTimes, R.id.btnEq, R.id.btnDiv};

    TextView display;
    Button noButtons[] = new Button[noIdArr.length];
    Button operators[] = new Button[opIdArr.length];

    String operator = "";
    String op = "";

    float result = 0;
    float leftNo, rightNo;

    boolean isSet = false;
    boolean once = false;
    boolean toggle = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        display = (TextView)findViewById(R.id.display);
        inputs();
        operate();
    }

    private void calculate(String op)
    {
        boolean error = false;

        switch (op) {
            case "+":
                result = leftNo + rightNo;
                break;
            case "-":
                result = leftNo - rightNo;
                break;
            case "/":
                if (rightNo == 0.0) {
                    error = true;
                }
                else {
                    result = leftNo / rightNo;
                }
                break;
            case "*":
                result = leftNo * rightNo;
                break;
            default:
                display.setText("");
                break;
        }
        if (!error) {
            display.setText(String.format(Locale.ENGLISH, "%.2f", result));
        }
        else {
            display.setText(R.string.maths_error);
        }
    }

    private void inputs()
    {
        for (int i = 0; i < noButtons.length; i++)
        {
            final int b = i;
            noButtons[i] = (Button)findViewById(noIdArr[i]);

            noButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!once) {
                        display.setText("");
                        once = true;
                    }
                    display.append(noButtons[b].getText());
                }
            });
        }
    }
    private void operate()
    {
        for (int i = 0; i < operators.length; i++)
        {
            final int b = i;
            operators[i] = (Button)findViewById(opIdArr[i]);
            operators[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String temp = operators[b].getText().toString();
                    if (!temp.equals("="))
                    {
                        operator = operators[b].getText().toString();
                        Log.d("Operator 1", operator);
                    }
                    else if(temp.equals("="))
                    {
                        operator = "";
                    }
                    if (!isSet && !toggle) {
                        leftNo = Float.parseFloat(display.getText().toString());
                        op = operator;
                        Log.d("Operator 2", op);
                        isSet = true;
                    }
                    else {
                        rightNo = Float.parseFloat(display.getText().toString());
                        if (!toggle) {
                            isSet = false;
                            toggle = true;
                        }
                        else {
                            leftNo = result;
                            Log.d("Operator 3", operator);
                        }
                        calculate(op);
                        op = operator;
                    }
                    Log.d("right", "" + rightNo);
                    Log.d("left", "" + leftNo);
                    once = false;
                }
            });
        }
    }
}
