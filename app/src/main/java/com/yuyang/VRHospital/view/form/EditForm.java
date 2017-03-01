package com.yuyang.VRHospital.view.form;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.yuyang.VRHospital.R;

/**
 * Created by yuyang on 16/4/23.
 */
public class EditForm extends BaseForm {
    private EditText editText;

    public EditForm(Context context, ViewGroup parent, String tag, String zhishiCode,int layout_id) {
        super(context, parent, tag, zhishiCode, layout_id);
    }

    @Override
    protected View createView(String content) {
        editText = (EditText) inflater.inflate(R.layout.layout_form_edit, null);
        return editText;
    }

    @Override
    public String getContent() {
        if (TextUtils.isEmpty(editText.getText())){
            return "";
        }
        return editText.getText().toString();
    }

    @Override
    public boolean checkPostInfo() {
        return true;
    }

}
