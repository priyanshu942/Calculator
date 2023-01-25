package com.example.calci;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
TextView result ,cal;
    MaterialButton clear,obrack,cbrack,divide;
    MaterialButton seven,eight,nine,multiply;
    MaterialButton four,five,six,minus;
    MaterialButton one,two,three,plus;
    MaterialButton clearall,zero,dot,equalto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     result=findViewById(R.id.result);
     cal=findViewById(R.id.solution);

        assignId(clear,R.id.C);
        assignId(obrack,R.id.openbracket);
        assignId(cbrack,R.id.closebracket);
        assignId(divide,R.id.divide);
        assignId(seven,R.id.seven);
        assignId(eight,R.id.eight);
        assignId(nine,R.id.nine);
        assignId(multiply,R.id.multiply);
        assignId(four,R.id.four);
        assignId(five,R.id.five);
        assignId(six,R.id.six);
        assignId(minus,R.id.minus);
        assignId(one,R.id.one);
        assignId(two,R.id.two);
        assignId(three,R.id.three);
        assignId(plus,R.id.plus);
        assignId(clearall,R.id.clearall);
        assignId(zero,R.id.zero);
        assignId(dot,R.id.dot);
        assignId(equalto,R.id.equalsto);




    }
void assignId(MaterialButton btn,int id)
{
    btn=findViewById(id);
    btn.setOnClickListener(this);
}
    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText=button.getText().toString();
        String datatocal=cal.getText().toString();
        if(buttonText.equals("AC"))
        {
            cal.setText("");
            datatocal="";
           result.setText("0");
            return;
        }
        if(buttonText.equals("="))
        {
         cal.setText(result.getText());
         return;
        }
        if(buttonText.equals("C"))
        {
            datatocal=datatocal.substring(0,datatocal.length()-1);
        }
        else
        {
            datatocal=datatocal+buttonText;
        }

        cal.setText(datatocal);
        String finalresult=getresult(datatocal);
        if(!finalresult.equals("err"))
        {
            result.setText(finalresult);
        }
    }
    String getresult(String data)
    {
try{
  Context context=Context.enter();
  context.setOptimizationLevel(-1);
  Scriptable scriptable=context.initSafeStandardObjects();
  String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
  if(finalResult.endsWith(".0"))
  {
      finalResult=finalResult.replace(".0","");
  }
  return finalResult;
}catch (Exception e)
{
    return "err";
}
    }
}