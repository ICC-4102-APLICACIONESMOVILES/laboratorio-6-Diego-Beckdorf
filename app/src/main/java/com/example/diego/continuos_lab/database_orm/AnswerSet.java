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
    @PrimaryKey(autoGenerate = true)
    private long answerSetId;
    @NonNull
    private long questionId;
    @NonNull
    private long answerId;

    public AnswerSet(){
    }

    @NonNull
    public long getAnswerSetId() {return answerSetId;}
    public void setAnswerSetId(@NonNull long answerSetId) {this.answerSetId = answerSetId;}

    public long getAnswerId() {return answerId;}
    public void setAnswerId(long answerId) {this.answerId = answerId;}

    public long getQuestionId() {return questionId;}
    public void setQuestionId(long questionId) {this.questionId = questionId;}
}
