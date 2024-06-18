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

import com.thltd.androidapp.R;
import com.thltd.androidapp.ui.base.UiState;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AuthActivity extends AppCompatActivity {
    private  AuthViewModel viewModel;
    private EditText edUsername;
    private EditText edPassword;
    private Button btnLogin;

    private void bindingView(){
        viewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);
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
        btnLogin.setOnClickListener(this::onBtnLoginClick);
    }

    private void onBtnLoginClick(View view) {
        viewModel.login(edUsername.getText().toString(), edPassword.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_auth);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bindingView();
        bindingAction();
    }
}