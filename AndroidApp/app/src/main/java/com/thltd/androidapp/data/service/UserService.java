package com.thltd.androidapp.data.service;

import com.thltd.androidapp.data.api.UserRepository;
import com.thltd.androidapp.data.dto.login.LoginRequest;
import com.thltd.androidapp.data.dto.login.LoginResponse;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;

public class UserService {
    private final UserRepository userRepository;
    @Inject
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public Single<Response<LoginResponse>> login(LoginRequest loginRequest){
        return userRepository.login(loginRequest);
    }

}
