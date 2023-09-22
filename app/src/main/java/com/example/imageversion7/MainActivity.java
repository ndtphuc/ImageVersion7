package com.example.imageversion7;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private Button prevButton, nextButton;
    private TabLayout indicator;
    private int[] imageResources = {R.drawable.image1, R.drawable.image2, R.drawable.image3}; // Add more images as needed
    private int currentItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        prevButton = findViewById(R.id.prevButton);
        nextButton = findViewById(R.id.nextButton);
        indicator = findViewById(R.id.indicator);

        // Initialize ViewPager2 with image adapter
        ImageAdapter imageAdapter = new ImageAdapter(imageResources);
        viewPager.setAdapter(imageAdapter);

        // Set up CompositePageTransformer for image transitions
        CompositePageTransformer transformer = new CompositePageTransformer();
        transformer.addTransformer(new MarginPageTransformer(50));
        transformer.addTransformer(new ScalePageTransformer());
        viewPager.setPageTransformer(transformer);

        // Set up TabLayoutMediator for the indicator
        new TabLayoutMediator(indicator, viewPager, (tab, position) -> {
            // Optional: Customize tab text or icon here
        }).attach();

        // Button click listeners
        prevButton.setOnClickListener(view -> showPreviousImage());
        nextButton.setOnClickListener(view -> showNextImage());
    }

    private void showPreviousImage() {
        if (currentItem > 0) {
            currentItem--;
            viewPager.setCurrentItem(currentItem);
        }
    }

    private void showNextImage() {
        if (currentItem < imageResources.length - 1) {
            currentItem++;
            viewPager.setCurrentItem(currentItem);
        }
    }
}
