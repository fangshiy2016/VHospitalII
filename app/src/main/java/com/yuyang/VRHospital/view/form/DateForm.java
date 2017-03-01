package com.yuyang.VRHospital.view.form;

import android.app.DatePickerDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.yuyang.VRHospital.R;
import com.yuyang.VRHospital.utils.DateUtils;

/**
 * Created by yuyang on 16/4/23.
 */
public class DateForm extends BaseForm {
    private TextView textView;
    private DatePickerDialog datePickerDialog;

    public DateForm(Context context, ViewGroup parent, String tag, String zhishiCode, int layout_id) {
        super(context, parent, tag, zhishiCode, layout_id);
    }

    @Override
    protected View createView(String content) {
        textView = (TextView) inflater.inflate(R.layout.layout_form_text, null);
        datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                textView.setText(year+"-"+monthOfYear+"-"+dayOfMonth);
                datePickerDialog.hide();
            }
        }, DateUtils.getYear(),DateUtils.getMonth(),DateUtils.getDay());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
        return textView;
    }

    @Override
    public String getContent() {
        if(TextUtils.isEmpty(textView.getText())){
            return "";
        }
        return textView.getText().toString();
    }

    @Override
    public boolean checkPostInfo() {
        return true;
    }
}
