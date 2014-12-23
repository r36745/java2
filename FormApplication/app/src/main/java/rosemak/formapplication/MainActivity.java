package rosemak.formapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

//Steven Roseman

public class MainActivity extends Activity implements Form_Fragment.OnClickListener, Main_Fragment.OnButtonClickListener{
    public static final String TAG = "MainActivity";
    public static final int REQUEST_DETAIL = 1;
    public static final int DELETEREQUEST = 1;
    public static final String DELETEIDEAEXTRA = "rosemak.formapplication.DeleteXtra";
    private ArrayList<Idea> mIdeaArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Main_Fragment  mf = new Main_Fragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.container, mf, Main_Fragment.TAG)
                .commit();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == DELETEREQUEST) {

           // mIdeaArrayList.remove(data.getIntExtra(DELETEIDEAEXTRA, 0));
            Main_Fragment mainFragment = (Main_Fragment) getFragmentManager().findFragmentById(R.id.container);
            mainFragment.updateListData();

        }
    }

    @Override
    public void addIdea(Idea idea) {
        Log.i(TAG, "ideaName= " + idea.getmIdeaName());
        Log.i(TAG, "ideaDescription= " + idea.getmIdeaDescription());
        Log.i(TAG, "ideaPriority= " + idea.getmIdeaPriority());

        Main_Fragment mainFragment = Main_Fragment.newInstance(idea);
        getFragmentManager().beginTransaction()
                .replace(R.id.container, mainFragment, Main_Fragment.TAG)
                .commit();

    }

    @Override
    public void ideaPostion(Idea idea) {
        Log.i(TAG, "Idea Name= " + idea.getmIdeaName());
        Log.i(TAG, "Description= " + idea.getmIdeaDescription());
        Log.i(TAG, "Idea Last Priority= " + idea.getmIdeaName());

        Bundle extras = new Bundle();
        extras.putSerializable("Obj", idea);
        extras.putSerializable("Name", idea.getmIdeaName());
        extras.putSerializable("Description", idea.getmIdeaDescription());
        extras.putSerializable("Priority", idea.getmIdeaPriority());
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtras(extras);
        startActivityForResult(intent, REQUEST_DETAIL);



    }

    @Override
    public void deleteContact(int position) {
        Log.i(TAG, "idea position= " + position);

        Intent deleteIntent = new Intent(this, DetailActivity.class);
        deleteIntent.putExtra(DetailActivity.IDEAEXTRA,mIdeaArrayList);
        deleteIntent.putExtra(DetailActivity.DELETEEXTRA, position);
        startActivityForResult(deleteIntent,DELETEREQUEST);

    }

    @Override
    public ArrayList<Idea> getIdeas(ArrayList list) {
        return mIdeaArrayList;
    }



}
