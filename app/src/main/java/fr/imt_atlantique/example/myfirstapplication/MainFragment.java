package fr.imt_atlantique.example.myfirstapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;


import fr.imt_atlantique.example.myfirstapplication.onDisplayInfo.OnDisplayInfo;

public class MainFragment extends Fragment {
    private EditText surnameEt;
    private EditText birthdayEt;
    private Button displaySurnameBtn;
    private SharedPreferences.Editor editor;

    private View rootView;

    public static final String EXTRA_SHARE_MESSAGE = "This is a shared message";

    private OnDisplayInfo mainActivity;

//    public static final String DATE_ARG = "date args";
    private String dateArgs;
    private String surname;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (savedInstanceState!=null){
//            dateArgs = savedInstanceState.getString("date", "");
//            surname = savedInstanceState.getString("surname","");
//        }
        if (getArguments() != null) {
            dateArgs = getArguments().getString("date","");
            surname = getArguments().getString("surname","");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 1. Afficher nom dans une autre fragment
     * 2. Display sa date ou modifier le departement
     */

    /**
     *
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_activity_main, container, false);
        displaySurnameBtn = rootView.findViewById(R.id.surname_bt);
        surnameEt = rootView.findViewById(R.id.surname);
        birthdayEt = rootView.findViewById(R.id.birthday);
        surnameEt.setText(surname);
        birthdayEt.setText(dateArgs);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = getActivity();
        if (activity instanceof OnDisplayInfo) {
            this.mainActivity = (OnDisplayInfo) activity;
        }
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState ){
        if (displaySurnameBtn!=null){

            displaySurnameBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    mainActivity.onDisplaySurname(surnameEt.getText().toString());
                }
            });
        }
        if (birthdayEt!=null){
            birthdayEt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mainActivity.onDisplayDate();
                }
            });
        }
    }

    public String getSurname(){
        return surnameEt.getText().toString();
    }

    public static MainFragment newInstance(String surname, String date){
        MainFragment mainFragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString("surname", surname);
        args.putString("date", date);
        mainFragment.setArguments(args);
        return mainFragment;
    }
}
