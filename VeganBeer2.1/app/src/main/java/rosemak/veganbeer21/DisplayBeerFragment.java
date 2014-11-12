package rosemak.veganbeer21;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by stevierose on 11/10/14.
 */
public class DisplayBeerFragment extends Fragment {
    public static final String TAG = "DISPLAYFRAGMENT.TAG";
    public static final String ARG_TEXT_BEER = "DISPLAYFRAGMENT.ARG_TEXT_BEER";

    public static DisplayBeerFragment newInstance(String text) {
        DisplayBeerFragment frag = new DisplayBeerFragment();

        Bundle args = new Bundle();
        args.putString(ARG_TEXT_BEER, text);
        frag.setArguments(args);

        return frag;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.side_detail_fragment, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle args = getArguments();


        if (args != null && args.containsKey(ARG_TEXT_BEER)){
            setCurrentPositon(args.getString(ARG_TEXT_BEER));
        }

    }

    public void setCurrentPositon(String text) {

        getArguments().putString(ARG_TEXT_BEER, text);
        TextView tv = (TextView)getView().findViewById(R.id.beerName);
        tv.setText(text);
    }



}
