package com.royal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.royal.api.GameService;
import com.royal.config.RetrofitClient;
import com.royal.model.LoginResponseModel;
import com.royal.model.UserModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GamePlayActivity extends AppCompatActivity {

    ImageButton imgBtn[] = new ImageButton[9];
    int score = 100;
    TextView tvFirstName;
    TextView tvCredit;
    int roundCount =0;

    int betAmount = 100;

    String id;
    String token;

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
        tvFirstName = findViewById(R.id.tvGamePlayUserName);
        tvCredit = findViewById(R.id.tvGamePlayCredits);

        //set
        SharedPreferences preferences = getSharedPreferences("DIAMOND",MODE_PRIVATE);
        String firstName  = preferences.getString("firstName","DIAMOND");
        int credit  =preferences.getInt("credit",0);
        id = preferences.getString("id","");
        token = preferences.getString("token","");

        //betAmound
        score = betAmount;
        tvCredit.setText(score+"");
        tvFirstName.setText(firstName);


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
                               //loose
                               //subtract credit
                               Toast.makeText(getApplicationContext(),"Your Loose the Game Please Try Again !!! ",Toast.LENGTH_LONG).show();

                                updateCredit(betAmount*-1);
                               Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                               startActivity(intent);

                           }else{
                               view.setBackground(getResources().getDrawable(R.drawable.diamond));
                               //
                                //get credit from textView
                                score = score * 2; //100
                               // double it
                               //set credit to text view
                               tvCredit.setText(score+"");
                               //3 time
                               roundCount++;
                               //api -> credit -> 800
                               if(roundCount == 3){
                                   //declare you win and credit the score

                                   updateCredit(score);

                                   Toast.makeText(getApplicationContext(),"Congratulations you won"+score+" Credits",Toast.LENGTH_LONG).show();
                                   Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                                   startActivity(intent);
                               }

                           }
                 }
            });
        }


    }


    void updateCredit(int updateCredit){

        SharedPreferences preferences = getSharedPreferences("DIAMOND",MODE_PRIVATE);
        SharedPreferences.Editor editor =  preferences.edit();

        int credit = preferences.getInt("credit",0);

        credit = credit + updateCredit;

        editor.putInt("credit",credit);
        editor.apply();




        Retrofit retrofit = RetrofitClient.getRetrofit();
        GameService gameService = retrofit.create(GameService.class);
        UserModel user = new UserModel();
        user.setCredit(updateCredit);
        Call<LoginResponseModel> call =  gameService.creditUpadate(id,user,token);


        call.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                Log.i("api",response.message());
                Log.i("api","200");
                Log.i("api",response.toString());


            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable throwable) {
                Log.i("api","error");

                Log.i("api",throwable.toString());
            }
        });
    }

}