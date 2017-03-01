package com.yuyang.VRHospital.view.form;

import android.content.Context;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.GridView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.yuyang.VRHospital.R;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import com.yuyang.VRHospital.utils.XmlParseUtil;
import com.yuyang.VRHospital.view.adapter.GridAdapter;
import com.yuyang.VRHospital.network.Url;
/**
 * Created by yuyang on 16/4/23.
 */
public class ImagesForm extends BaseForm  implements MultiChoiceModeListener {

    private GridView mGridView;
    private GridAdapter mGridAdapter;
    private TextView mActionText;
    private static final int MENU_SELECT_ALL = 0;
    private static final int MENU_UNSELECT_ALL = MENU_SELECT_ALL + 1;
    private Map<Integer, Boolean> mSelectMap = new HashMap<Integer, Boolean>();

    private Context  mContext;

    public ImagesForm(Context context, ViewGroup parent, String tag, String zhishiCode, int layout_id) {
        super(context, parent, tag, zhishiCode, layout_id);

        mContext = context;
    }

    @Override
    protected View createView(String content) {
        String listValue = XmlParseUtil.ParseContent(content);
        String[] array = listValue.split(",");
        //改为解析XML
        mGridView = (GridView) inflater.inflate(R.layout.layout_form_grid, null);
        mGridView.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE_MODAL);

        mGridAdapter = new GridAdapter(mContext, array);
        mGridView.setAdapter(mGridAdapter);
        mGridView.setMultiChoiceModeListener(this);

        return mGridView;
        /*
        radioGroup = (RadioGroup) inflater.inflate(R.layout.layout_form_radio,null);
        for (String radio:array){
            AppCompatRadioButton radioButton = (AppCompatRadioButton) inflater.inflate(R.layout.layout_form_radio_item,null);
            radioButton.setText(radio);
            radioGroup.addView(radioButton);
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkedRadio = (AppCompatRadioButton) radioGroup.findViewById(checkedId);
            }
        });
        return radioGroup;
        */
    }

    @Override
    public String getContent() {
        if(mGridView == null){
            return "";
        }
        return "Test_te";//checkedRadio.getText().toString();
    }

    @Override
    public boolean checkPostInfo() {
        return false;
    }

    ///////////////MultiChoiceModeListener/////////////////
    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        // TODO Auto-generated method stub
        View v = LayoutInflater.from(mContext).inflate(R.layout.actionbar_layout, null);
        mActionText = (TextView) v.findViewById(R.id.action_text);
        mActionText.setText(formatString(mGridView.getCheckedItemCount()));
        mode.setCustomView(v);
        //mContext.getMenuInflater().inflate(R.menu.action_menu, menu);

        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        // TODO Auto-generated method stub
        menu.getItem(MENU_SELECT_ALL).setEnabled(
                mGridView.getCheckedItemCount() != mGridView.getCount());
        return true;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.menu_select:
                for (int i = 0; i < mGridView.getCount(); i++) {
                    mGridView.setItemChecked(i, true);
                    mSelectMap.put(i, true);
                }
                break;
            case R.id.menu_unselect:
                for (int i = 0; i < mGridView.getCount(); i++) {
                    mGridView.setItemChecked(i, false);
                    mSelectMap.clear();
                }
                break;
        }
        return true;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        // TODO Auto-generated method stub
        mGridAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position,
                                          long id, boolean checked) {
        // TODO Auto-generated method stub
        mActionText.setText(formatString(mGridView.getCheckedItemCount()));
        mSelectMap.put(position, checked);
        mode.invalidate();
    }

    /** Override MultiChoiceModeListener end **/
    private String formatString(int count) {
        return String.format(mContext.getString(R.string.selection), count);
    }
}
