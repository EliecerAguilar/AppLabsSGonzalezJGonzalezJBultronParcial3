package com.example.applabssgonzalezjgonzalezjbultron.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CompradorBDHelper extends SQLiteOpenHelper {
    String queryCreate = "CREATE TABLE Comprador(id INTEGER PRIMARY KEY, nombre TEXT, estado TEXT, usuario TEXT, fecha TEXT)";//imagen BLOB,

    public CompradorBDHelper(Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(queryCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
