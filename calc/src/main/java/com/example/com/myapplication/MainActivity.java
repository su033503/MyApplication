package com.example.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edit1, edit2;
    Button btnAdd, btnSub, btnMul, btnDiv, btnRes;
    TextView textResult;
    String num1, num2;
    Float result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("초간단 계산기");
        edit1 = (EditText)findViewById(R.id.Edit1);
        edit2 = (EditText)findViewById(R.id.Edit2);
        btnAdd = (Button)findViewById(R.id.BtnAdd);
        btnSub = (Button)findViewById(R.id.BtnSub);
        btnMul = (Button)findViewById(R.id.BtnMul);
        btnDiv = (Button)findViewById(R.id.Btndiv);
        btnRes = (Button)findViewById(R.id.BtnRes);
        textResult = (TextView)findViewById(R.id.TextResult);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                if(num1.equals("")||num2.equals("")){
                    Toast.makeText(getApplicationContext(), "값을 입력하지 않았어요. 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else {
                    result = Float.parseFloat(num1) + Float.parseFloat(num2);
                    textResult.setText("계산 결과 :" + result.toString());
                }
            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                if(num1.equals("")||num2.equals("")){
                    Toast.makeText(getApplicationContext(), "값을 입력하지 않았어요. 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else {
                    result = Float.parseFloat(num1) - Float.parseFloat(num2);
                    textResult.setText("계산 결과 :" + result.toString());
                }
            }
        });
        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                if(num1.equals("")||num2.equals("")){
                    Toast.makeText(getApplicationContext(), "값을 입력하지 않았어요. 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else {
                    result = Float.parseFloat(num1) * Float.parseFloat(num2);
                    textResult.setText("계산 결과 :" + result.toString());
                }
            }
        });
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                if(num1.equals("")||num2.equals("")){
                    Toast.makeText(getApplicationContext(), "값을 입력하지 않았어요. 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else if(num2.equals("0")) {
                    Toast.makeText(getApplicationContext(), "0으로 나눌수는 없어요.", Toast.LENGTH_SHORT).show();
                }
                else {
                    result = Float.parseFloat(num1) / Float.parseFloat(num2);
                    textResult.setText("계산 결과 :" + result.toString());
                }
            }
        });
        btnRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                if(num1.equals("")||num2.equals("")){
                    Toast.makeText(getApplicationContext(), "값을 입력하지 않았어요. 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else if(num2.equals("0")) {
                    Toast.makeText(getApplicationContext(), "0으로 나눌수는 없어요.", Toast.LENGTH_SHORT).show();
                }
                else {
                    result = Float.parseFloat(num1) % Float.parseFloat(num2);
                    textResult.setText("계산 결과 :" + result.toString());
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
