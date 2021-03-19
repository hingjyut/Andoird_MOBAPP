package fr.imt_atlantique.example.myfirstapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class DisplaySurnameFragment extends Fragment {

    private TextView tvDisplayName;
    private View rootView;
    public static final String ARG_PARAM = "surname";
    protected String newName;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if (getArguments()!=null){
            newName = getArguments().getString(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.fragment_display_surname, container, false);
        tvDisplayName = rootView.findViewById(R.id.display_surname_tv);
        tvDisplayName.setText(newName);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public static DisplaySurnameFragment newInstance(String param){
        DisplaySurnameFragment displayFragment = new DisplaySurnameFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, param);
        displayFragment.setArguments(args);
        return displayFragment;
    }

    public String getNewName(){
        return this.newName;
    }
}

