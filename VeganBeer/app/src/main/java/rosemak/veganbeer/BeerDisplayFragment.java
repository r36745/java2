package rosemak.veganbeer;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//Steven Roseman
//Java2

public class BeerDisplayFragment extends Fragment {
    public static final String TAG = "DISPLAYFRAGMENT.TAG";
    public static final String ARG_TEXT_BEER = "DISPLAYFRAGMENT.ARG_TEXT_BEER";


    public static BeerDisplayFragment newInstance(String text) {
        BeerDisplayFragment frag;
        frag = new BeerDisplayFragment();

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
        if (args != null && args.containsKey(ARG_TEXT_BEER)) {
            setDisplayText(args.getString(ARG_TEXT_BEER));
        }

    }

    public void setDisplayText(String text) {

        TextView tv = (TextView) getView().findViewById(R.id.beer_vendorName);
        tv.setText(text);

    }
}
