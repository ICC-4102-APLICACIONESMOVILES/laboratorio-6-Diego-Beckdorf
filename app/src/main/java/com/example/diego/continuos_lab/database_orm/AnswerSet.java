package com.example.diego.continuos_lab.database_orm;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "AnswerSet",
        foreignKeys = {@ForeignKey(entity = Question.class,
                                  parentColumns = "questionId",
                                  childColumns = "questionId",
                                  onDelete = CASCADE),
                       @ForeignKey(entity = Answer.class,
                                   parentColumns = "answerId",
                                   childColumns = "answerId",
                                   onDelete = CASCADE)})
public class AnswerSet {
    @NonNull
    @PrimaryKey
    private String answerSetId;
    @NonNull
    private String questionId;
    @NonNull
    private String answerId;

    public AnswerSet(){
    }

    @NonNull
    public String getAnswerSetId() {return answerSetId;}
    public void setAnswerSetId(@NonNull String answerSetId) {this.answerSetId = answerSetId;}

    public String getAnswerId() {return answerId;}
    public void setAnswerId(String answerId) {this.answerId = answerId;}

    public String getQuestionId() {return questionId;}
    public void setQuestionId(String questionId) {this.questionId = questionId;}
}
