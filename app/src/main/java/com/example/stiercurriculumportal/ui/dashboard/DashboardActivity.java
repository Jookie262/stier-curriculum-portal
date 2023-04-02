package com.example.stiercurriculumportal.ui.dashboard;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stiercurriculumportal.R;
import com.example.stiercurriculumportal.data.model.Student;
import com.example.stiercurriculumportal.ui.dashboard.fragments.course_record.CourseRecord;
import com.example.stiercurriculumportal.ui.dashboard.fragments.curriculum_checklist.CurriculumChecklist;
import com.example.stiercurriculumportal.ui.dashboard.fragments.profile.ProfileFragment;
import com.example.stiercurriculumportal.ui.login.LoginActivity;
import com.google.android.material.navigation.NavigationView;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    DashboardViewModel dashboardViewModel;
    TextView student_name, student_course;
    ImageView profile_picture;
    Student student;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        dashboardViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(DashboardViewModel.class);
        intent = getIntent();
        int id = intent.getIntExtra("Student_ID", 0);
        student = dashboardViewModel.getOneStudent(id);


        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        student_name = navigationView.getHeaderView(0).findViewById(R.id.student_name);
        student_course = navigationView.getHeaderView(0).findViewById(R.id.student_course);
        profile_picture = navigationView.getHeaderView(0).findViewById(R.id.profile_picture);

        setHeaderName();
        setHeaderProgram();
        setImageProfile();

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.view_curriculum:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CurriculumChecklist()).commit();
                break;
            case R.id.course_record:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CourseRecord()).commit();
                break;
            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
                break;
            case R.id.logout_btn:
                logoutDashboard();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setHeaderName(){
        student_name.setText(student.getFirstName() + " " + student.getLastName());
    }

    public void setHeaderProgram(){
        student_course.setText(student.getProgram());
    }

    public void setImageProfile(){
        if(student.getImage() != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(student.getImage(), 0, student.getImage().length);
            profile_picture.setImageBitmap(bitmap);
        }
    }

    private void logoutDashboard(){
        startActivity(new Intent(DashboardActivity.this, LoginActivity.class));
        finish();
    }
}