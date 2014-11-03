package rosemak.veganbeer;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by stevierose on 10/30/14.
 */
public class BeerListFragment extends ListFragment {
    public static final String TAG = "BEERLISTFRAGMENT.TAG";
    protected  JSONObject beerData = null;
    public JSONArray jsonArray = null;
    public JSONObject jsonResponse = null;
    public ArrayAdapter<String> adapter;


    private OnListClickListener mListener;
    public static BeerListFragment newInstance() {

        BeerListFragment frag = new BeerListFragment();

        return frag;
    }

    public interface OnListClickListener {
        public void displayText(String text);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof OnListClickListener) {

            mListener = (OnListClickListener) activity;


        } else {
            throw  new  IllegalArgumentException("Doesn't work");
        }


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        if (isNetworkAvailable()) {

            adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
            setListAdapter(adapter);
            GetBeer getBeer = new GetBeer();
            getBeer.execute();

        } else {
            Toast.makeText(getActivity(),"No WIFI Connection", Toast.LENGTH_LONG).show();
        }

    }

    private boolean isNetworkAvailable() {

        ConnectivityManager manager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.master_detail_fragment, container, false);

        return view;
    }

    @Override
    public void onListItemClick(ListView _l, View _v, int _position, long id) {


        String beers = (String)_l.getItemAtPosition(_position);
        mListener.displayText(beers);

    }

    private class GetBeer extends AsyncTask<Object, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(Object[] objects) {
            int statusCode = -1;

            try {

                URL url = new  URL("https://api.brewerydb.com/v2/categories?key=851b96f1795e0c133ec62aca2bc49ab4&format=json");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.connect();
                statusCode = connection.getResponseCode();
                if (statusCode == HttpURLConnection.HTTP_OK) {

                    InputStream inputStream = connection.getInputStream();
                    String stringReader = IOUtils.toString(inputStream);

                    jsonResponse = new JSONObject(stringReader);

                    jsonArray = jsonResponse.getJSONArray("data");

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
                Log.i(TAG, "beerPlus=  "+ jsonArray);
                for (int i=0; i<jsonArray.length();i++) {
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
