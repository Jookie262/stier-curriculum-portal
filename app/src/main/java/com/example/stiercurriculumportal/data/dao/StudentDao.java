package com.example.stiercurriculumportal.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.stiercurriculumportal.data.model.Student;

@Dao
public interface StudentDao {

    @Insert
    void insert(Student student);

    @Update
    void update(Student student);

    @Delete
    void delete(Student student);

    @Query("SELECT EXISTS(SELECT * FROM student WHERE studentId = :id)")
    boolean checkExistStudentId(int id);

    @Query("SELECT EXISTS(SELECT * FROM student WHERE username = :username)")
    boolean checkExistUsername(String username);

    @Query("SELECT EXISTS(SELECT * FROM student WHERE username = :username AND password = :password)")
    boolean checkExistUsernamePassword(String username, String password);

    @Query("SELECT studentId FROM student WHERE  username = :username AND password = :password")
    int getStudentID(String username, String password);

    @Query("SELECT * FROM student WHERE studentId = :id")
    Student getOneStudent(int id);
}
