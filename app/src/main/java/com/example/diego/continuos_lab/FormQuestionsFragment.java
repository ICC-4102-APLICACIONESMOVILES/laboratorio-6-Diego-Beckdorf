package com.example.diego.continuos_lab;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.diego.continuos_lab.database_interface.DaoAccess;
import com.example.diego.continuos_lab.database_orm.Form;
import com.example.diego.continuos_lab.database_orm.Question;
import com.example.diego.continuos_lab.layout_helpers.QuestionListAdapter;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FormQuestionsFragment extends Fragment {

    private Context context;
    private FormQuestionProvider listener;
    public interface FormQuestionProvider {
        void getFormQuestions(Form form);
    }


    public FormQuestionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form_questions, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        if (context instanceof FormQuestionProvider) {
            listener = (FormQuestionProvider) context;
        }
    }


    public void fillQuestionList(List<Question> questions) {
        QuestionListAdapter listAdapter = new QuestionListAdapter(this, context, questions);
        ListView questionsListView = getView().findViewById(R.id.formQuestionsList);
        questionsListView.setAdapter(listAdapter);
    }

}
