package rosemak.weatherdata;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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

/**
 * Created by stevierose on 12/3/14.
 */
public class MainFragment extends Fragment {

    public static final String TAG = "MainFragment";
    private static ArrayList<City> mCityArrayList;
    private static ArrayAdapter<City> weatherListAdapter;
    public OnButtonClickListener mListener;
    protected JSONObject weatherData = null;
    private JSONArray jsonArray;
    public String mainWeatherType;
    public String descriptionType;
    private String cityName;
    private City newCity;

    public interface OnButtonClickListener{

        public void cityClass(City cityClass);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof OnButtonClickListener) {
            mListener = (OnButtonClickListener) activity;
        }
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
            return isAvailable;
        }
        return isAvailable;
    }

    private void writeToFile(String _fileName, String _weatherDescription, String _weatherType) {
        File external = getActivity().getExternalFilesDir(null);
        File file = new File(external, _fileName);

        try {
            FileOutputStream outPutStream = new FileOutputStream(file);
            outPutStream.write(_weatherDescription.getBytes());
            outPutStream.write(_weatherType.getBytes());
            outPutStream.close();
        } catch (FileNotFoundException e) {
            Log.e(TAG, "File Not Found" , e);
        } catch (IOException e) {
            Log.e(TAG, "IOException= ", e);
        }
    }

    private String readFromFile(String _fileName) {
        File external = getActivity().getExternalFilesDir(null);
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
            Log.e(TAG, "Input File Not Found" , e);
        } catch (IOException e) {
            Log.e(TAG, "Input IOException= ", e);
        }
        return null;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.master_fragment, container, false);

        return mainView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final EditText weatherEditText = (EditText) getActivity().findViewById(R.id.weatherEditText);
        final ListView weatherListView = (ListView) getActivity().findViewById(R.id.weatherListView);
        Button searchButton = (Button) getActivity().findViewById(R.id.searchButton);
        newCity = new City();




        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cityName = weatherEditText.getText().toString();
                String openWeatherURL = "http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&APPID=32c831d8a6937f237acff8eef3d4a58c";
                if (cityName.length() < 2) {
                    Toast.makeText(getActivity(), "Invalid Entry", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        if(isNetworkAvailable()) {
                            URL queryURL = new URL(openWeatherURL);
                            new GetWeather().execute(queryURL);

                            newCity.setWeather_name(cityName);
                            newCity.setWeather_description(descriptionType);
                            newCity.setWeather_type(mainWeatherType);




                        } else {
                            //load saved Data
                        }

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }



                }
                mCityArrayList = new ArrayList<City>();
                mCityArrayList.add(newCity);
                weatherListAdapter = new ArrayAdapter<City>(getActivity(),android.R.layout.simple_list_item_1, mCityArrayList);
                weatherListView.setAdapter(weatherListAdapter);
                Log.i(TAG, "Array List= " +mCityArrayList);

                // mCityArrayList.add(cityName);
                // mListener.cityNameChosen(cityName);
                weatherEditText.setText("");
                //  weatherListAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, mCityArrayList);
                //  weatherListView.setAdapter(weatherListAdapter);
            }
        });

        weatherListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                City city = mCityArrayList.get(position);
                mListener.cityClass(city);

            }
        });


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



                }
            } catch (JSONException e) {
                Log.e(TAG, "JSON Exception= " ,e);
            }

        }

    }
}
