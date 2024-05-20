package com.example.Edu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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


    private Button Addpostbutton;
    private TextView userNameSurname;

    private ImageButton homebutton;
    private ImageButton favoritesbutton;
    private ImageButton searchbutton;

    private Button logout;

    private FloatingActionButton addimg;
    private ImageView imageprofile;
    private Uri profileImageUri;





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
//                FirebaseAuth.getInstance().signOut();
//                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                startActivity(intent);
//                finish();
//                LoginActivity.log = false;
                FirebaseAuth.getInstance().signOut();
                SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.PREFS_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(LoginActivity.KEY_LOGGED_IN, false);
                editor.apply();

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        userNameSurname = findViewById(R.id.namesurname);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        // Retrieve data from SharedPreferences
        String name = sharedPreferences.getString("name", "");
        String surname = sharedPreferences.getString("surname", "");
        String profileImageUriString = sharedPreferences.getString("profileImageUri", "");

        // Concatenate name and surname and set in TextView
        String fullName = name + " " + surname;
        userNameSurname.setText(fullName);

        if (!profileImageUriString.isEmpty()) {
            profileImageUri = Uri.parse(profileImageUriString);
            imageprofile.setImageURI(profileImageUri);
        }





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

       SharedPreferences.Editor editor = getSharedPreferences("MyPrefs", MODE_PRIVATE).edit();
        editor.putString("profileImageUri", uri.toString());
       editor.apply();

//        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
//            // Receive new name and surname from EditProfileActivity
//            String newName = data.getStringExtra("newName");
//            String newSurname = data.getStringExtra("newSurname");
//
//            // Set the text of the TextView with received data
//            String fullName = newName + " " + newSurname;
//            userNameSurname.setText(fullName);
//        }
    }
}
