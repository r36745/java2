package rosemak.addapplicatoin;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Steven Roseman
 */
public class ListFragment extends Fragment {
    public static final String TAG = "MainFragment";
    private ArrayList<Idea> ideaArrayList;
    private ListView dataList;
    private IdeaAdapter mIdeaAdapter;
    //private ArrayAdapter<Idea> mIdeaAdapter;
    private ActionMode mActionMode;
    private static final String ARG_IDEAOBJ = "ideaName";
    private int mIdeaSelected = -1;

   public static ListFragment newInstance(Idea idea) {
       ListFragment listFragment = new ListFragment();
       Bundle args = new Bundle();
       String ideaName = idea.getmIdeaName();
       Log.i(TAG, "Name= " + ideaName);
       String description = idea.getmIdeaDescription();
       Log.i(TAG, "Description= " + description);
       String priority = idea.getmIdeaPriority();
       Log.i(TAG, "Priority= " + priority);

       args.putSerializable(ARG_IDEAOBJ, idea);
       listFragment.setArguments(args);

       return listFragment;
   }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ideaArrayList = new ArrayList<Idea>();
        //mIdeaAdapter = new ArrayAdapter<Idea>(getActivity(),android.R.layout.simple_list_item_1,ideaArrayList);

        mIdeaAdapter = new IdeaAdapter(getActivity(), ideaArrayList);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        dataList = (ListView) rootView.findViewById(R.id.listView);
        dataList.setAdapter(new IdeaAdapter(getActivity(),ideaArrayList));
        dataList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Log.i(TAG, "Here");
                if (mActionMode != null) {
                    return false;
                }
                mIdeaSelected = position;
                mActionMode = getActivity().startActionMode(mActionModeCallBack);

                return true;
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(getArguments() != null) {
            Idea ideaObj = (Idea) getArguments().getSerializable(ARG_IDEAOBJ);
            String ideaName = ideaObj.getmIdeaName();
            String ideaDescription = ideaObj.getmIdeaDescription();
            String ideaPriority = ideaObj.getmIdeaPriority();

            Log.i(TAG, "Final Name= " + ideaName);
            ideaArrayList.add(new Idea(ideaName, ideaDescription, ideaPriority));
            Log.i(TAG, "ArrayList= " +ideaArrayList);

        }


    }

    public void deleteIdea(){
        ideaArrayList.remove(mIdeaSelected);
        mIdeaAdapter.notifyDataSetChanged();

    }

    private ActionMode.Callback mActionModeCallBack = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.ideamenu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.ideaDelete:
                    Log.i(TAG, String.valueOf(mIdeaAdapter.getItem(mIdeaSelected)));
                    DeleteFragment delete = new DeleteFragment();
                    Bundle args = new Bundle();
                    args.putString("name", String.valueOf(mIdeaAdapter.getItem(mIdeaSelected)));
                    delete.setArguments(args);
                    delete.show(getActivity().getFragmentManager(),"DeleteFragment" );
                    mode.finish();

                    return true;
                default:
                    return false;
            }

        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

            mActionMode = null;
        }
    };
}
