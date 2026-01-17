package com.royal.api;

import com.royal.model.LoginResponseModel;
import com.royal.model.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface GameService {

   @PUT("api/users/credit/{id}")
   Call<LoginResponseModel> creditUpadate(@Path("id") String id, @Body UserModel credit, @Header("Authorization") String token);


}
