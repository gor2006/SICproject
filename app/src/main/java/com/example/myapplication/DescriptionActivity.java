package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DescriptionActivity extends AppCompatActivity {

    ImageView back;
    TextView publisher_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description);

        back = findViewById(R.id.back);
        publisher_page = findViewById(R.id.publisher);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

        publisher_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOtherprofileActivity();
            }
        });
    }

    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void openOtherprofileActivity(){
        Intent intent = new Intent(this, OtherprofileActivity.class);
        startActivity(intent);
    }

}