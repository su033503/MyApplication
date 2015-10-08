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
    EditText edit1,edit2;
    TextView textResult;
    String num1, num2;
    Integer result;
    Button[] numButtons = new Button[10];
    Integer[] numBtnIDs = { R.id.BtnNum0, R.id.BtnNum1, R.id.BtnNum2, R.id.BtnNum3, R.id.BtnNum4,
            R.id.BtnNum5, R.id.BtnNum6, R.id.BtnNum7, R.id.BtnNum8, R.id.BtnNum9};
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("그리드레이아웃 계산기");

        edit1 = (EditText)findViewById(R.id.Edit1);
        edit2 = (EditText)findViewById(R.id.Edit2);
        findViewById(R.id.BtnAdd).setOnTouchListener(Cal);
        findViewById(R.id.BtnSub).setOnTouchListener(Cal);
        findViewById(R.id.BtnMul).setOnTouchListener(Cal);
        findViewById(R.id.BtnDiv).setOnTouchListener(Cal);
        textResult = (TextView)findViewById(R.id.TextResult);

        for (i=0; i<numBtnIDs.length; i++) {
            numButtons[i] = (Button)findViewById(numBtnIDs[i]);
        }
        for(i=0; i<numBtnIDs.length; i++) {
            final int index;
            index = i;

            numButtons[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(edit1.isFocused()) {
                        num1 = edit1.getText().toString() + numButtons[index].getText().toString();
                        edit1.setText(num1);
                    }
                    else if(edit2.isFocused()) {
                        num2 = edit2.getText().toString() + numButtons[index].getText().toString();
                        edit2.setText(num2);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "먼저 에디트텍스트를 선택하세요", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    Button.OnTouchListener Cal = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            num1 = edit1.getText().toString();
            num2 = edit2.getText().toString();
            if(num1.equals("")||num2.equals("")){
                Toast.makeText(getApplicationContext(), "계산할 값을 전부 입력하지 않았어요. 채워주세요.", Toast.LENGTH_SHORT).show();
            }
            else {
                switch (v.getId()) {
                    case R.id.BtnAdd:
                        result = Integer.parseInt(num1) + Integer.parseInt(num2);
                        textResult.setText("계산 결과 : " + result.toString());
                        break;
                    case R.id.BtnSub:
                        result = Integer.parseInt(num1) - Integer.parseInt(num2);
                        textResult.setText("계산 결과 : " + result.toString());
                        break;
                    case R.id.BtnMul:
                        result = Integer.parseInt(num1) * Integer.parseInt(num2);
                        textResult.setText("계산 결과 : " + result.toString());
                        break;
                    case R.id.BtnDiv:
                        if (num2.equals("0")) {
                            Toast.makeText(getApplicationContext(), "0으로 나눌수는 없어요.", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        result = Integer.parseInt(num1) / Integer.parseInt(num2);
                        textResult.setText("계산 결과 : " + result.toString());
                        break;
                }
            }
            return false;
        }
    };

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
