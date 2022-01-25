package com.example.jsonparse;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FlickerElem {
    private final String title,link,media,date_taken;

    public FlickerElem(String title, String link, String media, String date_taken) {
        this.title = title;
        this.link = link;
        this.media = media;
        this.date_taken = date_taken;
    }


    public static List<FlickerElem> getelem(Context context, URL url) throws IOException, JSONException {
        List<FlickerElem> flickElem = new ArrayList<FlickerElem>();
        JSONObject obj = new JSONObject(Readbodyhttp_Req(context,url));
        JSONArray m_jArry = obj.getJSONArray("items");

        for (int i=0; i<m_jArry.length(); i++) {
            flickElem.add(
                    new FlickerElem(
                            m_jArry.getJSONObject(i).getString("title"),
                            m_jArry.getJSONObject(i).getString("link"),
                            m_jArry.getJSONObject(i).getJSONObject("media").getString("m"),
                            m_jArry.getJSONObject(i).getString("date_taken")
                            )
            );

        }
        return  flickElem;
    }


public static String Readbodyhttp_Req(Context constext, URL url) throws IOException {
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    String body = null;
    try {
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append('\n');
        }

        body = sb.toString();

        Log.d("HTTP-GET", body);



    } catch (Exception e){
        Log.i("Exception", String.valueOf(e));
    }
    return  body;
}
    @Override
    public String toString() {
        return "FlickElem{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", date_taken='" + date_taken + '\'' +
                ", media='" + media + '\'' +
                '}';
    }


    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getMedia() {
        return media;
    }

    public String getDate_taken() {
        return date_taken;
    }
}
