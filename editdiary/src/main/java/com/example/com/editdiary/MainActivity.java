package com.example.com.editdiary;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText edtDiary, edtDate;
    Button btnWrite;
    DatePicker dp;
    String fileName;
    View dateView;
    Calendar cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("발전된 일기장");


        edtDiary = (EditText)findViewById(R.id.edtDiary);
        edtDate = (EditText)findViewById(R.id.edtDate);
        btnWrite = (Button)findViewById(R.id.btnWrite);

        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateView = (View) View.inflate(MainActivity.this, R.layout.datepick, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("날짜 선택");
                dlg.setView(dateView);
                dp = (DatePicker)dateView.findViewById(R.id.datePicker1);

               cal = Calendar.getInstance();
                int cYear = cal.get(Calendar.YEAR);
                int cMonth = cal.get(Calendar.MONTH);
                int cDay = cal.get(Calendar.DAY_OF_MONTH);

                dp.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        cal.set(year,monthOfYear,dayOfMonth);
                    }
                });

                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int cYear = cal.get(Calendar.YEAR);
                        int cMonth = cal.get(Calendar.MONTH);
                        int cDay = cal.get(Calendar.DAY_OF_MONTH);
                        String date = Integer.toString(cYear)+"년 "+Integer.toString(cMonth + 1)+"월 "+ Integer.toString(cDay)+"일";
                        fileName = Integer.toString(cYear) + "_" + Integer.toString(cMonth + 1) + "_" + Integer.toString(cDay) + ".txt";
                        String str = readDiary(fileName);
                        edtDate.setText(date);
                        edtDiary.setText(str);
                        btnWrite.setEnabled(true);
                    }
                });
                dlg.setNegativeButton("취소", null);
                dlg.show();
            }
        });
    }
    String readDiary(String fName) {
        String diaryStr = null;
        FileInputStream inFs;
        try {
            inFs = openFileInput(fName);
            byte [] txt = new byte[500];
            inFs.read(txt);
            inFs.close();
            diaryStr = (new String(txt)).trim();
            btnWrite.setText("수정하기");
        } catch (IOException e) {
            edtDiary.setHint("일기 없음");
            btnWrite.setText("새로 저장");
        }
        return diaryStr;
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
