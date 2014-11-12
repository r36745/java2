package rosemak.veganbeer21;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by stevierose on 11/8/14.
 */
public class BeerListFragment extends ListFragment{

    public static final String TAG = "BEERLISTFRAGMENT.TAG";
    private OnListClickListener mListener;

    public static BeerListFragment newInstance() {

        BeerListFragment frag = new BeerListFragment();
        return frag;
    }

    public interface OnListClickListener {
        public void currentPosition(String text);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof OnListClickListener) {

            mListener = (OnListClickListener) activity;
        } else {

            throw new IllegalArgumentException("Doesnt Work");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String president = (String) l.getItemAtPosition(position);
        mListener.currentPosition(president);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.master_detail_fragment,container, false);

        return rootView;
    }


}
