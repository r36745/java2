package rosemak.weatherdata;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


public class MainActivity extends Activity implements MainFragment.OnButtonClickListener {
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment mainFragment = new MainFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.container1, mainFragment, MainFragment.TAG)
                .commit();
    }


    @Override
    public void cityClass(City cityClass) {

        Toast.makeText(this, cityClass.getWeather_name(), Toast.LENGTH_LONG).show();
        Log.i(TAG, "Name= " + cityClass.getWeather_name());
    }
}
