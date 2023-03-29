package com.example.stiercurriculumportal.data.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "student")
public class Student {
    @PrimaryKey
    private int studentId;
    private String lastName;
    private String firstName;
    private String middleName;
    private String curriculumVersion;
    private String termAndYear;
    private String program;
    private String username;
    private String password;

    @Ignore
    private String confirmPassword;

    public Student(){}

    public Student(int studentId, String lastName, String firstName, String middleName, String curriculumVersion, String termAndYear, String program, String username, String password) {
        this.studentId = studentId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.curriculumVersion = curriculumVersion;
        this.termAndYear = termAndYear;
        this.program = program;
        this.username = username;
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setCurriculumVersion(String curriculumVersion) {
        this.curriculumVersion = curriculumVersion;
    }

    public void setTermAndYear(String termAndYear) {
        this.termAndYear = termAndYear;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getCurriculumVersion() {
        return curriculumVersion;
    }

    public String getTermAndYear() {
        return termAndYear;
    }

    public String getProgram() {
        return program;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
