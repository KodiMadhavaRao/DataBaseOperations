package com.example.madhava.databaseoperations;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText itemName;
    EditText itemPrice;
    Button saveButton,viewItem,viewAllItems, closeList;
    String sitemName;
    String sitemPrice;
    long litemPrice;
    SqlDTO sqlDTO;
    View view1;
    int price;
    EditText dialogEdit;
    ListView listView;
    clSQLDatabase sqlDatabase;

    /**
     * Activity life cycle methods
     * saved Instance state will be used when configuration changes are made like
     * screen orientation handy for realtime projects
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        sqlDTO=new SqlDTO(MainActivity.this,sqlDatabase);
        saveButton.setOnClickListener(this);
        viewItem.setOnClickListener(this);
        viewAllItems.setOnClickListener(this);
        closeList.setOnClickListener(this);

        sqlDatabase=new clSQLDatabase(this);
    }

    private void findViews()
    {
        itemName=(EditText) findViewById(R.id.item_name);
        itemPrice=(EditText) findViewById(R.id.item_price);
        saveButton=(Button) findViewById(R.id.save);
        viewItem=(Button) findViewById(R.id.view_item);
        viewAllItems=(Button) findViewById(R.id.view_all_items);
        closeList =(Button) findViewById(R.id.close_list);
        listView=(ListView)findViewById(R.id.list_view);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            /**
             * When the button is clciked it will check with
             * below id's and perform operations
             */
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
                        dialogEdit=view1.findViewById(R.id.dialog_edit_text);
                        final String itemid=dialogEdit.getText().toString().trim();
                        price=sqlDTO.getItemByName(itemid);

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
            case  R.id.close_list:
                listView.setAdapter(null);
                break;

        }

    }
}
