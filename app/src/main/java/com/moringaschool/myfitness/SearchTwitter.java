package com.moringaschool.myfitness;

import android.os.Bundle;
import android.widget.Toast;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import androidx.appcompat.app.AppCompatActivity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class SearchTwitter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_twitter);
        TwitterListAdapter  adapter= new TwitterListAdapter(this,R.layout.listtwitters,loadTweets());
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
        ArrayList<Tweet> twits = new ArrayList<Tweet>();
        try {
            HttpClient cl = new DefaultHttpClient();
            HttpGet get = new HttpGet("http://search.twitter.com/search.json?q=android");
            HttpResponse rep = cl.execute(get);
            if(rep.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                String result = EntityUtils.toString(rep.getEntity());
                JSONObject root = new JSONObject(result);
                JSONArray sessions = root.getJSONArray("results");

                for(int i = 0; i<sessions.length(); i++){
                    JSONObject session = sessions.getJSONObject(i);
                    Tweet tweet = new Tweet();
                    tweet.content = session.getString("text");
                    tweet.author = session.getString("from_user");
                    twits.add(tweet);
                }
            }

        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"Error encountered loading tweets",Toast.LENGTH_SHORT);
        }
    return twits;
}

}

