package com.royal;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GamePlayActivity extends AppCompatActivity {

    ImageButton imgBtn[] = new ImageButton[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game_play);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //bind
        imgBtn[0]  = findViewById(R.id.imgBtnGamePlay1);
        imgBtn[1]  = findViewById(R.id.imgBtnGamePlay2);
        imgBtn[2]  = findViewById(R.id.imgBtnGamePlay3);
        imgBtn[3]  = findViewById(R.id.imgBtnGamePlay4);
        imgBtn[4]  = findViewById(R.id.imgBtnGamePlay5);
        imgBtn[5]  = findViewById(R.id.imgBtnGamePlay6);
        imgBtn[6]  = findViewById(R.id.imgBtnGamePlay7);
        imgBtn[7]  = findViewById(R.id.imgBtnGamePlay8);
        imgBtn[8]  = findViewById(R.id.imgBtnGamePlay9);

        //0 5 6
        for(ImageButton btn : imgBtn){
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                        view.setBackground(getResources().getDrawable(R.drawable.guitar));
                        view.setBackground(getDrawable(R.drawable.guitar));
                 }
            });
        }


    }
}