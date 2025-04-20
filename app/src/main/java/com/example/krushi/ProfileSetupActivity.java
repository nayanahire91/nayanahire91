package com.example.krushi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SignupActivity extends AppCompatActivity {

    // Fields from requirements
    private TextInputEditText etName, etAddress, etPincode;
    private Spinner spinnerMarkets, spinnerCrops;
    private ImageView ivProfileImage;
    private Button btnSignUp, btnUploadImage;

    private Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize views
        etName = findViewById(R.id.etName);
        etAddress = findViewById(R.id.etAddress);
        etPincode = findViewById(R.id.etPincode);
        spinnerMarkets = findViewById(R.id.spinnerMarkets);
        spinnerCrops = findViewById(R.id.spinnerCrops);
        ivProfileImage = findViewById(R.id.ivProfileImage);
        btnUploadImage = findViewById(R.id.btnUploadImage);
        btnSignUp = findViewById(R.id.btnSignUp);

        // Setup spinners
        setupSpinners();

        // Image upload
        btnUploadImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 1);
        });

        // Signup button
        btnSignUp.setOnClickListener(v -> {
            if (validateInputs()) {
                registerFarmer();
            }
        });
    }

    private void setupSpinners() {
        // Markets
        List<String> markets = new ArrayList<>();
        markets.add("Select Market");
        markets.add("Local APMC");
        markets.add("Regional Market");
        markets.add("National Market");

        ArrayAdapter<String> marketAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, markets);
        marketAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMarkets.setAdapter(marketAdapter);

        // Crops
        List<String> crops = new ArrayList<>();
        crops.add("Select Crops");
        crops.add("Wheat");
        crops.add("Rice");
        crops.add("Vegetables");
        crops.add("Fruits");

        ArrayAdapter<String> cropAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, crops);
        cropAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCrops.setAdapter(cropAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            ivProfileImage.setImageURI(selectedImageUri);
        }
    }

    private boolean validateInputs() {
        if (etName.getText().toString().trim().isEmpty()) {
            etName.setError("Name is required");
            return false;
        }

        if (etAddress.getText().toString().trim().isEmpty()) {
            etAddress.setError("Address is required");
            return false;
        }

        if (etPincode.getText().toString().trim().isEmpty()) {
            etPincode.setError("Pincode is required");
            return false;
        }

        if (spinnerMarkets.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Please select a market", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (spinnerCrops.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Please select crops", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (selectedImageUri == null) {
            Toast.makeText(this, "Please upload a profile image", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void registerFarmer() {
        // Get all values
        String name = etName.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        String pincode = etPincode.getText().toString().trim();
        String market = spinnerMarkets.getSelectedItem().toString();
        String crops = spinnerCrops.getSelectedItem().toString();

        // Here you would:
        // 1. Convert image to Base64 or upload to server
        // 2. Send data to your API
        // 3. Handle response

        Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show();
        finish();
    }
}