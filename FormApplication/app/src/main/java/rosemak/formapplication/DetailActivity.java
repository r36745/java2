package rosemak.formapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * Steven Roseman
 */
public class DetailActivity extends Activity implements DetailFragment.DetailClickListener {
    public static final String TAG = "DetailActivity";
    public static final String EXTRA_IDEA_NAME = "DetailActivity.Name";
    public static final String EXTRA_IDEA_DESCRIPTION = "DetailActivity.Description";
    public static final String EXTRA_IDEA_PRIORITY = "DetailActivity.Priority";

    private Idea mIdea;
    private int mDelete;

    public static final String IDEAEXTRA = "rosemak.formapplication.Idea";
    public static final String DELETEEXTRA = "rosemak.formapplication.Delete";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent callingIntent = getIntent();

        String ideaName = callingIntent.getStringExtra("Name");
        Log.i(TAG, "Detail Name= " +ideaName);
        String ideaDescription = callingIntent.getStringExtra("Description");
        Log.i(TAG, "Detail Description= " +ideaDescription);
        String ideaPriority = callingIntent.getStringExtra("Priority");
        Log.i(TAG, "Detail Priority= " +ideaPriority);


        DetailFragment detailFragment = DetailFragment.newInstance(ideaName, ideaDescription, ideaPriority);
        getFragmentManager().beginTransaction()
                .replace(R.id.detailContainer, detailFragment, DetailFragment.TAG)
                .commit();

        Intent detailIntent = getIntent();
        if (detailIntent != null) {
            mIdea = (Idea) detailIntent.getSerializableExtra(IDEAEXTRA);
            mDelete = detailIntent.getIntExtra(DELETEEXTRA,0);

        }


    }

    public void shareButton(View v) {

        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "My Idea is" + " " + name );
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "Share data with..."));
    }

    @Override
    public int getDelete() {
        return mDelete;
    }

    @Override
    public void deleteContact() {

        Intent returnIntent = new Intent();
        returnIntent.putExtra(MainActivity.DELETEIDEAEXTRA, mDelete);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
