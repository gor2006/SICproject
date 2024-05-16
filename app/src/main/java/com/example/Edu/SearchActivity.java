package com.example.Edu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SearchActivity extends AppCompatActivity {

    private ImageButton homebutton;
    private ImageButton favoritesbutton;
    private ImageButton profilebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        homebutton = (ImageButton) findViewById(R.id.imageButton);
        favoritesbutton = (ImageButton) findViewById(R.id.imageButton3);
        profilebutton = (ImageButton) findViewById(R.id.imageButton4);
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomeActivity();
            }
        });
        favoritesbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFavoritesActivity();
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
    public void openFavoritesActivity(){
        Intent intent = new Intent(this, FavoritesActivity.class);
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
