package rosemak.weatherinfo;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by stevierose on 11/30/14.
 */
public class MainFragment extends Fragment {
    public static final String TAG = "MainFragment";
    private static ArrayList<String> mCityArrayList;
    private static ArrayAdapter<String> weatherListAdapter;
    public OnButtonClickListener mListener;

    public interface OnButtonClickListener{
        public void cityNameChosen(String text);
        public void cityPosition(int position);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof OnButtonClickListener) {
            mListener = (OnButtonClickListener) activity;
        }
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
        mCityArrayList = new ArrayList<String>();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cityName = weatherEditText.getText().toString();
                if (cityName.length() < 2) {
                    Toast.makeText(getActivity(), "Invalid Entry", Toast.LENGTH_LONG).show();
                } else {

                    mCityArrayList.add(cityName);
                    mListener.cityNameChosen(cityName);
                    weatherEditText.setText("");
                    weatherListAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, mCityArrayList);
                    weatherListView.setAdapter(weatherListAdapter);

                }

            }
        });

        weatherListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mListener.cityPosition(position);
            }
        });
    }

    public void updateListData() {
        ListView weatherListView = (ListView) getView().findViewById(R.id.weatherListView);
        BaseAdapter cityAdapter = (BaseAdapter) weatherListView.getAdapter();
        cityAdapter.notifyDataSetChanged();
    }

}
