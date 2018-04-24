package com.example.diego.continuos_lab.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.diego.continuos_lab.database_interface.DaoAccess;
import com.example.diego.continuos_lab.database_orm.User;
import com.example.diego.continuos_lab.database_orm.Form;
import com.example.diego.continuos_lab.database_orm.Question;
import com.example.diego.continuos_lab.database_orm.Answer;
import com.example.diego.continuos_lab.database_orm.AnswerSet;

@Database(entities = {User.class, Form.class, Question.class, Answer.class, AnswerSet.class},
        version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DaoAccess daoAccess() ;
}
