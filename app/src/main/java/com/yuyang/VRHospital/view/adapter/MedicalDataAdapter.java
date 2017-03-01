package com.yuyang.VRHospital.view.adapter;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.yuyang.VRHospital.R;
import com.yuyang.VRHospital.bean.MedicalDataBean;
import com.yuyang.VRHospital.bean.ZhiShiInfo;
import com.yuyang.VRHospital.common.Contants;
import com.yuyang.VRHospital.view.activity.DiagnoseExplainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by yuyang on 16/4/21.
 */
public class MedicalDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{
    private List<ZhiShiInfo> dataBeans = new ArrayList<>();
    private int loadState = Contants.CAN_LOAD_MORE;
    private int TYPE_ITEM = 0;
    private int TYPE_FOOT = 1;

    public MedicalDataAdapter(){
    }

    public void setLoadState(int loadState){
        this.loadState = loadState;
    }

    public int getLoadState(){
        return loadState;
    }

    public void setDataBeans(List<ZhiShiInfo> beans){
        if(beans != null) {
            this.dataBeans = beans;
            notifyDataSetChanged();
        }
    }

    public void addDataBeans(List<ZhiShiInfo> beans){
        if(beans != null){
            dataBeans.addAll(beans);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == getItemCount()-1){
            return TYPE_FOOT;
        }else {
            return TYPE_ITEM;
        }
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , ZhiShiInfo dataBean);
    }

    private Context mContent;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContent = parent.getContext();

        if(viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medical_data, parent, false);

            //view.setOnClickListener(this);//增加点击事件 //这点暂时注释掉 ，只用按钮查看详细
            MedicalDataHolder holder = new MedicalDataHolder(view);

            holder.btnDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    View view = (View)v.getParent().getParent();
                    ZhiShiInfo dataBean = (ZhiShiInfo)view.getTag();

                    Intent intent = new Intent(mContent, DiagnoseExplainActivity.class);
                    Bundle b = new Bundle();
                    b.putString(DiagnoseExplainActivity.TITLE, dataBean.getTitle());
                    b.putString(DiagnoseExplainActivity.LABLE, dataBean.getVipCotent());//dataBean.getDtContent());
                    intent.putExtras(b);
                    mContent.startActivity(intent);
                }
            });

            return holder;
        }else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item_footer, parent, false);
            return new FootHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  MedicalDataHolder) {
            MedicalDataHolder viewHolder = (MedicalDataHolder) holder;
            ZhiShiInfo dataBean = dataBeans.get(position);
            viewHolder.itemView.setTag(dataBean);//绑定数据

            viewHolder.name.setText(dataBean.getTitle());
            viewHolder.time.setText("No:"+dataBean.getCode());
        }else {
            FootHolder viewHolder = (FootHolder) holder;
            if(loadState == Contants.CAN_LOAD_MORE){
                viewHolder.footText.setText("加载更多");
                viewHolder.progress.setVisibility(View.VISIBLE);
            }else if (loadState == Contants.LOAD_FAIL){
                viewHolder.footText.setText("加载失败");
                viewHolder.progress.setVisibility(View.GONE);
            }else {
                viewHolder.footText.setText("没用更多了");
                viewHolder.progress.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据ZhiShiInfo dataBean
            mOnItemClickListener.onItemClick(v, (ZhiShiInfo)v.getTag());
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        if(dataBeans.size() == 0){
            return 0;
        }else {
            return dataBeans.size() + 1;
        }
    }

    class MedicalDataHolder extends RecyclerView.ViewHolder{
       // public View itemBg;
        public TextView name;
        public TextView time;
        public Button   btnDetail;
        public MedicalDataHolder(View itemView) {
            super(itemView);
           // itemBg = itemView.findViewById(R.id.medical_item_bg);
            time = (TextView) itemView.findViewById(R.id.medical_time);
            name = (TextView) itemView.findViewById(R.id.medical_name);
            btnDetail = (Button)itemView.findViewById(R.id.media_view_detail);
            //btnDetail.setVisibility(View.INVISIBLE);

            TextPaint tp = name.getPaint();
            tp.setFakeBoldText(true);

        }
    }

    class FootHolder extends RecyclerView.ViewHolder{
        public TextView footText;
        public View progress;

        public FootHolder(View itemView) {
            super(itemView);
            footText = (TextView) itemView.findViewById(R.id.footer_text);
            progress = itemView.findViewById(R.id.footer_progress);
        }
    }
}
