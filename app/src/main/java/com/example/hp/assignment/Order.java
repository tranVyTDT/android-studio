package com.example.hp.assignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.ui.database.FirebaseListAdapter;

import java.util.ArrayList;
import java.util.List;

public class Order extends AppCompatActivity {

    private ListView listView;
    String value;
    ArrayList<String> myArrayList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent intent = getIntent();
        value = intent.getStringExtra(employee.nameStore);

        //Truy xuất firebase
        Firebase.setAndroidContext(this);
        final Firebase firebase = new Firebase("https://android-fabfd.firebaseio.com/"+value+"dish");
        listView = (ListView) findViewById(R.id.listView);

        final ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_order, (ArrayList<String>) myArrayList);
        listView.setAdapter(myArrayAdapter);

        firebase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String myChildValues = dataSnapshot.getValue(String.class);
                myArrayList.add(myChildValues);
                myArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }

}
