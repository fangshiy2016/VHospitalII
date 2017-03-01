package com.yuyang.VRHospital.view.form;

import android.content.Context;
import android.view.ViewGroup;

import com.yuyang.VRHospital.R;
import com.yuyang.VRHospital.bean.DiagnoseItemBean;
import com.yuyang.VRHospital.bean.HistoryItemBean;
import com.yuyang.VRHospital.view.form.ImageForm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuyang on 16/4/23.
 */
public class DynamicFormHelper {

    public static final String CELL_TYPE_EDIT = "edit";//字符内容
    public static final String CELL_TYPE_DATE = "date";//日期
    public static final String CELL_TYPE_RADIO = "radio";//单选------0判断
    public static final String CELL_TYPE_CHECKBOX = "checkbox";//多选
    public static final String CELL_TYPE_SPINNER = "spinner";//下拉
    public static final String CELL_TYPE_NUMBER = "number";//数字-------表示数字大小问题
    public static final String CELL_TYPE_LIM_NUMBER = "lim_number";//数字带范围-------表示数值范围
    public static final String CELL_TYPE_IMAGE = "image";//图文问题-----------------表示图片选择类问题
    public static final String CELL_TYPE_UNKNOWN = "unknown";//图文问题-----------------表示图片选择类问题

    public static final int QUESTION_TYPE_JUDGE  = 0;//单选------0判断
    public static final int QUESTION_TYPE_NUMBER = 1;//数字-------表示数字大小问题
    public static final int QUESTION_TYPE_LIM_NUMBER = 2;//数字带范围-------表示数值范围
    public static final int QUESTION_TYPE_IMAGE = 3;//图文问题-----------------表示图片选择类问题
    public static final int QUESTION_TYPE_STRING = 4;//字符内容

    public static String qType2CellType(int qType) {
        switch (qType){
            case QUESTION_TYPE_JUDGE: return CELL_TYPE_RADIO;
            case QUESTION_TYPE_NUMBER: return CELL_TYPE_NUMBER;
            case QUESTION_TYPE_LIM_NUMBER: return CELL_TYPE_LIM_NUMBER;
            case QUESTION_TYPE_IMAGE: return CELL_TYPE_IMAGE;
            default:return CELL_TYPE_UNKNOWN;
        }
    }
    private Context context;
    private List<BaseForm> listForm;
    private List<ImageForm> listImageForm;

    private ViewGroup parent;
    private int layout_id;

    public DynamicFormHelper(Context context,ViewGroup parent,int layout_id){
        this.context = context;
        this.parent = parent;
        this.layout_id = layout_id;
        listForm = new ArrayList<BaseForm>();
        listImageForm = new ArrayList<ImageForm>();
    }

    /**
     * 展示动态表单
     */
    public void parseDiagnoseShow(List<DiagnoseItemBean> itemBeans){
        listForm.clear();
        listImageForm.clear();
        parent.removeAllViews();
        for(DiagnoseItemBean itemBean:itemBeans){
            BaseForm dtt = null;
            ImageForm imf = null;

            int qType = itemBean.getqType();
            String type = qType2CellType(qType);

            if(CELL_TYPE_EDIT.equals(type)){//编辑框
                dtt = new EditForm(context, parent, itemBean.getCode(), itemBean.getZhiShiCode(), layout_id);
            }else if(CELL_TYPE_DATE.equals(type)){//时间选择
                dtt = new DateForm(context,parent, itemBean.getCode(),itemBean.getZhiShiCode(),layout_id);
            }else if(CELL_TYPE_RADIO.equals(type)){//单选
                dtt = new RadioForm(context,parent, itemBean.getCode(),itemBean.getZhiShiCode(),layout_id);
                dtt.setItemContentNoBg();
            }else if(CELL_TYPE_CHECKBOX.equals(type)){//多选
                dtt = new CheckBoxForm(context,parent, itemBean.getCode(),itemBean.getZhiShiCode(),layout_id);
                dtt.setItemContentNoBg();
            }else if(CELL_TYPE_SPINNER.equals(type)){//下拉
                dtt = new SpinnerForm(context,parent, itemBean.getCode(),itemBean.getZhiShiCode(),layout_id);
            }else if(CELL_TYPE_NUMBER.equals(type)){//数字
                dtt = new NumberForm(context, parent, itemBean.getCode(), itemBean.getZhiShiCode(), layout_id);
            }else if(CELL_TYPE_LIM_NUMBER.equals(type)){//有范围的数字
                dtt = new NumberForm(context, parent, itemBean.getCode(), itemBean.getZhiShiCode(), layout_id);
            }else if(CELL_TYPE_IMAGE.equals(type)){//图文
                //图片的还没想好实现方式
                imf = new ImageForm(context, parent, itemBean.getCode(), itemBean.getZhiShiCode(), R.layout.layout_diagnose_img_item);
                if(imf != null) {

                    imf.addToParent(itemBean.getTitle(), itemBean.getOption(), false, true);//暂时去掉往期追溯功能
                    listImageForm.add(imf);
                }
                //dtt = new ListImageForm(context, parent, itemBean.getZhiShiCode(), layout_id);
            }
            if(dtt != null){
                dtt.addToParent(qType, itemBean.getTitle(), itemBean.getOption(), false, true);//暂时去掉往期追溯功能
                listForm.add(dtt);
            }
        }
    }

    /**
     * 展示动态表单
     */
    public void parseHistoryShow(List<HistoryItemBean> itemBeans){
        for(HistoryItemBean itemBean:itemBeans){
            BaseForm dtt = null;
            String type = itemBean.getType();

            if(CELL_TYPE_EDIT.equals(type)){//编辑框
                dtt = new EditForm(context,parent, itemBean.getLable(),itemBean.getTag(),layout_id);
            }else if(CELL_TYPE_DATE.equals(type)){//时间选择
                dtt = new DateForm(context,parent, itemBean.getLable(),itemBean.getTag(),layout_id);
            }else if(CELL_TYPE_RADIO.equals(type)){//单选
                dtt = new RadioForm(context,parent, itemBean.getLable(),itemBean.getTag(),layout_id);
            }else if(CELL_TYPE_CHECKBOX.equals(type)){//多选
                dtt = new CheckBoxForm(context,parent, itemBean.getLable(),itemBean.getTag(),layout_id);
            }else if(CELL_TYPE_SPINNER.equals(type)){//下拉
                dtt = new SpinnerForm(context,parent, itemBean.getLable(),itemBean.getTag(),layout_id);
            }else if(CELL_TYPE_NUMBER.equals(type)) {//数字
                dtt = new NumberForm(context, parent, itemBean.getLable(), itemBean.getTag(), layout_id);
            }
            else if(CELL_TYPE_IMAGE.equals(type)){//图文
                //图片的还没想好实现方式
                dtt = new ImagesForm(context, parent, itemBean.getLable(), itemBean.getTag(), layout_id);
            }
            if(dtt != null){
                dtt.addToParent(0, itemBean.getLable(),itemBean.getContent(),false,true);
                listForm.add(dtt);
            }
        }
    }

    /**
     * 获取动态表单内容
     * @return
     */
    public String CheckBaseForm(){
        String checkInfo = "";
        for (BaseForm baseForm:listForm){
            if(baseForm.checkPostInfo() != true){

                checkInfo += baseForm.getLable() + " 输入值错误\r\n";
            }
        }
        return checkInfo;
    }

    public Map<String,String> getSubmitParams(){
        Map<String,String> params = new HashMap<>();
        for (BaseForm baseForm:listForm){
            if(baseForm.getqType() == 0 || baseForm.getqType() == 3){
                if(baseForm.getValue() >= 0) {
                    params.put(baseForm.getTag(), Integer.toString(baseForm.getValue()));
                }
            }else {
                params.put(baseForm.getTag(), baseForm.getContent());
            }
        }

        for (ImageForm imgForm:listImageForm){
           params.put(imgForm.getTag(), Integer.toString(imgForm.getValue()));
        }

        return params;
    }

    //for 20160905 得到问题和答案
    public String getSubmitContent(){
        String zhenDuanInfo = "";
        //Map<String,String> params = new HashMap<>();
        for (BaseForm baseForm:listForm){

            if(zhenDuanInfo != "") zhenDuanInfo += "<br>";
            zhenDuanInfo += baseForm.getLable() +"(<b>" + baseForm.getContent() + "</b>)";
        }

        //for (ImageForm imgForm:listImageForm){
        //    params.put(imgForm.getLable(),  imgForm.getContent());
        //}

        return zhenDuanInfo;
    }
    //end
}
