package com.example.Edu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements MyAdapter.OnItemClickListener
{

    private ImageButton searchbutton;
    private ImageButton favoritesbutton;
    private ImageButton profilebutton;

    private Button Addpostbutton;



    RecyclerView recyclerView;
    ArrayList<User> userArrayList;
    MyAdapter myAdapter;

    FirebaseFirestore db;



        @Override
    public void onItemClick(User user) {
        Intent intent = new Intent(MainActivity.this, DescriptionActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



       recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        db = FirebaseFirestore.getInstance();
        userArrayList = new ArrayList<User>();
        myAdapter = new MyAdapter(MainActivity.this, userArrayList, this);

        recyclerView.setAdapter(myAdapter);

       EventChangeListener();



//        ConstraintLayout constraintLayout = findViewById(R.id.example);
        androidx.constraintlayout.widget.ConstraintLayout constraintLayout = findViewById(R.id.example);



        favoritesbutton = (ImageButton) findViewById(R.id.imageButton3);
        profilebutton = (ImageButton) findViewById(R.id.imageButton4);


        Addpostbutton = (Button) findViewById(R.id.addpostbtn2);

        Addpostbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddpostActivity();
            }
        });

        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDescActivity();
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

//    @Override
//    public void onItemClick(User user) {
//        Intent intent = new Intent(MainActivity.this, DescriptionActivity.class);
//        intent.putExtra("user", user);
//        startActivity(intent);
//    }

    private void EventChangeListener() {
        db.collection("user")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error != null){
                            Log.e("Firestore error", error.getMessage());
                            return;
                        }

                        for (DocumentChange dc: value.getDocumentChanges()){

                            if(dc.getType() == DocumentChange.Type.ADDED)

                                userArrayList.add(dc.getDocument().toObject(User.class));

                        }

                        myAdapter.notifyDataSetChanged();
                    }
                });
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

    private void openAddpostActivity(){
        Intent intent = new Intent(this, AddpostActivity.class);
        startActivity(intent);
    }
}