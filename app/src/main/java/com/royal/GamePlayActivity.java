package com.royal;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

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

        //textView bind

        //set

        //0 1 2 3 4 5 6 7 8
        //  X     X       X

        int bomb1 = (int)(Math.random()*9); //0.33658752159
        int bomb2 = (int)(Math.random()*9);
        int bomb3 = (int)(Math.random()*9);

        Log.i("bomb",bomb1+"");//1
        Log.i("bomb",bomb2+"");//4
        Log.i("bomb",bomb3+"");//5


        ArrayList<ImageButton> list = new ArrayList<>();
        list.add(imgBtn[bomb1]);
        list.add(imgBtn[bomb2]);
        list.add(imgBtn[bomb3]);

        for(ImageButton btn : imgBtn){

             btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                        view.setBackground(getResources().getDrawable(R.drawable.guitar));
                           if(list.contains(view)){
                               view.setBackground(getResources().getDrawable(R.drawable.bomb));
                           }else{
                               view.setBackground(getResources().getDrawable(R.drawable.diamond));
                               //
                           }
                 }
            });
        }


    }
}