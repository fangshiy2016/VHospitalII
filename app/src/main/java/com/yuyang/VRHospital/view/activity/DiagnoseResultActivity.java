package com.yuyang.VRHospital.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yuyang.VRHospital.BaseActivity;
import com.yuyang.VRHospital.R;
import com.yuyang.VRHospital.bean.CaseItemBean;
import com.yuyang.VRHospital.bean.ResultBean;
import com.yuyang.VRHospital.bean.ResultItemBean;
import com.yuyang.VRHospital.cache.sp.SPDao;
import com.yuyang.VRHospital.cache.sp.SPKey;
import com.yuyang.VRHospital.network.Url;
import com.yuyang.VRHospital.presenter.CaseSavePresenterImpl;
import com.yuyang.VRHospital.presenter.ResultPresenterImpl;
import com.yuyang.VRHospital.presenter.iPresenter.ICaseSavePresenter;
import com.yuyang.VRHospital.presenter.iPresenter.IResultPresenter;
import com.yuyang.VRHospital.utils.encrypt.RandomCode;
import com.yuyang.VRHospital.view.activity.iView.IDiagnoseResultActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Hashtable;
import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import butterknife.Bind;

/**
 * Created by yuyang on 16/4/23.
 * 诊断结果
 */
public class DiagnoseResultActivity extends BaseActivity implements IDiagnoseResultActivity {
    public static final String TAG = "tag";
    public static final String ANSWER_CODE = "AnswerCode";

    //public static final String BASE_AGE      = "BaseAge";
    //public static final String BASE_SEX      = "BaseSex";
    //public static final String BASE_MARRIAGE = "BaseMarriage";

    private int QR_WIDTH = 100, QR_HEIGHT = 100;
    //@Bind(R.id.result_age)
   // TextView mAge;
   // @Bind(R.id.result_marriage_state)
   // TextView mMarriageState;
   // @Bind(R.id.result_sex)
    TextView mSex;
    @Bind(R.id.result_qr_code)
    //SimpleDraweeView mQrCode;
    ImageView mQrCode;
    @Bind(R.id.result_code)
    TextView mCode;

    @Bind(R.id.result_info_layout)
    LinearLayout mInfoLayout;

    //@Bind(R.id.result_zhiliao_layout)
    //LinearLayout mZhiliaoLayout;

    @Bind(R.id.result_question)
    ImageView mQuestionZhishi;

    @Bind(R.id.result_doctor_advice)
    TextView mDoctorAdvice;

    @Bind(R.id.result_zhenduan_info)
    TextView mZhenduanInfo;

    @Bind(R.id.zhenduan_result_title)
    TextView mResultTitle;

    //@Bind(R.id.result_zhishi_info)
    //TextView mZhishiInfo;

    @Bind(R.id.case_submit)
    Button submit;

    private String mZhishiTitle     = "";
    private String mZhishiContent   = "";
    private Activity theActivity;
    private IResultPresenter resultPresenter;
    private ICaseSavePresenter caseSavePresenter;
    private CaseItemBean mCaseItem = new CaseItemBean();

    public  void disableSubmit() {
        mCode.setVisibility(View.VISIBLE);
        mQrCode.setVisibility(View.VISIBLE);
        submit.setEnabled(false);
        submit.setTextColor(getResources().getColor(R.color.font_color_dark));
        submit.setBackgroundResource(R.drawable.btn_register_select);
    }

    @Override
    public void initLayoutId() {
        layoutId = R.layout.activity_diagnose_result;
    }

    @Override
    public void initViews() {
        //showBackAction(true);
        //titleView.setText(R.string.result_title);
        getSupportActionBar().setTitle(R.string.result_title);

        String tag = getIntent().getStringExtra(TAG);
        String answerCode = getIntent().getStringExtra(ANSWER_CODE);

        //String baseAge      = getIntent().getStringExtra(BASE_AGE);
        //String baseMarriage = getIntent().getStringExtra(BASE_MARRIAGE);
        //boolean bSexMan     = getIntent().getBooleanExtra(BASE_SEX, true);

        //mAge.setText(baseAge);
        //mSex.setText(bSexMan ? "男":"女");
        //mMarriageState.setText(baseMarriage);
        //mQrCode.setImageURI(Uri.parse("http://img.my.csdn.net/uploads/201604/18/1460943716_5112.jpg"));//resultBean.getQr_code()));
        //mCode.setText(getResources().getString(R.string.result_code, "ED34D"));//resultBean.getCode()));

        //if(!baseAge.isEmpty() && baseAge != null) mCaseItem.setAge(Integer.parseInt(baseAge));
        //if(bSexMan == false)
        //    mCaseItem.setSex(1);
        //else
        //   mCaseItem.setSex(0);
        //mCaseItem.setJieHunStatus(2);

        if(TextUtils.isEmpty(tag)){
            Toast.makeText(this, "请求参数不能为空", Toast.LENGTH_SHORT).show();
        }
        //answerCode = "HPVJL1";//测试专用
        resultPresenter = new ResultPresenterImpl(this);
        resultPresenter.loadResult(answerCode);

        caseSavePresenter = new CaseSavePresenterImpl(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                caseSavePresenter.saveCaseBean(mCaseItem);
            }
        });

        //mCode.setVisibility(View.INVISIBLE);
        //mQrCode.setVisibility(View.INVISIBLE);
        theActivity = this;
        mQuestionZhishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(theActivity, DiagnoseExplainActivity.class);
                Bundle b = new Bundle();
                b.putString(DiagnoseExplainActivity.TITLE, mZhishiTitle);
                b.putString(DiagnoseExplainActivity.LABLE, mZhishiContent);//dataBean.getDtContent());
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setContent(ResultBean resultBean) {
        String strCaseCode = RandomCode.getSecurityCode();
        mCode.setText("病历推送码:" + strCaseCode);

        createQRImage(Url.WEIXIN_URI_RELEASE + strCaseCode);
        mCaseItem.setCaseCode(strCaseCode);

        mCaseItem.setCreateData(new java.sql.Date(System.currentTimeMillis()));

        String strKeshi = "";
        strKeshi = SPDao.getSharedPreferences(SPKey.USER_KESHI, "");
        mCaseItem.setKeShi(strKeshi);//resultBean.getResult().getKeShiModel().getName());
        String userName = SPDao.getSharedPreferences(SPKey.USER_NAME, "王医生");
        String userCode = SPDao.getSharedPreferences(SPKey.USER_CODE, "");

        String zhenDuanInfo = SPDao.getSharedPreferences(SPKey.ZHENDUN_INFO, "");

        mCaseItem.setDoctorName(userName);
        mCaseItem.setDoctorCode(userCode);
        mCaseItem.setZdXinxi(zhenDuanInfo);

        mZhenduanInfo.setText(Html.fromHtml(zhenDuanInfo));
        mCaseItem.setSex(1);
        //mDoctorAdvice.setText();//resultBean.getDoctor_advice());
        String zhishiContent;
        String resultContent;
        //List<ResultItemBean> jianceBeans = new ArrayList<>();
        List<ResultItemBean> zhiliaoBeans = new ArrayList<>();
        if(resultBean.getResult().getTitle() != null) {
            //mCaseItem.setJieLun(resultBean.getResult().getTitle());
            //jianceBeans.add(new ResultItemBean("结论", resultBean.getResult().getTitle()));
            mResultTitle.setText(resultBean.getResult().getTitle());
        }
        if(resultBean.getResult().getContent() != null) {
            mCaseItem.setJieLun(resultBean.getResult().getContent());
        }

        //if(resultBean.getResult().getKeShiModel() != null) {
        //    jianceBeans.add(new ResultItemBean("科室", strKeshi));//resultBean.getResult().getKeShiModel().getName()));
        //}

        //String jianceInfo = "";
        //if(resultBean.getResult().getJianCeXiangList() != null){
        //    for(JianCeBean item:resultBean.getResult().getJianCeXiangList()) {
        //        if(jianceInfo != "") jianceInfo += ",";

        //        jianceInfo += item.getName();
        //    }
        //    jianceBeans.add(new ResultItemBean("检测项", jianceInfo));
        //    mCaseItem.setZdXinxi(jianceInfo);
        //}
        /*
        if(resultBean.getResult().getZhiLiaoXiangList() != null){
            for(ZhiLiaoBean item:resultBean.getResult().getZhiLiaoXiangList()) {

                zhiliaoBeans.add(new ResultItemBean(item.getTitle(), XmlParseUtil.TrimHtmltag(item.getContent())));
            }
        }
        */
        if(resultBean.getResult().getZhiShiModel() != null) {
            //zhishiContent = resultBean.getResult().getZhiShiModel().getTitle();
            mZhishiTitle     = resultBean.getResult().getZhiShiModel().getTitle();
            mZhishiContent   = resultBean.getResult().getZhiShiModel().getVipCotent();

            mCaseItem.setZhiShiCode(resultBean.getResult().getZhiShiModel().getCode());

            //zhishiContent += ":";
            //zhishiContent = resultBean.getResult().getZhiShiModel().getDtContent();
            //mZhishiInfo.setText(Html.fromHtml(zhishiContent));
        }

        resultContent    = resultBean.getResult().getContent();
        if(resultContent != null) {
            //mCaseItem.setJyJianyi(resultContent);
            mDoctorAdvice.setText(Html.fromHtml(resultContent));

        }
        //ResultItemBean itemBean6 = new ResultItemBean("知识点:",resultBean.getResult().getZhiShiCode());
        //ResultItemBean itemBean3 = new ResultItemBean("医学建议:",resultBean.getResult().getContent());
        //List<ResultItemBean> itemBeans = new ArrayList<>();
        //itemBeans.add(itemBean1);
        //itemBeans.add(itemBean2);
        //itemBeans.add(itemBean3);
        //itemBeans.add(itemBean4);
        //itemBeans.add(itemBean5);
        //itemBeans.add(itemBean6);

        /*
        if(jianceBeans != null)
        {
            for (ResultItemBean itemBean:jianceBeans){
                View itemView = LayoutInflater.from(this).inflate(R.layout.layout_result_info_item, null);
                TextView lable = (TextView) itemView.findViewById(R.id.result_item_lable);
                TextView content = (TextView) itemView.findViewById(R.id.result_item_content);
                lable.setText(getResources().getString(R.string.result_item_label, itemBean.getLable()));
                content.setText(itemBean.getContent());

                mInfoLayout.addView(itemView);
            }
        }
        */
        /*
        if(zhiliaoBeans != null)
        {
            for (ResultItemBean itemBean:zhiliaoBeans){
                View itemView = LayoutInflater.from(this).inflate(R.layout.layout_result_info_item, null);
                TextView lable = (TextView) itemView.findViewById(R.id.result_item_lable);
                TextView content = (TextView) itemView.findViewById(R.id.result_item_content);
                lable.setText(getResources().getString(R.string.result_item_label, itemBean.getLable()));
                content.setText(itemBean.getContent());
                mZhiliaoLayout.addView(itemView);
            }
        }*/

    }

    @Override
    public void showErrorDialog() {
        Toast.makeText(this,R.string.load_fail,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoadProgress() {
        showProgressDialog();
    }

    @Override
    public void hideLoadProgress() {
        hideProgressDialog();
    }

    //要转换的地址或字符串,可以是中文
    public void createQRImage(String url)
    {
        try
        {
            //判断URL合法性
            if (url == null || "".equals(url) || url.length() < 1)
            {
                return;
            }
            Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();

            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            Integer  margin = 1;
            hints.put(EncodeHintType.MARGIN, 1);
            //图像数据转换，使用了矩阵转换
            BitMatrix bitMatrix = new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
            int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
            //下面这里按照二维码的算法，逐个生成二维码的图片，
            //两个for循环是图片横列扫描的结果
            for (int y = 0; y < QR_HEIGHT; y++)
            {
                for (int x = 0; x < QR_WIDTH; x++)
                {
                    if (bitMatrix.get(x, y))
                    {
                        pixels[y * QR_WIDTH + x] = 0xff000000;
                    }
                    else
                    {
                        pixels[y * QR_WIDTH + x] = 0xffffffff;
                    }
                }
            }
            //生成二维码图片的格式，使用ARGB_8888
            Bitmap bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
            //显示到一个ImageView上面
            mQrCode.setImageBitmap(bitmap);
        }
        catch (WriterException e)
        {
            e.printStackTrace();
        }
    }
}
