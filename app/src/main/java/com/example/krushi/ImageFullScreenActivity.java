package com.example.krushi;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ImageFullScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_full_screen);

        // 🖼️ XML मधील ImageView ओळखणे
        ImageView fullImageView = findViewById(R.id.fullImageView);

        // 📥 Intent मधून फोटो मिळवणे
        int imageResId = getIntent().getIntExtra("imageResId", -1);
        if (imageResId != -1) {
            fullImageView.setImageResource(imageResId);
        }

        // 🔙 फोटो क्लिक केल्यावर Activity बंद करणे
        fullImageView.setOnClickListener(v -> finish());
    }
}
