package com.thltd.androidapp.ui.auth;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.thltd.androidapp.ProfileFragment;
import com.thltd.androidapp.R;
import com.thltd.androidapp.databinding.ActivityAuthBinding;
import com.thltd.androidapp.ui.base.UiState;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AuthActivity extends AppCompatActivity {
    private  AuthViewModel viewModel;
    private ActivityAuthBinding binding;
    private void bindingView(){
        viewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        binding = ActivityAuthBinding.inflate(getLayoutInflater());
    }
    private void bindingAction(){
        viewModel.getUiState().observe(this, uiState -> {
            switch (uiState.getStatus()){
                case LOADING -> {
                    Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show();
                }
                case SUCCESS -> {
                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                }
                case ERROR -> {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.btnLogin.setOnClickListener(this::onBtnLoginClick);
    }

    private void onBtnLoginClick(View view) {
        viewModel.login(binding.edUsername.getText().toString(), binding.edPassword.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        bindingView();
        setContentView(binding.getRoot());
        bindingAction();
    }
}