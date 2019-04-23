package com.limra.jaipurilohar.util;


import android.content.Context;
import android.text.TextUtils;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util {

    public Util() {

    }
    private static HashMap imageMap;

    private static void loadMap(){
        imageMap = new HashMap<>();
        imageMap.put("shadab",R.drawable.shadab);
        imageMap.put("tony", R.drawable.tony);
        imageMap.put("usman",R.drawable.usman);
        imageMap.put("nizam", R.drawable.nizam);
        imageMap.put("rauf",R.drawable.rauf);
        imageMap.put("siraz", R.drawable.siraz);
        imageMap.put("wasim",R.drawable.wasim);
        imageMap.put("akram",R.drawable.akram);
        imageMap.put("aslam",R.drawable.aslam);
        imageMap.put("naim",R.drawable.naim);
        imageMap.put("saddam",R.drawable.saddam);
        imageMap.put("azaz",R.drawable.azaz);
        imageMap.put("fayyaz",R.drawable.fayyaz);
        imageMap.put("zakir",R.drawable.zakir);
        imageMap.put("aabid",R.drawable.aabid);
        imageMap.put("nizamuddin",R.drawable.nizamuddin);
    }

    public static Integer getImageUrl(String imageUrl){
        loadMap();
        Integer drawable = null;
        if(!TextUtils.isEmpty(imageUrl))
            drawable =  (Integer) imageMap.get(imageUrl);
         else
            drawable = R.drawable.avtar;
        unloadMap();
        return drawable;
    }

    private static void unloadMap() {
        imageMap = null;
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
