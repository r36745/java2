package rosemak.listapplication;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by stevierose on 12/9/14.
 */
public class FormFragment extends Fragment {

    public static final String TAG = "FormFragment";
    private OnClickListener mListener;
    private Idea newIdea;


    public interface OnClickListener {
        public void newIdeas(Idea idea);

    }




    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof OnClickListener) {
            mListener = (OnClickListener) activity;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.form_fragment,container, false);

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button saveButton = (Button) getActivity().findViewById(R.id.saveButton);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText name = (EditText) getActivity().findViewById(R.id.ideaEditText);
                EditText description = (EditText) getActivity().findViewById(R.id.descriptionEditText);
                EditText priority = (EditText) getActivity().findViewById(R.id.priorityEditText);


                String ideaName = name.getText().toString();
                Log.i(TAG, "Name= " + ideaName);
                String ideaDescription = description.getText().toString();
                String ideaPriority = priority.getText().toString();

                newIdea = new Idea();
                newIdea.setIdea_name(ideaName);
                newIdea.setIdea_description(ideaDescription);
                newIdea.setIdea_priority(ideaPriority);

                name.setText("");
                description.setText("");
                priority.setText("");

                //mListener.ideaName(ideaName);
                mListener.newIdeas(newIdea);

            }
        });

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

