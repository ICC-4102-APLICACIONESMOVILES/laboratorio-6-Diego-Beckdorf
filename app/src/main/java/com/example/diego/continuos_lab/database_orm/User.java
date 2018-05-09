package com.example.diego.continuos_lab.database_orm;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity
public class User {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private long userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public User() {
    }

    @NonNull
    public long getUserId() {return userId;}
    public void setUserId(@NonNull long userId) {this.userId = userId;}

    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
}
