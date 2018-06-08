package com.example.palap.billspliting;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Calc extends AppCompatActivity {

    TextView tv;
    GridLayout gridLayout;
    GridLayout.LayoutParams gLayoutParams;
    CheckBox checkBox;
    CheckBox[] checkBoxesStore;
    int value = 750;
//    int checkedCheckBoxes = 0;
    float sum = 0f;
    int idValue = 1;
//    int result;
    int row[],column[];
    String names[];
    int itemsno;
    String set;
    int money[];
    int num;
    String result[];

//    String[] names = {"Person A", "Person B", "Person C","Person D"};
//    String[] money = {"700", "300", "100", "600","900"};
    int[] count;
    int columnToBeUsed = 0;
    double[] columnDivision;
    int[] billForPerson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        Bundle bundle=getIntent().getExtras();
        num= Integer.parseInt(bundle.getString("people"));
        names=new String[num];
        names=bundle.getStringArray("names");
        set=bundle.getString("itemsno");
        itemsno= Integer.valueOf(set);
        money=new int[itemsno];
        money=bundle.getIntArray("items");

        result=new String[50];
        row = new int[40];
        column = new int[40];
        tv = new TextView(this);
        gridLayout = (GridLayout)findViewById(R.id.insideLinearLayout);
        gridLayout.setColumnCount(money.length + 1);
        gridLayout.setRowCount(names.length + 1);
        checkBox = new CheckBox(this);
        checkBoxesStore = new CheckBox[50];
        count = new int[50];
        billForPerson = new int[50];
        columnDivision = new double[50];

        //To add the names of people
        for (int i = 0; i < names.length; i++) {
            gLayoutParams = new GridLayout.LayoutParams(GridLayout.spec(i + 1), GridLayout.spec(0));
            tv = new TextView(this);
            tv.setText(names[i]);
            tv.setTextSize(18);
            tv.setTextColor(Color.parseColor("#000000"));
            tv.setBackgroundResource(R.drawable.border);
            gridLayout.addView(tv, gLayoutParams);
        }



        //To add the costs of items
        for (int i = 0; i < money.length; i++) {
            gLayoutParams = new GridLayout.LayoutParams(GridLayout.spec(0), GridLayout.spec(i + 1));
            tv = new TextView(this);
            tv.setText(money[i]);
            tv.setTextSize(18);
            tv.setTextColor(Color.parseColor("#000000"));
            tv.setGravity(Gravity.CENTER_HORIZONTAL);
            tv.setBackgroundResource(R.drawable.border);
            gridLayout.addView(tv, gLayoutParams);
        }


        //To generate checkboxes
        for (int i = 0; i < money.length; i++) {
            for (int j = 0; j < names.length; j++) {
                checkBox = new CheckBox(this);
                gLayoutParams = new GridLayout.LayoutParams(GridLayout.spec(j + 1), GridLayout.spec(i + 1));
                checkBox.setId(idValue);
                setRowColumnPosition(j+1,i+1,idValue);
//                Log.v("Ids", idValue + "");
                checkBox.setPadding(5, 5, 5, 5);
//                checkBox.setBackgroundColor(Color.parseColor("#6FF7D8"));
                gridLayout.addView(checkBox, gLayoutParams);
                idValue++;
            }
//            Log.v("Ids", "Column changes");
        }

        for (int i=0;i<(money.length * names.length);i++){
            checkBoxesStore[i]  =  findViewById(i+1);
        }

    }
    @SuppressLint("ResourceType")
    public void totalSum(View v) {
        for (int i =0;i<money.length;i++){
            count[i] = 0;
            columnDivision[i]=0;
            columnToBeUsed = 0;
            billForPerson[i] = 0;
        }
        for (int i=0;i<(money.length * names.length);i++) {
            if (checkBoxesStore[i].isChecked()) {
                for (int j = 1; j <= money.length; j++) {
                    if (getColumn(i + 1) == j) {
                        count[j-1]++;
                    }
                }
            }
        }
        for (int i =0;i<money.length;i++){
            columnDivision[i] = (double)Integer.valueOf(money[i])/count[i];
        }
        for (int i=0;i<(money.length * names.length);i++) {
            if (checkBoxesStore[i].isChecked()) {
                for (int j = 1; j <= names.length; j++) {
                    if (getRow(i + 1) == j) {
                        for (int k = 1; k <= money.length; k++) {
                            if (getColumn(i + 1) == k) {
                                billForPerson[j-1]+=columnDivision[k-1];
                            }
                        }
                    }
                }
            }
        }
        for(int m =0 ;m<names.length;m++){
//            Toast.makeText(Calc.this,String.valueOf(names[m])+" must pay Rs. "+billForPerson[m]+"",Toast.LENGTH_SHORT).show();
            result[m]=String.valueOf(names[m])+"must pay Rs."+billForPerson[m];
        }

        Intent it=new Intent(Calc.this,Result.class);
        Bundle b=new Bundle();
        b.putStringArray("result",result);
        it.putExtras(b);
        startActivity(it);
    }


    public void setRowColumnPosition(int rowV,int columnV,int id) {
        row[id] = rowV;
        column[id] = columnV;
    }

    public int getRow(int id) {
        return row[id];
    }

    public int getColumn(int id){
        return  column[id];
    }

}
