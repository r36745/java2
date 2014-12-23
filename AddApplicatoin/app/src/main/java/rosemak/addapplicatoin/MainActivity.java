package rosemak.addapplicatoin;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

//Steven Roseman
public class MainActivity extends ActionBarActivity implements FormFragment.OnButtonClick, DeleteFragment.DeleteDialogListener{
        public static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListFragment listFragment = new ListFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.container, listFragment, ListFragment.TAG)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.action_addButton:
                FormFragment formFragment = new FormFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, formFragment, FormFragment.TAG)
                        .commit();
                return true;
            default:
        return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void formIdeaPassed(Idea idea) {
        Log.i(TAG, "ideaName= " + idea.getmIdeaName());
        Log.i(TAG, "ideaDescription= " + idea.getmIdeaDescription());
        Log.i(TAG, "ideaPriority= " + idea.getmIdeaPriority());

        ListFragment mainFragment = ListFragment.newInstance(idea);
        getFragmentManager().beginTransaction()
                .replace(R.id.container, mainFragment, ListFragment.TAG)
                .commit();
    }

    @Override
    public void deleteIdeaName() {

        ListFragment listFragment = (ListFragment) getFragmentManager().findFragmentById(R.id.container);
        listFragment.deleteIdea();
    }
}
