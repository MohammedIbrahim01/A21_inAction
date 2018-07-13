package com.example.x.a21_inaction.day_zero.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "benefits")
public class Benefit {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;

    @Ignore
    public Benefit(String title) {
        this.title = title;
    }

    public Benefit(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
