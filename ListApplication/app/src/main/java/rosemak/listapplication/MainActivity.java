package rosemak.listapplication;

import android.app.Activity;
import android.os.Bundle;


public class MainActivity extends Activity implements MainFragment.OnListClickListener {

    public static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment listFragment = new MainFragment();
        getFragmentManager().beginTransaction().replace(R.id.listFrame, listFragment, MainFragment.TAG)
                .commit();
    }


    @Override
    public void listIdea(Idea idea) {


    }
}
