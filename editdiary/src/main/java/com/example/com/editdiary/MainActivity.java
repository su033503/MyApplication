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
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
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

        cal = Calendar.getInstance();
        final int cYear = cal.get(Calendar.YEAR);   //오늘 날짜는 또 쓰이는 곳이 있으므로 final로 생성
        final int cMonth = cal.get(Calendar.MONTH);
        final int cDay = cal.get(Calendar.DAY_OF_MONTH);
        makeDiary(cYear, cMonth, cDay);

        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateView = (View) View.inflate(MainActivity.this, R.layout.datepick, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("날짜 선택");
                dlg.setView(dateView);

                dp = (DatePicker) dateView.findViewById(R.id.datePicker1);
                dp.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        cal.set(year, monthOfYear, dayOfMonth);
                    }
                });

                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int sYear = cal.get(Calendar.YEAR);
                        int sMonth = cal.get(Calendar.MONTH);
                        int sDay = cal.get(Calendar.DAY_OF_MONTH);
                        makeDiary(sYear, sMonth, sDay);
                    }
                });
                dlg.setNegativeButton("취소", null);
                dlg.show();
            }
        });
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream outFs = openFileOutput(fileName,1);
                    String str = edtDiary.getText().toString();
                    outFs.write(str.getBytes());
                    outFs.close();
                    Toast.makeText(getApplicationContext(), fileName + " 이 저장됨", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {

                }
            }
        });
    }
    public void makeDiary(int year, int month, int day) {
        String date = Integer.toString(year)+"년 "+Integer.toString(month + 1)+"월 "+ Integer.toString(day)+"일";
        fileName = Integer.toString(year) + "_" + Integer.toString(month + 1) + "_" + Integer.toString(day) + ".txt";
        String str = readDiary(fileName);
        edtDate.setText(date);
        edtDiary.setText(str);
        btnWrite.setEnabled(true);
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
