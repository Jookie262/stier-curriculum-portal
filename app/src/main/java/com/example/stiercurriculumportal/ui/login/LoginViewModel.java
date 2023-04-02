package com.example.stiercurriculumportal.ui.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.stiercurriculumportal.data.repo.StudentRepository;

public class LoginViewModel extends AndroidViewModel {

    private StudentRepository studentRepository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        studentRepository = new StudentRepository(application);
    }

    public boolean checkExistUsernamePassword(String username, String password){
        return studentRepository.checkExistUsernamePassword(username, password);
    }

    public int getStudentID(String username, String password){
        return studentRepository.getStudentID(username, password);
    }

}
