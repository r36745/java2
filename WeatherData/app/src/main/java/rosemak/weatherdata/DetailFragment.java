package rosemak.weatherdata;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by stevierose on 12/3/14.
 */
public class DetailFragment extends Fragment{

    public static final String TAG = "DetailFragment";

    private static final String ARG_WEATHERNAME = "cityName";
    private static final String ARG_WEATHERTYPE = "weatherType";
    private static final String ARG_WEATHERDESCRIPTION = "weatherDescription";

    private String mCityName;
    private String mWeatherType;
    private String mWeatherDescription;

    public static DetailFragment newInstance(String cityName, String weatherType, String weatherDescription) {
        DetailFragment frag = new DetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_WEATHERNAME, cityName);
        args.putSerializable(ARG_WEATHERTYPE, weatherType);
        args.putSerializable(ARG_WEATHERDESCRIPTION, weatherDescription);
        frag.setArguments(args);
        return frag;
    }

    public DetailFragment() {
         mCityName = "";
        mWeatherType = "";
       mWeatherDescription = "";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments()!= null) {
            mCityName = (String) getArguments().getSerializable(ARG_WEATHERNAME);
            mWeatherType = (String) getArguments().getSerializable(ARG_WEATHERTYPE);
            mWeatherDescription = (String) getArguments().getSerializable(ARG_WEATHERDESCRIPTION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View detailView = inflater.inflate(R.layout.detail_fragment, container, false);
        return detailView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        TextView cityName = (TextView) getActivity().findViewById(R.id.cityNameWeather);
        cityName.setText(mCityName);

        TextView weatherType = (TextView) getActivity().findViewById(R.id.weatherType);
        weatherType.setText(mWeatherType);

        TextView weatherDescription = (TextView) getActivity().findViewById(R.id.weatherDescription);
        weatherDescription.setText(mWeatherDescription);

    }
}
