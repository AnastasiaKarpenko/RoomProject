package com.example.karpena2.roomproject.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Album.class, Song.class, AlbumSong.class}, version = 1)
public abstract class MusicDatabase extends RoomDatabase {

    abstract MusicDao getMusicDao();

}
