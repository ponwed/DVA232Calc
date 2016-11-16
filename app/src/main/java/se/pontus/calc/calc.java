package se.pontus.calc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class calc extends AppCompatActivity {

    TextView display;
    int[] noIdArr = {R.id.btnSeven, R.id.btnEight, R.id.btnNine, R.id.btnFour, R.id.btnFive,
            R.id.btnSix, R.id.btnOne, R.id.btnTwo, R.id.btnThree, R.id.btnZero};
    int[] opIdArr = {R.id.btnPlus, R.id.btnMinus, R.id.btnTimes, R.id.btnEq, R.id.btnDiv};

    Button noButtons[] = new Button[noIdArr.length];
    Button operators[] = new Button[opIdArr.length];

    float num1, num2;
    String operator;
    float result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        num1=num2 = -1;
        display = (TextView)findViewById(R.id.display);
        inputs();
        operators();
    }

    private void calculate()
    {
        switch (operator)
        {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            default:
                display.setText("Error");
                break;
        }
        display.setText(Float.toString(result));
        num1=num2 = -1;
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
                    display.append(noButtons[b].getText());
                }
            });
        }
    }
    private void operators()
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
                    }
                    if (num1 == -1)
                    {
                        num1 = Float.parseFloat(display.getText().toString());
                    }
                    else if (num2 == -1)
                    {
                        num2 = Float.parseFloat(display.getText().toString());
                    }
                    if (num1 != -1 && num2 != -1) {
                        calculate();
                    }
                    else
                    {
                        display.setText("");
                    }
                }
            });
        }
    }
}
