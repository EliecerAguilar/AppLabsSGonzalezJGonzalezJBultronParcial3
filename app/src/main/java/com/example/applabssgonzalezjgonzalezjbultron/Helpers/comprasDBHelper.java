package com.example.applabssgonzalezjgonzalezjbultron.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class comprasDBHelper extends SQLiteOpenHelper {

    //declaracion de la tabla
    final String TABLA_COMPRAS = "CREATE TABLE compras (id_usr TEXT, id_art TEXT, cantCompra INTEGER)";

    public comprasDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_COMPRAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS compras");
        onCreate(db);
    }
}
