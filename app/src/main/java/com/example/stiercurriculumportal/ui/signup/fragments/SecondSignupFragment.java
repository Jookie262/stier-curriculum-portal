package com.example.stiercurriculumportal.ui.signup.fragments;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.stiercurriculumportal.R;
import com.example.stiercurriculumportal.data.model.Student;
import com.example.stiercurriculumportal.ui.signup.SignupViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecondSignupFragment extends Fragment {
    Button back_btn, next_btn;

    TextInputLayout term_and_year, program, username, password, confirm_password;

    AutoCompleteTextView term_and_year_auto, program_auto;

    Student student;

    SignupViewModel signupViewModel;

    public SecondSignupFragment(Student student, SignupViewModel signupViewModel){
        this.student = student;
        this.signupViewModel = signupViewModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second_signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        back_btn = view.findViewById(R.id.back_btn);
        next_btn = view.findViewById(R.id.next_btn);
        term_and_year = view.findViewById(R.id.term_and_year);
        term_and_year_auto = view.findViewById(R.id.term_and_year_auto);
        program = view.findViewById(R.id.program);
        program_auto = view.findViewById(R.id.program_auto);
        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        confirm_password = view.findViewById(R.id.confirm_password);

        String[] term_and_year_array = getResources().getStringArray(R.array.term_and_year);
        String[] program_array = getResources().getStringArray(R.array.program);

        getStudent();

        populateDropdownMenu(term_and_year_array, term_and_year_auto);
        populateDropdownMenu(program_array, program_auto);


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String get_term_and_year = term_and_year_auto.getText().toString().trim();
                String get_program = program_auto.getText().toString().trim();
                String get_username = username.getEditText().getText().toString().trim();
                String get_password = password.getEditText().getText().toString().trim();
                String get_confirm_password = confirm_password.getEditText().getText().toString().trim();

                setStudent(getCurriculumVersion(get_term_and_year, get_program), get_term_and_year, get_program, get_username, get_password, get_confirm_password);
                getFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.second_signup_fragment_enter, R.anim.second_signup_fragment_close)
                        .replace(R.id.signup_fragment, new FirstSignupFragment(student, signupViewModel))
                        .commit();
            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String get_term_and_year = term_and_year_auto.getText().toString().trim();
                String get_program = program_auto.getText().toString().trim();
                String get_username = username.getEditText().getText().toString().trim();
                String get_password = password.getEditText().getText().toString().trim();
                String get_confirm_password = confirm_password.getEditText().getText().toString().trim();

                setStudent(getCurriculumVersion(get_term_and_year, get_program), get_term_and_year, get_program, get_username, get_password, get_confirm_password);
                if(validateInput(get_term_and_year, get_program, get_username, get_password, get_confirm_password)){

                    try {
                        signupViewModel.insertStudent(student);

                        getFragmentManager()
                                .beginTransaction()
                                .setCustomAnimations(R.anim.first_signup_fragment_enter, R.anim.first_signup_fragment_close)
                                .replace(R.id.signup_fragment, new StatusSignupFragment("success","You have successfully sign up. You can now log in", student, signupViewModel))
                                .commit();
                    } catch (Exception ex) {
                        getFragmentManager()
                                .beginTransaction()
                                .setCustomAnimations(R.anim.first_signup_fragment_enter, R.anim.first_signup_fragment_close)
                                .replace(R.id.signup_fragment, new StatusSignupFragment("failed","Error in inserting student", student, signupViewModel))
                                .commit();
                    }

                }
            }
        });
    }

    private String getCurriculumVersion(String term_year_txt, String program_txt){
        Pattern compile = Pattern.compile("\\d{2}(?=\\D*$)");
        Matcher endMatcher = compile.matcher(term_year_txt);
        Pattern first_compile = Pattern.compile("\\d+");
        Matcher firstMatcher = first_compile.matcher(term_year_txt);

        if(endMatcher.find() && firstMatcher.find()){
            return program_txt + "-" + endMatcher.group() + "-0" + firstMatcher.group();
        }

        return program_txt;
    }

    public void setStudent(String curriculumversion, String termandyear, String programtext, String usernametext, String password, String confirm){
        student.setCurriculumVersion(curriculumversion);
        student.setTermAndYear(termandyear);
        student.setProgram(programtext);
        student.setUsername(usernametext);
        student.setPassword(password);
        student.setConfirmPassword(confirm);
    }

    public void getStudent(){
        term_and_year_auto.setText(student.getTermAndYear());
        program_auto.setText(student.getProgram());
        username.getEditText().setText(student.getUsername());
        password.getEditText().setText(student.getPassword());
        confirm_password.getEditText().setText(student.getConfirmPassword());
    }

    public void populateDropdownMenu(String[] items, AutoCompleteTextView autoCompleteTextView){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item, items);
        autoCompleteTextView.setAdapter(adapter);
    }

    public boolean validateInput(String termandyear, String programtext, String usernametext, String passwordtext, String confirmpasswordtext){
        boolean isValid = true;

        if(termandyear.isEmpty()){
            term_and_year.setError("Please select term and year");
            isValid = false;
        } else {
            term_and_year.setError(null);
        }

        if(programtext.isEmpty()){
            program.setError("Please select program");
            isValid = false;
        } else {
            program.setError(null);
        }

        if(usernametext.isEmpty()){
            username.setError("Please type username");
            isValid = false;
        } else if (signupViewModel.checkExistUsername(usernametext)) {
            username.setError("Username already exists");
            isValid = false;
        } else {
            username.setError(null);
        }

        if(passwordtext.isEmpty()){
            password.setError("Please type password");
            isValid = false;
        } else if (passwordtext.length() < 8) {
            password.setError("Password should be at least 8 characters");
            isValid = false;
        } else {
            password.setError(null);
        }

        if(confirmpasswordtext.isEmpty()){
            confirm_password.setError("Please type confirm password");
            isValid = false;
        } else if(!confirmpasswordtext.equals(passwordtext)){
            confirm_password.setError("Not same as password");
            isValid = false;
        } else {
            confirm_password.setError(null);
        }

        return isValid;
    }
}