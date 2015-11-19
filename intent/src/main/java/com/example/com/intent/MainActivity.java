package com.example.com.intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup rGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("메인 액티비티");
        rGroup=(RadioGroup)findViewById(R.id.RGroup);

        Button btnCalculate = (Button)findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkflag = 0;
                EditText edtNum1 = (EditText) findViewById(R.id.edtNum1);
                EditText edtNum2 = (EditText) findViewById(R.id.edtNum2);
                String num1 = edtNum1.getText().toString();
                String num2 = edtNum2.getText().toString();
                if(num1.equals("")||num2.equals("")) {
                    Toast.makeText(getApplicationContext(), "계산할 값을 입력하세요.", Toast.LENGTH_SHORT).show();
                    checkflag = 1;
                }
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                switch(rGroup.getCheckedRadioButtonId()) {
                    case R.id.RAdd:
                        intent.putExtra("Method","Add");
                        break;
                    case R.id.RSub:
                        intent.putExtra("Method", "Sub");
                        break;
                    case R.id.RMul:
                        intent.putExtra("Method", "Mul");
                        break;
                    case R.id.RDiv:
                        if(Integer.parseInt(num2)==0) {
                            Toast.makeText(getApplicationContext(), "0으로는 나눌 수 없습니다.", Toast.LENGTH_SHORT).show();
                            checkflag = 1;
                        }
                        else
                            intent.putExtra("Method", "Div");
                        break;
                }
                if(checkflag == 0) {
                    intent.putExtra("Num1", Integer.parseInt(num1));
                    intent.putExtra("Num2", Integer.parseInt(num2));
                    startActivityForResult(intent, 0);
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            int hap = data.getIntExtra("Hap",0);
            Toast.makeText(getApplicationContext(),"결과 :"+hap, Toast.LENGTH_SHORT).show();
        }
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