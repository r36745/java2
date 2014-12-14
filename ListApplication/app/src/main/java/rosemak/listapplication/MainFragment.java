package rosemak.listapplication;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by stevierose on 12/9/14.
 */
public class MainFragment extends Fragment  {
    public static final String TAG = "ListFragment";
    private static ArrayList<String> mIdeaArrayList;
    private static ArrayAdapter<String> ideaListAdapter;
    private Idea  newIdea;
    public InputMethodManager imm;
    public String mIdeaName;
    private String mDesrcription;
    private String mPriority;
    //private ListView ideaListView;

    public static final String ARG_IDEANAME = "ideaName";
    private static final String ARG_IDEADESCRIPTION = "ideaDescription";
    private static final String ARG_IDEAPRIORITY = "ideaPriority";
    private OnListClickListener mListener;

    public interface OnListClickListener{
        public void listIdea(Idea idea);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    public static MainFragment newInstance(Idea idea) {
        MainFragment listFragment = new MainFragment();

        Bundle args = new Bundle();
        args.putSerializable(ARG_IDEANAME,idea.getIdea_name());
        args.putSerializable(ARG_IDEADESCRIPTION,idea.getIdea_description());
        args.putSerializable(ARG_IDEAPRIORITY,idea.getIdea_priority());
        listFragment.setArguments(args);
        Log.i(TAG, "Name of something= " +idea.getIdea_name());

        return listFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnListClickListener) {
            mListener = (OnListClickListener) activity;
        }
    }



    public MainFragment() {
        mIdeaName = "";
        mDesrcription = "";
        mPriority = "";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mIdeaArrayList = new ArrayList<>();
        //mIdeaArrayList.add("");

        if (getArguments()!= null) {
            mIdeaName = (String) getArguments().getSerializable(ARG_IDEANAME);
            Log.i(TAG, "Final Idea Name= " + mIdeaName);


           mIdeaArrayList.add(mIdeaName);
            Log.i(TAG, "ArrayList= " +mIdeaArrayList);

           ListView ideaListView = (ListView)getActivity().findViewById(R.id.listViewForm);

            ideaListAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, mIdeaArrayList);

            ideaListView.setAdapter(ideaListAdapter);

        }else {
            Intent formIntent = new Intent(getActivity(), FormActivity.class);
            startActivity(formIntent);
        }


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);







        Button addButton = (Button) getActivity().findViewById(R.id.formButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent formIntent = new Intent(getActivity(), FormActivity.class);
                startActivity(formIntent);
            }
        });



    }



}
