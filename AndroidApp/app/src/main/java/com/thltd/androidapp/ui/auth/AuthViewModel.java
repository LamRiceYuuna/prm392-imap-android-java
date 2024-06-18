package com.thltd.androidapp.ui.auth;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.thltd.androidapp.data.dto.login.LoginRequest;
import com.thltd.androidapp.data.dto.login.LoginResponse;
import com.thltd.androidapp.data.repository.UserService;
import com.thltd.androidapp.ui.base.UiState;

import java.util.function.Function;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Response;

@HiltViewModel
public class AuthViewModel extends ViewModel {
    private final UserService userService;
    private Disposable disposable;
    @Inject
    public AuthViewModel(UserService userService){
        this.userService = userService;
    }
    private MutableLiveData<UiState> uiState = new MutableLiveData<>();

    public LiveData<UiState> getUiState() {
        return uiState;
    }

    public void login(String username, String password){
        uiState.setValue(UiState.loading());
        disposable = userService.login(new LoginRequest(username, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::loginHandlerSuccess,
                        this::loginHandlerError
                );
    }

    private void loginHandlerError(Throwable throwable) {
        uiState.postValue(UiState.error(throwable));
    }

    private void loginHandlerSuccess(Response<LoginResponse> loginResponse) {
        if(loginResponse.isSuccessful() && loginResponse.body() != null){
            var rs = loginResponse.body();
            uiState.postValue(UiState.success(rs));
        }else{
            uiState.postValue(UiState.error(new Throwable(loginResponse.message())));
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if(disposable != null){
            disposable.dispose();
        }
    }
}
