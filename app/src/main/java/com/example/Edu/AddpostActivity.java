package com.example.Edu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddpostActivity extends AppCompatActivity {

    private ImageView profileImageView;
    private Button changeImageButton;
    private Button Add;
    private EditText date;
    private EditText offonline;
    private EditText title;
    private EditText link;
    private EditText description;
    private EditText place;

    FirebaseFirestore db;
    FirebaseStorage storage;
    StorageReference storageReference;

    private Uri imageUri;
    private static final int PICK_IMAGE_REQUEST = 1;

    private static final int MAP_REQUEST_CODE = 101;


    PopupWindow popupWindow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addpost);

        db=FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        title = findViewById(R.id.username2);
        date = findViewById(R.id.date);
        offonline = findViewById(R.id.offon);
        description = findViewById(R.id.username5);
        link = findViewById(R.id.username8);
        place = findViewById(R.id.locationTextView);
        profileImageView = findViewById(R.id.imageView5);
        changeImageButton = (Button) findViewById(R.id.upload);
        Add = (Button) findViewById(R.id.add);

//        Add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String Title = title.getText().toString();
//                String Date = date.getText().toString();
//                String Offonline = offonline.getText().toString();
//                String Description = description.getText().toString();
//                String Link = link.getText().toString();
//                String Place = place.getText().toString();
//                String liked = "no";
//                Map<String, Object> user = new HashMap<>();
//                user.put("titleAuthor", Title);
//                user.put("date", Date);
//                user.put("offlineOnline", Offonline);
//                user.put("description", Description);
//                user.put("link", Link);
//                user.put("place", Place);
//                user.put("liked", liked);
//
//
//                db.collection("user")
//                        .add(user)
//                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                            @Override
//                            public void onSuccess(DocumentReference documentReference) {
//                                Toast.makeText(AddpostActivity.this, "Successful", Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(AddpostActivity.this, "Failed", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//
//
//                openProfileActivity();
//            }
//        });



        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
                openProfileActivity();
            }
        });


        popupWindow = new PopupWindow(this);
        popupWindow.setFocusable(true);
        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.options_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ListView listView = new ListView(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            offonline.setText(adapter.getItem(position).toString());
            popupWindow.dismiss();
        });

        // Ensure the ListView is focusable and clickable
        listView.setFocusable(true);
        listView.setClickable(true);

        popupWindow.setContentView(listView);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE)); // Set background color





        // Set OnClickListener to the EditText
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePickerDialog();
            }
        });

        changeImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });


    }

    private void savePostData(String imageUrl) {
        String Title = title.getText().toString();
        String Date = date.getText().toString();
        String Offonline = offonline.getText().toString();
        String Description = description.getText().toString();
        String Link = link.getText().toString();
        String Place = place.getText().toString();
        String liked = "no";

        Map<String, Object> user = new HashMap<>();
        user.put("titleAuthor", Title);
        user.put("date", Date);
        user.put("offlineOnline", Offonline);
        user.put("description", Description);
        user.put("link", Link);
        user.put("place", Place);
        user.put("liked", liked);
        if (imageUrl != null) {
            user.put("imageUrl", imageUrl);
        }

        db.collection("user")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(AddpostActivity.this, "Post added successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddpostActivity.this, "Failed to add post", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void uploadImage() {
        if (imageUri != null) {
            StorageReference ref = storageReference.child("images/" + System.currentTimeMillis());
            ref.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    savePostData(uri.toString());
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddpostActivity.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            savePostData(null);
        }
    }

    private void showDateTimePickerDialog() {
        // Get current date and time
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Create and show DatePickerDialog for date selection
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Set selected date to Calendar instance
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        // Create and show TimePickerDialog for time selection
                        TimePickerDialog timePickerDialog = new TimePickerDialog(
                                AddpostActivity.this,
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                        // Set selected time to Calendar instance
                                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                        calendar.set(Calendar.MINUTE, minute);

                                        // Set selected date and time to the EditText
                                        String selectedDateTime = calendar.getTime().toString();
                                        date.setText(selectedDateTime);
                                    }
                                },
                                hourOfDay, minute, true
                        );
                        timePickerDialog.show();
                    }
                },
                year, month, dayOfMonth
        );
        datePickerDialog.show();
    }

    public void showOptionsDialog(View view) {
        int[] location = new int[2];
        offonline.getLocationOnScreen(location);
        popupWindow.showAtLocation(offonline, Gravity.NO_GRAVITY, location[0], location[1] + offonline.getHeight());
    }

    private void openProfileActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                profileImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}