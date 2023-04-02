package com.example.stiercurriculumportal.ui.signup.fragments;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.stiercurriculumportal.R;
import com.example.stiercurriculumportal.data.model.Student;
import com.example.stiercurriculumportal.ui.login.LoginActivity;
import com.example.stiercurriculumportal.ui.signup.SignupViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class FirstSignupFragment extends Fragment {

    Button back_btn, next_btn;

    TextInputLayout student_id, first_name, middle_name, last_name;

    ImageView profile_picture;

    byte[] image_byte;

    Student student;

    SignupViewModel signupViewModel;


    public FirstSignupFragment(Student student, SignupViewModel signupViewModel){
        this.student = student;
        this.signupViewModel = signupViewModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first_signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        back_btn = view.findViewById(R.id.back_btn);
        next_btn = view.findViewById(R.id.next_btn);
        profile_picture = view.findViewById(R.id.profile_picture);
        student_id = view.findViewById(R.id.student_id);
        first_name = view.findViewById(R.id.first_name);
        middle_name = view.findViewById(R.id.middle_name);
        last_name = view.findViewById(R.id.last_name);

        getStudent();

        profile_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFromGallery();
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finish();
            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String get_student_id = student_id.getEditText().getText().toString().trim();
                String get_last_name = last_name.getEditText().getText().toString().trim();
                String get_first_name = first_name.getEditText().getText().toString().trim();
                String get_middle_name = middle_name.getEditText().getText().toString().trim();

                if(validateInput(get_student_id, get_last_name, get_first_name, get_middle_name)) {
                    setStudent(Integer.parseInt(get_student_id), get_last_name, get_first_name, get_middle_name, image_byte);
                    getFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations(R.anim.first_signup_fragment_enter, R.anim.first_signup_fragment_close)
                            .replace(R.id.signup_fragment, new SecondSignupFragment(student, signupViewModel))
                            .commit();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 2){
            if(data != null){
                Uri uri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                    ByteArrayOutputStream arrayInputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, arrayInputStream);
                    profile_picture.setImageBitmap(bitmap);
                    image_byte = arrayInputStream.toByteArray();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void getFromGallery(){
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        Uri downloadsURI = Uri.parse(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath());
        i.setDataAndType(downloadsURI, "image/*");
        startActivityForResult(Intent.createChooser(i, "Select image from downloads"),2);
    }

    public void setStudent(int studentid, String lastname, String firstname, String middlename, byte[] imagebyte){
        student.setStudentId(studentid);
        student.setLastName(lastname);
        student.setFirstName(firstname);
        student.setMiddleName(middlename);

        if(imagebyte != null){
            student.setImage(imagebyte);
        }
    }

    public void getStudent(){
        if(student != null){
            student_id.getEditText().setText(student.getStudentId() == 0 ? "" : String.valueOf(student.getStudentId()) );
            last_name.getEditText().setText(student.getLastName());
            first_name.getEditText().setText(student.getFirstName());
            middle_name.getEditText().setText(student.getMiddleName());

            if(student.getImage() != null){
                Bitmap bitmap = BitmapFactory.decodeByteArray(student.getImage(), 0, student.getImage().length);
                profile_picture.setImageBitmap(bitmap);
            }
        }
    }

    public boolean validateInput(String studentid, String lastname, String firstname, String middlename){
        boolean isValid = true;

        try {
            if (studentid.isEmpty()) {
                student_id.setError("Please input Student ID");
                isValid = false;
            } else if(studentid.length() != 11 && studentid.length() != 10){
                student_id.setError("Student ID should contains 10 numbers");
                isValid = false;
            } else if(signupViewModel.checkExistStudentId(Integer.parseInt(studentid))){
                student_id.setError("Student ID already exist");
                isValid = false;
            } else {
                student_id.setError(null);
            }
        } catch (Exception ex){
            student_id.setError("Student ID should contains 10 numbers");
        }

        if (lastname.isEmpty()) {
            last_name.setError("Please input Last Name");
            isValid = false;
        } else {
            last_name.setError(null);
        }

        if (firstname.isEmpty()) {
            first_name.setError("Please input First Name");
            isValid = false;
        } else {
            first_name.setError(null);
        }

        if (middlename.isEmpty()) {
            middle_name.setError("Please input Middle Name");
            isValid = false;
        } else {
            middle_name.setError(null);
        }

        return isValid;
    }
}