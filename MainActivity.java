package com.example.db_mus;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    SimpleCursorAdapter adapter;
    EditText year, title, duration, sing;
    DBHelper helper;
    SQLiteDatabase musicDB;
    Cursor tunes;
    String[] playlist_fields;
    int[] views = {R.id.id, R.id.artist, R.id.title, R.id.year, R.id.duration };;
    int i = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new DBHelper(this);
        musicDB = helper.getWritableDatabase();

        lv = findViewById(R.id.playlist);
        year = findViewById(R.id.year);
        title = findViewById(R.id.title);
        duration = findViewById(R.id.duration);
        sing = findViewById(R.id.name);


        tunes = musicDB.rawQuery("SELECT * FROM playlist", null);
        playlist_fields = tunes.getColumnNames();


        adapter = new SimpleCursorAdapter(this, R.layout.paylist_item, tunes, playlist_fields, views, 0 );
        lv.setAdapter(adapter);
    }

    public void onClick(View v) {
        String name = sing.getText().toString();
        String s_title = title.getText().toString();
        String s_year = year.getText().toString();
        String s_dur = duration.getText().toString();
        int i_year = Integer.parseInt(s_year);
        int i_dur = Integer.parseInt(s_dur);

        i++;
        musicDB.execSQL("INSERT INTO " + helper.TABLE_NAME + " VALUES (" + i + ", '" + s_title + "', '" + name + "', " + i_dur + ", " + i_year + ")");

        tunes = musicDB.rawQuery("SELECT * FROM playlist", null);
        playlist_fields = tunes.getColumnNames();
        adapter = new SimpleCursorAdapter(this, R.layout.paylist_item, tunes, playlist_fields, views, 0 );
        lv.setAdapter(adapter);
//
    }
}
