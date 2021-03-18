package fr.imt_atlantique.example.myfirstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LastNameEditActivity extends AppCompatActivity {


    private EditText lastNameEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_lastname);
        lastNameEt= findViewById(R.id.last_name_edit_et);
        Intent intent = getIntent();
        TextView lastNameTv = findViewById(R.id.last_name_edit_tv);
        String lastName = intent.getStringExtra("last name");
        System.out.println("last name"+lastName);
        if (lastName.length()==0 ||lastName==null){
            lastNameTv.setText("Please input a family name");
        }else{
            lastNameTv.setText("The origin family name is : "+lastName +", you would like to modify it as: ");
        }
        LinearLayout linearLayout = findViewById(R.id.edit_lastname_layout);
        Button confirmBt = new Button(this);
        confirmBt.setText("Confirm");
        confirmBt.setVisibility(View.VISIBLE);

//        return to main activity
        confirmBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back2MainActivity();
            }
        });
        linearLayout.addView(confirmBt);
    }

    public void back2MainActivity() {
        System.out.println("back 2 main");
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.putExtra("modified last name", lastNameEt.getText().toString());
        System.out.println(lastNameEt.getText().toString());
        this.setResult(MainActivity.SURNAME_ANSWER_CODE, intent);
        this.finish();
    }
}

