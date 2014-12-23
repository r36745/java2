package rosemak.formapplication;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Steven Roseman
 */
public class Main_Fragment extends Fragment  {
    public static final String TAG = "MainFragment";
    private Button addButton;
    private ListView dataList;
    private ArrayList<Idea> ideaArrayList;
    private Idea mIdea;
    private static final String IDEAFILE = "ideaInfo.txt";

    private static final String ARG_IDEAOBJ = "ideaName";
    private static final String ARG_IDEADESCRIPTION = "ideaDescription";
    private static final String ARG_IDEAPRIORITY = "ideaPriority";
    private Idea finalIdea;
    private OnButtonClickListener mListener;
    public  int global = 0;

    public interface OnButtonClickListener {
        public void ideaPostion(Idea idea);
        public void deleteContact(int position);
        public ArrayList<Idea> getIdeas(ArrayList list);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof OnButtonClickListener) {
            mListener = (OnButtonClickListener) activity;
        } else {
            throw new IllegalArgumentException("OnButtonClickListener not working");
        }
    }

    public static Main_Fragment newInstance(Idea mIdea) {
        Main_Fragment mainFragment = new Main_Fragment();
        String ideaName = mIdea.getmIdeaName();
        Log.i(TAG, "Name= " + ideaName);
        String description = mIdea.getmIdeaDescription();
        Log.i(TAG, "Description= " + description);
        String priority = mIdea.getmIdeaPriority();
        Log.i(TAG, "Priority= " + priority);

        Bundle args = new Bundle();

        args.putSerializable(ARG_IDEAOBJ, mIdea);
        //args.putSerializable(ARG_IDEADESCRIPTION, description);
        //args.putSerializable(ARG_IDEAPRIORITY, priority);
        mainFragment.setArguments(args);

        return mainFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addButton = (Button)getView().findViewById(R.id.addButton);
        dataList = (ListView)getView().findViewById(R.id.listViewItems);
        ideaArrayList = new ArrayList<Idea>();
        UIHelper helper = new UIHelper();

        if (getArguments()!= null) {
            Idea ideaObj = (Idea) getArguments().getSerializable(ARG_IDEAOBJ);
            String ideaName = ideaObj.getmIdeaName();
            String ideaDescription = ideaObj.getmIdeaDescription();
            String ideaPriority = ideaObj.getmIdeaPriority();

            Log.i(TAG, "Final Name= " + ideaName);
            ideaArrayList.add(new Idea(ideaName, ideaDescription, ideaPriority));
            Log.i(TAG, "ArrayList= " +ideaArrayList);

            helper.writeToFile(getActivity(), IDEAFILE, ideaName, ideaDescription, ideaPriority);




           dataList.setAdapter(new IdeaAdapter(getActivity(),ideaArrayList));

        }

        //addButton clicked goes to Form Screen
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Form_Fragment ff = new Form_Fragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, ff, Form_Fragment.TAG)
                        .commit();

            }
        });

        dataList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Bundle args = getArguments();
                mListener.deleteContact(position);

                mIdea = ideaArrayList.get(position);
                mListener.ideaPostion(mIdea);
            }
        });


    }

    public void updateListData() {
        ListView dataList = (ListView) getView().findViewById(R.id.listViewItems);
        BaseAdapter ideaAdapter = (BaseAdapter) dataList.getAdapter();
        ideaAdapter.notifyDataSetChanged();
    }




}
