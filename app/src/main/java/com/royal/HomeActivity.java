package com.royal;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    TextView tvCredit;
    TextView tvUserName;

    //Button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //bind
        tvCredit = findViewById(R.id.tvHomeCredits);
        tvUserName = findViewById(R.id.tvHomeUserName);

        SharedPreferences preferences = getSharedPreferences("DIAMOND",MODE_PRIVATE);

        String firstName  = preferences.getString("firstName","DIAMOND");
        int credit  =preferences.getInt("credit",0);


        tvUserName.setText(firstName);
        tvCredit.setText(credit+"");

        //onClick

        //play -> intent -> GamePlay

    }
}