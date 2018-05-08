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
    void insertSingleUSer(User user);
    @Insert
    void insertSingleForm(Form form);
    @Insert
    void insertSingleQuestion(Question question);
    @Insert
    void insertSingleAnswer(Answer answer);
    @Insert
    void insertSingleAnswerSet(AnswerSet answerSet);
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
    User getUser(int userId);
    @Query ("SELECT * FROM Form WHERE formId = :formId")
    Form getForm(int formId);
    @Query ("SELECT * FROM Question WHERE questionId = :questionId")
    Question getQuestion(int questionId);
    @Query ("SELECT * FROM Answer WHERE answerId = :answerId")
    Answer getAnswer(int answerId);
    @Query ("SELECT * FROM AnswerSet WHERE answerSetId = :answerSetId")
    AnswerSet getAnswerSet(int answerSetId);
    //endregion

    //region Get by relationship
    @Query ("SELECT * FROM Question WHERE formId = :formId")
    List<Question> getFormQuestions(String formId);
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


