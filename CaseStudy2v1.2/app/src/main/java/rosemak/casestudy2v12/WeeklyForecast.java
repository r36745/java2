package rosemak.casestudy2v12;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Steven ROSeman
 */
public class  WeeklyForecast extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static WeeklyForecast newInstance(int sectionNumber) {
        WeeklyForecast fragment = new WeeklyForecast();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
