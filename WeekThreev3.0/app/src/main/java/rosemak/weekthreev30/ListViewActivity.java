package rosemak.weekthreev30;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by stevierose on 11/16/14.
 */
public class ListViewActivity extends ListActivity {
    private OnListClickListener mListener;
    public ListView showReminderListView;
    public static final String TAG = "LISTVIEWFRAGMENT";
    public ArrayList<String> mShowReminder = new ArrayList<String>();
    public ArrayAdapter<String> showReminderAdapter;
    public String nameString;
    public StringBuffer buffer;

    public interface OnListClickListener {
        public void currentPosition(String position);
        public ArrayList<String>getShows();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        try {
            FileInputStream fis = this.openFileInput("showName.txt");
            BufferedInputStream bis = new BufferedInputStream(fis);
            buffer = new StringBuffer();

            while (bis.available()!=0) {

                char c = (char) bis.read();
                buffer.append(c);
            }
            bis.close();
            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        nameString = buffer.toString();
        if(nameString.contains(mShowReminder.toString())) {
            Log.i(TAG, "Already Saved");
        } else {
            mShowReminder.add(nameString);
        }

        showReminderAdapter  = new ArrayAdapter<String>(this, R.layout.row_layout,R.id.listText, mShowReminder);
        Log.i(TAG, "array= " + mShowReminder);
        setListAdapter(showReminderAdapter);
        Toast.makeText(this, buffer.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String showName = (String)l.getItemAtPosition(position);
        mListener.currentPosition(showName);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_form, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id == R.id.addItems) {

            Intent formIntent = new Intent(ListViewActivity.this, FormActivity.class);
            startActivity(formIntent);

        } else if(id == R.id.listData) {

            Intent listIntent = new Intent(ListViewActivity.this, ListViewActivity.class);
            startActivity(listIntent);

        }

        return super.onOptionsItemSelected(item);
    }





}
