package com.example.jsonparse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JSON task = new JSON();
        task.execute("https://www.flickr.com/services/feeds/photos_public.gne?format=json&tags=cats&nojsoncallback=1");
    }
    public class JSON extends AsyncTask<String, Void, List<FlickerElem>> {

        @Override
        protected List<FlickerElem> doInBackground(String... strings) {
            List<FlickerElem> flickelems = new ArrayList<FlickerElem>();
            try {
                flickelems= FlickerElem.getelem(getApplicationContext(), new URL(strings[0]));

            } catch (IOException e) {
                System.out.println("==IO" + e);
            }catch (JSONException e){
                System.out.println("==JSON" + e);
            }

            return flickelems;
        }
        @Override
        protected void onPostExecute(List<FlickerElem> flickItems) {
            super.onPostExecute(flickItems);
            final ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(new CustomListAdapter(flickItems, getApplicationContext()));

        }
    }









   /* public void SHOW(View view) {
        Log.i("Show btn!!!!!!!!!!!! ", "--->");
        String str = "{\n" +
                "  \"1\":{\n" +
                "    \"id_module\":\"f83d6101cc\",\n" +
                "    \"adresse_mac\":\"00:6A:8E:16:C6:26\",\n" +
                "    \"mot_de_passe\":\"mp0001\",\"name\":\"a\"\n" +
                "  },  \n" +
                "  \"2\":{\n" +
                "    \"id_module\":\"64eae5403b\",\n" +
                "    \"adresse_mac\":\"00:6A:8E:16:C6:26\",\n" +
                "    \"mot_de_passe\":\"mp0002\",\n" +
                "    \"name\":\"a\"\n" +
                "  }\n" +
                "}";
        JSONObject json = null;
        try {
            json = new JSONObject(str);
            for(int i=1; i<=2; i++){
                String module = json.getJSONObject(String.valueOf(i)).getString("id_module");
                String address = json.getJSONObject(String.valueOf(i)).getString("adresse_mac");
                Log.i("id_module: ", "--->"+module);
                Log.i("adresse_mac","--->"+address);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/
}