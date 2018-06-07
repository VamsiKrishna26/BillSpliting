package com.example.palap.billspliting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class People extends AppCompatActivity {
    TextView tv;
    EditText tv1;
    LinearLayout l1;
    Button b1;
    String set;
    String names[];
    int num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        l1 = new LinearLayout(this);
        l1.setBackgroundResource(R.drawable.back);
        tv = new TextView(this);
        l1.setOrientation(LinearLayout.VERTICAL);
        setContentView(l1);
        Intent it=getIntent();
        set=it.getStringExtra("people");
        tv.setText("Give the names of people");
        tv.setTextSize(20);
        tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,340));
        tv.setGravity(Gravity.CENTER);
        l1.addView(tv);
        num= Integer.parseInt(set);
        names=new String[num];
        for(int i=0;i<num;i++)
        {
            tv1=new EditText(l1.getContext()); //
            tv1.setId(i);
//            Toast.makeText(People.this,String.valueOf(tv1.getId()),Toast.LENGTH_SHORT).show();
            tv1.setHint("Person " + (i+1));
            l1.addView(tv1);
        }
        b1=new Button(this);
        b1.setText("Submit");
        l1.addView(b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(People.this,Food.class);
                Bundle b = new Bundle();
                for(int i=0;i<num;i++)
                {
                    tv1 = (EditText)findViewById(i);
                    names[i]= tv1.getText().toString();
                }
                b.putString("people",set);
                b.putStringArray("names",names);
                it.putExtras(b);
                startActivity(it);
            }
        });
    }
}