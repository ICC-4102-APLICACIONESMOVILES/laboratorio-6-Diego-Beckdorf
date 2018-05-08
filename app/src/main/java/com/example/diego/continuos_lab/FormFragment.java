package com.example.diego.continuos_lab;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.diego.continuos_lab.database_orm.Form;
import com.example.diego.continuos_lab.layout_helpers.FormListAdapter;

import java.util.List;


public class FormFragment extends Fragment {
    private Context context;
    private static final String ARG_FORMS = "forms";

    private FormDeleter deleterListener;
    public interface FormDeleter {
        void callFormDelete(Form form);
    }

    public FormDeleter getDeleterListener() {
        return deleterListener;
    }

    public FormFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_form, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        if (context instanceof FormDeleter) {
            deleterListener = (FormDeleter) context;
        }
    }

    public void populateTable(List<Form> forms) {
        ListAdapter listAdapter = new FormListAdapter(this, context, forms);
        ListView formsListView = getView().findViewById(R.id.formsListView);
        formsListView.setAdapter(listAdapter);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
