package com.example.hp.assignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class employee_user extends AppCompatActivity {

    Button btnSignIn, btnSignUp;
    TextView tvNameStore;
    String nameStore;

    private Firebase firebase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_user);

        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);

        tvNameStore = findViewById(R.id.tvNameStore);

        final Intent intent = getIntent();
        nameStore = intent.getStringExtra(employee.nameStore);
        tvNameStore.setText(nameStore);

        //Khởi tạo dữ liệu Firebase
        Firebase.setAndroidContext(this);
        firebase = new Firebase("https://android-fabfd.firebaseio.com/"+nameStore);
        firebase.child("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signIn = new Intent(employee_user.this, SignIn.class);
                startActivity(signIn);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUp = new Intent(employee_user.this, SignUp.class);
                startActivity(signUp);
            }
        });
    }
}
