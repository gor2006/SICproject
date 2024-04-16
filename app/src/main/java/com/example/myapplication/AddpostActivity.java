package com.example.myapplication;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.io.IOException;
import java.util.Calendar;

public class AddpostActivity extends AppCompatActivity {

    private ImageView profileImageView;
    private Button changeImageButton;
    private Button Add;

    private EditText editTextDate;


    private static final int PICK_IMAGE_REQUEST = 1;

    private static final int MAP_REQUEST_CODE = 101;

    EditText editText;
    PopupWindow popupWindow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addpost);

        editText = findViewById(R.id.offon);

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
            editText.setText(adapter.getItem(position).toString());
            popupWindow.dismiss();
        });

        // Ensure the ListView is focusable and clickable
        listView.setFocusable(true);
        listView.setClickable(true);

        popupWindow.setContentView(listView);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE)); // Set background color



        profileImageView = findViewById(R.id.imageView5);
        changeImageButton = (Button) findViewById(R.id.upload);
        Add = (Button) findViewById(R.id.add);

        editTextDate = findViewById(R.id.date);

        // Set OnClickListener to the EditText
        editTextDate.setOnClickListener(new View.OnClickListener() {
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

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfileActivity();
            }
        });
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
                                        editTextDate.setText(selectedDateTime);
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
        editText.getLocationOnScreen(location);
        popupWindow.showAtLocation(editText, Gravity.NO_GRAVITY, location[0], location[1] + editText.getHeight());
    }

    private void openProfileActivity(){
        Intent intent = new Intent(this, ProfileActivity.class);
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