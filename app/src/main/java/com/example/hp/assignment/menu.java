package com.example.hp.assignment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class menu extends Fragment implements View.OnClickListener {
    Button out;
    Button add;
    Boolean check = true;
    ListView menuelement;
    ArrayList<String> d ;
    ArrayList<Integer> p ;
    EditText price;
    EditText dish;
    Firebase firebase;
    String nameStore;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_menu,container,false);
        Firebase.setAndroidContext(getActivity());
        final MyAdapter myAdapter = new MyAdapter();
        menuelement = view.findViewById(R.id.menuelement);
        out = view.findViewById(R.id.out);
        add = view.findViewById(R.id.button2);
        price = view.findViewById(R.id.editText2);
        dish = view.findViewById(R.id.editText);
        out.setOnClickListener(this);
        d = new ArrayList<>();
        p = new ArrayList<>();


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pr = price.getText().toString();
                String di = dish.getText().toString();

                firebase = new Firebase("https://android-fabfd.firebaseio.com/"+nameStore+"/dish");
                Firebase table = firebase.child(di);
                table.setValue(pr);
                d.add(di);
                d = new ArrayList<>();
                p = new ArrayList<>();
                p.add(Integer.parseInt(pr));
                myAdapter.notifyDataSetChanged();

            }
        });
        //get string form main activity 1
        Intent intent = getActivity().getIntent();
        final Bundle bundle = intent.getExtras();
        //

        Firebase FB = new Firebase("https://android-fabfd.firebaseio.com/"+bundle.get("storeName"));

        //set json data become object

        FB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.getKey();
                nameStore = name;

                for (DataSnapshot postSnapshot: dataSnapshot.child("dish").getChildren()) {
                    String post = postSnapshot.getKey();
                    int post1 = postSnapshot.getValue(Integer.class);

                    d.add(post);
                    p.add(post1);
                }
                    myAdapter.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        //


        menuelement.setAdapter(myAdapter);

        return  view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.out: {
                Toast.makeText(getActivity(),"Outed",Toast.LENGTH_LONG).show();
                getActivity().onBackPressed();
                break;
            }

        }
    }

    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return d.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, final View convertView, final ViewGroup parent) {

            View listView  = getLayoutInflater().inflate(R.layout.menuelement , null);

            TextView textView1 = listView.findViewById(R.id.dishname);
            TextView textView2 = listView.findViewById(R.id.dishprice);


            String storeName = d.get(position);
            textView1.setText(storeName);
            int storeNameint = p.get(position);
            textView2.setText(storeNameint+"");

            return listView;
        }
    }

}
