package com.example.stiercurriculumportal.ui.signup.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stiercurriculumportal.R;
import com.example.stiercurriculumportal.data.model.Student;
import com.example.stiercurriculumportal.ui.signup.SignupActivity;
import com.example.stiercurriculumportal.ui.signup.SignupViewModel;

public class StatusSignupFragment extends Fragment {

    private String STATUS, DESCRIPTION_STATUS;
    private Student student;
    private ImageView img_status;
    private TextView txt_status, desc_status;
    private Button signup_btn, login_btn;

    SignupViewModel signupViewModel;

    public StatusSignupFragment(String STATUS, String DESCRIPTION_STATUS, Student student, SignupViewModel signupViewModel){
        this.STATUS = STATUS;
        this.DESCRIPTION_STATUS = DESCRIPTION_STATUS;
        this.student = student;
        this.signupViewModel = signupViewModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_status_signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        img_status = view.findViewById(R.id.img_status);
        txt_status = view.findViewById(R.id.txt_status);
        desc_status = view.findViewById(R.id.desc_status);
        signup_btn = view.findViewById(R.id.signup_btn);
        login_btn = view.findViewById(R.id.login_btn);

        if(STATUS.toUpperCase().equals("SUCCESS")){
            img_status.setImageResource(R.drawable.baseline_check_24);
            txt_status.setText("Success");
            desc_status.setText("You have successfully sign up. You can now log in");

            signup_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getContext(), SignupActivity.class));
                    getActivity().finish();
                }
            });

        } else {
            img_status.setImageResource(R.drawable.baseline_clear_24);
            txt_status.setText("Failed");
            desc_status.setText(DESCRIPTION_STATUS);
            signup_btn.setText("Back");

            signup_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations(R.anim.second_signup_fragment_enter, R.anim.second_signup_fragment_close)
                            .replace(R.id.signup_fragment, new SecondSignupFragment(student, signupViewModel))
                            .commit();
                }
            });
        }
    }
}