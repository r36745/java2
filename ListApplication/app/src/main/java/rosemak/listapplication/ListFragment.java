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
public class ListFragment extends Fragment  {
    public static final String TAG = "ListFragment";
    private static ArrayList<Idea> mIdeaArrayList;
    private static ArrayAdapter<Idea> ideaListAdapter;
    private Idea  newIdea;
    public InputMethodManager imm;
    public String mIdeaName;
    private String mDesrcription;
    private String mPriority;
    private ListView ideaListView;
    private static final String ARG_IDEANAME = "ideaName";
    private static final String ARG_IDEADESCRIPTION = "ideaDescription";
    private static final String ARG_IDEAPRIORITY = "ideaPriority";

    public static ListFragment newInstance(Idea idea) {
        ListFragment listFragment = new ListFragment();

        Bundle args = new Bundle();
        args.putSerializable(ARG_IDEANAME,idea.getIdea_name());
        args.putSerializable(ARG_IDEADESCRIPTION,idea.getIdea_description());
        args.putSerializable(ARG_IDEAPRIORITY,idea.getIdea_priority());
        listFragment.setArguments(args);
        Log.i(TAG, "Name of something= " +idea.getIdea_name());
       /* Bundle args = new Bundle();
        args.putSerializable(ARG_IDEANAME, name);
        args.putSerializable(ARG_IDEADESCRIPTION, description);
        args.putSerializable(ARG_IDEAPRIORITY, priority);
        listFragment.setArguments(args);
        Log.i(TAG, "List Frag= " +name);*/
        return listFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    public ListFragment() {
        mIdeaName = "";
        mDesrcription = "";
        mPriority = "";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        // mIdeaName = (String) getArguments().getSerializable(ARG_IDEANAME);
       // mIdeaArrayList = new ArrayList<Idea>();
       // mIdeaArrayList.add();
       // ideaListView = (ListView)getActivity().findViewById(R.id.listViewForm);
       // ideaListAdapter = new ArrayAdapter<Idea>(getActivity(), android.R.layout.simple_list_item_1, mIdeaArrayList);
        /*mIdeaName = (String) getArguments().getSerializable(ARG_IDEANAME);
        mDesrcription = (String) getArguments().getSerializable(ARG_IDEADESCRIPTION);
        mPriority = (String) getArguments().getSerializable(ARG_IDEAPRIORITY);

        Log.i(TAG, "IDEA NAME= " +mIdeaName);
*/

       /* mIdeaArrayList = new ArrayList<Idea>();

        ideaListAdapter = new ArrayAdapter<Idea>(getActivity(), android.R.layout.simple_list_item_1, mIdeaArrayList);
        ideaListView = (ListView)getActivity().findViewById(R.id.listViewForm);
        ideaListView.setAdapter(ideaListAdapter);*/


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
