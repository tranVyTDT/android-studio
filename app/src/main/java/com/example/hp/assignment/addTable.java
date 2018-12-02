package com.example.hp.assignment;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static com.example.hp.assignment.ReadJSONExample.readText;


public class addTable extends Fragment {
    Button finish;
    Button add;
    Button delete;
    TextView amoutTables;
    public ArrayList<Store> data;
    public Store store;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_table,container,false);

        finish = view.findViewById(R.id.finish);
        add = view.findViewById(R.id.add);
        delete = view.findViewById(R.id.delete);
        amoutTables = view.findViewById(R.id.texttable);
        amoutTables.setText("your store has "+0+" tables");
        loadStore();

        finish.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                try {
                    updateData();//error
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getActivity(),"closed",Toast.LENGTH_LONG).show();
                getActivity().onBackPressed();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                store.setAmountTables(store.getAmountTables()+1);
                amoutTables.setText("your store has "+store.getAmountTables()+" tables");
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                store.setAmountTables(store.getAmountTables()-1);
                amoutTables.setText("your store has "+store.getAmountTables()+" tables");
            }
        });


        return view;

    }

    @android.support.annotation.RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void updateData() throws IOException, JSONException {
        JSONObject jsonRoot = null;
        String jsonText = readText(getContext(), R.raw.data);
        try
        {
            jsonRoot = new JSONObject(jsonText);

        }
        catch (Exception e)
        {
            Toast.makeText(getContext(), "json error",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        JSONArray jArray = jsonRoot.getJSONArray("Store");
        for(int i = 0; i < jsonRoot.length() ; i++)
        {
            JSONObject json_data = jArray.getJSONObject(i);
            int tables = json_data.getInt("tables");
            if (json_data.getString("StoreName").equals(getActivity().getIntent().getStringExtra("storeName")))
            {
                try {

                    json_data.put("tables",store.getAmountTables());//error errorerrorerrorerrorerrorerrorerrorerror
                    jArray.put(json_data);
                    jArray.remove(0);
                    Toast.makeText(getContext(), "data "+json_data.toString(),Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getContext(), "fail to put data",Toast.LENGTH_LONG).show();
                }
            }

        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void loadStore() {
        try {
            data = ReadJSONExample.readDataJSONFile(getContext());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for(int i = 0 ; i < data.size() ; i++)
        {
            if(Objects.requireNonNull(getActivity()).getIntent().getStringExtra("storeName").equals(data.get(i).getStoreName()));
            {
                Toast.makeText(getContext(), "i = " + i + " name = " +getActivity().getIntent().getStringExtra("storeName") + " = " + data.get(i).getStoreName() ,Toast.LENGTH_LONG).show();
                store = data.get(i);
                amoutTables.setText("your store has "+store.getAmountTables()+" tables");
            }

        }

    }


}
