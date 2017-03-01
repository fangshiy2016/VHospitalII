package com.yuyang.VRHospital.view.form;

import android.content.Context;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.yuyang.VRHospital.R;

/**
 * Created by yuyang on 16/4/23.
 */
public class SpinnerForm extends BaseForm {
    private AppCompatSpinner spinner;
    private String[] array;

    public SpinnerForm(Context context, ViewGroup parent, String tag, String zhishiCode,int layout_id) {
        super(context, parent, tag, zhishiCode, layout_id);
    }

    @Override
    protected View createView(String content) {
        array = content.split(",");
        spinner = (AppCompatSpinner) inflater.inflate(R.layout.layout_form_spinner, null);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context,R.layout.layout_form_spinner_item,array);
        spinner.setAdapter(arrayAdapter);

        return spinner;
    }

    @Override
    public String getContent() {
        return array[spinner.getSelectedItemPosition()];
    }

    @Override
    public boolean checkPostInfo() {
        return true;
    }
}
