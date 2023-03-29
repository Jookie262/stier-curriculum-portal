package com.example.stiercurriculumportal.ui.signup;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.stiercurriculumportal.data.model.Student;
import com.example.stiercurriculumportal.data.repo.StudentRepository;

public class SignupViewModel extends AndroidViewModel {

    private StudentRepository studentRepository;

    public SignupViewModel(@NonNull Application application) {
        super(application);
        studentRepository =  new StudentRepository(application);
    }

    public void insertStudent(Student student){
        studentRepository.insertStudent(student);
    }

    public void updateStudent(Student student){
        studentRepository.updateStudent(student);
    }

    public void deleteStudent(Student student){
        studentRepository.deleteStudent(student);
    }

    public boolean checkExistStudentId(int id){
        return studentRepository.checkExistStudentId(id);
    }

    public boolean checkExistUsername(String username){
        return studentRepository.checkExistUsername(username);
    }
}
