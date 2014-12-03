package rosemak.weatherinfo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

//Steven Roseman
//Java II

public class MainActivity extends Activity implements MainFragment.OnButtonClickListener{
    public static final String TAG = "MainActivity";
    protected JSONObject weatherData = null;
    private JSONArray jsonArray;
    public String mainWeatherType;
    public String descriptionType;
    private City mCity;
    private ArrayList<City> mCityArrayList;
    private static final String WEATHERFILE = "weatherData.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment mainFragment = new MainFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.container1, mainFragment, MainFragment.TAG)
                .commit();
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
            return isAvailable;
        }
        return isAvailable;
    }

    private void writeToFile(String _fileName, String _weatherDescription, String _weatherType) {
        File external = getExternalFilesDir(null);
        File file = new File(external, _fileName);

        try {
            FileOutputStream outPutStream = new FileOutputStream(file);
            outPutStream.write(_weatherDescription.getBytes());
            outPutStream.write(_weatherType.getBytes());
            outPutStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFromFile(String _fileName) {
        File external = getExternalFilesDir(null);
        File file = new File(external, _fileName);
        if (!file.exists()) {
            return null;
        }

        try {
            FileInputStream inputStream = new FileInputStream(file);
            InputStreamReader inPutReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inPutReader);

            StringBuffer buffer = new StringBuffer();
            String bufferString = null;
            while ((bufferString = bufferedReader.readLine()) !=null ){
                buffer.append(bufferString +"\n");
            }
            bufferedReader.close();
            return buffer.toString().trim();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
           return null;
    }


    @Override
    public void cityNameChosen(String city) {

        String openWeatherURL = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&APPID=32c831d8a6937f237acff8eef3d4a58c";
        try {
            if(isNetworkAvailable()){
                URL queryURL = new URL(openWeatherURL);
                new GetWeather().execute(queryURL);
            } else {

                String text = readFromFile(WEATHERFILE);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Saved Data Window");
                alertDialog.setIcon(R.drawable.ic_launcher);
                alertDialog.setMessage("Weather Data Selected");
                final TextView weatherDescription = new TextView(MainActivity.this);
                weatherDescription.setText(text);
                alertDialog.setView(weatherDescription);
                alertDialog.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();

                Log.i(TAG, "Saved data= " + text);


            }

        } catch (MalformedURLException e) {
            Log.e(TAG, "Malformed Exception= " + e);
        }

    }

    @Override
    public void cityPosition(int position) {
       mCityArrayList = new ArrayList<City>();
        mCityArrayList.add(new City(descriptionType,mainWeatherType));

        DisplayFragment displayFragment = (DisplayFragment) getFragmentManager().findFragmentByTag(DisplayFragment.TAG);

        if(displayFragment == null) {
            displayFragment = DisplayFragment.newInstance(mCityArrayList.get(position));
            getFragmentManager().beginTransaction()
                    .replace(R.id.container2, displayFragment, DisplayFragment.TAG)
                    .commit();
        }

    }



    private class GetWeather extends AsyncTask<URL, Void, JSONObject> {
        final String TAG = "ASYNCTASK";

        @Override
        protected JSONObject doInBackground(URL... urls) {

            String jsonString = "";

            for (URL queryURL : urls) {
                try {
                    URLConnection conn = queryURL.openConnection();
                    jsonString = IOUtils.toString(conn.getInputStream());
                    break;
                } catch (IOException e) {
                    Log.e(TAG, "Could not establish URLConnection to " + queryURL.toString());
                }
            }
            Log.i(TAG, "Received Data=  " + jsonString);

            JSONObject apiData;

            try {
                apiData = new JSONObject(jsonString);
            } catch (JSONException e) {
                Log.e(TAG, "Cannot convert API to JSON");
                apiData = null;
            }

            try {
                jsonArray = apiData.getJSONArray("weather");
                Log.i(TAG, "Data Received= " + apiData.toString());
            } catch (JSONException e) {
                Log.e(TAG, "Not parsing data record: " + apiData.toString());
            }

            return apiData;
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            weatherData = result;
            try {
                jsonArray = weatherData.getJSONArray("weather");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject weatherString = jsonArray.getJSONObject(i);
                    mainWeatherType = weatherString.getString("main");
                    descriptionType = weatherString.getString("description");
                    Log.i(TAG, "Weather= " + mainWeatherType);
                    Log.i(TAG, "Description= " + descriptionType);

                    writeToFile(WEATHERFILE, descriptionType, mainWeatherType);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }
}
