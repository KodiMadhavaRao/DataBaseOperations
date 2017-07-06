package com.example.madhava.databaseoperations;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.Collections;
import java.util.ArrayList;

/**
 * Created by madhava on 7/6/2017.
 */

public class MyAdapter extends BaseAdapter
{
    int i=0;
    Context context;
    ArrayList<data> spareItemList;
    public MyAdapter(Context context, ArrayList spareItemsList)
    {
        this.context=context;
        this.spareItemList=spareItemsList;
    }

    @Override
    public int getCount() {
        return spareItemList.size();
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
        Collections.reverse(spareItemList);
        LayoutInflater li=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View v=li.inflate(R.layout.list_items,null);
        TextView viewIdNo=v.findViewById(R.id.view_id_no);
        TextView viewItemName=v.findViewById(R.id.view_Item_name);
        TextView viewPrice=v.findViewById(R.id.view_item_price);
        viewIdNo.setText(""+spareItemList.get(i).getId());
        viewItemName.setText(spareItemList.get(i).getItemName());
        viewPrice.setText(""+spareItemList.get(i).getPrice());
        return v;
    }
}
