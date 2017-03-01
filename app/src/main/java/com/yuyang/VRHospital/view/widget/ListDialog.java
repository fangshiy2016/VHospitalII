package com.yuyang.VRHospital.view.widget;

import android.app.Dialog;
import android.content.Context;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.yuyang.VRHospital.R;

/**
 * Created by yuyang on 16/4/22.
 */
public class ListDialog extends Dialog {
    private ListView listView;
    private String title;

    public ListDialog(Context context,String title) {
        super(context);
        this.title = title;
        init(title);
    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(title);
    }*/

    private void init(String title) {
        setTitle(title);
        setContentView(R.layout.layout_list_dialog);
        listView = (ListView) findViewById(R.id.dialog_list);
    }

    public void setAdapter(BaseAdapter adapter){
        listView.setAdapter(adapter);
    }

    public void setItemClickListener(AdapterView.OnItemClickListener listener){
        listView.setOnItemClickListener(listener);
    }

}
