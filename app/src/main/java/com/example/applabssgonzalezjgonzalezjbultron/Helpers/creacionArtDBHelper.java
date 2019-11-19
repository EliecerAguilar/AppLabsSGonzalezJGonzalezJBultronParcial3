package com.example.applabssgonzalezjgonzalezjbultron.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class creacionArtDBHelper extends SQLiteOpenHelper {
    final String TABLA_CREACION = "CREATE TABLE creacion(id_usr TEXT,id_art TEXT,cantStock INTEGER)";

    public creacionArtDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_CREACION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS creacion");
        onCreate(db);
    }
}
