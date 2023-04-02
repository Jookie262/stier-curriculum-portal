package com.example.stiercurriculumportal.ui.dashboard;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.stiercurriculumportal.data.model.Student;
import com.example.stiercurriculumportal.data.repo.StudentRepository;

public class DashboardViewModel extends AndroidViewModel {
    StudentRepository studentRepository;
    public DashboardViewModel(@NonNull Application application) {
        super(application);
        studentRepository = new StudentRepository(application);
    }

    public Student getOneStudent(int id){
        return studentRepository.getOneStudent(id);
    }
}
