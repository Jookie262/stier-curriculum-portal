package com.example.stiercurriculumportal.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.stiercurriculumportal.R;
import com.example.stiercurriculumportal.ui.dashboard.DashboardActivity;
import com.example.stiercurriculumportal.ui.signup.SignupActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout username, password;
    private Button login_btn;
    private TextView signup_btn;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("STIEr Curriculum Portal: Log In");
        loginViewModel = new ViewModelProvider(this,(ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(LoginViewModel.class);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login_btn = findViewById(R.id.login_btn);
        signup_btn = findViewById(R.id.signup_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String get_username = username.getEditText().getText().toString().trim();
                String get_password = password.getEditText().getText().toString().trim();
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(LoginActivity.this);

                if(validateInput(get_username, get_password)){

                    if(loginViewModel.checkExistUsernamePassword(get_username, get_password)){
                        builder.setTitle("Success");
                        builder.setMessage("You have successfully login!");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                                finish();
                            }
                        });
                        builder.show();
                    } else {
                        builder.setTitle("Failed");
                        builder.setMessage("Wrong username or password");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        builder.show();
                    }
                }
            }
        });

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
                finish();
            }
        });
    }

    private boolean validateInput(String user_name, String pass_word){
        boolean isValid = true;

        if(user_name.isEmpty()){
            username.setError("Please type username");
            isValid = false;
        } else {
            username.setError(null);
        }

        if(pass_word.isEmpty()){
            password.setError("Please type password");
            isValid = false;
        } else {
            password.setError(null);
        }

        return isValid;
    }

}