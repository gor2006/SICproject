package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private ImageButton searchbutton;
    private ImageButton favoritesbutton;
    private ImageButton profilebutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ConstraintLayout constraintLayout = findViewById(R.id.example);
        androidx.constraintlayout.widget.ConstraintLayout constraintLayout = findViewById(R.id.example);



        searchbutton = (ImageButton) findViewById(R.id.imageButton2);
        favoritesbutton = (ImageButton) findViewById(R.id.imageButton3);
        profilebutton = (ImageButton) findViewById(R.id.imageButton4);


        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDescActivity();
            }
        });
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearchActivity();
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

    public void openSearchActivity(){
        Intent intent = new Intent(this, SearchActivity.class);
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
    public void openDescActivity(){
        Intent intent = new Intent(this, DescriptionActivity.class);
        startActivity(intent);
    }
}