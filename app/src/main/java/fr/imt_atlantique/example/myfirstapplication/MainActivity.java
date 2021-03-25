package fr.imt_atlantique.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import fr.imt_atlantique.example.myfirstapplication.domain.User;

public class MainActivity extends AppCompatActivity {

    private List<LinearLayout> phoneLLs;
    private EditText nameEt;
    private EditText surnameEt;
    private EditText emailEt;
    private EditText birthdayEt;
    private EditText phoneEt;
    private Spinner countySpn;
    private EditText cityEt;
    private Button validBt;
    private SharedPreferences.Editor editor;

    public static final String EXTRA_SHARE_MESSAGE = "This is a shared message";
    public static final int BIRTHDAY_CODE = 5;
    public static final int DATE_ANSWER_CODE = 50;

    public static final int SURNAME_CODE = 6;
    public static final int SURNAME_ANSWER_CODE = 60;

    private Button cameraBtn;       // A button for turning camera on
    private ImageView imageView;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int SAVE_PHOTO_INTO_GALLERY = 2;
    private static final String TAG = "MainActivity";


    private String currentPhotoPath;
    private Uri currentPhotoUri;
    private File currentPhotoFile;
    private String currentPhotoName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneLLs = new LinkedList<>();
        nameEt = findViewById(R.id.name);
        surnameEt = findViewById(R.id.surname);
        emailEt = findViewById(R.id.email);
        birthdayEt = findViewById(R.id.birthday);
        countySpn = findViewById(R.id.county_spinner);
        phoneEt = findViewById(R.id.phone);
        phoneEt.setInputType(InputType.TYPE_CLASS_NUMBER);
        cityEt = findViewById(R.id.city_et);
        validBt = findViewById(R.id.validate);
        surnameEt.setFocusable(false);

        setContentView(R.layout.activity_main);
        cameraBtn = findViewById(R.id.camera_btn);
        imageView = findViewById(R.id.image_view);
        // Button onclick function
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.i("Start camera", "onCreate mode");
                    startCamera();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.first_app_data), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
//        editor.clear(); // clear preference file
        String savedPhones = sharedPreferences.getString("phone", "");
        if (savedPhones != "") {
            String[] phones = savedPhones.split("\n");
            for (String phone : phones) {
//                createALinearLayoutWithDeleteAndPhoneNumber(phone);
                createALinearLayoutWithDeleteAndPhoneNumberCallBtn(phone);
            }
        }
        Log.i("Lifecycle", "onCreate method");
    }


    /**
     * TP final: storage
     */

    public void startCamera() throws IOException {

//        //Internal
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

        // External
        // start a new implicit intent
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            try {
                currentPhotoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                ex.printStackTrace();
            }
            // Continue only if the File was successfully created
            if (currentPhotoFile != null) {
                currentPhotoUri = FileProvider.getUriForFile(this,
                        "fr.imt_atlantique.example.myfirstapplication.provider",
                        currentPhotoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, currentPhotoUri);   // Define photo's storage location
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);       // Starts to take photo
            }
        }
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
            //internal
//            case REQUEST_IMAGE_CAPTURE:
//                if (resultCode==Activity.RESULT_OK){
//                    Log.d(TAG, "requestCode: " + requestCode + "  resultCode: " + resultCode + "  data：" + data);
//                    Bitmap bitmap = null;
//                    try {
//                        Bundle extras = data.getExtras();
//                        bitmap = (Bitmap) extras.get("data");
//                        File thumbnailFile = createFile(getFilesDir(), "thumbnails");
//                        FileOutputStream fos = new FileOutputStream(thumbnailFile);
//                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
//                        fos.flush();
//                        fos.close();
//                        imageView.setImageBitmap(bitmap);   // shows photo on the screen
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//                break;

            // external
            case REQUEST_IMAGE_CAPTURE:
                if (resultCode == Activity.RESULT_OK) {
                    Log.d(TAG, "requestCode: " + requestCode + "  resultCode: " + resultCode + "  data：" + data);
                    Bitmap bitmap = null;
                    try {
                        // Once user finished taking photo, the photo has been stored in "currentPhotoUri", so now gets the photo back
                        bitmap = MediaStore.Images.Media.getBitmap(
                                this.getContentResolver(),
                                currentPhotoUri
                        );

//                        setPic();   // shows photo on the screen
                        imageView.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    galleryAddPic();
                }

                break;

        }
    }

    private void setPic() {
        // Get the dimensions of the View
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;
        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        imageView.setImageBitmap(bitmap);
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        currentPhotoName = imageFileName;
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        System.out.println(storageDir.toString() + "storage dir");
        if (!(storageDir.exists())) {
            storageDir.mkdir();
        }
        System.out.println(storageDir.toString());
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }


    public static File createFile(File storageDir, String folderName) throws IOException {
        // Create a file name
        String fileName = createUniqueFileName();
        // Use an existing directory or create it if necessary
        File picturesDir = new File(storageDir, folderName);
        if (!(picturesDir.exists())) {
            picturesDir.mkdir();
        }

        // Create the name of the file with suffix .jpg
        File pathToFile = File.createTempFile(
                fileName,
                ".jpg",
                picturesDir
        );

        System.out.println(pathToFile.toString());
        return pathToFile;
    }

    private static String createUniqueFileName() throws IOException {
        // Create a unique file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = "JPEG_" + timeStamp + "_";
        return fileName;
    }


    /**
     * Récupération des informations saisies dans une activité lancée par intent
     *
     * @param view
     */
    public void go2LastNameEdit(View view) {
        Intent intent = new Intent(Intent.ACTION_EDIT);
        if (intent.resolveActivity(getPackageManager()) != null) {
            Log.i("Activity", "go to last name edit matches success");
            intent.addCategory("EDITNAME");
            Set<String> categories = intent.getCategories();
            for (String s : categories) {
                System.out.println("category is: " + s);
            }
            intent.putExtra("last name", surnameEt.getText().toString());

            startActivityForResult(intent, SURNAME_CODE);
        } else {
            Log.i("Activity", "go to last name edit matches failure");
        }
    }

    /**
     * Numérotation effective d’un numéro de téléphone
     * line 127 onClick function
     *
     * @param phoneNumber
     */
    public void createALinearLayoutWithDeleteAndPhoneNumberCallBtn(String phoneNumber) {
        LinearLayout newLinearLayout = new LinearLayout(this);
        Button callButton = new Button(this);

        Button deleteButton = new Button(this);
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        int lastIndex = mainLayout.getChildCount() - 1;
        deleteButton.setText("delete");
        // views decoration
        newLinearLayout.setGravity(Gravity.CENTER);
        callButton.setWidth(200);
        deleteButton.setWidth(50);
        if (!(phoneNumber == "" || phoneNumber == null)) {
            callButton.setText(phoneNumber);
            newLinearLayout.addView(callButton);
            newLinearLayout.addView(deleteButton);
            phoneLLs.add(newLinearLayout);
            mainLayout.addView(newLinearLayout, lastIndex);
            phoneEt.setText("");
            callButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    go2PhoneDialActivity(phoneNumber);
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:+33" + phoneNumber));
                    startActivity(intent);
                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    newLinearLayout.removeAllViews();
                    phoneLLs.remove(newLinearLayout);
                }
            });
        }
    }

    /**
     * Go to another activity by using implicit intent
     */
    public void go2firstNameView(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (intent.resolveActivity(getPackageManager()) != null) {
            Log.i("Activity", "go to first name view matches success");
            intent.putExtra("first name", nameEt.getText().toString());
            startActivity(intent);
        } else {
            Log.i("Activity", "go to first name view matches failure");
        }
    }

    /**
     * TP3: search a city in browser
     * Implementation of Intent implicite
     * 3. Lancement d’une activité de recherche sur internet par intent implicite
     */

    public void searchCity(MenuItem menuItem) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String city = cityEt.getText().toString();
        if (city != "" || city != null) {
            intent.setData(Uri.parse("http://fr.wikipedia.org/?search=" + city));
            startActivity(intent);
        }
    }

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
    public void callDisplayActivity(View view) {
        Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
        User user = new User(surnameEt.getText().toString(), nameEt.getText().toString(),
                birthdayEt.getText().toString(), countySpn.getSelectedItem().toString(), cityEt.getText().toString(),
                emailEt.getText().toString(), getAllPhoneNumbersAsAString());
        intent.putExtra("info", user);
        System.out.println("Finished putExtra");
        System.out.println(user.toString());
        startActivity(intent);
        System.out.println("Started");
    }

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
    public void addPhoneNumber(View v) {
        String phoneNumber = phoneEt.getText().toString();
//        createALinearLayoutWithDeleteAndPhoneNumber(phoneNumber);
        createALinearLayoutWithDeleteAndPhoneNumberCallBtn(phoneNumber);
    }


    /**
     * Create a LinearLayout contains a TextView and a delete button
     * TextView shows the number that user typed, delete button is to delete the whole LinearLayout
     *
     * @param phoneNumber
     */
    public void createALinearLayoutWithDeleteAndPhoneNumber(String phoneNumber) {
        LinearLayout newLinearLayout = new LinearLayout(this);
        TextView phoneTv = new TextView(this);
        Button deleteButton = new Button(this);
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        int lastIndex = mainLayout.getChildCount() - 1;
        deleteButton.setText("delete");
        // views decoration
        newLinearLayout.setGravity(Gravity.CENTER);
        phoneTv.setWidth(200);
        deleteButton.setWidth(50);
        if (!(phoneNumber == "" || phoneNumber == null)) {
            phoneTv.setText(phoneNumber);
            newLinearLayout.addView(phoneTv);
            newLinearLayout.addView(deleteButton);
            phoneLLs.add(newLinearLayout);
            mainLayout.addView(newLinearLayout, lastIndex);
            phoneEt.setText("");
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    newLinearLayout.removeAllViews();
                    phoneLLs.remove(newLinearLayout);
                }
            });
        }

    }

    /**
     * Go to DateActivity, it's bound with birthday EditText
     *
     * @param view
     */
    public void go2DateActivity(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        startActivityForResult(intent, BIRTHDAY_CODE);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Lifecycle", "onStart method");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Lifecycle", "onResume method");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Lifecycle", "onPause method");
    }

    @Override
    protected void onDestroy() {
        saveAllInputsIntoXML();
        super.onDestroy();
        Log.i("Lifecycle", "onDestroy method");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Lifecycle", "onStop method");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Lifecycle", "onRestart method");
    }

}