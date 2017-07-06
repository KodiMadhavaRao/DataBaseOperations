package com.example.madhava.databaseoperations;

import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText itemName;
    EditText itemPrice;
    Button saveButton,viewItem,viewAllItems,updateList;
    String sitemName;
    String sitemPrice;
    long litemPrice;
    SqlDTO sqlDTO;
    View view1;
    ListView listView;
    clSQLDatabase sqlDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        sqlDTO=new SqlDTO(MainActivity.this,sqlDatabase);
        saveButton.setOnClickListener(this);
        viewItem.setOnClickListener(this);
        viewAllItems.setOnClickListener(this);
        updateList.setOnClickListener(this);

        sqlDatabase=new clSQLDatabase(this);
    }

    private void findViews()
    {
        itemName=(EditText) findViewById(R.id.item_name);
        itemPrice=(EditText) findViewById(R.id.item_price);
        saveButton=(Button) findViewById(R.id.save);
        viewItem=(Button) findViewById(R.id.view_item);
        viewAllItems=(Button) findViewById(R.id.view_all_items);
        updateList=(Button) findViewById(R.id.update_list);
        listView=(ListView)findViewById(R.id.list_view);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.save:
                try{
                    sitemName = itemName.getText().toString().trim();
                    sitemPrice = this.itemPrice.getText().toString().trim();
                    if(itemName.equals(null))
                    {
                        itemName.setError("Invalid input");
                    }
                    litemPrice = Long.parseLong(sitemPrice);
                    sqlDTO = new SqlDTO(MainActivity.this, sqlDatabase);
                    sqlDTO.addItem(sitemName, litemPrice);
                    itemPrice.setText("");
                    itemName.setText("");
                    ArrayList<data> arrayList=sqlDTO.getAllItems();
                    MyAdapter myAdapter=new MyAdapter(MainActivity.this,arrayList);
                    listView.setAdapter(myAdapter);
                }

                catch (NumberFormatException exception)
                {
                    itemPrice.setError("Enter Valid number");
                }

                break;

            case R.id.view_item :
                final AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(this);
                LayoutInflater layoutInflater=this.getLayoutInflater();
                view1=layoutInflater.inflate(R.layout.dialogcustom,null);
                alertDialogBuilder.setView(view1);

                sqlDTO=new SqlDTO(MainActivity.this,sqlDatabase);
                alertDialogBuilder.setTitle("Search By Item Name");
                alertDialogBuilder.setPositiveButton("Search", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText editText=view1.findViewById(R.id.dialog_edit_text);
                        final String itemid=editText.getText().toString().trim();
                        int price=sqlDTO.getItemByName(itemid);
//                        Toast.makeText(MainActivity.this, ""+price, Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog alertDialog=alertDialogBuilder.create();
                alertDialog.show();
                break;
            case  R.id.view_all_items:
                SqlDTO sqldto=new SqlDTO(MainActivity.this,sqlDatabase);
                ArrayList<data> arrayList=sqldto.getAllItems();
                MyAdapter myAdapter=new MyAdapter(MainActivity.this,arrayList);
                listView.setAdapter(myAdapter);

//               for (int i=0;i<arrayList.size();i++)
//               {
////                   arrayList.get(i);
//                   Log.i("Index"+i,""+arrayList.get(i).getId());
//               }
                break;
            case  R.id.update_list:
                break;

        }

    }
}
