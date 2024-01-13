package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class FavoritesActivity extends AppCompatActivity {

    private ImageButton homebutton;
    private ImageButton searchbutton;
    private ImageButton profilebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites);

        homebutton = (ImageButton) findViewById(R.id.imageButton);
        searchbutton = (ImageButton) findViewById(R.id.imageButton2);
        profilebutton = (ImageButton) findViewById(R.id.imageButton4);

        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomeActivity();
            }
        });
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearchActivity();
            }
        });
        profilebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfileActivity();
            }
        });
    }

    public void openHomeActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void openSearchActivity(){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
    public void openProfileActivity(){
        Intent intent;
        if(LoginActivity.log == false){
            intent = new Intent(this, LoginActivity.class);
        }else
            intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);

    }
}
