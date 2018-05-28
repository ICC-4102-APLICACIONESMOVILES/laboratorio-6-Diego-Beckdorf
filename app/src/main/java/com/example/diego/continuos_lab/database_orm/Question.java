package com.example.diego.continuos_lab.database_orm;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Form.class,
                                  parentColumns = "formId",
                                  childColumns = "formId",
                                  onDelete = CASCADE))
public class Question {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private long questionId;
    private long formId;
    private String statement;

    public Question(long formId, String statement){
        this.formId = formId;
        this.statement = statement;
    }

    @Ignore
    public Question(String statement){
        this.statement = statement;
    }

    @NonNull
    public long getQuestionId() {return questionId;}
    public void setQuestionId(@NonNull long questionId) {this.questionId = questionId;}

    public long getFormId() {return formId;}
    public void setFormId(long formId) {this.formId = formId;}

    public String getStatement() {return statement;}
    public void setStatement(String statement) {this.statement = statement;}
}
