package com.royal;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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


            }
        });

    }

    //


    //


    //
}