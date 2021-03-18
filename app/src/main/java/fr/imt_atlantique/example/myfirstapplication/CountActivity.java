
package fr.imt_atlantique.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class CountActivity extends AppCompatActivity {


    public class MainActivity extends AppCompatActivity {

        private int savedNumber;
        private TextView numberTv;
        private SharedPreferences.Editor editor;

//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putInt("savedNumber", savedNumber);
//        System.out.println("you rotate me when number is: "+savedNumber);
//
//    }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            System.out.println("you create me again");
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            numberTv = findViewById(R.id.number);

            SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
            savedNumber = sharedPreferences.getInt("savedNumber", 0);
            savedNumber = savedNumber==0 ? 0 : savedNumber;
            numberTv.setText(savedNumber+"");

//        if (savedInstanceState!=null){
//            savedNumber = savedInstanceState.getInt("savedNumber");
//            numberTv.setText(savedNumber+"");
//        }else{
//            savedNumber=0;
//            numberTv.setText(savedNumber+"");
//        }

            Log.i("Lifecycle","onCreate method");

        }


        public void addOne(View view){
            savedNumber++;
            numberTv.setText(savedNumber+"");
            editor.putInt("savedNumber", savedNumber);
            editor.apply();
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
            /*

             */
            super.onDestroy();
            Log.i("Lifecycle", "onDestroy method");
        }

        @Override
        protected void onStop() {
            super.onStop();
            Log.i("Lifecycle","onStop method");
        }

        @Override
        protected void onRestart() {
            super.onRestart();
            Log.i("Lifecycle","onRestart method");
        }
    }
}