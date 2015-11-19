package com.example.com.intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by user 1 on 2015-11-18.
 */
public class SecondActivity extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        setTitle("Second 액티비티");

        Intent inIntent = getIntent();
        String method = inIntent.getStringExtra("Method");
        final int value1 = inIntent.getIntExtra("Num1", 0);
        final int value2 = inIntent.getIntExtra("Num2", 0);
        int result = 0;
        switch (method) {
            case "Add" : result = value1 + value2; break;
            case "Sub" : result = value1 - value2; break;
            case "Mul" : result = value1 * value2; break;
            case "Div" : result = value1 / value2; break;
        }

        Button btnReturn = (Button)findViewById(R.id.btnReturn);
        final int finalResult = result;
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outIntent = new Intent(getApplicationContext(),MainActivity.class);
                outIntent.putExtra("Hap", finalResult);
                setResult(RESULT_OK,outIntent);
                finish();
            }
        });

    }

}
