//package com.example.Edu;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.ImageButton;
//
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//
//import java.lang.reflect.Type;
//import java.util.HashSet;
//import java.util.Set;
//
//public class FavoritesActivity extends AppCompatActivity {
//
//    private static final String TAG = "FavoritesActivity";
//    private ImageButton homeButton;
//    private ImageButton profileButton;
//
//    private RecyclerView recyclerView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.favorites);
//
//        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
////        loadFavoritePosts();
//
//        homeButton = findViewById(R.id.imageButton);
//        profileButton = findViewById(R.id.imageButton4);
//
//        homeButton.setOnClickListener(v -> openHomeActivity());
//        profileButton.setOnClickListener(v -> openProfileActivity());
//    }
//
//    private void openHomeActivity() {
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//    }
//
//    private void openProfileActivity() {
//        Intent intent;
//        if (!LoginActivity.log) {
//            intent = new Intent(this, LoginActivity.class);
//        } else {
//            intent = new Intent(this, ProfileActivity.class);
//        }
//        startActivity(intent);
//    }
//
////    private void loadFavoritePosts() {
////        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
////        Gson gson = new Gson();
////        String json = sharedPreferences.getString("favoritePosts", null);
////        Type setType = new TypeToken<Set<User>>() {}.getType();
////        Set<User> favoritePostsSet = gson.fromJson(json, setType);
////        if (favoritePostsSet == null) {
////            favoritePostsSet = new HashSet<>();
////        }
////        Log.d(TAG, "Loaded favorite posts: " + gson.toJson(favoritePostsSet));
////        FavoritesAdapter adapter = new FavoritesAdapter(this, favoritePostsSet);
////        recyclerView.setAdapter(adapter);
////    }
//}

package com.example.Edu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Type;
import java.util.ArrayList;

import java.util.List;

public class FavoritesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<User> userArrayList;
    MyAdapter myAdapter;
    FirebaseFirestore db;
    private ImageButton homeButton;
    private ImageButton profileButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites);

        recyclerView = findViewById(R.id.recyclerViewFavorites);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        homeButton = findViewById(R.id.imageButton);
        profileButton = findViewById(R.id.imageButton4);

        homeButton.setOnClickListener(v -> openHomeActivity());
        profileButton.setOnClickListener(v -> openProfileActivity());

        db = FirebaseFirestore.getInstance();
        userArrayList = new ArrayList<>();
        myAdapter = new MyAdapter(FavoritesActivity.this, userArrayList, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(User user) {
                Intent intent = new Intent(FavoritesActivity.this, DescriptionActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(myAdapter);

        fetchLikedPosts();
    }

    private void fetchLikedPosts() {
        db.collection("user").whereEqualTo("liked", "yes")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Log.e("Firestore error", error.getMessage());
                            return;
                        }

                        for (DocumentChange dc : value.getDocumentChanges()) {
                            if (dc.getType() == DocumentChange.Type.ADDED) {
                                User user = dc.getDocument().toObject(User.class);
                                user.setId(dc.getDocument().getId());  // Set the document ID
                                userArrayList.add(user);
                            }
                        }

                        myAdapter.notifyDataSetChanged();
                    }
                });
    }

    private void openHomeActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void openProfileActivity() {
//        Intent intent;
//        if (!LoginActivity.log) {
//            intent = new Intent(this, LoginActivity.class);
//        } else {
//            intent = new Intent(this, ProfileActivity.class);
//        }
//        startActivity(intent);
        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.PREFS_NAME, MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean(LoginActivity.KEY_LOGGED_IN, false);

        Intent intent;
        if (!isLoggedIn) {
            intent = new Intent(this, LoginActivity.class);
        } else {
            intent = new Intent(this, ProfileActivity.class);
        }
        startActivity(intent);
    }
}


//public class FavoritesActivity extends AppCompatActivity {
//
//    private static final String TAG = "FavoritesActivity";

//    private RecyclerView recyclerView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.favorites);
//
//        recyclerView = findViewById(R.id.recyclerVieww);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        List<User> favoritePosts = loadFavoritePosts();
//
//        FavoritesAdapter adapter = new FavoritesAdapter(this, favoritePosts);
//        recyclerView.setAdapter(adapter);
//
//        homeButton = findViewById(R.id.imageButton);
//        profileButton = findViewById(R.id.imageButton4);
//
//        homeButton.setOnClickListener(v -> openHomeActivity());
//        profileButton.setOnClickListener(v -> openProfileActivity());
//    }
//

//
//    private List<User> loadFavoritePosts() {
//        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//        Gson gson = new Gson();
//        String json = sharedPreferences.getString("favoritePosts", null);
//        Type listType = new TypeToken<List<User>>() {}.getType();
//        List<User> favoritePostsList = gson.fromJson(json, listType);
//
//        if (favoritePostsList == null) {
//            favoritePostsList = new ArrayList<>();
//        }
//        Log.d(TAG, "Loaded favorite posts: " + gson.toJson(favoritePostsList));
//        return favoritePostsList;
//    }
//}


