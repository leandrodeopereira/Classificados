package com.pereira.classificados.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Android on 18/01/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    // nome do arquivo(se nao existir, ele vai criar)
    private static final String DATABASE_NAME = "classificados.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //vai criar a tabela
        db.execSQL(MyStore.ItemAdTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //editar o banco
        if(oldVersion == 1){
            upgrade1to2(db);
        }
    }

    private void upgrade1to2(SQLiteDatabase db){
        db.execSQL("ALTER TABLE " + MyStore.ItemAdTable.TABLE_NAME
                    + " ADD COLUMN " + MyStore.ItemAdTable.PRICE
                    + " TEXT DEFAULT (0)");
    }
}
