package rosemak.weekthreev30;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by stevierose on 11/16/14.
 */
public class FormActivity extends Activity{

    public static final String TAG = "FORMACTIVITY";
    public EditText showNameEditText, showDateEditText, showTimeEditText;
    public Button saveButton;
    public String showNameText, showDateText, showTimeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        showNameEditText = (EditText) findViewById(R.id.showNameEditText);
        showDateEditText = (EditText) findViewById(R.id.showDateEditText);
        showTimeEditText = (EditText) findViewById(R.id.showTimeEditText);
        saveButton = (Button) findViewById(R.id.saveItemsButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNameText = showNameEditText.getText().toString();
                showDateText = showDateEditText.getText().toString();
                showTimeText = showTimeEditText.getText().toString();
                resetEditText();

                try {
                    FileOutputStream fos = openFileOutput("showName.txt", Context.MODE_PRIVATE);
                    fos.write(showNameText.getBytes());
                    fos.close();
                    Toast.makeText(FormActivity.this, "Show Saved", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent listIntent = new Intent(FormActivity.this, ListViewActivity.class);
                startActivity(listIntent);
            }
        });
    }

    private void resetEditText() {

        showNameEditText.setText("");
        showDateEditText.setText("");
        showTimeEditText.setText("");
    }
}
