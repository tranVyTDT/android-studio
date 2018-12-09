package com.example.hp.assignment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class hostory extends Fragment {

    ArrayList<String> table ;
    ArrayList<String> status ;
    ArrayList<String> name ;
    ArrayList<String> dish ;
    MyAdapter myAdapter;
    ListView listView;

    public hostory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_hostory,container,false);
        listView = view.findViewById(R.id.historylist);
        table = new ArrayList<>();
        name = new ArrayList<>();
        dish = new ArrayList<>();
        Intent intent = getActivity().getIntent();
        final Bundle bundle = intent.getExtras();
        myAdapter = new MyAdapter();
        Firebase.setAndroidContext(getContext());
        Firebase FB = new Firebase("https://android-fabfd.firebaseio.com/"+bundle.get("storeName"));

        FB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.child("order").getChildren()) {
                    String statustemp = postSnapshot.child("status").getValue(String.class);
                    if(statustemp.equals("1")){
                        String nametemp = postSnapshot.getKey();
                        //Toast.makeText(getContext(), "name = " + nametemp, Toast.LENGTH_SHORT).show();
                        String tabletemp = postSnapshot.child("table").getValue(String.class);
                        name.add(nametemp);
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
        listView.setAdapter(myAdapter);
        return view;
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

            View listView  = getLayoutInflater().inflate(R.layout.historylayout , null);

            TextView textView1 = listView.findViewById(R.id.his1);
            TextView textView3 = listView.findViewById(R.id.his2);
            TextView textView4 = listView.findViewById(R.id.his3);


            String temp1 = name.get(position);
            String temp3 = table.get(position);
            String temp4 = dish.get(position);
            textView1.setText(temp1);
            textView3.setText("table number : "+temp3);
            textView4.setText(temp4);
            myAdapter.notifyDataSetChanged();





            return listView;
        }
    }

}
