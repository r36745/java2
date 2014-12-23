package rosemak.addapplicatoin;

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
 * Steven Roseman
 */
public class FormFragment extends Fragment {
    public static final String TAG = "FormFragment";
    private OnButtonClick mListener;
    private Idea mIdea;
    private EditText nameEditText;
    private EditText descriptionEditText;
    private EditText priorityEditText;
    private Button saveButton;

    public interface OnButtonClick {
        public void formIdeaPassed(Idea idea);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_form, container, false);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnButtonClick) {
            mListener = (OnButtonClick) activity;
        } else {
            throw new IllegalArgumentException("OnButtonClick Not Functioning");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        nameEditText = (EditText) getView().findViewById(R.id.nameEditText);
        descriptionEditText = (EditText) getView().findViewById(R.id.descriptionEditText);
        priorityEditText = (EditText) getView().findViewById(R.id.priorityEditText);
        saveButton = (Button) getView().findViewById(R.id.saveButton);
        mIdea = new Idea();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ideaNameString = nameEditText.getText().toString();
                Log.i(TAG, "Name= " + ideaNameString);
                String ideDescription = descriptionEditText.getText().toString();
                Log.i(TAG, "Description= " + ideDescription);
                String ideaPriority = priorityEditText.getText().toString();
                Log.i(TAG, "Priority= " + ideaPriority);

                mIdea.setmIdeaName(ideaNameString);
                mIdea.setmIdeaDescription(ideDescription);
                mIdea.setmIdeaPriority(ideaPriority);

                nameEditText.setText("");
                descriptionEditText.setText("");
                priorityEditText.setText("");
                mListener.formIdeaPassed(mIdea);
            }
        });

    }
}
