package com.yuyang.VRHospital.view.form;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yuyang.VRHospital.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuyang on 16/4/23.
 */
public class CheckBoxForm extends BaseForm {
    private List<AppCompatCheckBox> checkBoxes = new ArrayList<>();
    private LinearLayout linearLayout;

    public CheckBoxForm(Context context, ViewGroup parent, String tag, String zhishiCode,int layout_id) {
        super(context, parent, tag, zhishiCode, layout_id);
    }

    @Override
    protected View createView(String content) {
        linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        String[] array = content.split(",");
        for (String str:array){
            AppCompatCheckBox checkBox = (AppCompatCheckBox) inflater.inflate(R.layout.layout_form_checkbox,null);
            checkBox.setText(str);
            checkBoxes.add(checkBox);
            linearLayout.addView(checkBox);
        }
        return linearLayout;
    }

    @Override
    public String getContent() {
        StringBuilder str = new StringBuilder("");
        for (AppCompatCheckBox checkBox:checkBoxes){
            if(checkBox.isChecked()){
                str.append(checkBox.getText()).append(",");
            }
        }
        if(str.length() > 0){
            return str.substring(0,str.length()-1);
        }else {
            return str.toString();
        }
    }

    @Override
    public boolean checkPostInfo() {
        return true;
    }
}
