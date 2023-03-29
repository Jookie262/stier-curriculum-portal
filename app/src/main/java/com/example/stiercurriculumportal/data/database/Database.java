package com.example.stiercurriculumportal.data.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.stiercurriculumportal.data.dao.StudentDao;
import com.example.stiercurriculumportal.data.model.Student;

@androidx.room.Database(entities = {Student.class}, version = 1)
public abstract class Database extends RoomDatabase {
    public abstract StudentDao studentDao();
    private static Database instance;

    public static synchronized Database getInstance(Context context){
        if(instance == null){
            instance = Room
                    .databaseBuilder(context.getApplicationContext(), Database.class, "stier_cp_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
