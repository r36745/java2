package rosemak.casestudy3;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Steven Roseman
 */
public class FeaturedStory_Fragment extends Fragment {
    public static final String TAG = "FeaturedStoryFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_featured_story, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextView featuredStory = (TextView) getActivity().findViewById(R.id.featureStory);

        String featureStory = "President Barrack Obama said Friday that Sony Pictures" + "\n" + "Entertainment made a mistake by nixing the release of a comedic film" + "\n" +" after company was hacked and received cyber threats.";
        featuredStory.setText(featureStory);
    }
}
