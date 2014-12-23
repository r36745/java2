package rosemak.formapplication;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by stevierose on 12/21/14.
 */
public class Form_Fragment extends Fragment {
    public static final String TAG = "FormFragment";

    public Idea idea;

    private EditText nameEditText;
    private EditText descriptionEditText;
    private EditText priorityEditText;
    private Button saveButton;
    private OnClickListener mListener;

    public interface OnClickListener {
        public void addIdea(Idea idea);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnClickListener) {
            mListener = (OnClickListener) activity;
        } else {
            throw new IllegalArgumentException("Listener Not Referenced");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_form, container, false);
        return rootView;
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        nameEditText = (EditText) getView().findViewById(R.id.nameEditText);
        descriptionEditText = (EditText) getView().findViewById(R.id.descriptionEditText);
        priorityEditText = (EditText) getView().findViewById(R.id.priorityEditText);
        saveButton = (Button) getView().findViewById(R.id.saveButton);
        idea = new Idea();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ideaNameString = nameEditText.getText().toString();
                Log.i(TAG, "Name= " + ideaNameString);
                String ideDescription = descriptionEditText.getText().toString();
                Log.i(TAG, "Description= " + ideDescription);
                String ideaPriority = priorityEditText.getText().toString();
                Log.i(TAG, "Priority= " + ideaPriority);

                idea.setmIdeaName(ideaNameString);
                idea.setmIdeaDescription(ideDescription);
                idea.setmIdeaPriority(ideaPriority);

                nameEditText.setText("");
                descriptionEditText.setText("");
                priorityEditText.setText("");
               mListener.addIdea(idea);



            }
        });

    }
}
