package com.moringaschool.myfitness;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import androidx.appcompat.app.AppCompatActivity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.jar.Attributes;

public class SearchTwitter extends ListActivity {
ListView lst;
    ArrayList<Tweet> twits = new ArrayList<Tweet>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       new MyTask().execute();
    }
    @Override
    protected void onStart() {

        super.onStart();
    }
    @Override
    protected  void onPause () {

        super.onPause();
    }
    @Override
    protected  void onStop() {

        super.onStop();
    }
    @Override
    protected  void onRestart() {

        super.onRestart();
    }
    @Override
    protected  void onDestroy() {

        super.onDestroy();
    }
private ArrayList<Tweet> loadTweets(){

        String searchString = "developer";
        String CONSUMER_API_KEY="deXjw80SMgcJtF46qSu2lMe9A";

        String bearerToken = "AAAAAAAAAAAAAAAAAAAAAEoobQEAAAAAt7pFHG%2Fc8sxTySridMyoVo2DCZ4%3DimcDhLZoUL7nwGCF61fzXHbpvgZvoO5DNqWmU9xQN3a2NPbP2y";
        try {
            HttpClient cl = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet("https://api.twitter.com/2/tweets?ids=1261326399320715264,1278347468690915330");
            httpGet.setHeader("Authorization", String.format("Bearer %s", bearerToken));
            httpGet.setHeader("Content-Type", "application/json");
            httpGet.setHeader("start_time", "2021-01-01T00:00:00Z");
            httpGet.setHeader("granularity", "day");
            Log.e("headers",httpGet.getURI().toString());
            HttpResponse rep = cl.execute(httpGet);
            if(rep.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                String result = EntityUtils.toString(rep.getEntity());
                Log.e("Response result",result);
                JSONObject root = new JSONObject(result);
                JSONArray sessions = root.getJSONArray("data");

                for(int i = 0; i<sessions.length(); i++){
                    JSONObject session = sessions.getJSONObject(i);
                    Tweet tweet = new Tweet();
                    tweet.content = session.getString("text");
                    tweet.author = session.getString("id");
                    twits.add(tweet);
                }
            }else
            {
                Log.e("No response 00000000000",rep.getStatusLine().toString());

            }

        }
        catch (Exception e){
            Log.e("Okwamo 00000000000",e.toString());
        }
    return twits;
}
    private class MyTask extends AsyncTask<Void,Void,Void>{
        private ProgressDialog progressDialog;


        @Override
        protected Void doInBackground(Void... voids) {
            loadTweets();
            return null;
        }

        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(SearchTwitter.this,
                    "", "Loading. Please wait...", true);
        }



        protected void onPostExecute(Void result) {
            progressDialog.dismiss();
            setListAdapter(new TwitterListAdapter(
                    SearchTwitter.this, R.layout.listtwitters, twits));
        }

    }

}

