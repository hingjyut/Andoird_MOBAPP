package fr.imt_atlantique.example.myfirstapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import fr.imt_atlantique.example.myfirstapplication.onDisplayInfo.OnDisplayInfo;

public class MainFragment extends Fragment {
    private List<LinearLayout> phoneLLs;
    private EditText nameEt;
    private EditText surnameEt;
    private EditText emailEt;
    private EditText birthdayEt;
    private EditText phoneEt;
    private Spinner countySpn;
    private EditText cityEt;
    private Button validBt;
    private Button displaySurnameBtn;
    private SharedPreferences.Editor editor;

    private View rootView;

    public static final String EXTRA_SHARE_MESSAGE = "This is a shared message";
    public static final int BIRTHDAY_CODE = 5;
    public static final int DATE_ANSWER_CODE = 50;

    public static final int SURNAME_CODE = 6;
    public static final int SURNAME_ANSWER_CODE = 60;

    private OnDisplayInfo mainActivity;

//    public static final String DATE_ARG = "date args";
    private String dateArgs;
    private String surname;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dateArgs = getArguments().getString("date");
            surname = getArguments().getString("surname");
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
    public void onAttach(Activity activity){
        super.onAttach(activity);
        mainActivity = (OnDisplayInfo) activity;
        System.out.println("EditFragment, OnAttach");
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

    public static MainFragment newInstance(String surname, String date){
        MainFragment mainFragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString("surname", surname);
        args.putString("date", date);
        mainFragment.setArguments(args);
        return mainFragment;
    }


//    public void setDate(String date){
//        birthdayEt = rootView.findViewById(R.id.birthday);
//        birthdayEt.setText(date);
//    }
//
//    public void setSurname(String surname){
//        surnameEt = rootView.findViewById(R.id.surname);
//        surnameEt.setText(surname);
//    }


    /**
     * Récupération des informations saisies dans une activité lancée par intent
     *
     * @param view
     */
//    public void go2LastNameEdit(View view) {
//        Intent intent = new Intent(Intent.ACTION_EDIT);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            Log.i("Activity", "go to last name edit matches success");
//            intent.addCategory("EDITNAME");
//            Set<String> categories = intent.getCategories();
//            for (String s : categories) {
//                System.out.println("category is: " + s);
//            }
//            intent.putExtra("last name", surnameEt.getText().toString());
//
//            startActivityForResult(intent, SURNAME_CODE);
//        } else {
//            Log.i("Activity", "go to last name edit matches failure");
//        }
//    }

    /**
     * Numérotation effective d’un numéro de téléphone
     * line 127 onClick function
     * @param phoneNumber
     */
//    public void createALinearLayoutWithDeleteAndPhoneNumberCallBtn(String phoneNumber){
//        LinearLayout newLinearLayout = new LinearLayout(this);
//        Button callButton = new Button(this);
//
//        Button deleteButton = new Button(this);
//        LinearLayout mainLayout = (LinearLayout) rootView.findViewById(R.id.main_fragment);
//        int lastIndex = mainLayout.getChildCount() - 1;
//        deleteButton.setText("delete");
//        // views decoration
//        newLinearLayout.setGravity(Gravity.CENTER);
//        callButton.setWidth(200);
//        deleteButton.setWidth(50);
//        if (!(phoneNumber == "" || phoneNumber == null)) {
//            callButton.setText(phoneNumber);
//            newLinearLayout.addView(callButton);
//            newLinearLayout.addView(deleteButton);
//            phoneLLs.add(newLinearLayout);
//            mainLayout.addView(newLinearLayout, lastIndex);
//            phoneEt.setText("");
//            callButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    go2PhoneDialActivity(phoneNumber);
//                    Intent intent = new Intent(Intent.ACTION_DIAL);
//                    intent.setData(Uri.parse("tel:+33"+phoneNumber));
//                    startActivity(intent);
//                }
//            });
//
//            deleteButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    newLinearLayout.removeAllViews();
//                    phoneLLs.remove(newLinearLayout);
//                }
//            });
//        }
//    }

    /**
     * Go to another activity by using implicit intent
     */
//    public void go2firstNameView(View view) {
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            Log.i("Activity", "go to first name view matches success");
//            intent.putExtra("first name", nameEt.getText().toString());
//            startActivity(intent);
//        } else {
//            Log.i("Activity", "go to first name view matches failure");
//        }
//    }

    /**
     * TP3: search a city in browser
     * Implementation of Intent implicite
     * 3. Lancement d’une activité de recherche sur internet par intent implicite
     */

//    public void searchCity(MenuItem menuItem) {
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        String city = cityEt.getText().toString();
//        if (city != "" || city != null) {
//            intent.setData(Uri.parse("http://fr.wikipedia.org/?search=" + city));
//            startActivity(intent);
//        }
//    }

    /**
     * Implementation of sharing information to another application
     */
    public void shareInfo(MenuItem menuItem) {
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        // share city to another app
        sendIntent.putExtra(EXTRA_SHARE_MESSAGE, cityEt.getText().toString());
        sendIntent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(sendIntent, "share with");
        startActivity(shareIntent);
    }

    /**
     * Implementation of Parcelable
     * <p>
     * 1. Lancement d’une activité d’affichage des champs saisis par intent explicite
     * 3. Usage des Parcelable
     */
//    public void callDisplayActivity(View view) {
//        Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
//        User user = new User(surnameEt.getText().toString(), nameEt.getText().toString(),
//                birthdayEt.getText().toString(), countySpn.getSelectedItem().toString(), cityEt.getText().toString(),
//                emailEt.getText().toString(), getAllPhoneNumbersAsAString());
//        intent.putExtra("info", user);
//        System.out.println("Finished putExtra");
//        System.out.println(user.toString());
//        startActivity(intent);
//        System.out.println("Started");
//    }

    /**
     * Get all numbers from a list and combine them as a string
     *
     * @return a string value contains allNumbers
     */
    public String getAllPhoneNumbersAsAString() {
        String res = "";
        for (LinearLayout linearLayout : phoneLLs) {
            TextView phoneTv = (TextView) linearLayout.getChildAt(0);
            res += phoneTv.getText().toString() + "\n";
        }
        return res;
    }

    /**
     * It's bound with "add" button, adds a new LinearLayout contains a phone number and a delete button with each click
     */
//    public void addPhoneNumber(View v) {
//        String phoneNumber = phoneEt.getText().toString();
////        createALinearLayoutWithDeleteAndPhoneNumber(phoneNumber);
//        createALinearLayoutWithDeleteAndPhoneNumberCallBtn(phoneNumber);
//    }


    /**
     * Create a LinearLayout contains a TextView and a delete button
     * TextView shows the number that user typed, delete button is to delete the whole LinearLayout
     *
     * @param phoneNumber
     */
//    public void createALinearLayoutWithDeleteAndPhoneNumber(String phoneNumber) {
//        LinearLayout newLinearLayout = new LinearLayout(this);
//        TextView phoneTv = new TextView(this);
//        Button deleteButton = new Button(this);
//        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
//        int lastIndex = mainLayout.getChildCount() - 1;
//        deleteButton.setText("delete");
//        // views decoration
//        newLinearLayout.setGravity(Gravity.CENTER);
//        phoneTv.setWidth(200);
//        deleteButton.setWidth(50);
//        if (!(phoneNumber == "" || phoneNumber == null)) {
//            phoneTv.setText(phoneNumber);
//            newLinearLayout.addView(phoneTv);
//            newLinearLayout.addView(deleteButton);
//            phoneLLs.add(newLinearLayout);
//            mainLayout.addView(newLinearLayout, lastIndex);
//            phoneEt.setText("");
//            deleteButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    newLinearLayout.removeAllViews();
//                    phoneLLs.remove(newLinearLayout);
//                }
//            });
//        }
//    }

    /**
     * Go to DateActivity, it's bound with birthday EditText
     *
     * @param view
     */
    public void go2DateActivity(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        startActivityForResult(intent, BIRTHDAY_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case BIRTHDAY_CODE:
                if (resultCode == DATE_ANSWER_CODE) {
                    String message = data.getStringExtra("birthday_data");
                    birthdayEt.setText(message);
                }
                break;
            case SURNAME_CODE:
                if (resultCode == SURNAME_ANSWER_CODE) {
                    surnameEt.setFocusable(true);
                    String lastName = data.getStringExtra("modified last name");
                    surnameEt.setText(lastName);
                    surnameEt.setFocusable(false);
                }
                break;

        }
    }

    /**
     * It is called when user clicks reset in Menu. It's to clean all inputs
     *
     * @param menuItem
     */
    public void resetAction(MenuItem menuItem) {
        for (LinearLayout linearLayout : phoneLLs) {
            linearLayout.removeAllViews();
        }
        phoneLLs.clear();
        nameEt.setText("");
        surnameEt.setText("");
        emailEt.setText("");
        birthdayEt.setText("");
        phoneEt.setText("");
        cityEt.setText("");
        countySpn.setSelection(0);
    }

    /**
     * TP1 validate button's onClick function
     */
    public void validateAction(View v) {
        String context = "surname: " + surnameEt.getText().toString() + " name: " + nameEt.getText().toString()
                + " birthday: " + birthdayEt.getText().toString() + " county : " + countySpn.getSelectedItem().toString()
                + " email: " + emailEt.getText().toString() + " phone: " + getAllPhoneNumbersAsAString();
        Snackbar snackbar = Snackbar.make(v, context, Snackbar.LENGTH_LONG);
        snackbar.setAction("dismiss", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();
            }
        }).show();
        saveAllInputsIntoXML();
    }

    /**
     * Save date into a xml file
     */
    public void saveAllInputsIntoXML() {
        editor.putString("name", nameEt.getText().toString());
        editor.putString("surname", surnameEt.getText().toString());
        editor.putString("email", emailEt.getText().toString());
        editor.putString("birthday", birthdayEt.getText().toString());
        editor.putString("county", countySpn.getSelectedItem().toString());
        editor.putString("phone", getAllPhoneNumbersAsAString());
        editor.apply();
    }


}
