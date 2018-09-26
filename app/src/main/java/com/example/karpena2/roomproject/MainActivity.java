package com.example.karpena2.roomproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.karpena2.roomproject.database.Album;
import com.example.karpena2.roomproject.database.MusicDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button mAddBtn;
    private Button mGetBtn;

    // add Room database to the project
    // insert/read data to/from database
    // add Content Provider to Room

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


    }

    private List<Album> createAlbums() {
        List<Album> albums = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            albums.add(new Album(i, "album " + i, "release " + System.currentTimeMillis()));
        }
        return albums;
    }


}
