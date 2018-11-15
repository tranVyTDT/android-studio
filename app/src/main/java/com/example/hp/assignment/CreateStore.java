package com.example.hp.assignment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;


public class CreateStore extends Fragment {

    ListView listOfStore;
    ArrayList<Stores> list;
    Button button;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        list = new ArrayList<Stores>();

        //should put date to the array here
        Stores stores = new Stores(button,"circleK");
        list.add(stores);
        list.add(stores);
        list.add(stores);
        list.add(stores);
        list.add(stores);

        View view = inflater.inflate(R.layout.fragment_create_store,container,false);
        listOfStore = view.findViewById(R.id.listOfStore);

        CustomAdapter customAdapter = new CustomAdapter();

        listOfStore.setAdapter(customAdapter);

        return view;
    }


    class CustomAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return list.size();
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
        public View getView(int position, View convertView, ViewGroup parent) {
            View listView  = getLayoutInflater().inflate(R.layout.listofstore , null);

            TextView textView = listView.findViewById(R.id.storename);
            Button button = listView.findViewById(R.id.join);

            textView.setText(list.get(position).getNameOfstore());

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // jump to the store that user will choose;
                }
            });

            return listView;
        }
    }
}
