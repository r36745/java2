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
import android.widget.AdapterView;
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
    public ListView ideaListView;

    public static final String ARG_IDEANAME = "ideaName";
    private static final String ARG_IDEADESCRIPTION = "ideaDescription";
    private static final String ARG_IDEAPRIORITY = "ideaPriority";
    private OnListClickListener mListener;

    public interface OnListClickListener{
        public void listIdea(Idea idea);
    }



    public static MainFragment newInstance(String ideaName, String ideaDescription, String ideaPriority) {
        MainFragment listFragment = new MainFragment();

        Bundle args = new Bundle();
        args.putSerializable(ARG_IDEANAME,ideaName);
        args.putSerializable(ARG_IDEADESCRIPTION,ideaDescription);
        args.putSerializable(ARG_IDEAPRIORITY,ideaPriority);
        listFragment.setArguments(args);
        Log.i(TAG, "Name of something= " +ideaName);

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ideaListView = (ListView)getActivity().findViewById(R.id.mainListView);


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        mIdeaArrayList = new ArrayList<>();
        // mIdeaArrayList.add("");



        if (getArguments()!= null) {
             mIdeaName = (String) getArguments().getSerializable(ARG_IDEANAME);
            Log.i(TAG, "Final Idea Name= " + mIdeaName);
            String text = "myTet";
            mIdeaArrayList.add(text);



            Log.i(TAG, "ArrayList= " +mIdeaArrayList);



            // mIdeaArrayList.add(newIdea.getIdea_name(mIdeaName));
            //IdeaAdapter ideaAdapter = new IdeaAdapter(getActivity(),mIdeaArrayList );

            ideaListAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, mIdeaArrayList);
            ideaListView.setAdapter(ideaListAdapter);

            ideaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });


        }else {
            Intent formIntent = new Intent(getActivity(), FormActivity.class);
            startActivity(formIntent);
        }







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
