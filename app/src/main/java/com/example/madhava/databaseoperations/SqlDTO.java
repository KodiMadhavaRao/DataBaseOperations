package com.example.madhava.databaseoperations;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * Created by madhava on 7/5/2017.
 */

public class SqlDTO {
    Context context;
    clSQLDatabase db;
    public SqlDTO(Context context, clSQLDatabase sqlDatabase) {
        this.context=context;
        db=sqlDatabase;
    }

    public void addItem(String sitemName, long litemPrice)
    {
        SQLiteDatabase database=db.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(clSQLDatabase.ITEM_NAME,sitemName);
        contentValues.put(clSQLDatabase.PRICE,litemPrice);
        database.insert(clSQLDatabase.TABLE_NAME,null,contentValues);
        database.close();
        Toast.makeText(context, "Items Added into Database", Toast.LENGTH_SHORT).show();
    }
    public void deleteItem(int id)
    {

    }
    public int getItemByName(String itemName)
    {
//        String[] args={itemName};
        int pricedb=0;
        SQLiteDatabase sqLiteDatabase=db.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.query(clSQLDatabase.TABLE_NAME
                , new String[]{clSQLDatabase.ID,clSQLDatabase.ITEM_NAME,clSQLDatabase.PRICE}
                , clSQLDatabase.ITEM_NAME +" =?"
                , new String[]{itemName}
                , null, null, null);
        Log.i("Cursor length",""+cursor.getCount());
        if(cursor!=null && cursor.moveToFirst())
        {
            TextView tv;
            int iddb=cursor.getInt(0);
            String itemNamedb=cursor.getString(1);
            pricedb=cursor.getInt(2);
            Log.i("Searched values ",""+iddb+" "+itemNamedb+""+pricedb);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
               View view= LayoutInflater.from(context).inflate(R.layout.item_view_dialog,null);
                builder.setView(view);
            tv=(TextView)view.findViewById(R.id.dialog_textview);
            tv.setText("Id:"+iddb+"\nItem Name:"+itemNamedb+"\nPrice:"+pricedb);
            builder.setTitle("Product Info");
            AlertDialog alertDialog = builder.create();
            alertDialog.getWindow().setLayout(600, 400); //Controlling width and height.
            alertDialog.show();
        }
        return pricedb;
    }
    public  ArrayList<data> getAllItems()
    {
        ArrayList<data> arrayList=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=db.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.query(clSQLDatabase.TABLE_NAME
        ,new String[]{clSQLDatabase.ID,clSQLDatabase.ITEM_NAME,clSQLDatabase.PRICE}
        ,null
        ,null
        ,null
        ,null
        ,null);
        if (cursor!=null&&cursor.moveToFirst())
        {
           do {

               arrayList.add(new data(cursor.getInt(0),cursor.getString(1),cursor.getInt(2)));
           }while (cursor.moveToNext());
        }
        return arrayList;
    }
}
