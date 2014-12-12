package rosemak.listapplication;

import android.app.Activity;
import android.os.Bundle;


public class MainActivity extends Activity {

    public static final String TAG = "MainActivity";
    public static final String EXTRA_IDEA_NAME= "com.rosemak.listapplication";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ListFragment listFragment = new ListFragment();
        getFragmentManager().beginTransaction().replace(R.id.listFrame, listFragment, ListFragment.TAG)
                .commit();

    }



}
