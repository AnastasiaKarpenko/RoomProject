package com.example.karpena2.roomproject;

import android.arch.persistence.room.Room;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import com.example.karpena2.roomproject.database.MusicDao;
import com.example.karpena2.roomproject.database.MusicDatabase;

public class MusicProvider extends ContentProvider {
    private static final String TAG = MusicDatabase.class.getSimpleName();
    private static final String AUTHORITY = "com.example.karpena2.roomproject.musicprovider";
    private static final String TABLE_ALBUM = "album";

    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    private static final int ALBUM_TABLE_CODE = 100;
    private static final int ALBUM_ROW_CODE = 101;

    static {
        URI_MATCHER.addURI(AUTHORITY, TABLE_ALBUM, ALBUM_TABLE_CODE);
        URI_MATCHER.addURI(AUTHORITY, TABLE_ALBUM + "/*", ALBUM_ROW_CODE);
    }

    private MusicDao mMusicDao;


    public MusicProvider() {
    }


    @Override
    public boolean onCreate() {
        if (getContext() != null) {
            mMusicDao = Room.databaseBuilder(getContext().getApplicationContext(), MusicDatabase.class, "music_database")
                    .build()
                    .getMusicDao();
        }
        return true;
    }

    @Override
    public String getType(Uri uri) {
        switch (URI_MATCHER.match(uri)) {
            case ALBUM_TABLE_CODE:
                return "vnd.android.cursor.dir/" + AUTHORITY + "." + TABLE_ALBUM;
            case ALBUM_ROW_CODE:
                return "vnd.android.cursor.item/" + AUTHORITY + "." + TABLE_ALBUM;
            default:
                throw new UnsupportedOperationException("Not yet implemented");

        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        int code = URI_MATCHER.match(uri);

        if (code != ALBUM_ROW_CODE && code != ALBUM_TABLE_CODE) {
            return null;
        }

        Cursor cursor;

        if (code == ALBUM_TABLE_CODE) {
            cursor = mMusicDao.getAlbumsCursor();
        } else {
            cursor = mMusicDao.getAlbumWithIdCursor((int) ContentUris.parseId(uri));
        }
        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
