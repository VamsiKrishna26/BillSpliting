package com.example.palap.billspliting;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class Food extends AppCompatActivity {
    LinearLayout l1;
    TextView tv;
    EditText et;
    int num, i,id;
    String names[];
    Button b1;
    String itemsno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        l1 = new LinearLayout(this);
        l1.setBackgroundResource(R.drawable.back);
        l1.setOrientation(LinearLayout.VERTICAL);
        setContentView(l1);
        tv = new TextView(this);
        Bundle bundle = getIntent().getExtras();
        num= Integer.parseInt(bundle.getString("people"));
        names = new String[num];
        names = bundle.getStringArray("names");
        tv=new TextView(this);
        tv.setText("How many items have you ordered?");
        tv.setTextSize(30);
        tv.setTypeface(null, Typeface.BOLD_ITALIC);
        tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,340));
        tv.setGravity(Gravity.CENTER);
        l1.addView(tv);
        et=new EditText(this);
        id=2;
        et.setId(id);
        et.setInputType(InputType.TYPE_CLASS_NUMBER);
        l1.addView(et);
        b1=new Button(this);
        b1.setText("Submit");
        l1.addView(b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(Food.this,Items.class);
                Bundle b1=new Bundle();
                et = (EditText)findViewById(id);
//                Toast.makeText(Food.this,et.getText().toString(), Toast.LENGTH_SHORT).show();
                itemsno= et.getText().toString();
                b1.putString("people", String.valueOf(num));
                b1.putStringArray("names",names);
                b1.putString("itemsno",itemsno);
                it.putExtras(b1);
                startActivity(it);
            }
        });
    }
}
