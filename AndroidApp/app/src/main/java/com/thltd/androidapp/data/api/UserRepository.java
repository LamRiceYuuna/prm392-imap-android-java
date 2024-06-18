package com.thltd.androidapp.data.api;

import com.thltd.androidapp.data.dto.login.LoginRequest;
import com.thltd.androidapp.data.dto.login.LoginResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserRepository {
    @Headers("Content-Type: application/json")
    @POST("api/v1/user/login")
    Single<Response<LoginResponse>> login(@Body LoginRequest loginRequest);
}
