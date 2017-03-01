package com.yuyang.VRHospital.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yuyang.VRHospital.R;

import java.util.List;

/**
 * Created by yuyang on 16/4/22.
 */
public class DialogAdapter extends BaseAdapter {
    private List<String> itemStrs;
    private Context context;

    public DialogAdapter(Context context, List<String> strs){
        this.context = context;
        itemStrs = strs;
    }

    @Override
    public int getCount() {
        return itemStrs.size();
    }

    @Override
    public String getItem(int position) {
        return itemStrs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dialog_list,parent,false);
        TextView item = (TextView) view.findViewById(R.id.dialog_list_item);
        item.setText(itemStrs.get(position));
        return view;
    }
}
