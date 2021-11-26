package com.tkf.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {

    //intialize
    TextView workingsTV;
    TextView resultTV;
    String workings = "";
    String formula = "";
    String tempFormula = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        initTextView();
    }
    boolean leftBracket = true;
    private void setWorkings(String givenValue)
    {
        workings = workings + givenValue;
        workingsTV.setText(workings);
    }
    private void initTextView()
    {
        workingsTV = findViewById(R.id.workingsTV);
        resultTV = findViewById(R.id.resultTextView);
    }
    public void equalOnClick(View view)
    {
        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        checkForPowerOf();
        try
        {
            result = (Double)engine.eval(formula);
        }
        catch (ScriptException e)
        {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }

        if(result != null)
        {
            resultTV.setText(String.valueOf(result.doubleValue()));
        }
    }

    private void checkForPowerOf()
    {
        ArrayList<Integer> indexPowers = new ArrayList<>();
        for(int i=0;i < workings.length();i++)
        {
            if(workings.charAt(i) == '^')
            {
                indexPowers.add(i);
            }
            formula = workings;
            tempFormula = workings;
            for(Integer index:indexPowers)
            {
                changeFormula(index);
            }
            formula = tempFormula;
        }
    }

    private void changeFormula(Integer index)
    {
        String numberLeft = "";
        String numberRight = "";

        for(int i = index + 1; i < workings.length() ; i++ )
        {
            if(isNumeric(workings.charAt(i)))
            {
                numberRight = numberRight + workings.charAt(i);
            }
            else break;
        }
        for(int i = index - 1; i >= 0 ; i-- )
        {
            if(isNumeric(workings.charAt(i)))
            {
                numberLeft = numberLeft + workings.charAt(i);
            }
            else break;
        }
        String original = numberLeft + '^' + numberRight;
        String changed = "Math.pow(" + numberLeft + "," + numberRight + ")";
        tempFormula = tempFormula.replace(original,changed);
    }

    private boolean isNumeric(char c)
    {
        return (c <= '9' && c >= '0') || c == '.';
    }



    public void clearOnClick(View view)
    {
        workingsTV.setText("");
        workings = "";
        resultTV.setText("");
        leftBracket = true;
    }


    public void bracketsOnClick(View view)
    {
        if(leftBracket)
        {
            setWorkings("(");
            leftBracket = false;
        }
        else
        {
            setWorkings(")");
            leftBracket = true;
        }
    }

    public void powerOfOnClick(View view)
    {
        setWorkings("^");
        
    }

    public void divisionOnClick(View view)
    {
        setWorkings("/");
    }

    public void sevenOnClick(View view)
    {
        setWorkings("7");
    }

    public void eightOnclick(View view)
    {
        setWorkings("8");
    }

    public void nineOnClick(View view)
    {
        setWorkings("9");
    }

    public void timesOnClick(View view)
    {
        setWorkings("*");
    }

    public void fourOnClick(View view)
    {
        setWorkings("4");
    }

    public void fiveOnClick(View view)
    {
        setWorkings("5");
    }

    public void sixOnClick(View view)
    {
        setWorkings("6");
    }

    public void minusOnClick(View view)
    {
        setWorkings("-");
    }

    public void oneOnClick(View view)
    {
        setWorkings("1");
    }

    public void twoOnClick(View view)
    {
        setWorkings("2");
    }

    public void threeOnClick(View view)
    {
        setWorkings("3");
    }

    public void plusOnClick(View view)
    {
        setWorkings("+");
    }

    public void decimalOnClick(View view)
    {
        setWorkings(".");
    }

    public void zeroOnClick(View view)
    {
        setWorkings("0");
    }
    
}