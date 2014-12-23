package rosemak.casestudy3;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Steven Roseman
 */
public class RecentStories_fragment extends Fragment {

    public static final String TAG = "RecentStoriesFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recent_stories, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String[] rs = {
                "Sony Calls Hack a State-Sponsored Criminal Act",
                "Happy Solstice! Get the Scientific Reason for the Season",
                "DNA Test Leads to Murder Charges...20 Years Later",
                "Four Teens Die in Fiery Head-On Crash",
        };

        ListView rsListView = (ListView) getActivity().findViewById(R.id.recentStories);
        ArrayAdapter rsAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, rs );
        rsListView.setAdapter(rsAdapter);

    }
}
