package com.example.diego.continuos_lab.database_orm;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Form.class,
                                  parentColumns = "formId",
                                  childColumns = "formId",
                                  onDelete = CASCADE))
public class Question {
    @NonNull
    @PrimaryKey
    private String questionId;
    private String formId;
    private String statement;

    public Question(){
    }

    @NonNull
    public String getQuestionId() {return questionId;}
    public void setQuestionId(@NonNull String questionId) {this.questionId = questionId;}

    public String getFormId() {return formId;}
    public void setFormId(String formId) {this.formId = formId;}

    public String getStatement() {return statement;}
    public void setStatement(String statement) {this.statement = statement;}
}
