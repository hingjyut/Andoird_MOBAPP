package fr.imt_atlantique.example.myfirstapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;


import java.util.LinkedList;
import java.util.List;

import fr.imt_atlantique.example.myfirstapplication.onDisplayInfo.OnDisplayInfo;

public class MainFragment extends Fragment {
    private EditText surnameEt;
    private EditText birthdayEt;
    private Button displaySurnameBtn;
    private SharedPreferences.Editor editor;

    private List<LinearLayout> phoneLLs;
    private EditText nameEt;
    private EditText emailEt;
    private EditText phoneEt;
    private Spinner countySpn;
    private EditText cityEt;
    private Button validBt;

    public static final String EXTRA_SHARE_MESSAGE = "This is a shared message";
    public static final int BIRTHDAY_CODE = 5;
    public static final int DATE_ANSWER_CODE = 50;

    public static final int SURNAME_CODE = 6;
    public static final int SURNAME_ANSWER_CODE = 60;


    private View rootView;


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
//        setHasOptionsMenu(true);

        if (getArguments() != null) {
            dateArgs = getArguments().getString("date","");
            surname = getArguments().getString("surname","");
            System.out.println("in main fragment onCreate "+ dateArgs + " " + surname);
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
        phoneLLs = new LinkedList<>();
        rootView = inflater.inflate(R.layout.fragment_activity_main, container, false);
        displaySurnameBtn = rootView.findViewById(R.id.surname_bt);
        surnameEt = rootView.findViewById(R.id.surname);
        birthdayEt = rootView.findViewById(R.id.birthday);

        // Other views
//        nameEt = rootView.findViewById(R.id.name);
//        emailEt = rootView.findViewById(R.id.email);
//        phoneEt = rootView.findViewById(R.id.phone);
//        countySpn = rootView.findViewById(R.id.county_spinner);
//
        Log.i("Lifecycle", "onCreate method");

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

//    public void searchCity(MenuItem menuItem) {
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        String city = cityEt.getText().toString();
//        if (city != "" || city != null) {
//            intent.setData(Uri.parse("http://fr.wikipedia.org/?search=" + city));
//            startActivity(intent);
//        }
//    }

//    public void shareInfo(MenuItem menuItem) {
//        Intent sendIntent = new Intent(Intent.ACTION_SEND);
//        // share city to another app
//        sendIntent.putExtra(EXTRA_SHARE_MESSAGE, cityEt.getText().toString());
//        sendIntent.setType("text/plain");
//        Intent shareIntent = Intent.createChooser(sendIntent, "share with");
//        startActivity(shareIntent);
//    }

//    public void resetAction(MenuItem menuItem) {
//        for (LinearLayout linearLayout : phoneLLs) {
//            linearLayout.removeAllViews();
//        }
////        phoneLLs.clear();
////        nameEt.setText("");
//        surnameEt.setText("");
////        emailEt.setText("");
////        birthdayEt.setText("");
////        phoneEt.setText("");
////        cityEt.setText("");
////        countySpn.setSelection(0);
//    }

//
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        // TODO Add your menu entries here
//        inflater.inflate(R.menu.menu_main, menu) ;
//    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("Fragment Lifecycle", "onStart method");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("Fragment Lifecycle", "onResume method");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("Fragment Lifecycle", "onPause method");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("Fragment Lifecycle", "onStop method");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Fragment Lifecycle", "onDestroy method");
    }

}
