package rosemak.casestudy2v12;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

//Steven ROseman
public class MainActivity extends ActionBarActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    return DetailedForecast.newInstance(position);
                case 1:
                    return WeeklyForecast.newInstance(position);
                case 2:
                    return HourByHour.newInstance(position);
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    public static class HourByHour extends Fragment {
        public static final String TAG = "MainActivity";
        protected JSONObject weatherData = null;
        public JSONArray jsonArray = null;
        public JSONObject jsonResponse = null;
        public ArrayAdapter<String> adapter;
        public JSONObject object;
        public TextView titleText;
        public TextView mFcttext;
        public String mCondition;
        public String mIcon;
        private static final String ARG_SECTION_NUMBER = "section_number";

        public static HourByHour newInstance(int sectionNumber) {
            HourByHour fragment = new HourByHour();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public HourByHour() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_hourbyhour_forecast, container, false);
            return rootView;
        }

        @Override
        public void onActivityCreated( Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            GetHourByHour getHourByHour = new GetHourByHour();
            getHourByHour.execute();


        }

        public class GetHourByHour extends AsyncTask<Object, Void, JSONObject> {

            @Override
            protected JSONObject doInBackground(Object[] objects) {
                int statusCode = -1;

                try {


                    URL url = new  URL("https://api.wunderground.com/api/72c9d521256891a4/hourly10day/q/NC/Charlotte.json");

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    connection.connect();
                    statusCode = connection.getResponseCode();
                    if (statusCode == HttpURLConnection.HTTP_OK) {

                        InputStream inputStream = connection.getInputStream();
                        String stringReader = IOUtils.toString(inputStream);

                        jsonResponse = new JSONObject(stringReader);
                        //  JSONObject timeObject = jsonResponse.getJSONObject("FCTTIME");
                        //  Log.i(TAG, "TIMEOBJECT" +timeObject);
                        //jsonArray = jsonResponse.getJSONArray("hourly_forecast");



                        // object = jsonResponse.getJSONObject("hourly_forecast").getJSONObject("txt_forecast");
                        // jsonArray = object.getJSONArray("forecastday");
                       // Log.i(TAG, "JSONArray= " + jsonArray);

                        // Log.i(TAG, "Object= " +object);

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
                super.onPostExecute(result);

                weatherData = result;



                try {
                    jsonArray = weatherData.getJSONArray("hourly_forecast");
                    Log.i(TAG, "FINALPlaceJSON= " +jsonArray);
                    for (int i=0; i< jsonArray.length(); i++) {
                        JSONObject weatherString = jsonArray.getJSONObject(i);
                        mCondition = weatherString.getString("condition");
                        Log.i(TAG, "TitleTextFinal= " + mCondition);
                        mIcon = weatherString.getString("wspd");
                        Log.i(TAG, "FctTextFinal= " + mIcon);

                        TextView textView = (TextView)getActivity().findViewById(R.id.condition);
                        textView.setText(mCondition);
                        TextView iconTextView = (TextView) getActivity().findViewById(R.id.icon);
                        iconTextView.setText(mIcon);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }


        }

    }



    public static class WeeklyForecast extends Fragment {
        public static final String TAG = "MainActivity";
        protected JSONObject weatherData = null;
        public JSONArray jsonArray = null;
        public JSONObject jsonResponse = null;
        public ArrayAdapter<String> adapter;
        public JSONObject object;
        public TextView titleText;
        public TextView mFcttext;
        public String mTitle;
        public String fcttext;
        private static final String ARG_SECTION_NUMBER = "section_number";
        public JSONObject timeObject;

        public static WeeklyForecast newInstance(int sectionNumber) {
            WeeklyForecast fragment = new WeeklyForecast();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public WeeklyForecast() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_weekly_forecast, container, false);
            return rootView;
        }

        @Override
        public void onActivityCreated( Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            if (Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)).equals("1")) {
                GetWeeklyForecast getWeeklyForecast = new GetWeeklyForecast();
                getWeeklyForecast.execute();
            }

        }

       public class GetWeeklyForecast extends AsyncTask<Object, Void, JSONObject> {

            @Override
            protected JSONObject doInBackground(Object[] objects) {
                int statusCode = -1;

                try {


                    URL url = new  URL("https://api.wunderground.com/api/72c9d521256891a4/forecast10day/q/NC/Charlotte.json");

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    connection.connect();
                    statusCode = connection.getResponseCode();
                    if (statusCode == HttpURLConnection.HTTP_OK) {

                        InputStream inputStream = connection.getInputStream();
                        String stringReader = IOUtils.toString(inputStream);

                        jsonResponse = new JSONObject(stringReader).getJSONObject("forecast").getJSONObject("txt_forecast");


                        //jsonArray = jsonResponse.getJSONArray("hourly_forecast");



                        // object = jsonResponse.getJSONObject("hourly_forecast").getJSONObject("txt_forecast");
                        // jsonArray = object.getJSONArray("forecastday");
                        Log.i(TAG, "JSONArray= " + jsonArray);

                        // Log.i(TAG, "Object= " +object);

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
                super.onPostExecute(result);

                weatherData = result;



                try {
                    jsonArray = weatherData.getJSONArray("forecastday");
                    Log.i(TAG, "FINALJSONForeCastDay= " +jsonArray);
                    for (int i=0; i< jsonArray.length(); i++) {
                        JSONObject weatherString = jsonArray.getJSONObject(i);
                        mTitle = weatherString.getString("fcttext");
                        fcttext = weatherString.getString("fcttext_metric");

                        TextView textView = (TextView)getActivity().findViewById(R.id.weeklyWeather);
                        textView.setText(mTitle);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }


        }
    }

    public static class DetailedForecast extends Fragment{
        public static final String TAG = "MainActivity";
        protected JSONObject weatherData = null;
        public JSONArray jsonArray = null;
        public JSONObject jsonResponse = null;
        public ArrayAdapter<String> adapter;
        public JSONObject object;
        public TextView titleText;
        public TextView mFcttext;
        public String mTitle;
        public String fcttext;
        private static final String ARG_SECTION_NUMBER = "section_number";

        public static DetailedForecast newInstance(int sectionNumber) {
            DetailedForecast fragment = new DetailedForecast();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public DetailedForecast() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_detailed_forecast, container, false);
            return rootView;
        }

        @Override
        public void onActivityCreated( Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);

            //TextView textView = (TextView)getActivity().findViewById(R.id.weatherData);
            //textView.setText(mCondition);

            if (Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)).equals("0")) {
                GetWeather getWeather = new GetWeather();
                getWeather.execute();
            }


        }

        public class GetWeather extends AsyncTask<Object, Void, JSONObject> {

            @Override
            protected JSONObject doInBackground(Object[] objects) {
                int statusCode = -1;

                try {


                    URL url = new  URL("https://api.wunderground.com/api/72c9d521256891a4/hourly/q/NC/Charlotte.json");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    connection.connect();
                    statusCode = connection.getResponseCode();
                    if (statusCode == HttpURLConnection.HTTP_OK) {

                        InputStream inputStream = connection.getInputStream();
                        String stringReader = IOUtils.toString(inputStream);

                        jsonResponse = new JSONObject(stringReader);
                        //  JSONObject timeObject = jsonResponse.getJSONObject("FCTTIME");
                        //  Log.i(TAG, "TIMEOBJECT" +timeObject);
                        //jsonArray = jsonResponse.getJSONArray("hourly_forecast");



                        // object = jsonResponse.getJSONObject("hourly_forecast").getJSONObject("txt_forecast");
                        // jsonArray = object.getJSONArray("forecastday");
                        Log.i(TAG, "JSONArray= " + jsonArray);

                        // Log.i(TAG, "Object= " +object);

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
                super.onPostExecute(result);

                weatherData = result;



                try {
                    jsonArray = weatherData.getJSONArray("hourly_forecast");
                    Log.i(TAG, "FINALJSON= " +jsonArray);
                    for (int i=0; i< jsonArray.length(); i++) {
                        JSONObject weatherString = jsonArray.getJSONObject(i);
                        mTitle = weatherString.getString("wx");
                        fcttext = weatherString.getString("feelslike");

                        TextView textView = (TextView)getActivity().findViewById(R.id.weatherData);
                        textView.setText(mTitle);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }


        }

    }

}
