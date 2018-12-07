package com.example.hp.assignment;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    Button addtable;
    Button menu1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        addtable = findViewById(R.id.addtable);
        menu1 = findViewById(R.id.menu);

        addtable.setOnClickListener(this);
        menu1.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (v.getId()) {
            case R.id.addtable: {
                fragmentTransaction.replace(R.id.main2, new addTable());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            }

            case R.id.menu: {
                fragmentTransaction.replace(R.id.main2, new menu());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            }
        }
    }
}
