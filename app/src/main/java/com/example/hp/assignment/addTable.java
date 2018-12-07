package com.example.hp.assignment;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseReference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



public class addTable extends Fragment {
    Button finish;
    Button add;
    Button delete;
    TextView amoutTables;
    Store store = new Store();
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_table,container,false);


        //get string form main activity 1
        Intent intent = getActivity().getIntent();
        final Bundle bundle = intent.getExtras();
        //
        Firebase.setAndroidContext(getActivity());
        Firebase FB = new Firebase("https://android-fabfd.firebaseio.com/"+bundle.get("storeName"));


        //set json data become object
        Firebase mMainMenuRef = FB.child("dish");



        //

        //find id widget
        finish = view.findViewById(R.id.finish);
        add = view.findViewById(R.id.add);
        delete = view.findViewById(R.id.delete);
        amoutTables = view.findViewById(R.id.texttable);
        amoutTables.setText("your store has "+0+" tables");
        //
        finish.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"closed",Toast.LENGTH_LONG).show();
                getActivity().onBackPressed();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                store.setAmountTables(store.getAmountTables()+1);

                Firebase FB = new Firebase("https://android-fabfd.firebaseio.com/"+bundle.get("storeName"));
                Firebase table = FB.child("table");
                table.setValue(store.getAmountTables());
                amoutTables.setText("your store has "+store.getAmountTables()+" tables");
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                store.setAmountTables(store.getAmountTables()-1);
                Firebase FB = new Firebase("https://android-fabfd.firebaseio.com/"+bundle.get("storeName"));
                Firebase table = FB.child("table");
                table.setValue(store.getAmountTables());
                amoutTables.setText("your store has "+store.getAmountTables()+" tables");
            }
        });

        mMainMenuRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                storeData(dataSnapshot);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        FB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                storeData2(dataSnapshot);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        //Toast.makeText(getActivity(),"lenght = " + store.getAmountTables()+" after ",Toast.LENGTH_LONG).show();
        return view;

    }

    public void storeData(DataSnapshot dataSnapshot)
    {
        ArrayList<String> d = new ArrayList();
        ArrayList<Integer> p = new ArrayList();
        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
            String post = postSnapshot.getKey();
            int post1 = postSnapshot.getValue(Integer.class);
            d.add(post);
            p.add(post1);

        }
        store.setDishes(d);
        store.setPrice(p);


    }
    public void storeData2(DataSnapshot dataSnapshot)
    {
        String name = dataSnapshot.getKey();
        store.setStoreName(name);
        int number = dataSnapshot.child("table").getValue(Integer.class);
        store.setAmountTables(number);
    }

}
