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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diego.continuos_lab.database_interface.DaoAccess;
import com.example.diego.continuos_lab.database_orm.Form;
import com.example.diego.continuos_lab.database_orm.Question;
import com.example.diego.continuos_lab.layout_helpers.NewQuestionListAdapter;
import com.google.android.gms.location.FusedLocationProviderClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewFormFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewFormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewFormFragment extends Fragment {

    private ArrayList<String> questionStatementList;

    public interface NewFormListener{
        void newForm(String name, String date, String category, String description,
                     List<Question> questionList);
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
        final View view = inflater.inflate(R.layout.fragment_new_form, container, false);

        // Set question ListView Adapter
        String[] array = {""};
        questionStatementList= new ArrayList<String>(Arrays.asList(array));
        final ListView questionsContainerList = view.findViewById(R.id.questions_container_list);
        final NewQuestionListAdapter questionListAdapter = new NewQuestionListAdapter
                (getContext(), questionStatementList);
        questionsContainerList.setAdapter(questionListAdapter);

        // Set onClick for Add question button
        final Button addQuestionButton = view.findViewById(R.id.new_form_add_question_btn);
        addQuestionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO: check function to add new questions on mainthread
                questionListAdapter.add("");
                questionListAdapter.notifyDataSetChanged();
            }
        });

        // Set onClick for Submit button
        final Button submitButton = view.findViewById(R.id.formSubmit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Get basic form data
                TextView nameView = view.findViewById(R.id.name);
                final String name = nameView.getText().toString();
                TextView dateView = view.findViewById(R.id.date);
                final String date = dateView.getText().toString();
                Spinner categoryView = view.findViewById(R.id.category);
                final String category = categoryView.getSelectedItem().toString();
                TextView descriptionView = view.findViewById(R.id.description);
                final String description = descriptionView.getText().toString();
                // Creating questions from statements
                List<Question> questionList = new ArrayList<>();
                ListView questionListView = view.findViewById(R.id.questions_container_list);
                for (int i = 0; i < questionListView.getChildCount(); i++) {
                    View questionListViewChild = questionListView.getChildAt(i);
                    EditText questionStatement = questionListViewChild.findViewById(R.id.new_question_statement);
                    String statement = questionStatement.getText().toString();
                    questionList.add(new Question(statement));
                }
                listener.newForm(name, date, category, description, questionList);
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
