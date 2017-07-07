package com.example.madhava.databaseoperations;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

    /**
     * Adding item to Sqlite dataBase
     * @param sitemName
     * @param litemPrice
     */
    public void addItem(String sitemName, long litemPrice)
    {
        SQLiteDatabase database=db.getWritableDatabase();
        //Handy in case of large database use Android methods
        //Never write raw querys
        ContentValues contentValues=new ContentValues();
        contentValues.put(clSQLDatabase.ITEM_NAME,sitemName);
        contentValues.put(clSQLDatabase.PRICE,litemPrice);
        database.insert(clSQLDatabase.TABLE_NAME,null,contentValues);
        database.close();
        Toast.makeText(context, "Items Added into Database", Toast.LENGTH_SHORT).show();
    }
    public void deleteItem(int id)
    {
        //// TODO: 7/6/2017
    }
    public int getItemByName(String itemName)
    {

//        String[] args={itemName};
        int pricedb[]=new int[]{25};
        int iddb[];
        String itemNamedb[];
        TextView tv;
        SQLiteDatabase
                sqLiteDatabase=db.getReadableDatabase();
/*        Cursor cursor=sqLiteDatabase.query(clSQLDatabase.TABLE_NAME //Table Name
                , new String[]{clSQLDatabase.ID,clSQLDatabase.ITEM_NAME,clSQLDatabase.PRICE} //Items that you want sqlite to return
                , clSQLDatabase.ITEM_NAME +" =?"    //SQLiteDirectCursorDriver: SELECT id, item_name, price FROM spareparts WHERE item_name =?
                , new String[]{itemName}            //while debugging i got this item_name = > < max operations can be performed here
                ,clSQLDatabase.ITEM_NAME ,null,null);*/

        Cursor cursor=sqLiteDatabase.query(clSQLDatabase.TABLE_NAME //Table Name
                , new String[]{clSQLDatabase.ID,clSQLDatabase.ITEM_NAME,clSQLDatabase.PRICE} //Items that you want sqlite to return
                ,clSQLDatabase.ITEM_NAME +"= ?"  //SQLiteDirectCursorDriver: SELECT id, item_name, price FROM spareparts WHERE item_name =?
                , new String[]{itemName}         //while debugging i got this item_name = > < max operations can be performed here
                ,null,null,clSQLDatabase.ID);
        Log.i("Cursor length",""+cursor.getCount());
//
//        String sqlQuery="select * from "+clSQLDatabase.TABLE_NAME+" where "+clSQLDatabase.ITEM_NAME+" = '"+itemName+"'";
//        Log.i("Query",""+sqlQuery);
//        Cursor cursor=sqLiteDatabase.rawQuery(sqlQuery,null);
        if(cursor!=null && cursor.moveToFirst())
        { int i=0;
            pricedb=new int[cursor.getCount()];
            iddb=new int[cursor.getCount()];
            itemNamedb=new String[cursor.getCount()];
           do {
               iddb[i]=cursor.getInt(0);
               itemNamedb[i]=cursor.getString(1);
               pricedb[i]=cursor.getInt(2);
               Log.i("Searched values ",""+iddb[i]+" "+itemNamedb[i]+""+pricedb[i]);
            i++;
           }while (cursor.moveToNext());

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
               View view= LayoutInflater.from(context).inflate(R.layout.item_view_dialog,null);
                builder.setView(view);
            ListView listView=view.findViewById(R.id.dialog_listview);
            AlertAdapter alertAdapter=new AlertAdapter(context,iddb,itemNamedb,pricedb);
            listView.setAdapter(alertAdapter);
//            tv=(TextView)view.findViewById(R.id.dialog_textview);
//            tv.setText("Id:"+iddb+"\nItem Name:"+itemNamedb+"\nPrice:"+pricedb);
            builder.setTitle("Product Info");
            AlertDialog alertDialog = builder.create();
            alertDialog.getWindow().setLayout(600, 400); //Controlling width and height.
            alertDialog.show();

        }

//        boolean isvalue=pricedb.equals(null);
//       int result=isvalue?R.id.nodata:R.id.success;
//        return result;
        if(pricedb[0]==25)
        {
            return R.id.nodata;
        }
        else {
            return R.id.success;
        }

    }
    public ArrayList<data> getAllItems()
    {
        ArrayList<data> arrayList=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=db.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.query(clSQLDatabase.TABLE_NAME
        ,new String[]{clSQLDatabase.ID,clSQLDatabase.ITEM_NAME,clSQLDatabase.PRICE}
        ,null //same as above but null will return all values
        ,null
        ,null
        ,null
        ,null);
        if (cursor!=null&&cursor.moveToFirst())
        {
           do {
            //Assume Cursor like database  point to first values
               //and fetch column values with 0,1,2,.....
               arrayList.add(new data(cursor.getInt(0),cursor.getString(1),cursor.getInt(2)));
           }while (cursor.moveToNext());
        }
        return arrayList;
    }
}
