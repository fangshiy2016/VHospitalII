package com.yuyang.VRHospital.view.form;

import android.content.Context;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import com.yuyang.VRHospital.utils.XmlParseUtil;
import java.io.StringReader;

import com.yuyang.VRHospital.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/**
 * Created by yuyang on 16/4/23.
 */
public class RadioForm extends BaseForm {

    private Boolean    mIsCheck = false;
    private RadioGroup radioGroup;
    private AppCompatRadioButton checkedRadio;
    String[] mArray;
    public RadioForm(Context context, ViewGroup parent, String tag, String zhishiCode,int layout_id) {
        super(context, parent, tag, zhishiCode, layout_id);
        mUnselect.setVisibility(View.VISIBLE);
    }

    @Override
    protected View createView(String content) {
        String listValue = XmlParseUtil.ParseContent(content);
        mArray = listValue.split(",");
        //改为解析XML

        radioGroup = (RadioGroup) inflater.inflate(R.layout.layout_form_radio,null);
        int i = 0;
        radioGroup.removeAllViews();
        mIsCheck = false;
        for (String radio:mArray){
            AppCompatRadioButton radioButton = (AppCompatRadioButton) inflater.inflate(R.layout.layout_form_radio_item,null);
            radioButton.setText(radio);
            radioButton.setTag(++i);
            //setValue();

            radioGroup.addView(radioButton);
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkedRadio = (AppCompatRadioButton) radioGroup.findViewById(checkedId);
                setValue((int)checkedRadio.getTag());
                mIsCheck = true;
            }
        });

        mUnselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup.removeAllViews();
                mIsCheck = false;
                int i = 0;
                setValue(-1);
                for (String radio:mArray){
                    AppCompatRadioButton radioButton = (AppCompatRadioButton) inflater.inflate(R.layout.layout_form_radio_item,null);
                    radioButton.setText(radio);
                    radioButton.setTag(++i);
                    //setValue();

                    radioGroup.addView(radioButton);
                }
            }
        });
        return radioGroup;
    }

    @Override
    public String getContent() {
        if(checkedRadio == null){
            return "";
        }
        return checkedRadio.getText().toString();
    }

    @Override
    public boolean checkPostInfo() {
        return true;
    }
}
