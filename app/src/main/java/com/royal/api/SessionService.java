package com.royal.api;

import com.royal.model.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SessionService {

    //signup
    @POST("api/auth/signup")
    Call<UserModel> signup(@Body UserModel userModel);

    //login

    //forgetpassword

    //changepassword

}
