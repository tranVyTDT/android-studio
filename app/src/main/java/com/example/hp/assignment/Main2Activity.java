package com.example.hp.assignment;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;


public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    Button addtable;
    Button menu1;
    ListView order;
    ArrayList<String> table ;
    ArrayList<String> status ;
    ArrayList<String> name ;
    ArrayList<String> dish ;
    String nameofstore ;
    MyAdapter myAdapter;
    Button history;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = this.getIntent();
        final Bundle bundle = intent.getExtras();
        Firebase.setAndroidContext(this);
        myAdapter = new MyAdapter();
        addtable = findViewById(R.id.addtable);
        menu1 = findViewById(R.id.menu);
        order = findViewById(R.id.order);
        addtable.setOnClickListener(this);
        menu1.setOnClickListener(this);
        table = new ArrayList<>();
        name = new ArrayList<>();
        dish = new ArrayList<>();
        status = new ArrayList<>();
        nameofstore = (String) bundle.get("storeName");
        history = findViewById(R.id.history);
        history.setOnClickListener(this);
        Firebase FB = new Firebase("https://android-fabfd.firebaseio.com/"+bundle.get("storeName"));

        FB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.child("order").getChildren()) {
                    String statustemp = postSnapshot.child("status").getValue(String.class);
                    if(statustemp.equals("0")){
                        String nametemp = postSnapshot.getKey();
                        String tabletemp = postSnapshot.child("table").getValue(String.class);
                        name.add(nametemp);
                        status.add(statustemp);
                        table.add(tabletemp);
                        String dishtemp = "";
                        for (DataSnapshot post: postSnapshot.child("listdish").getChildren()) {
                            dishtemp += post.getKey() +" : "+post.getValue(String.class)+"\n";
                        }
                        dish.add(dishtemp);
                    }

                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
  /*      for(String str : name)
        {
            Log.v(str,"-----------------------------------------------------------");
            //Toast.makeText(getBaseContext(),"data" + "  " +str,Toast.LENGTH_LONG).show();
            Firebase FBtemp = new Firebase("https://android-fabfd.firebaseio.com/"+bundle.get("storeName")+"/order"+str);
            FBtemp.addValueEventListener(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot postSnapshot: dataSnapshot.child("listdish").getChildren()) {
                                String dishtemp = postSnapshot.getKey() +" : "+postSnapshot.getValue(String.class);

                                dish.add(dishtemp);
                            }

                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    }
            );
        }*/
        order.setAdapter(myAdapter);

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
            case R.id.history: {
                fragmentTransaction.replace(R.id.main2, new hostory());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            }
        }
    }

    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return name.size();
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

            View listView  = getLayoutInflater().inflate(R.layout.order , null);

            TextView textView1 = listView.findViewById(R.id.namefb);
            TextView textView3 = listView.findViewById(R.id.tablefb);
            TextView textView4 = listView.findViewById(R.id.dishfb);
            Button button1 = listView.findViewById(R.id.button3);


                final String temp1 = name.get(position);

                String temp3 = table.get(position);
                String temp4 = dish.get(position);
                textView1.setText(temp1);
                textView3.setText("table number : "+temp3);
                textView4.setText(temp4);
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Firebase firebase = new Firebase("https://android-fabfd.firebaseio.com/");
                        firebase.child(nameofstore).child("order").child(temp1).child("status").setValue("1");
                        table = new ArrayList<>();
                        name = new ArrayList<>();
                        status = new ArrayList<>();
                        dish = new ArrayList<>();
                        myAdapter.notifyDataSetChanged();
                    }
                });




            return listView;
        }
    }
}
