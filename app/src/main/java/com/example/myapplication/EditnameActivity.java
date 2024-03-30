package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

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

        MaterialButton submit = (MaterialButton) findViewById(R.id.namesubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = newNameEditText.getText().toString();
                String newSurname = newSurnameEditText.getText().toString();

                // Pass data back to Profile activity
                Intent intent = new Intent();
                intent.putExtra("newName", newName);
                intent.putExtra("newSurname", newSurname);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
