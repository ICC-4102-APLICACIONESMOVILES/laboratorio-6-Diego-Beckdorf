package com.example.diego.continuos_lab.database_orm;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.location.Location;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Question.class,
                                  parentColumns = "questionId",
                                  childColumns = "questionId",
                                  onDelete = CASCADE))
public class Answer {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private long answerId;
    private long questionId;
    private String content;
    private double latitude;
    private double longitude;

    public Answer(long questionId, String content, double latitude, double longitude){
        this.questionId = questionId;
        this.content = content;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @NonNull
    public long getAnswerId() {return answerId;}
    public void setAnswerId(@NonNull long answerId) {this.answerId = answerId;}

    public long getQuestionId() {return questionId;}
    public void setQuestionId(long questionId) {this.questionId = questionId;}

    public String getContent() {return content;}
    public void setContent(String content) {this.content = content;}

    public double getLatitude() {return latitude;}
    public void setLatitude(double latitude) {this.latitude= latitude;}

    public double getLongitude() {return longitude;}
    public void setLongitude(double content) {this.longitude = longitude;}

    public Location getLocation() {
        double lat_start = this.getLatitude();
        double lon_start = this.getLongitude();

        Location location = new Location("point");
        location.setLatitude( lat_start );
        location.setLongitude( lon_start );
        return location;
    }
}
