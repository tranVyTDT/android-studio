package com.example.hp.assignment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button createStore;
    Button connectWithStore;
    TextView select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createStore = findViewById(R.id.createStore);
        connectWithStore = findViewById(R.id.connectWithStore);
        select = findViewById(R.id.select);

        createStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select.setVisibility(View.INVISIBLE);
                connectWithStore.setVisibility(View.INVISIBLE);
                createStore.setVisibility(View.INVISIBLE);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main , new CreateStore());
                fragmentTransaction.commit();
            }
        });
    }

}
