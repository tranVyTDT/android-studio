package com.example.hp.assignment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    Button addtable;
    Button menu;
    public String Storename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        addtable = findViewById(R.id.addtable);
        menu = findViewById(R.id.menu);
        runJson();
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main2 , new BlankFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        addtable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main2 , new addTable());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
    }

    public void runJson()
    {
        boolean check = false;
        try {
            ArrayList<Store> data = ReadJSONExample.readDataJSONFile(this);
            for (int i = 0 ; i < data.size() ; i++)
            {
                if(getIntent().getStringExtra("storeName").equals(data.get(i).getStoreName())){
                    check = true;
                }

            }
            if(check)
            {
                Toast.makeText(this,"wellcome to your store " + getIntent().getStringExtra("storeName"),Toast.LENGTH_LONG).show();
                Storename = getIntent().getStringExtra("storeName");
            }
            else
            {
                Toast.makeText(this,"wrong store or without any data"+getIntent().getStringExtra("storeName"),Toast.LENGTH_LONG).show();

            }
        } catch(Exception e)  {
            e.printStackTrace();
        }
    }
}
