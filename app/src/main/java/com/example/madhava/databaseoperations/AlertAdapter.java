package com.example.madhava.databaseoperations;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by madhava on 7/6/2017.
 */

public class AlertAdapter extends BaseAdapter
{
    Context context;
    int[] iddb;
    String[] itemNamedb;
    int[] pricedb;
    public AlertAdapter(Context context, int[] iddb, String[] itemNamedb, int[] pricedb) {
    this.context=context;
        this.iddb=iddb;;
        this.itemNamedb=itemNamedb;
        this.pricedb=pricedb;
    }

    @Override
    public int getCount() {
        return iddb.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        LayoutInflater li=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View v=li.inflate(R.layout.alert_dialog_list_items,null);
        TextView id=(TextView)v.findViewById(R.id.alert_serch_id);
        TextView Itemname=(TextView)v.findViewById(R.id.alert_serch_item);
        TextView price=(TextView)v.findViewById(R.id.alert_serch_price);
        id.setText(""+iddb[i]);
        Itemname.setText(itemNamedb[i]);
        price.setText(""+pricedb[i]);

        return v;
    }
}
