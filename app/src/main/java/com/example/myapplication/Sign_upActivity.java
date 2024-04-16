package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Sign_upActivity extends AppCompatActivity {

    EditText inpEmail, inpPassword, inpconfPassword;
    TextView haveacc;
    MaterialButton signupbtn;
    FirebaseAuth mAuth;
    ProgressBar progressBar;

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser != null) {
//            openHomeActivity();
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        signupbtn = findViewById(R.id.signupbtn);
        haveacc = findViewById(R.id.haveacc);
        inpEmail = findViewById(R.id.username);
        inpPassword = findViewById(R.id.password);
        inpconfPassword = findViewById(R.id.confpassword);
        progressBar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();
//        signupbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                progressBar.setVisibility(View.VISIBLE);
//                String email, password, confpassword;
//                email = String.valueOf(inpEmail.getText());
//                password = String.valueOf(inpPassword.getText());
//                confpassword = String.valueOf(inpconfPassword.getText());
//
//                if (TextUtils.isEmpty(email)) {
//                    Toast.makeText(Sign_upActivity.this, "Enter an email", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(password)) {
//                    Toast.makeText(Sign_upActivity.this, "Enter a password", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(confpassword)) {
//                    Toast.makeText(Sign_upActivity.this, "confirm your password", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (!password.equals(confpassword)) {
//                    Toast.makeText(Sign_upActivity.this, "passwords doesn't match", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                mAuth.createUserWithEmailAndPassword(email, password)
//                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                progressBar.setVisibility(View.GONE);
//                                if (task.isSuccessful()) {
//                                    Toast.makeText(Sign_upActivity.this, "Account created",
//                                            Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                                    startActivity(intent);
//                                    finish();
//                                } else {
//                                    Toast.makeText(Sign_upActivity.this, "Authentication failed",
//                                            Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//            }
//        });
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String email, password, confpassword;
                email = inpEmail.getText().toString().trim();
                password = inpPassword.getText().toString().trim();
                confpassword = inpconfPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confpassword)) {
                    Toast.makeText(Sign_upActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                if (!password.equals(confpassword)) {
                    Toast.makeText(Sign_upActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                // Create user with email and password
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    // Send email verification
                                    user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(Sign_upActivity.this, "Verification email sent", Toast.LENGTH_SHORT).show();
//                                                Intent intent = new Intent(Sign_upActivity.this, LoginActivity.class);
//                                                startActivity(intent);
//                                                finish();
                                                openLoginActivity();
                                            } else {
                                                Toast.makeText(Sign_upActivity.this, "Failed to send verification email", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                } else {
                                    Toast.makeText(Sign_upActivity.this, "Authentication failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        haveacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity();
            }
        });
    }

    public void openHomeActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
