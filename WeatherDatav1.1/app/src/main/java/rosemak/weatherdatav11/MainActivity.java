package rosemak.weatherdatav11;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;


public class MainActivity extends Activity implements MainFragment.OnButtonClickListener {

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        MainFragment mainFragment = new MainFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer1, mainFragment, MainFragment.TAG)
                .commit();
    }


    @Override
    public void cityClass(City cityClass) {

        DetailFragment detailFragment = DetailFragment.newInstance(cityClass.getWeather_name(), cityClass.getWeatherType(), cityClass.getWeatherDescription());
        getFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer2, detailFragment, DetailFragment.TAG)
                .commit();
        Log.i(TAG, "The City Name= " + cityClass.getWeather_name() + "Weather Type= " + cityClass.getWeatherType());
    }
}
