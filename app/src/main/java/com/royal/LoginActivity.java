package com.royal;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.royal.api.SessionService;
import com.royal.config.RetrofitClient;
import com.royal.model.LoginResponseModel;
import com.royal.model.UserModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    //instance
    EditText edtEmail ;
    EditText edtPassword;
    Button btnLogin;

    TextView tvShowEmail;
    TextView tvShowPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //binding
        edtEmail = findViewById(R.id.edtLoginEmail);
        edtPassword =findViewById(R.id.edtLoginPassword);
        btnLogin = findViewById(R.id.btnLoginButton);
        tvShowEmail = findViewById(R.id.tvLoginShowEmail);
        tvShowPassword = findViewById(R.id.tvLoginShowPassword);

        //click event
        //add listener

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                Log.i("LoginActivity","email => "+email);
                Log.i("LoginActivity","password => "+password);

                tvShowEmail.setText("Email => "+email);
                tvShowPassword.setText("Password => "+password);


                //api

                UserModel userModel = new UserModel();
                userModel.setEmail(email);
                userModel.setPassword(password);
                Retrofit retrofit = RetrofitClient.getRetrofit();
                SessionService sessionService = retrofit.create(SessionService.class);

                sessionService.login(userModel).enqueue(new Callback<LoginResponseModel>() {
                    @Override
                    public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {


                        Log.i("login api",response.toString());

                        if(response.body() == null){
                            Log.i("login api", "Invalid Credentials...");

                        }else {
                            Log.i("login api", response.body().getMessage());
                            Log.i("login api", response.body().getToken());
                        }


//                        Toast.makeText(getApplicationContext(),"Login done",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<LoginResponseModel> call, Throwable throwable) {
                        Toast.makeText(getApplicationContext(),"PTA",Toast.LENGTH_LONG).show();

                    }
                });
            }
        });

    }

    //


    //


    //
}