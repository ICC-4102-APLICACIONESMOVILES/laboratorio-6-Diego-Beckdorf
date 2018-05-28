package com.example.diego.continuos_lab;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.diego.continuos_lab.database_orm.Answer;
import com.example.diego.continuos_lab.database_orm.AnswerSet;
import com.example.diego.continuos_lab.database_orm.Question;
import com.example.diego.continuos_lab.layout_helpers.AnswerQuestionListAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnswerFormFragment extends Fragment {

    long formId;
    FormResponseSaver formResponseSaver;
    FormQuestionGetter formQuestionGetter;
    public interface FormResponseSaver {
        void saveResponse(List<Answer> answerList);
    }
    public interface FormQuestionGetter {
        void getFormQuestions(AnswerQuestionListAdapter adapter);
    }


    public AnswerFormFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof FormResponseSaver) {
            formResponseSaver = (FormResponseSaver) context;
        }
        if (context instanceof FormQuestionGetter) {
            formQuestionGetter = (FormQuestionGetter) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        assert bundle != null;
        formId = bundle.getLong("formId");
        final View view = inflater.inflate(R.layout.fragment_answer_form, container, false);
        Button responseSubmitBtn = view.findViewById(R.id.form_response_submit);
        responseSubmitBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ListView answersListView = view.findViewById(R.id.form_questions_listview);
                int answersListViewChildsCount = answersListView.getChildCount();
                List<Answer> answerList = new ArrayList<>(Arrays.asList(new Answer[]{}));
                for (int i = 0; i < answersListViewChildsCount; i++) {
                    View answerListViewChild = answersListView.getChildAt(i);
                    EditText answer = answerListViewChild.findViewById(R.id.question_statement_answer);
                    String content = answer.getText().toString();
                    answerList.add(new Answer(formId, content, 0, 0));
                }
                formResponseSaver.saveResponse(answerList);
            }
        });
        return view;
    }


    public void renderQuestions(List<Question> questions) {
        ListAdapter listAdapter = new AnswerQuestionListAdapter(getContext(), questions);
        ListView questionListView = getView().findViewById(R.id.form_questions_listview);
        questionListView.setAdapter(listAdapter);
    }
}
