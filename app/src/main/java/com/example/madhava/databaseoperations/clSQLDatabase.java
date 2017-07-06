package com.example.madhava.databaseoperations;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by madhava on 7/5/2017.
 */

public class clSQLDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="germanexperts";
    public static final String TABLE_NAME="spareparts";
    public static final String ID="id";
    public static final String ITEM_NAME="item_name";
    public static final String PRICE="price";
    public static final int VERSION=1;

    /**
     *
     * @param context
     */
    public clSQLDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    /**
     * Table will be crated just once
     * @param sqLiteDatabase
     */

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    String createTable="CREATE TABLE "+ TABLE_NAME +"("+ ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+ITEM_NAME+" TEXT,"+PRICE+" INTEGER"+")";
       Log.i("Table",""+createTable);
        sqLiteDatabase.execSQL(createTable);
        Log.i("DataBase","Table Created");
    }

    /**
     * when the version is increased automatically onUpgradle will be called
     * then you either drop you table and create new or update an existing
     * @param sqLiteDatabase
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
