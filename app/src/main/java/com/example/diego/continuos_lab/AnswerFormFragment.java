package com.example.diego.continuos_lab;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.diego.continuos_lab.database_orm.AnswerSet;
import com.example.diego.continuos_lab.database_orm.Question;
import com.example.diego.continuos_lab.layout_helpers.AnswerQuestionListAdapter;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnswerFormFragment extends Fragment {

    FormResponseSaver formResponseSaver;
    FormQuestionGetter formQuestionGetter;
    public interface FormResponseSaver {
        void saveResponse(List<AnswerSet> answerSets);
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.answer_form_fragment, container, false);
        Button responseSubmitBtn = view.findViewById(R.id.form_response_submit);
        responseSubmitBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                List<AnswerSet> answerSets = null;
                formResponseSaver.saveResponse(answerSets);
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
