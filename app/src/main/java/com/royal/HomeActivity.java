package com.royal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    TextView tvCredit;
    TextView tvUserName;

    Button btnPlay,btnLeaderboard,btnLogout;


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
        btnPlay = findViewById(R.id.btnHomePlay);
        btnLeaderboard = findViewById(R.id.btnHomeLeaderboard);
        btnLogout = findViewById(R.id.btnHomeLogout);

        SharedPreferences preferences = getSharedPreferences("DIAMOND",MODE_PRIVATE);

        String firstName  = preferences.getString("firstName","DIAMOND");
        int credit  =preferences.getInt("credit",0);


        tvUserName.setText(firstName);
        tvCredit.setText(credit+"");

        //onClick

        //play -> intent -> GamePlay

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),GamePlayActivity.class);
                startActivity(intent);
            }
        });

    }
}