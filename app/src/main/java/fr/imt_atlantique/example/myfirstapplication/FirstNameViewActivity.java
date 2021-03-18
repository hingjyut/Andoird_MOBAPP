package fr.imt_atlantique.example.myfirstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FirstNameViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_firstname);
        TextView textView = findViewById(R.id.show_first_name);
        Intent intent = getIntent();
        String firstName = intent.getStringExtra("first name");
        System.out.println(firstName);
        textView.setText("Your first name is: "+firstName);

        // add a button for going back to main activity
        LinearLayout linearLayout = findViewById(R.id.view_firstname_layout);
        Button back = new Button(this);
        back.setText("Back");
        linearLayout.addView(back);
        // return to main activity
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back2MainActivity();
            }
        });
    }

    public void back2MainActivity(){
        this.finish();
    }
}
