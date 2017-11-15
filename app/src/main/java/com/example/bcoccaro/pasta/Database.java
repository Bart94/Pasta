package com.example.bcoccaro.pasta;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DBPasta";
    public static final String TABLE_NAME = "PastaTable";
    public static final String PASTA_NAME = "pastaName";
    public static final String COOKING_TIME_NAME = "cookingTimeName";
    private static final String[] COLUMNS = {PASTA_NAME, COOKING_TIME_NAME};

    public Database(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATION_TABLE = "CREATE TABLE PastaTable ( "
                + "pastaId INTEGER PRIMARY KEY AUTOINCREMENT"
                + "pastaName TEXT, " + "cookingTimeName TEXT )";
        db.execSQL(CREATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertPasta(Context context, PastaObject pasta) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues attributes = new ContentValues();
        attributes.put(PASTA_NAME, pasta.getName());
        attributes.put(COOKING_TIME_NAME, pasta.getCooking_time());

        db.insert(TABLE_NAME, null, attributes);

        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        int i = cursor.getCount();

        db.close();

    }

    public ArrayList<PastaObject> getAllValues() {
        int i =0;
        PastaObject pasta;
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<PastaObject> list = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                pasta = new PastaObject();
                pasta.setName(cursor.getString(0));
                pasta.setCooking_time(cursor.getString(1));
                list.add(pasta);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public PastaObject searchByName(String name){

        PastaObject pasta = new PastaObject();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE pastaName LIKE " + name;

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        pasta.setName(cursor.getString(0));
        pasta.setCooking_time(cursor.getString(1));

        return pasta;
    }
}
