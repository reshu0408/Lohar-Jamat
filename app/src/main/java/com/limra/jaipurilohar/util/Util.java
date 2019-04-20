package com.limra.jaipurilohar.util;


import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.limra.jaipurilohar.R;
import com.limra.jaipurilohar.search.SearchActivity;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public Util() {

    }

    public static String loadJSONFromAsset(Context context, String fileName) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static List<String> getGotraList(Context context){
        List<String> gotraList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(Util.loadJSONFromAsset(context, "gotra.json"));
            for (int i = 0; i < jsonArray.length(); i++) {
                String gotra = jsonArray.getString(i);
                gotraList.add(gotra);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  gotraList;
    }
}
