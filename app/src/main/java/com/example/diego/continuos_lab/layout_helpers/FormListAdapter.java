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

import com.example.diego.continuos_lab.FormFragment;
import com.example.diego.continuos_lab.R;
import com.example.diego.continuos_lab.database_orm.Form;

import java.util.List;

public class FormListAdapter extends ArrayAdapter<Form>{

    public FormListAdapter(@NonNull Context context, @NonNull List<Form> objects) {
        super(context, R.layout.form_row, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        @SuppressLint("ViewHolder") View formRowView = inflater.inflate(R.layout.form_row,
                parent, false);

        Form form = getItem(position);
        TextView name = formRowView.findViewById(R.id.name);
        TextView date = formRowView.findViewById(R.id.date);
        TextView category = formRowView.findViewById(R.id.category);
        TextView description = formRowView.findViewById(R.id.description);

        assert form != null;
        name.setText(form.getName());
        date.setText(form.getDate());
        category.setText(form.getCategory());
        description.setText(form.getDescription());

        return formRowView;
    }
}
