package com.example.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView text1,text2;
    Switch chkAgree;
    RadioGroup rGroup1;
    RadioButton rdoJel, rdoKit, rdoLol;
    ImageView imgVer;
    Button btnEsc, btnRst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("안드로이드 사진 보기");

        text1=(TextView)findViewById(R.id.Text1);
        chkAgree=(Switch)findViewById(R.id.ChkAgree);

        text2=(TextView)findViewById(R.id.Text2);
        rGroup1=(RadioGroup)findViewById(R.id.Rgroup1);
        rdoJel=(RadioButton)findViewById(R.id.RdoJel);
        rdoKit=(RadioButton)findViewById(R.id.RdoKit);
        rdoLol=(RadioButton)findViewById(R.id.RdoLol);

        imgVer=(ImageView)findViewById(R.id.ImgVer);
        btnEsc=(Button)findViewById(R.id.BtnEsc);
        btnRst=(Button)findViewById(R.id.BtnRst);

        chkAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                if(chkAgree.isChecked()) {
                    text2.setVisibility(android.view.View.VISIBLE);
                    rGroup1.setVisibility(android.view.View.VISIBLE);
                    imgVer.setVisibility(android.view.View.VISIBLE);
                    btnEsc.setVisibility(android.view.View.VISIBLE);
                    btnRst.setVisibility(android.view.View.VISIBLE);

                }
                else {
                    text2.setVisibility(android.view.View.INVISIBLE);
                    rGroup1.setVisibility(android.view.View.INVISIBLE);
                    imgVer.setVisibility(android.view.View.INVISIBLE);
                    btnEsc.setVisibility(android.view.View.INVISIBLE);
                    btnRst.setVisibility(android.view.View.INVISIBLE);
                }
            }
        });
        rGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(rGroup1.getCheckedRadioButtonId()) {
                    case R.id.RdoJel:
                        imgVer.setImageResource(R.drawable.jellybean);
                        break;
                    case R.id.RdoKit:
                        imgVer.setImageResource(R.drawable.kitkat);
                        break;
                    case R.id.RdoLol:
                        imgVer.setImageResource(R.drawable.lollipop);
                        break;
                }
            }
        });
        btnEsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        btnRst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                chkAgree.setChecked(false);
                rGroup1.clearCheck();
                imgVer.setImageResource(0);
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