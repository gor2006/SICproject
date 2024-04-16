package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    private Button folowersbutton;
    private Button folowingbutton;
    private Button Addpostbutton;
    private TextView userNameSurname;

    private ImageButton homebutton;
    private ImageButton favoritesbutton;
    private ImageButton searchbutton;

    private Button logout;

    private FloatingActionButton addimg;
    private ImageView imageprofile;


    TextView useremail;
    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        auth = FirebaseAuth.getInstance();
        useremail = findViewById(R.id.usernametext);
        user = auth.getCurrentUser();

        if(user == null){
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }else{
            useremail.setText(user.getEmail());
        }

        homebutton = (ImageButton) findViewById(R.id.imageButton);
        favoritesbutton = (ImageButton) findViewById(R.id.imageButton3);
        Addpostbutton = (Button) findViewById(R.id.addpostbtn);

        addimg = (FloatingActionButton) findViewById(R.id.addimg);
        imageprofile= (ImageView) findViewById(R.id.imageprofile);

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

        Addpostbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddpostActivity();
            }
        });

        folowersbutton = (Button) findViewById(R.id.button);
        folowingbutton = (Button) findViewById(R.id.button2);
        folowersbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openfollowersActivity();
            }
        });
        folowingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openfollowingsActivity();
            }
        });
        addimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(ProfileActivity.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

        logout = (Button) findViewById(R.id.button3);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
                LoginActivity.log = false;
            }
        });

        userNameSurname = findViewById(R.id.namesurname);


        userNameSurname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditnameActivity();
            }
        });
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
//        super.onActivityResult(requestCode, resultCode, data);
//
//    }

    private void openAddpostActivity(){
        Intent intent = new Intent(this, AddpostActivity.class);
        startActivity(intent);
    }


    public void openHomeActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openfollowersActivity(){
        Intent intent = new Intent(this, followers.class);
        startActivity(intent);
    }
    public void openfollowingsActivity(){
        Intent intent = new Intent(this, followings.class);
        startActivity(intent);
    }
    public void openFavoritesActivity(){
        Intent intent = new Intent(this, FavoritesActivity.class);
        startActivity(intent);
    }
    public void openLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public void openEditnameActivity(){
        Intent intent = new Intent(this, EditnameActivity.class);
        startActivityForResult(intent, 1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri uri = data.getData();
        imageprofile.setImageURI(uri);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            // Receive new name and surname from EditProfileActivity
            String newName = data.getStringExtra("newName");
            String newSurname = data.getStringExtra("newSurname");

            // Set the text of the TextView with received data
            String fullName = newName + " " + newSurname;
            userNameSurname.setText(fullName);
        }
    }
}
