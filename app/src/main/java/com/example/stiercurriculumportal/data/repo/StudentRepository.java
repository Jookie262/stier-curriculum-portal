package com.example.stiercurriculumportal.data.repo;

import android.app.Application;
import android.os.AsyncTask;

import com.example.stiercurriculumportal.data.dao.StudentDao;
import com.example.stiercurriculumportal.data.database.Database;
import com.example.stiercurriculumportal.data.model.Student;

import java.util.concurrent.ExecutionException;

public class StudentRepository {
    private StudentDao studentDao;

    public StudentRepository(Application application){
        Database database = Database.getInstance(application);
        studentDao = database.studentDao();
    }

    public void insertStudent(Student student){
        new InsertStudentAsync(studentDao).execute(student);
    }

    public void updateStudent(Student student){
        new UpdateStudentAsync(studentDao).execute(student);
    }

    public void deleteStudent(Student student){
        new DeleteStudentAsync(studentDao).execute(student);
    }

    public int getStudentID(String username, String password){
        try {
            return new GetStudentIDAsync(studentDao).execute(username, password).get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Student getOneStudent(int id){
        try {
            return new GetOneStudentAsync(studentDao).execute(id).get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkExistStudentId(int id){
        try {
            return new CheckExistStudentIdAsync(studentDao).execute(id).get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkExistUsername(String username){
        try {
            return new CheckExistUsernameAsync(studentDao).execute(username).get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkExistUsernamePassword(String username, String password){
        try {
            return new CheckExistUsernamePasswordAsync(studentDao).execute(username, password).get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public class InsertStudentAsync extends AsyncTask<Student, Void, Void>{
        StudentDao studentDao;

        public InsertStudentAsync(StudentDao studentDao){
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.insert(students[0]);
            return null;
        }
    }

    public class UpdateStudentAsync extends AsyncTask<Student, Void, Void>{
        StudentDao studentDao;

        public UpdateStudentAsync(StudentDao studentDao){
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.update(students[0]);
            return null;
        }
    }

    public class DeleteStudentAsync extends AsyncTask<Student, Void, Void>{
        StudentDao studentDao;

        public DeleteStudentAsync(StudentDao studentDao){
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.delete(students[0]);
            return null;
        }
    }

    public class CheckExistStudentIdAsync extends AsyncTask<Integer, Void, Boolean>{
        StudentDao studentDao;

        public CheckExistStudentIdAsync(StudentDao studentDao){
            this.studentDao = studentDao;
        }

        @Override
        protected Boolean doInBackground(Integer... integers) {
           return studentDao.checkExistStudentId(integers[0]);
        }
    }

    public class CheckExistUsernameAsync extends AsyncTask<String, Void, Boolean>{
        StudentDao studentDao;

        public CheckExistUsernameAsync(StudentDao studentDao){
            this.studentDao = studentDao;
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            return studentDao.checkExistUsername(strings[0]);
        }
    }

    public class CheckExistUsernamePasswordAsync extends AsyncTask<String, Void, Boolean>{
        StudentDao studentDao;

        public CheckExistUsernamePasswordAsync(StudentDao studentDao){
            this.studentDao = studentDao;
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            return studentDao.checkExistUsernamePassword(strings[0], strings[1]);
        }
    }

    public class GetOneStudentAsync extends AsyncTask<Integer, Void, Student>{
        StudentDao studentDao;

        public GetOneStudentAsync(StudentDao studentDao){
            this.studentDao = studentDao;
        }

        @Override
        protected Student doInBackground(Integer... integers) {
            return studentDao.getOneStudent(integers[0]);
        }
    }

    public class GetStudentIDAsync extends AsyncTask<String, Void, Integer>{
        StudentDao studentDao;

        public GetStudentIDAsync(StudentDao studentDao){
            this.studentDao = studentDao;
        }

        @Override
        protected Integer doInBackground(String... strings) {
            return studentDao.getStudentID(strings[0], strings[1]);
        }
    }
}
