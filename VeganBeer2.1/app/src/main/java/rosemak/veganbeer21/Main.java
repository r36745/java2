package rosemak.veganbeer21;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class Main extends ActionBarActivity implements BeerListFragment.OnListClickListener{

    public static final String TAG = "Beverage Depot";
    protected  JSONObject beerData = null;
    public JSONArray jsonArray = null;
    public JSONObject jsonResponse = null;
    public ArrayAdapter<String> adapter;
    public ArrayList<String> beveragetypes = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        PreferenceManager.setDefaultValues(this, R.xml.settings, false);
        SettingsPreferenceFragment settFrag = new SettingsPreferenceFragment();



            if (settFrag != null) {
                getFragmentManager().beginTransaction().replace(android.R.id.content, settFrag, SettingsPreferenceFragment.TAG)
                        .commit();

            }


        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            if(savedInstanceState == null) {
              //  beerExecution();

                BeerListFragment frag = BeerListFragment.newInstance();
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, frag, BeerListFragment.TAG)
                        .commit();

                adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
                frag.setListAdapter(adapter);


            }

        } else {

            if(savedInstanceState == null) {

                BeerListFragment frag = BeerListFragment.newInstance();
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container1, frag, BeerListFragment.TAG)
                        .commit();

                adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
                frag.setListAdapter(adapter);


            }

        }


    }

    public boolean isNetworkAvailable() {

        ConnectivityManager manager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;

    }

    public void beerExecution() {
        GetBeer getBeer = new GetBeer();
        getBeer.execute();
    }

    @Override
    public void currentPosition(String text) {
        Log.i(TAG, "Displaying= " +text);


        DisplayBeerFragment frag = (DisplayBeerFragment)getFragmentManager().findFragmentByTag(DisplayBeerFragment.TAG);

        if (frag ==null) {
            frag = DisplayBeerFragment.newInstance(text);
            getFragmentManager().beginTransaction().replace(R.id.fragment_container,frag,DisplayBeerFragment.TAG)
                    .commit();
        } else {
            frag.setCurrentPositon(text);
        }


    }

    public class GetBeer extends AsyncTask<Object, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(Object[] objects) {
            int statusCode = -1;

            try {

                URL url = new URL("https://api.brewerydb.com/v2/categories?key=851b96f1795e0c133ec62aca2bc49ab4&format=json");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.connect();
                statusCode = connection.getResponseCode();
                if (statusCode == HttpURLConnection.HTTP_OK) {

                    InputStream inputStream = connection.getInputStream();
                    String stringReader = IOUtils.toString(inputStream);

                    jsonResponse = new JSONObject(stringReader);

                    jsonArray = jsonResponse.getJSONArray("data");
                    Log.i(TAG, "responses= " + jsonArray);

                } else {
                    Log.i(TAG, "Error= " + statusCode);
                }
            } catch (MalformedURLException e) {

                Log.e(TAG, "Exception caught 1:", e);
            } catch (IOException e) {
                Log.e(TAG, "Exception caught 2: ", e);
            } catch (Exception e) {
                Log.e(TAG, "Exception caught 3: ", e);
            }

            return jsonResponse;
        }

        @Override
        protected void onPostExecute(JSONObject result) {


            beerData = result;


            try {


                jsonArray = beerData.getJSONArray("data");
                Log.i(TAG, "beerPlus=  " + jsonArray);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject beerString = jsonArray.getJSONObject(i);
                    String beerName = beerString.getString("name");

                    adapter.addAll(beerName);

                }


            } catch (JSONException e) {
                Log.e(TAG, "Exception2= ", e);
            }

        }
    }


}
