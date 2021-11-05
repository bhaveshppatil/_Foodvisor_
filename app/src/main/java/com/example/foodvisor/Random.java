package com.example.foodvisor;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.foodvisor.HomeScreenFragments.AddFoodFragment;
import com.example.foodvisor.HomeScreenFragments.DietsFragment;
import com.example.foodvisor.HomeScreenFragments.HomeFragment;
import com.example.foodvisor.HomeScreenFragments.ProgressFragment;
import com.example.foodvisor.HomeScreenFragments.RecipeFragment;

public class Random extends AppCompatActivity {

    Fragment fragment = null;
    MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

    }
}