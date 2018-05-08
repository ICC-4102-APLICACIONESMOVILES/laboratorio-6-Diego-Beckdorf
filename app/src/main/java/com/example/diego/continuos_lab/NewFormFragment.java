package com.example.diego.continuos_lab;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diego.continuos_lab.database_interface.DaoAccess;
import com.example.diego.continuos_lab.database_orm.Form;
import com.google.android.gms.location.FusedLocationProviderClient;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewFormFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewFormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewFormFragment extends Fragment {

    private MainActivity activity;

    public interface NewFormListener{
        void newForm(String name, String date, String category, String description);
    }
    private NewFormListener listener;

    public NewFormFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static NewFormFragment newInstance(String param1, String param2) {
        return new NewFormFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof NewFormListener)
        {
            this.listener = (NewFormListener) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_form, container, false);

        this.activity = (MainActivity) getActivity();

        final Button submitButton = view.findViewById(R.id.formSubmit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView nameView = activity.findViewById(R.id.name);
                System.out.println(nameView.getText());
                final String name = nameView.getText().toString();
                TextView dateView = activity.findViewById(R.id.date);
                final String date = dateView.getText().toString();
                Spinner categoryView = activity.findViewById(R.id.category);
                final String category = categoryView.getSelectedItem().toString();
                TextView descriptionView = activity.findViewById(R.id.description);
                final String description = descriptionView.getText().toString();
                listener.newForm(name, date, category, description);
            }
        });

        final ListView questionsContainerList = view.findViewById(R.id.questions_container_list);
        final Button addQuestionButton = view.findViewById(R.id.new_form_add_question_btn);
        addQuestionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                View newQuestion = getLayoutInflater().inflate(R.layout.new_form_question, null);
                questionsContainerList.addFooterView(newQuestion);
            }
        });
        return view;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
