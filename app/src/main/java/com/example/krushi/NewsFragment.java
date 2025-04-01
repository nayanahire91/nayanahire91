package com.example.krushi;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NewsFragment extends Fragment {

    public NewsFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        // ✅ पहिल्या इमेजसाठी क्लिक लिसनर
        ImageView newsImage1 = view.findViewById(R.id.img1);
        newsImage1.setOnClickListener(v -> openFullScreen(R.drawable.ic_news3));

        // ✅ दुसऱ्या इमेजसाठी क्लिक लिसनर
        ImageView newsImage2 = view.findViewById(R.id.img2);
        newsImage2.setOnClickListener(v -> openFullScreen(R.drawable.ic_news4));

        return view;
    }

    // 🖼️ फोटो फुलस्क्रीन मध्ये उघडण्यासाठी फंक्शन
    private void openFullScreen(int imageResId) {
        Intent intent = new Intent(getActivity(), ImageFullScreenActivity.class);
        intent.putExtra("imageResId", imageResId);
        startActivity(intent);
    }
}
