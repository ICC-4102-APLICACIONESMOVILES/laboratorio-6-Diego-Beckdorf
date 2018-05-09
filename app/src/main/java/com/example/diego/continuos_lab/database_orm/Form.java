package com.example.diego.continuos_lab.database_orm;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Form {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private long formId;
    private String name;
    private String date;
    private String category;
    private String description;

    public Form(String name, String date, String category, String description) {
        this.name = name;
        this.date = date;
        this.category = category;
        this.description = description;
    }

    @NonNull
    public long getFormId() {return formId;}
    public void setFormId(@NonNull long formId) {this.formId = formId;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getDate() {return date;}
    public void setDate(String date) {this.date = date;}

    public String getCategory() {return category;}
    public void setCategory(String category) {this.category = category;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
}
