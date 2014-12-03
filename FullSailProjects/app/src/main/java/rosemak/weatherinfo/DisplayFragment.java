package rosemak.weatherinfo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by stevierose on 12/1/14.
 */
public class DisplayFragment extends Fragment {
    public static final String TAG = "DisplayFragment";
    public static final String ARG_CITY_WEATHER = "DisplayFragment.ARG_CITY_WEATHER";
    private City mCity;


    public static DisplayFragment newInstance(City city) {
        DisplayFragment fragment;
        fragment = new DisplayFragment();

        Bundle args = new Bundle();
        args.putSerializable(ARG_CITY_WEATHER, city);
        fragment.setArguments(args);
        
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View displayView = inflater.inflate(R.layout.detail_fragment, container, false);

        return displayView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle args = getArguments();
        if (args != null && args.containsKey(ARG_CITY_WEATHER)) {
            mCity = (City) args.getSerializable(ARG_CITY_WEATHER);
            setDisplayCityData((City) args.getSerializable(ARG_CITY_WEATHER));

        }

    }

    public void setDisplayCityData(City city) {

        getArguments().putSerializable(ARG_CITY_WEATHER,city);
        TextView textView = (TextView) getView().findViewById(R.id.weatherDescription);
        textView.setText(mCity.getWeatherType());

        TextView textView1 = (TextView) getView().findViewById(R.id.weatherType);
        textView1.setText(mCity.getWeatherDescription());

    }
}
