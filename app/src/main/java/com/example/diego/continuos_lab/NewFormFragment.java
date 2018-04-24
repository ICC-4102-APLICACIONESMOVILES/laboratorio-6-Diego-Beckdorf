package com.example.diego.continuos_lab;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.diego.continuos_lab.database_interface.DaoAccess;
import com.example.diego.continuos_lab.database_orm.Form;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewFormFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewFormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewFormFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewFormFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewFormFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewFormFragment newInstance(String param1, String param2) {
        NewFormFragment fragment = new NewFormFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        final MainActivity activity = (MainActivity) getActivity();
        activity.setContentView(R.layout.fragment_new_form);

        final Button button = activity.findViewById(R.id.formSubmit);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                View nameView = v.findViewById(R.id.name);
                final String name = nameView.toString();
                View dateView = v.findViewById(R.id.date);
                final String date = dateView.toString();
                View categoryView = v.findViewById(R.id.category);
                final String category = categoryView.toString();
                View descriptionView = v.findViewById(R.id.description);
                final String description = descriptionView.toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Form newForm = new Form(name, date, category, description);
                        DaoAccess dao = activity.getAppDatabase().daoAccess();
                        dao.insertSingleForm(newForm);
                    }
                }).start();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_form, container, false);
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
