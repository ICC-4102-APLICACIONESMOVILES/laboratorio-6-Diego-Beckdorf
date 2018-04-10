package com.example.diego.continuos_lab.database_orm;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Question.class,
                                  parentColumns = "questionId",
                                  childColumns = "questionId",
                                  onDelete = CASCADE))
public class Answer {
    @NonNull
    @PrimaryKey
    private String answerId;
    private String questionId;
    private String content;

    public Answer(){
    }

    @NonNull
    public String getAnswerId() {return answerId;}
    public void setAnswerId(@NonNull String answerId) {this.answerId = answerId;}

    public String getQuestionId() {return questionId;}
    public void setQuestionId(String questionId) {this.questionId = questionId;}

    public String getContent() {return content;}
    public void setContent(String content) {this.content = content;}
}
