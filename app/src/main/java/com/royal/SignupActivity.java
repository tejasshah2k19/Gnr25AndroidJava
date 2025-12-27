package com.royal;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.royal.api.SessionService;
import com.royal.config.RetrofitClient;
import com.royal.model.UserModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignupActivity extends AppCompatActivity {


    TextInputLayout tilFirstName, tilEmail, tilPassword,tilLastName;
    TextInputEditText etFirstName, etEmail, etPassword,etLastName;
    MaterialButton btnSignup;

    TextView tvLoginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        tilFirstName = findViewById(R.id.tilFirstName);
        tilEmail = findViewById(R.id.tilEmail);
        tilPassword = findViewById(R.id.tilPassword);
        tilLastName = findViewById(R.id.tilLastName);

        etFirstName = findViewById(R.id.etFirstName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etLastName = findViewById(R.id.etLastName);

        btnSignup = findViewById(R.id.btnSignup);

        tvLoginLink = findViewById(R.id.tvSignupLoginLink);

        btnSignup.setOnClickListener(v -> validateAndSignup());

        tvLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void validateAndSignup() {
        tilFirstName.setError(null);
        tilEmail.setError(null);
        tilPassword.setError(null);
        tilLastName.setError(null);

        String firstName = etFirstName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String lastName = etLastName.getText().toString().trim();

        boolean isValid = true;

        if (TextUtils.isEmpty(firstName)) {
            tilFirstName.setError("First name required");
            isValid = false;
        }

        if (TextUtils.isEmpty(email)) {
            tilEmail.setError("Email required");
            isValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.setError("Enter valid email");
            isValid = false;
        }

        if (TextUtils.isEmpty(password)) {
            tilPassword.setError("Password required");
            isValid = false;
        } else if (password.length() < 6) {
            tilPassword.setError("Minimum 6 characters");
            isValid = false;
        }

        if (!isValid) return;

        // TODO: Call API / Firebase / Database here

    //rest api call

        //prepare your data
        UserModel userModel = new UserModel();
        userModel.setFirstName(firstName);
        userModel.setLastName(lastName);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setCredit(5000);// new user

        Retrofit retrofit = RetrofitClient.getRetrofit(); //retrofit

        SessionService sessionService = retrofit.create(SessionService.class);

        sessionService.signup(userModel).enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                Log.i("api","success");
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable throwable) {
                Log.i("api","fail");
            }
        });




        Toast.makeText(this,
                "Signup successful!\n" + firstName + " | " + email,
                Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);

    }
}