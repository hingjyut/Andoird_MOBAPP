package fr.imt_atlantique.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentTransaction;

import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

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
    private static String SHARED_FILE = "preference";
    private String date;
    private Map<String, String> cache = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(SHARED_FILE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        if (savedInstanceState == null) {
            mainFragment = new MainFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, mainFragment).commit();
        }
        String surname = sharedPreferences.getString("surname", "");
//        String firstName = sharedPreferences.getString("first name", "");
        String birthday = sharedPreferences.getString("birthday", "");
        String uri = sharedPreferences.getString("uri", null);
        mainFragment = MainFragment.newInstance(surname, birthday, uri);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, mainFragment)
                .commit();
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

    /**
     * Save date into a xml file
     */
    public void saveAllInputsIntoXML() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("surname", mainFragment.getSurname());
        editor.putString("birthday", mainFragment.getBirthday());
//        editor.putString(URI_KEY, editInformationFragment.getPhotoUri().getPath());
//        editor.putString(URI_KEY, editInformationFragment.getCurrentPhotoPath());
        editor.apply();
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
    public void onDisplayDate() {
//        saveAllInputsIntoXML();
        displayDateFragment = new DisplayDateFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, displayDateFragment);
        fragmentTransaction.addToBackStack(mainFragment.getClass().toString()).commit();
    }

    @Override
    public void onSetDate(String date) {
        saveAllInputsIntoXML();
        surname = sharedPreferences.getString("surname", "");
        mainFragment = MainFragment.newInstance(surname, date);
//        System.out.println(surname);
        this.date = date;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, mainFragment);
        fragmentTransaction.addToBackStack(mainFragment.getClass().toString()).commit();
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