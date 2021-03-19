package fr.imt_atlantique.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import fr.imt_atlantique.example.myfirstapplication.onDisplayInfo.OnDisplayInfo;

public class MainActivity extends AppCompatActivity implements OnDisplayInfo {

    public static final String EXTRA_SHARE_MESSAGE = "This is a shared message";
    public static final int BIRTHDAY_CODE = 5;
    public static final int DATE_ANSWER_CODE = 50;

    public static final int SURNAME_CODE = 6;
    public static final int SURNAME_ANSWER_CODE = 60;
    private MainFragment mainFragment;
    private DisplaySurnameFragment displaySurnameFragment;
    private DisplayDateFragment displayDateFragment;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String surname;
    private static String preference = "preference";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            mainFragment = new MainFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, mainFragment).commit();
        }
        sharedPreferences = getSharedPreferences(preference, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        surname = sharedPreferences.getString("surname", "");
    }

    @Override
    public void onDisplaySurname(String newName) {
        displaySurnameFragment = DisplaySurnameFragment.newInstance(newName);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        // replace editFragment by displayFragment, so the app shows display's layout
        fragmentTransaction.replace(R.id.fragmentContainer, displaySurnameFragment);
        fragmentTransaction.addToBackStack(mainFragment.getClass().toString()).commit();
    }

    /**
     * Save date into a xml file
     */
    public void saveAllInputsIntoXML() {
        if (displaySurnameFragment!=null){
            surname = displaySurnameFragment.getNewName();
        }else {
            surname = "";
        }
        System.out.println(surname);
        editor.putString("surname", surname);
        editor.apply();
    }


    @Override
    public void onDisplayDate() {
        displayDateFragment = new DisplayDateFragment();
        saveAllInputsIntoXML();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, displayDateFragment);
        fragmentTransaction.addToBackStack(mainFragment.getClass().toString()).commit();
    }

    @Override
    public void onSetDate(String date) {
        mainFragment = MainFragment.newInstance(surname, date);
        System.out.println(surname);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, mainFragment).commit();
    }
}