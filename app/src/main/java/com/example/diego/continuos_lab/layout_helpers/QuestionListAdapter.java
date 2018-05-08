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

import com.example.diego.continuos_lab.FormQuestionsFragment;
import com.example.diego.continuos_lab.R;
import com.example.diego.continuos_lab.database_orm.Question;

import java.util.List;

/**
 * Created by diego on 04-05-2018.
 */

public class QuestionListAdapter extends ArrayAdapter<Question> {

    public QuestionListAdapter(@NonNull FormQuestionsFragment frag, @NonNull Context context, @NonNull List<Question> objects) {
        super(context, R.layout.new_form_question, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        @SuppressLint("ViewHolder") View questionRowView = inflater.inflate(R.layout.new_form_question,
                parent, false);

        final Question question = getItem(position);

        assert question != null;

        return questionRowView ;
    }
}
