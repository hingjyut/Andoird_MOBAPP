package fr.imt_atlantique.example.myfirstapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import fr.imt_atlantique.example.myfirstapplication.domain.User;

public class DisplayActivity extends AppCompatActivity {


    private TextView showTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_layout);
        System.out.println("in display");
        showTv = findViewById(R.id.display_tv);
        showTv.setText("");
        User user = (User) getIntent().getParcelableExtra("info");
        System.out.println("user"+user.toString());
        showTv.setText(user.toString());
    }

}
