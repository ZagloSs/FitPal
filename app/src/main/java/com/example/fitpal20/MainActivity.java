package com.example.fitpal20;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.fitpal20.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(new HomeFragment());

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.dark_gray));
        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.dark_gray));

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(getIntent().getExtras() != null){
            String profile = getIntent().getStringExtra("profile");
            if(profile.equals("yes")){
                profile();
            }
        }




        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            if(item.getItemId() == R.id.homeBtn) {
                replaceFragment(new HomeFragment());
            }else if(item.getItemId() == R.id.rutineBtn) {
                replaceFragment(new RutinasFragment());
            }else if(item.getItemId() == R.id.historyBtn) {
                replaceFragment(new SeeRutineExercises());
            }else if(item.getItemId() == R.id.profileBtn) {
                replaceFragment(new ProfileFrgment.ProfileFragment());
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, fragment);
        ft.commit();
    }

    public void profile(){
        replaceFragment(new ProfileFrgment.ProfileFragment());
    }

}
