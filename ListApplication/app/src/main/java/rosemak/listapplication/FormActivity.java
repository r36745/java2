package rosemak.listapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by stevierose on 12/9/14.
 */
public class FormActivity extends Activity implements FormFragment.OnClickListener {

    public static final String TAG = "FormActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        FormFragment formFragment = new FormFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.formContainer, formFragment, FormFragment.TAG)
                .commit();


    }

    @Override
    public void newIdeas(Idea idea) {


        MainFragment listFragment = MainFragment.newInstance(idea.getIdea_name(), idea.getIdea_description(), idea.getIdea_priority());
        getFragmentManager().beginTransaction()
                .replace(R.id.listFrame, listFragment, MainFragment.TAG)
                .commit();
        Log.i(TAG, "City Name= " + idea.getIdea_name());
    }

}
