package com.example.Edu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
//import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

//public class EditnameActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.editname);
//
//        TextView Name = (TextView) findViewById(R.id.name);
//        MaterialButton submit = (MaterialButton) findViewById(R.id.namesubmit);
//        TextView fieldname = (TextView) findViewById(R.id.namesurname);
//
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fieldname.setText(String.valueOf(Name.getText().toString()));
//            }
//        });
//
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openProfileActivity();
//            }
//        });
//
//
//
//    }
//    public void openProfileActivity(){
//        Intent intent = new Intent(this, ProfileActivity.class);
//        startActivity(intent);
//    }
//}

public class EditnameActivity extends AppCompatActivity {

    private TextView newNameEditText;
    private TextView newSurnameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editname);

        newNameEditText = findViewById(R.id.name);
        newSurnameEditText = findViewById(R.id.surname);

        // Load data from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String savedName = sharedPreferences.getString("name", "");
        String savedSurname = sharedPreferences.getString("surname", "");

        newNameEditText.setText(savedName);
        newSurnameEditText.setText(savedSurname);

        MaterialButton submit = (MaterialButton) findViewById(R.id.namesubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = newNameEditText.getText().toString();
                String newSurname = newSurnameEditText.getText().toString();

                // Save data to SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", newName);
                editor.putString("surname", newSurname);
                editor.apply();

                Intent intent = new Intent(EditnameActivity.this, ProfileActivity.class);
                startActivity(intent);




//                HashMap<String, Object> hashMap = new HashMap<>();
//                hashMap.put("name", newName);
//                hashMap.put("surname", newSurname);

//                FirebaseFirestore.getInstance().collection("User")
//                        .document("Userdata").set(hashMap)
//                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void unused) {
//                                Toast.makeText(EditnameActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(EditnameActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        });

//                // Pass data back to Profile activity
//                Intent intent = new Intent();
//                intent.putExtra("newName", newName);
//                intent.putExtra("newSurname", newSurname);
//                setResult(RESULT_OK, intent);
//                finish();
            }
        });
    }
}
