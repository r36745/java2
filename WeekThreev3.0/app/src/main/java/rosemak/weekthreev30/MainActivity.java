package rosemak.weekthreev30;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {
    public static final String TAG = "MAINACTIVITY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id == R.id.addItems) {

            Intent formIntent = new Intent(MainActivity.this, FormActivity.class);
            startActivity(formIntent);

        } else if(id == R.id.listData) {

            Intent listIntent = new Intent(MainActivity.this, ListViewActivity.class);
            startActivity(listIntent);

        }

        return super.onOptionsItemSelected(item);
    }


    public void currentPosition(String position) {
        Log.i(TAG, "Current Position= " + position);
    }
}
