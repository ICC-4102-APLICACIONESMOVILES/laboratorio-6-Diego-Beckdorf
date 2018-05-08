package com.example.diego.continuos_lab.layout_helpers;


import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.diego.continuos_lab.FormFragment;
import com.example.diego.continuos_lab.FormQuestionsFragment;
import com.example.diego.continuos_lab.FormResponseFragment;
import com.example.diego.continuos_lab.MainActivity;
import com.example.diego.continuos_lab.R;
import com.example.diego.continuos_lab.database_orm.Form;
import com.google.android.gms.location.FusedLocationProviderClient;

import java.util.List;

public class FormListAdapter extends ArrayAdapter<Form>{

    private FormFragment frag;

    public FormListAdapter(@NonNull FormFragment frag, @NonNull Context context, @NonNull List<Form> objects) {
        super(context, R.layout.form_row, objects);
        this.frag = frag;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        @SuppressLint("ViewHolder") View formRowView = inflater.inflate(R.layout.form_row,
                parent, false);

        final Form form = getItem(position);
        TextView name = formRowView.findViewById(R.id.name);
        TextView date = formRowView.findViewById(R.id.date);
        TextView category = formRowView.findViewById(R.id.category);
        TextView description = formRowView.findViewById(R.id.description);
        Button deleteButton = formRowView.findViewById(R.id.formDelete);
        Button responseButton = formRowView.findViewById(R.id.formReponse);

        assert form != null;
        name.setText(form.getName());
        date.setText(form.getDate());
        category.setText(form.getCategory());
        description.setText(form.getDescription());

        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                frag.getDeleterListener().callFormDelete(form);
            }
        });
        responseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity act = (MainActivity) getContext();
                act.changeFragment(R.id.form_response_submit);
            }
        });

        return formRowView;
    }
}
