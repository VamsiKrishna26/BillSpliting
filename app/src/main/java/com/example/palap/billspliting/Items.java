package com.example.palap.billspliting;

import android.content.Intent;
import android.graphics.Typeface;
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

public class Items extends AppCompatActivity {
    LinearLayout l1;
    TextView tv;EditText tv1;
    String names[];
    int itemsno;
    String set;
    String items[];
    int num;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        l1=new LinearLayout(this);
        l1.setBackgroundResource(R.drawable.back);
        l1.setOrientation(LinearLayout.VERTICAL);
        setContentView(l1);
        Bundle bundle=getIntent().getExtras();
        tv=new TextView(this);
        tv.setText("Give the prices of items");
        tv.setTypeface(null, Typeface.BOLD_ITALIC);
        tv.setTextSize(20);
        tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,340));
        tv.setGravity(Gravity.CENTER);
        l1.addView(tv);
        num= Integer.parseInt(bundle.getString("people"));
//        Toast.makeText(Items.this,String.valueOf(num),Toast.LENGTH_LONG).show();
        names=new String[num];
        names=bundle.getStringArray("names");
        set=bundle.getString("itemsno");
        itemsno= Integer.valueOf(set);
        items=new String[itemsno];
        for(int i=0;i<itemsno;i++)
        {
            tv1=new EditText(l1.getContext());
            tv1.setId(i);
            tv1.setHint("Item "+(i+1));
            l1.addView(tv1);
        }
        b1=new Button(this);
        b1.setText("Submit");
        l1.addView(b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(Items.this,Calc.class);
                Bundle b=new Bundle();
                for(int i=0;i<itemsno;i++)
                {
                    tv1=(EditText)findViewById(i);
                    items[i]=tv1.getText().toString();
                }
                b.putString("people", String.valueOf(num));
                b.putStringArray("names",names);
                b.putString("itemsno", String.valueOf(itemsno));
                b.putStringArray("items",items);
                i1.putExtras(b);
                startActivity(i1);
            }
        });
    }
}
