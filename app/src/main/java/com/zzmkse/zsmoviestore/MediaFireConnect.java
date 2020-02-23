package com.zzmkse.zsmoviestore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MediaFireConnect {

    public static String getVideoLink(String videoLink) throws JSONException, IOException {
        String query= URLEncoder.encode(videoLink,"utf-8");
        String url="https://media-fire-api.herokuapp.com/?url="+query;
        URL websiteurl=new URL(url);
        HttpURLConnection myConnection=(HttpURLConnection)websiteurl.openConnection();
        myConnection.setRequestMethod("GET");
        myConnection.setRequestProperty("Content-type","application/json");
        myConnection.setRequestProperty("Accept","application/json;charset=utf-8");
        myConnection.setDoInput(true);
        InputStream stream=myConnection.getInputStream();
        BufferedReader reader=new BufferedReader(new InputStreamReader(stream));
        String line="";
        StringBuffer buffer=new StringBuffer();
        while ((line=reader.readLine())!=null)
        {
            buffer.append(line);
        }
        JSONArray myArray=new JSONArray(buffer.toString());
        JSONObject object=myArray.getJSONObject(0);
        String directlink=object.getString("file");
        return directlink;
    }
}
