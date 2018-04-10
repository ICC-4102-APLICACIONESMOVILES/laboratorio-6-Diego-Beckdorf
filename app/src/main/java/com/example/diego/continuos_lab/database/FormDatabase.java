package com.example.diego.continuos_lab.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.diego.continuos_lab.database_interface.DaoAccess;
import com.example.diego.continuos_lab.database_orm.Form;

@Database(entities = {Form.class}, version = 1, exportSchema = false)
public abstract class FormDatabase extends RoomDatabase {
    public abstract DaoAccess daoAccess() ;
}
