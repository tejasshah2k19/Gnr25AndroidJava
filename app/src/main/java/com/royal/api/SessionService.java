package com.royal.api;

import com.royal.model.LoginResponseModel;
import com.royal.model.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SessionService {

    //signup
    @POST("api/auth/signup")
    Call<UserModel> signup(@Body UserModel userModel);

    //login
    @POST("api/auth/login")
    Call<LoginResponseModel> login(@Body UserModel userModel);

    //forgetpassword

    //changepassword



}
