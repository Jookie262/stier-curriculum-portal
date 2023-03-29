package com.example.stiercurriculumportal.ui.signup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.stiercurriculumportal.R;
import com.example.stiercurriculumportal.data.model.Student;
import com.example.stiercurriculumportal.ui.signup.fragments.FirstSignupFragment;

public class SignupActivity extends AppCompatActivity {

    Student student;
    SignupViewModel signupViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        student = new Student();
        signupViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(SignupViewModel.class);

        getSupportActionBar().setTitle("STIEr Curriculum Portal: Sign Up");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.signup_fragment, new FirstSignupFragment(student, signupViewModel)).commit();
    }
}