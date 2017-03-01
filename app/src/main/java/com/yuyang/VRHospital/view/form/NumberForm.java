package com.yuyang.VRHospital.view.form;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.yuyang.VRHospital.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import android.text.TextWatcher;
import android.widget.Toast;

/**
 * Created by fanshy on 2016/6/19.
 */
public class NumberForm extends BaseForm {
    private EditText numberText;
    private float minNumValue;
    private float maxNumValue;
    private Context mContext;
    private boolean mBCheckSuccess;
    public NumberForm(Context context, ViewGroup parent, String tag, String zhishiCode,int layout_id) {
        super(context, parent, tag, zhishiCode, layout_id);
        mContext = context;
        mBCheckSuccess = true;
    }

    private void ParseContent(String content){
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(content));
            int eventType = xmlPullParser.getEventType();

            String nodeValue = "";
            String name = "";
            String version = "";
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG: {
                        if ("MaxNum".equals(nodeName)) {
                            maxNumValue  = Integer.parseInt(xmlPullParser.nextText());
                        }else if("MinNum".equals(nodeName)) {
                            minNumValue  = Integer.parseInt(xmlPullParser.nextText());
                        }
                        break;
                    }
                    case XmlPullParser.END_TAG: {
                        break;
                    }
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected View createView(String content) {
        minNumValue = 0;
        maxNumValue = 0;
        ParseContent(content);
        numberText = (EditText) inflater.inflate(R.layout.layout_form_number, null);

        numberText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (numberText.hasFocus() == false && !TextUtils.isEmpty(numberText.getText())) {
                    float curValue = Float.parseFloat(numberText.getText().toString());
                    if (curValue < minNumValue || curValue > maxNumValue) {
                        Toast.makeText(mContext, "输入的数字已经超过" + minNumValue + "-" + maxNumValue, Toast.LENGTH_SHORT).show();
                        numberText.setFocusable(true);
                        mBCheckSuccess = false;
                    } else {
                        mBCheckSuccess = true;
                    }
                }
            }
        });
        //numberText.addTextChangedListener(mTextWatcher);
        String hintText = "请输入";
        if(minNumValue != 0)
            hintText += "大于" + minNumValue;
        if(maxNumValue != 0)
            hintText += "小于" + maxNumValue;
        hintText += "的数字";
        numberText.setHint(hintText);
        numberText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        return numberText;
    }

    /*TextWatcher mTextWatcher = new TextWatcher() {
        private CharSequence temp;
        private int editStart ;
        private int editEnd ;
        @Override
        public void beforeTextChanged(CharSequence s, int arg1, int arg2,
                                      int arg3) {
            temp = s;
        }

        @Override
        public void onTextChanged(CharSequence s, int arg1, int arg2,
                                  int arg3) {
            //mTextView.setText(s);
        }

        @Override
        public void afterTextChanged(Editable s) {
            if(minNumValue >= maxNumValue || temp == null || temp == "") return;

            String  strMinnum = "";
            strMinnum += minNumValue;
            int minValueLen = strMinnum.length();
            int curValueLen = temp.length();

            if(curValueLen < minValueLen) return;

            editStart = numberText.getSelectionStart();
            editEnd = numberText.getSelectionEnd();
            if(editEnd == 0) return;

            float curValue = Float.parseFloat(temp.toString());
            if (curValue < minNumValue || curValue > maxNumValue) {
                Toast.makeText(mContext, "输入的数字已经超过" + minNumValue + "-"+ maxNumValue, Toast.LENGTH_SHORT).show();
                s.delete(editStart-1, editEnd);
                int tempSelection = editStart;
                numberText.setText(s);
                numberText.setSelection(tempSelection);
            }
        }
    };*/

    @Override
    public String getContent() {
        if (TextUtils.isEmpty(numberText.getText())){
            return "";
        }
        return numberText.getText().toString();
    }

    @Override
    public boolean checkPostInfo() {
        if(TextUtils.isEmpty(numberText.getText()) ) {
            //Toast.makeText(mContext, "数字型答案为空！", Toast.LENGTH_SHORT).show();
            mBCheckSuccess = true;
            return  mBCheckSuccess;
        }
        float curValue = Float.parseFloat(numberText.getText().toString());
        if (curValue < minNumValue || curValue > maxNumValue) {
            Toast.makeText(mContext, "输入的数字已经超过" + minNumValue + "-"+ maxNumValue, Toast.LENGTH_SHORT).show();
            numberText.setFocusable(true);
            mBCheckSuccess = false;
        }
        else{
            mBCheckSuccess = true;
        }
        return mBCheckSuccess;
    }

}
