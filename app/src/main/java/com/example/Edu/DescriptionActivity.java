//package com.example.Edu;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.ImageButton;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//
//import java.lang.reflect.Type;
//import java.util.HashSet;
//import java.util.Set;
//
//public class DescriptionActivity extends AppCompatActivity {
//
//    private static final String TAG = "DescriptionActivity";
//    private ImageButton backButton;
//    private ImageButton likeButton;
//    private TextView titleTextView, placeTextView, dateTextView, descriptionTextView, typeTextView, linkTextView;
//    private boolean isLiked = false;
//    private User user;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.description);
//
//        backButton = findViewById(R.id.back);
//        likeButton = findViewById(R.id.likeButton);
//
//        backButton.setOnClickListener(v -> openMainActivity());
//
//        titleTextView = findViewById(R.id.tex);
//        placeTextView = findViewById(R.id.textView14);
//        dateTextView = findViewById(R.id.textView11);
//        descriptionTextView = findViewById(R.id.textView16);
//        typeTextView = findViewById(R.id.publisher);
//        linkTextView = findViewById(R.id.textView10);
//
//        user = (User) getIntent().getSerializableExtra("user");
//
//        if (user != null) {
//            titleTextView.setText(user.getTitleAuthor());
//            placeTextView.setText(user.getPlace());
//            dateTextView.setText(user.getDate());
//            descriptionTextView.setText(user.getDescription());
//            typeTextView.setText(user.getOfflineOnline());
//            linkTextView.setText(user.getLink());
//        }
//
////        likeButton.setOnClickListener(v -> {
////            isLiked = !isLiked;
////            updateLikeButton();
////            saveFavoriteStatus();
////        });
////
////        loadFavoriteStatus();
////        updateLikeButton();
//    }
//
//    private void openMainActivity() {
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//    }
//
////    private void updateLikeButton() {
////        if (isLiked) {
////            likeButton.setImageResource(R.drawable.fulllike);
////        } else {
////            likeButton.setImageResource(R.drawable.love);
////        }
////    }
//
////    private void saveFavoriteStatus() {
////        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
////        SharedPreferences.Editor editor = sharedPreferences.edit();
////        Gson gson = new Gson();
////        String json = sharedPreferences.getString("favoritePosts", null);
////        Type setType = new TypeToken<Set<User>>() {}.getType();
////        Set<User> favoritePostsSet = gson.fromJson(json, setType);
////
////        if (favoritePostsSet == null) {
////            favoritePostsSet = new HashSet<>();
////        }
////
////        if (isLiked) {
////            favoritePostsSet.add(user);
////        } else {
////            favoritePostsSet.remove(user);
////        }
////
////        editor.putString("favoritePosts", gson.toJson(favoritePostsSet));
////        editor.apply();
////        Log.d(TAG, "Saved favorite posts: " + gson.toJson(favoritePostsSet));
////    }
//
////    private void loadFavoriteStatus() {
////        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
////        Gson gson = new Gson();
////        String json = sharedPreferences.getString("favoritePosts", null);
////        Type setType = new TypeToken<Set<User>>() {}.getType();
////        Set<User> favoritePostsSet = gson.fromJson(json, setType);
////
////        if (favoritePostsSet == null) {
////            favoritePostsSet = new HashSet<>();
////        }
////
////        isLiked = favoritePostsSet.contains(user);
////        Log.d(TAG, "Loaded favorite posts: " + gson.toJson(favoritePostsSet));
////    }
//}
package com.example.Edu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DescriptionActivity extends AppCompatActivity {

    private ImageButton backButton;
    private TextView titleTextView, placeTextView, dateTextView, descriptionTextView, typeTextView, linkTextView;
    private User user;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description);

        backButton = findViewById(R.id.back);
        titleTextView = findViewById(R.id.tex);
        placeTextView = findViewById(R.id.textView14);
        dateTextView = findViewById(R.id.textView11);
        descriptionTextView = findViewById(R.id.textView16);
        typeTextView = findViewById(R.id.publisher);
        linkTextView = findViewById(R.id.textView10);
        imageView = findViewById(R.id.imageView6);

        backButton.setOnClickListener(v -> openMainActivity());

        user = (User) getIntent().getSerializableExtra("user");

        if (user != null) {
            titleTextView.setText(user.getTitleAuthor());
            placeTextView.setText(user.getPlace());
            dateTextView.setText(user.getDate());
            descriptionTextView.setText(user.getDescription());
            typeTextView.setText(user.getOfflineOnline());
            linkTextView.setText(user.getLink());

            if (user.getImageUrl() != null && !user.getImageUrl().isEmpty()) {
                Glide.with(this).load(user.getImageUrl()).into(imageView);
            } else {
                imageView.setImageResource(R.drawable.edunkar); // Replace with your default image
            }
        }
    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
