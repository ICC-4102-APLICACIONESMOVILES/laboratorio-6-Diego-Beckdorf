package com.example.diego.continuos_lab.layout_helpers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.diego.continuos_lab.R;
import com.example.diego.continuos_lab.database_orm.Question;

import java.util.List;

/**
 * Created by diego on 04-05-2018.
 */

public class AnswerQuestionListAdapter extends ArrayAdapter<Question> {

    public AnswerQuestionListAdapter(@NonNull Context context, @NonNull List<Question> objects) {
        super(context, R.layout.answer_question_row, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        @SuppressLint("ViewHolder") View questionRowView = inflater.inflate(R.layout.answer_question_row,
                parent, false);

        final Question question = getItem(position);

        assert question != null;

        TextView statement = questionRowView.findViewById(R.id.question_statement);
        statement.setText(question.getStatement());

        return questionRowView ;
    }
}
