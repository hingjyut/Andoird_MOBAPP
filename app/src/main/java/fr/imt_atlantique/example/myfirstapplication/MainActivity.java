package fr.imt_atlantique.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import fr.imt_atlantique.example.myfirstapplication.onDisplayInfo.OnDisplayInfo;

public class MainActivity extends AppCompatActivity implements OnDisplayInfo {

    public static final int DATE_ANSWER_CODE = 50;
    public static final int SURNAME_ANSWER_CODE = 60;
    private MainFragment mainFragment;
    private DisplaySurnameFragment displaySurnameFragment;
    private DisplayDateFragment displayDateFragment;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String surname;
    private String date;
    private static final String PREFERENCE = "preference";
    private static final String SURNAME_KEY = "surname key";
    private static final String BIRTHDAY_KEY = "birthday key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        if (savedInstanceState == null) {
            mainFragment = new MainFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, mainFragment).commit();
        }
//        else {
//            date = sharedPreferences.getString(BIRTHDAY_KEY, "");
//            surname = sharedPreferences.getString(SURNAME_KEY, "");
//            mainFragment = MainFragment.newInstance(surname, date);
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, mainFragment).commit();
//        }

    }

    @Override
    public void onDisplaySurname(String newName) {
        displaySurnameFragment = DisplaySurnameFragment.newInstance(newName);
        surname = newName;
        saveAllInputsIntoXML();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        // replace editFragment by displayFragment, so the app shows display's layout
        fragmentTransaction.replace(R.id.fragmentContainer, displaySurnameFragment);
        fragmentTransaction.addToBackStack(mainFragment.getClass().toString()).commit();
    }

    @Override
    public void onDisplayDate() {
        saveAllInputsIntoXML();
        displayDateFragment = new DisplayDateFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, displayDateFragment);
        fragmentTransaction.addToBackStack(mainFragment.getClass().toString()).commit();
    }

    @Override
    public void onSetDate(String date) {
        mainFragment = MainFragment.newInstance(surname, date);
//        this.date = date;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, mainFragment);
        fragmentTransaction.addToBackStack(mainFragment.getClass().toString()).commit();
    }

    /**
     * Save date into a xml file
     */
    public void saveAllInputsIntoXML() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(BIRTHDAY_KEY, mainFragment.getBirthday());
        editor.putString(SURNAME_KEY, mainFragment.getSurname());
        editor.apply();
    }

    @Override
    protected void onStop() {
        saveAllInputsIntoXML();
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveAllInputsIntoXML();
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String date = savedInstanceState.getString("date", "");
        String surname = savedInstanceState.getString("surname","");
        this.date = date;
        this.surname = surname;
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Lifecycle", "onStart method");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Lifecycle", "onRestart method");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Lifecycle", "onResume method");
    }

    @Override
    protected  void onPause() {
        super.onPause();
        Log.i("Lifecycle", "onPause method");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Lifecycle", "onDestroy method");
    }


}