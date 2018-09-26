package com.example.karpena2.roomproject;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.karpena2.roomproject.database.Album;
import com.example.karpena2.roomproject.database.MusicDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private Button mAddBtn;
    private Button mGetBtn;

    // add Room database to the project | done
    // insert/read data to/from database | done
    // add Content Provider to Room | done

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MusicDao musicDao = ((AppDelegate) getApplicationContext()).getMusicDatabase().getMusicDao();

        mAddBtn = findViewById(R.id.add);
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicDao.insertAlbums(createAlbums());
            }
        });

        mGetBtn = findViewById(R.id.get);
        mGetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                showToast(musicDao.getAlbums());
                initLoader();
            }
        });


    }

    private void initLoader() {
        getSupportLoaderManager().initLoader(123, null, this).forceLoad();
    }

    private List<Album> createAlbums() {
        List<Album> albums = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            albums.add(new Album(i, "album " + i, "release " + System.currentTimeMillis()));
        }
        return albums;
    }

    private void showToast(List<Album> albums) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0, size = albums.size(); i < size; i++) {
            builder.append(albums.get(i).toString()).append("\n");
        }

        Toast.makeText(this, builder.toString(), Toast.LENGTH_SHORT).show();
    }


    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new CursorLoader(this,
                Uri.parse("content//com.example.karpena2.roomproject.musicprovider.album"),
                null,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        if (cursor != null && cursor.moveToFirst()) {
            StringBuilder builder = new StringBuilder();

            do {
                builder.append(cursor.getString(cursor.getColumnIndex("name"))).append("/n");
            } while (cursor.moveToNext());
            Toast.makeText(this, builder.toString(), Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}
