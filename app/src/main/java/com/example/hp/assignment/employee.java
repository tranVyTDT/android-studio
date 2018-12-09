package com.example.hp.assignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class employee extends AppCompatActivity {
    EditText edtInputStore;
    TextView txtWelcome;
    Button btnInputStore;
    static String nameStore = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        //Get find view by Id from Activity_employee
        edtInputStore = findViewById(R.id.edtInputStore);

        txtWelcome = findViewById(R.id.txtWelcome);

        btnInputStore = findViewById(R.id.btnInputStore);

        //Truy xuất firebase
        Firebase.setAndroidContext(this);
        final Firebase firebase = new Firebase("https://android-fabfd.firebaseio.com/");
        btnInputStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Lấy giá trị tên store
                final String value = edtInputStore.getText().toString();

                firebase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(value).exists()) {
                            Toast.makeText(employee.this, "Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(employee.this, Order.class);
                            intent.putExtra(nameStore, edtInputStore.getText().toString());
                            startActivity(intent);
                        } else {
                            Toast.makeText(employee.this, "Store have not existed! Please input again!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                    }
                });

            }
        });
    }
}
