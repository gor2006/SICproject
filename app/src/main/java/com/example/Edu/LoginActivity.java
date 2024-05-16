package com.example.Edu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    public static boolean log = false;
    MaterialButton signupbtn;
    MaterialButton loginbtn;
    EditText inpEmailLog;
    EditText inpPasswordLog;
    FirebaseAuth mAuth;

    TextView forgotpass;
    boolean passwordvisible;



//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
//            openSignupActivity();
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        inpEmailLog =  findViewById(R.id.username);
        inpPasswordLog =findViewById(R.id.password);

        inpPasswordLog.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right = 2;
                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(event.getRawX()>=inpPasswordLog.getRight()- inpPasswordLog.getCompoundDrawables()[Right].getBounds().width()){
                        int selection = inpPasswordLog.getSelectionEnd();
                        if(passwordvisible){
                            inpPasswordLog.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.unvisible,0);
                            inpPasswordLog.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordvisible=false;
                        }else{
                            inpPasswordLog.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.visible,0);
                            inpPasswordLog.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordvisible=true;
                        }
                        inpPasswordLog.setSelection(selection);
                        return true;
                    }
                }


                return false;
            }
        });

        signupbtn = findViewById(R.id.signupbtn);
        loginbtn = findViewById(R.id.login);


        mAuth = FirebaseAuth.getInstance();

        forgotpass = findViewById(R.id.forgotpass);

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ResetpassActivity.class);
                startActivity(intent);
                finish();
            }
        });


//        loginbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String email, password;
//                email = String.valueOf(inpEmailLog.getText());
//                password = String.valueOf(inpPasswordLog.getText());
//
//                if(TextUtils.isEmpty(email)){
//                    Toast.makeText(LoginActivity.this, "Enter an email", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if(TextUtils.isEmpty(password)){
//                    Toast.makeText(LoginActivity.this, "Enter a password", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                mAuth.signInWithEmailAndPassword(email, password)
//                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if (task.isSuccessful()) {
//                                    Toast.makeText(LoginActivity.this, "Login successful",
//                                            Toast.LENGTH_SHORT).show();
//                                    openProfileActivity();
//
//                                } else {
//                                    Toast.makeText(LoginActivity.this, "Authentication failed.",
//                                            Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//            }
//        });
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;
                email = inpEmailLog.getText().toString().trim();
                password = inpPasswordLog.getText().toString().trim();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Sign in user with email and password
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    if (user.isEmailVerified()) {
                                        Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                                        log = true;
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                   } else {
                                        Toast.makeText(LoginActivity.this, "Please verify your email", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(LoginActivity.this, "Authentication failed: ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
//                Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
//                startActivity(intent);
//                finish();
            }

        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignupActivity();
            }
        });


    }

    public void openProfileActivity(){
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(intent);
        finish();
    }
    public void openSignupActivity(){
        Intent intent = new Intent(this, Sign_upActivity.class);
        startActivity(intent);
    }

}