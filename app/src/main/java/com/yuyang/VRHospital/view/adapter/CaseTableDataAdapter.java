package com.yuyang.VRHospital.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yuyang.VRHospital.R;
import com.yuyang.VRHospital.bean.CaseItemBean;
import com.yuyang.VRHospital.common.Contants;

import java.util.List;

import de.codecrafters.tableview.TableDataAdapter;

/**
 * Created by yuyang on 16/4/21.
 */
public class CaseTableDataAdapter extends TableDataAdapter<CaseItemBean> {

    private List<CaseItemBean> caseItemBeanList;
    private int loadState = Contants.CAN_LOAD_MORE;

    public CaseTableDataAdapter(Context context, List<CaseItemBean> data) {
        super(context, data);
        caseItemBeanList = data;
    }

    public int getLoadState() {
        return loadState;
    }

    public void setLoadState(int loadState) {
        this.loadState = loadState;
    }

    @Override
    public int getCount() {
        if(caseItemBeanList == null || caseItemBeanList.size() == 0){
            return 0;
        }
        return caseItemBeanList.size() + 1;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == getCount()-1){
            return TYPE_FOOT;
        }else {
            return TYPE_ITEM;
        }
    }

    @Override
    public View getCellView(int rowIndex, int columnIndex, ViewGroup parentView) {
        View renderedView = null;
        if (getItemViewType(rowIndex) == TYPE_ITEM) {
            CaseItemBean caseItemBean = getRowData(rowIndex);
            switch (columnIndex) {
                case 0:
                    renderedView = renderString(caseItemBean.getCaseCode());
                    break;
                case 1:
                    renderedView = renderString(caseItemBean.getAge() > 0 ? Integer.toString(caseItemBean.getAge()) : "-");
                    break;
                case 2:
                    renderedView = renderString(caseItemBean.getSex() == 0 ? "男": "女");
                    break;
                case 3:
                    renderedView = renderString(caseItemBean.getKeShi());
                    break;
                case 4:
                    renderedView = renderString(caseItemBean.getCreateData().toString());
                    break;
            }
        }else {
            renderedView = LayoutInflater.from(parentView.getContext()).inflate(R.layout.layout_list_item_footer,null);
            TextView message = (TextView) renderedView.findViewById(R.id.footer_text);
            View progress = renderedView.findViewById(R.id.footer_progress);
            if(loadState == Contants.CAN_LOAD_MORE) {
                message.setText("加载更多");
                progress.setVisibility(View.VISIBLE);
            }else if(loadState == Contants.LOAD_FAIL){
                message.setText("加载失败");
                progress.setVisibility(View.GONE);
            }else {
                message.setText("没有更多了");
                progress.setVisibility(View.GONE);
            }
        }
        return renderedView;
    }

    private View renderString(String value) {
        TextView textView = new TextView(getContext());
        textView.setText(value);
        textView.setPadding(20, 20, 20, 20);
        textView.setTextSize(14);
        textView.setSingleLine(true);
        return textView;
    }
}
