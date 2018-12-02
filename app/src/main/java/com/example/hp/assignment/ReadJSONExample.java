package com.example.hp.assignment;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


import java.util.ArrayList;

public class ReadJSONExample {

    public static ArrayList<Store> readDataJSONFile(Context context) throws IOException,JSONException
    {
        JSONObject jsonRoot = null;
        ArrayList<Store> data = new ArrayList<>();
        String jsonText = readText(context, R.raw.data);
        try
        {
             jsonRoot = new JSONObject(jsonText);

        }
        catch (Exception e)
        {
            Toast.makeText(context, "json error",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        JSONArray jArray = jsonRoot.getJSONArray("Store");
        for(int i = 0; i < jsonRoot.length() ; i++)
        {
            JSONObject json_data = jArray.getJSONObject(i);
            ///
            ArrayList<String > Dishes = new ArrayList<>();
            ArrayList<Integer> Price = new ArrayList<>();

            String name = json_data.getString("StoreName");
            int tables = json_data.getInt("tables");

            JSONArray jsonDishes = json_data.getJSONArray("dishes");
            JSONArray jsonPrice = json_data.getJSONArray("price");
            for(int j = 0 ; j < jsonDishes.length() ; j++)
            {
                Dishes.add(jsonDishes.getString(j));
                Price.add(jsonPrice.getInt(j));
            }

            data.add(new Store(name , tables , Dishes , Price));
        }

        return data;
    }

    public static String readText(Context context, int data) throws IOException {
        InputStream is = context.getResources().openRawResource(data);
        BufferedReader br= new BufferedReader(new InputStreamReader(is));
        StringBuilder sb= new StringBuilder();
        String s = null;
        while((  s = br.readLine())!=null) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }
}
