package rosemak.casestudy1;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
//Steven Roseman

public class MainActivity extends ActionBarActivity implements ActionBar.OnNavigationListener {

    /**
     * The serialization (saved instance state) Bundle key representing the
     * current dropdown position.
     */
    private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[]teams = new String[]
                {
                        getString(R.string.football_team1),
                        getString(R.string.football_team2),
                        getString(R.string.football_team3),
                        getString(R.string.football_team4),
                        getString(R.string.football_team5),
                        getString(R.string.football_team6),
        };

        // Set up the action bar to show a dropdown list.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        // Set up the dropdown list navigation in the action bar.
        actionBar.setListNavigationCallbacks(
                // Specify a SpinnerAdapter to populate the dropdown list.


                new ArrayAdapter<String>(
                        actionBar.getThemedContext(),
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1,
                        teams),
                this);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore the previously serialized current dropdown position.
        if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
            getSupportActionBar().setSelectedNavigationItem(
                    savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // Serialize the current dropdown position.
        outState.putInt(STATE_SELECTED_NAVIGATION_ITEM,
                getSupportActionBar().getSelectedNavigationIndex());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(int position, long id) {
        // When the given dropdown item is selected, show its contents in the
        // container view.

       if (position == 0 ) {

           FootballTeamOneFragment footballTeamOneFragment = FootballTeamOneFragment.newInstance("","");
           getFragmentManager().beginTransaction()
                   .replace(R.id.container, footballTeamOneFragment, FootballTeamOneFragment.TAG)
                   .commit();
       } else if (position == 1) {

           FootballTeamTwoFragment footballTeamTwoFragment = FootballTeamTwoFragment.newInstance("","");
           getFragmentManager().beginTransaction()
                   .replace(R.id.container, footballTeamTwoFragment, FootballTeamOneFragment.TAG)
                   .commit();

       } else if (position == 2) {

           FootballTeamThreeFragment footballTeamThreeFragment = FootballTeamThreeFragment.newInstance("","");
           getFragmentManager().beginTransaction()
                   .replace(R.id.container, footballTeamThreeFragment, FootballTeamOneFragment.TAG)
                   .commit();
       } else if (position == 3) {

           FootballTeamFourFragment footballTeamFourFragment = FootballTeamFourFragment.newInstance("","");
           getFragmentManager().beginTransaction()
                   .replace(R.id.container, footballTeamFourFragment, FootballTeamOneFragment.TAG)
                   .commit();
       } else if (position == 4) {

           FootballTeamFiveFragment footballTeamFiveFragment = FootballTeamFiveFragment.newInstance("","");
           getFragmentManager().beginTransaction()
                   .replace(R.id.container, footballTeamFiveFragment, FootballTeamOneFragment.TAG)
                   .commit();
       } else if (position == 5) {

           FootballTeamSixFragment footballTeamSixFragment = FootballTeamSixFragment.newInstance("","");
           getFragmentManager().beginTransaction()
                   .replace(R.id.container, footballTeamSixFragment, FootballTeamOneFragment.TAG)
                   .commit();
       }
        return true;
    }



}
