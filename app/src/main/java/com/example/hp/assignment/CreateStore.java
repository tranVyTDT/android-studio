package com.example.hp.assignment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class CreateStore extends Fragment {
    Firebase firebase ,firebasec1;
    ListView listOfStore;
    ArrayList<Stores> list;
    Button button;
    Button create;
    EditText name;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_create_store,container,false);
        listOfStore = view.findViewById(R.id.listOfStore);
        create = view.findViewById(R.id.create);
        name = view.findViewById(R.id.name);
        Firebase.setAndroidContext(getActivity());


        final CustomAdapter customAdapter = new CustomAdapter();
        list = new ArrayList<Stores>();

        sharedPreferences = this.getActivity().getSharedPreferences("store",0);
        editor = sharedPreferences.edit();
        Firebase.setAndroidContext(getActivity());
        Firebase FB = new Firebase("https://android-fabfd.firebaseio.com/");
        //load data to esext store
        FB.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String string = dataSnapshot.getKey();
                Stores stores = new Stores(button,string);
                list.add(stores);
                customAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                customAdapter.notifyDataSetChanged();
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
        //

        // create new store
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Set<String> store = sharedPreferences.getStringSet("storename" , new HashSet<String>());
                String nameOfStore = name.getText().toString();
                //json on firebase
                firebase = new Firebase("https://android-fabfd.firebaseio.com/"+nameOfStore);
                Firebase table = firebase.child("table");
                table.setValue(0);
                //
                Stores stores = new Stores(button,nameOfStore);
                editor.clear();
                editor.putStringSet("storename", store).commit();
                customAdapter.notifyDataSetChanged();
            }
        });


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
        public View getView(final int position, final View convertView, final ViewGroup parent) {
            final String storeName = list.get(position).getNameOfstore();
            View listView  = getLayoutInflater().inflate(R.layout.listofstore , null);

            TextView textView = listView.findViewById(R.id.storename);
            Button button = listView.findViewById(R.id.join);

            textView.setText(storeName);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // jump to the store that user will choose;
                    Intent intent = new Intent(getActivity(),Main2Activity.class);
                    intent.putExtra("storeName",storeName);
                    startActivity(intent);
                }
            });

            return listView;
        }
    }
}
