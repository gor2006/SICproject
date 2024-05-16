package com.example.Edu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class FavoritesActivity extends AppCompatActivity {

    private static final String TAG = "FavoritesActivity";
    private ImageButton homeButton;
    private ImageButton profileButton;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        loadFavoritePosts();

        homeButton = findViewById(R.id.imageButton);
        profileButton = findViewById(R.id.imageButton4);

        homeButton.setOnClickListener(v -> openHomeActivity());
        profileButton.setOnClickListener(v -> openProfileActivity());
    }

    private void openHomeActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void openProfileActivity() {
        Intent intent;
        if (!LoginActivity.log) {
            intent = new Intent(this, LoginActivity.class);
        } else {
            intent = new Intent(this, ProfileActivity.class);
        }
        startActivity(intent);
    }

//    private void loadFavoritePosts() {
//        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//        Gson gson = new Gson();
//        String json = sharedPreferences.getString("favoritePosts", null);
//        Type setType = new TypeToken<Set<User>>() {}.getType();
//        Set<User> favoritePostsSet = gson.fromJson(json, setType);
//        if (favoritePostsSet == null) {
//            favoritePostsSet = new HashSet<>();
//        }
//        Log.d(TAG, "Loaded favorite posts: " + gson.toJson(favoritePostsSet));
//        FavoritesAdapter adapter = new FavoritesAdapter(this, favoritePostsSet);
//        recyclerView.setAdapter(adapter);
//    }
}
