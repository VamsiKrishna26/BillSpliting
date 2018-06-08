package com.example.palap.billspliting;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    LinearLayout l1;
    TextView tv;
    String result[];
    int num,i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        l1=new LinearLayout(this);
        l1.setBackgroundResource(R.drawable.back);
        l1.setOrientation(LinearLayout.VERTICAL);
        setContentView(l1);
        Bundle bundle=getIntent().getExtras();
        num=Integer.parseInt(bundle.getString("num1"));
        result=new String[num];
        result=bundle.getStringArray("result");

        for(i=0;i<num;i++)
        {   tv=new TextView(this);
            tv.setId(i);
            tv.setText(result[i]);
            tv.setTypeface(null, Typeface.BOLD_ITALIC);
            tv.setTextSize(20);
            l1.addView(tv);
        }
    }
}
