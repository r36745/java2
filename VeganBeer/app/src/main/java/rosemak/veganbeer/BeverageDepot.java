package rosemak.veganbeer;

import android.app.Activity;
import android.os.Bundle;


public class BeverageDepot extends Activity implements BeerListFragment.OnListClickListener {
    public static final String TAG = "Beverage Depot";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beverage_depot);




        if (savedInstanceState == null) {

            BeerListFragment frag = BeerListFragment.newInstance();
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container,frag, BeerListFragment.TAG)
                    .commit();

        }


    }

    @Override
    public void displayText(String text) {

          BeerDisplayFragment frag = (BeerDisplayFragment)getFragmentManager().findFragmentByTag(BeerDisplayFragment.TAG);

        if (frag == null) {
            frag = BeerDisplayFragment.newInstance(text);
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, frag, BeerDisplayFragment.TAG)
                    .commit();
        } else {
            frag.setDisplayText(text);
        }

    }


}
