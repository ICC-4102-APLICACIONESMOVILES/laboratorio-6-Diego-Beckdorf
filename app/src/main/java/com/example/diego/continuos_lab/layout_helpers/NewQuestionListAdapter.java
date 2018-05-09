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
 * Created by diego on 09-05-2018.
 */

public class NewQuestionListAdapter extends ArrayAdapter<String> {

    public NewQuestionListAdapter(@NonNull Context context, @NonNull List<String> objects) {
        super(context, R.layout.new_question_row, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        @SuppressLint("ViewHolder") View questionRowView = inflater.inflate(R.layout.new_question_row,
                parent, false);

        final String question_statement = getItem(position);

        assert question_statement != null;

        TextView statement_number = questionRowView.findViewById(R.id.new_question_statement_number);
        statement_number.setText(String.valueOf(position));

        return questionRowView ;
    }
}
