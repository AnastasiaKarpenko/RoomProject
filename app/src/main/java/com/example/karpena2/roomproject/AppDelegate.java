package com.example.karpena2.roomproject;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.karpena2.roomproject.database.MusicDatabase;

public class AppDelegate extends Application {

    private MusicDatabase mMusicDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

        mMusicDatabase = Room.databaseBuilder(getApplicationContext(), MusicDatabase.class, "music_database")
                .build();

    }

    public MusicDatabase getMusicDatabase() {
        return mMusicDatabase;
    }
}
