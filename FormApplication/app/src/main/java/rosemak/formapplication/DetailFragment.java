package rosemak.formapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Steven Roseman
 */
public class DetailFragment extends Fragment {
    public static final String TAG = "DetailFragment";
    private static final String ARG_IDEANAME = "cityName";
    private static final String ARG_IDEADESCRIPTION = "weatherType";
    private static final String ARG_IDEAPRIORITY = "weatherDescription";
    private static final String IDEAFILE = "ideaInfo.txt";
    private String mIdeaName;
    private String mIdeaDescription;
    private String mIdeaPriority;
    private DetailClickListener mListener;

    public interface DetailClickListener {
        public int getDelete();
        public void deleteContact();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof DetailClickListener) {
            mListener = (DetailClickListener) activity;
        } else {
            throw new IllegalArgumentException("Detail Click Listener Not Working");
        }
    }

    public static DetailFragment newInstance(String _name, String _description, String _priority) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_IDEANAME, _name);
        args.putSerializable(ARG_IDEADESCRIPTION, _description);
        args.putSerializable(ARG_IDEAPRIORITY, _priority);
        detailFragment.setArguments(args);

        return detailFragment;



    }

    public DetailFragment() {
        mIdeaName = "";
        mIdeaDescription = "";
        mIdeaPriority = "";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View detailView = inflater.inflate(R.layout.fragment_detail, container, false);
        return detailView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        UIHelper helper = new UIHelper();
        final TextView name = (TextView)getView().findViewById(R.id.ideaNameTextView);
        TextView description = (TextView) getView().findViewById(R.id.ideaDescription);
        TextView priority = (TextView) getView().findViewById(R.id.ideaPriority);
        Button deleteButton = (Button) getView().findViewById(R.id.deleteButton);

        if (getArguments() != null) {
            mIdeaName = (String) getArguments().getSerializable(ARG_IDEANAME);
            mIdeaDescription = (String) getArguments().getSerializable(ARG_IDEADESCRIPTION);
            mIdeaPriority = (String) getArguments().getSerializable(ARG_IDEAPRIORITY);

            name.setText(mIdeaName);
            description.setText(mIdeaDescription);
            priority.setText(mIdeaPriority);

            String text = helper.readFromFile(getActivity(), IDEAFILE);


            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
            alertDialog.setTitle("Saved Data Window");
            alertDialog.setIcon(R.drawable.ic_launcher);
            alertDialog.setMessage("Weather Data Selected");
            final TextView weatherDescription = new TextView(getActivity());
            weatherDescription.setText(text);
            alertDialog.setView(weatherDescription);
            alertDialog.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alertDialog.show();

            Log.i(TAG, "Saved data= " + text);


                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mListener.deleteContact();
                    }
                });






        }

    }
}
