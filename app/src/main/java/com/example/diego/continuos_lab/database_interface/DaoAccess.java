package com.example.diego.continuos_lab.database_interface;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.diego.continuos_lab.database_orm.Answer;
import com.example.diego.continuos_lab.database_orm.AnswerSet;
import com.example.diego.continuos_lab.database_orm.Form;
import com.example.diego.continuos_lab.database_orm.Question;
import com.example.diego.continuos_lab.database_orm.User;

import java.util.List;

@Dao
public interface DaoAccess {
    //region Single Insertion
    @Insert
    long insertSingleUSer(User user);
    @Insert
    long insertSingleForm(Form form);
    @Insert
    long insertSingleQuestion(Question question);
    @Insert
    long insertSingleAnswer(Answer answer);
    @Insert
    long insertSingleAnswerSet(AnswerSet answerSet);
    //endregion

    //region multi Insertion
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsers(List<User> users);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertForms(List<Form> forms);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertQuestions(List<Question> questions);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAnswers(List<Answer> answers);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAnswerSets(List<AnswerSet> answerSets);
    //endregion

    //region Get all
    @Query  ("SELECT * FROM User")
    List<User> getUsers();
    @Query  ("SELECT * FROM Form")
    List<Form> getForms();
    @Query  ("SELECT * FROM Question")
    List<Question> getQuestions();
    @Query  ("SELECT * FROM Answer")
    List<Answer> getAnswers();
    @Query  ("SELECT * FROM AnswerSet")
    List<AnswerSet> getAnswerSets();
    //endregion

    //region Get by id
    @Query ("SELECT * FROM User WHERE userId = :userId")
    User getUser(long userId);
    @Query ("SELECT * FROM Form WHERE formId = :formId")
    Form getForm(long formId);
    @Query ("SELECT * FROM Question WHERE questionId = :questionId")
    Question getQuestion(long questionId);
    @Query ("SELECT * FROM Answer WHERE answerId = :answerId")
    Answer getAnswer(long answerId);
    @Query ("SELECT * FROM AnswerSet WHERE answerSetId = :answerSetId")
    AnswerSet getAnswerSet(long answerSetId);
    //endregion

    //region Get by relationship
    @Query ("SELECT * FROM Question WHERE formId = :formId")
    List<Question> getFormQuestions(long formId);
    //endregion

    //region Update
    @Update
    void updateUser(User user);
    @Update
    void updateForm(Form form);
    @Update
    void updateQuestion(Question question);
    @Update
    void updateAnswer(Answer answer);
    @Update
    void updateAnswerSet(AnswerSet answerSet);
    //endregion

    //region Delete
    @Delete
    void deleteUser(User user);
    @Delete
    void deleteForm(Form form);
    @Delete
    void deleteQuestion(Question question);
    @Delete
    void deleteAnswer(Answer answer);
    @Delete
    void deleteAnswerSet(AnswerSet answerSet);
    //endregion
}


