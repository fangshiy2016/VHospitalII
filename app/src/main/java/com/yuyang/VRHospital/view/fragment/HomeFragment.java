package com.yuyang.VRHospital.view.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.yuyang.VRHospital.BaseFragment;
import com.yuyang.VRHospital.R;
import com.yuyang.VRHospital.broadcast.AppPreference;
import com.yuyang.VRHospital.broadcast.LocalBroadcastReceiver;
import com.yuyang.VRHospital.cache.sp.SPDao;
import com.yuyang.VRHospital.cache.sp.SPKey;
import com.yuyang.VRHospital.network.Url;
import com.yuyang.VRHospital.presenter.HomeLogicPresenterImpl;
import com.yuyang.VRHospital.presenter.iPresenter.IHomeLogicPresenter;
import com.yuyang.VRHospital.utils.ContentUriUtil;
import com.yuyang.VRHospital.utils.CropImage;
import com.yuyang.VRHospital.utils.UploadFileUtil;
import com.yuyang.VRHospital.view.CircleImageView;
import com.yuyang.VRHospital.view.activity.MainActivity;
import com.yuyang.VRHospital.view.activity.SplashActivity;
import com.yuyang.VRHospital.view.adapter.HospitalBannerView;
import com.yuyang.VRHospital.view.fragment.iFragment.IHomeFragment;
import com.yuyang.VRHospital.utils.ImageLoaderUtils;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.Target;
import android.app.AlertDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

import static android.support.v4.content.PermissionChecker.checkSelfPermission;

/**
 * Created by yuyang on 2016/4/19.
 */
public class HomeFragment extends BaseFragment implements IHomeFragment,View.OnClickListener {
    @Bind(R.id.toolbar_title)
    TextView title;
    //@Bind(R.id.home_banner)
    //ConvenientBanner<Integer> banner;
    @Bind(R.id.toolbar_find)
    ImageView logoutBtn;

    @Bind(R.id.frag_doc_mine_head)
    CircleImageView docHeaderImage;
    @Bind(R.id.home_doc_name)
    TextView welcome;
    @Bind(R.id.home_doc_keshi)
    TextView docKeshi;

    @Bind(R.id.home_diagnose)
    View diagnose;
    @Bind(R.id.home_medical_data)
    View medicalData;
    @Bind(R.id.home_case_db)
    View caseDb;
    @Bind(R.id.home_activation)
    View activation;


    private int mIsActivation = -1;
    //网络加载banner图片用
    private ArrayList<Integer> images = new ArrayList<Integer>();

    private MainActivity activity;
    private Fragment mTheFragment;
    private IHomeLogicPresenter homeLogicPresenter;
    private CharSequence mMethods[] = {"拍照", "从手机相册中选取" };

    private static final int REQUEST_EXTERNAL_STORAGE_CAMERA = 10;
    private static final int REQUEST_EXTERNAL_STORAGE = 11;

    @Override
    public void setIsActivation(int isActivation){
        if(mIsActivation > 0 && isActivation == 1) {
            ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation((float) 1.0);
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
            docHeaderImage.setColorFilter(filter);


            String headerImage = Url.IMAGE_URI_RELEASE + SPDao.getSharedPreferences(SPKey.HEADER_IMAGE, "");
            showUserImageUrl(headerImage);

            activation.setEnabled(false);

            matrix.setSaturation((float)0.0);
            ((ImageView)activation).setColorFilter(new ColorMatrixColorFilter(matrix));
        }
        mIsActivation = isActivation;
    }

    @Override
    protected void initLayoutId() {
        layoutId = R.layout.fragment_home;
    }

    protected void showUserImageUrl(String headerImage){
        if(headerImage != null && headerImage != "") {
            ImageLoaderUtils.displayImage(getActivity(), headerImage, docHeaderImage, R.mipmap.default_doctor_icon, new com.bumptech.glide.request.RequestListener<String, GlideDrawable>() {

                @Override
                public boolean onException(Exception arg0, String arg1, Target<GlideDrawable> arg2, boolean arg3) {
                    return false;
                }

                @Override
                public boolean onResourceReady(GlideDrawable arg0, String arg1, Target<GlideDrawable> arg2, boolean arg3, boolean arg4) {
                    if (arg2 != null && arg0 != null) {
                        arg2.onResourceReady(arg0, null);
                    }
                    return false;
                }
            });
        }
    }

    @Override
    protected void initViews() {
        title.setText(R.string.home_title);
        String userName = SPDao.getSharedPreferences(SPKey.USER_NAME, "王医生");
        String strKeshi = SPDao.getSharedPreferences(SPKey.USER_KESHI, "");

        String headerImage = Url.IMAGE_URI_RELEASE + SPDao.getSharedPreferences(SPKey.HEADER_IMAGE, "");
        showUserImageUrl(headerImage);
        mTheFragment = this;
        //mIsActivation = SPDao.getSharedPreferences(SPKey.IS_ACTIVATION, 0);

        logoutBtn.setImageResource(R.mipmap.toolbar_logout);
        logoutBtn.setVisibility(View.VISIBLE);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SplashActivity.class);
                intent.putExtra("immediately", true);
                startActivity(intent);
                LocalBroadcastReceiver.notifyCloseMainActivity(getContext());
                getActivity().finish();
            }
        });

        welcome.setText(getResources().getString(R.string.home_welcome, userName));
        docKeshi.setText(getResources().getString(R.string.home_doc_keshi, strKeshi));

        /*
        images.add(R.mipmap.banner1);
        banner.setPages(new CBViewHolderCreator<HospitalBannerView>() {
            @Override
            public HospitalBannerView createHolder() {
                return new HospitalBannerView();
            }}, images)
            .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
            .startTurning(4000);
        */
        activity = (MainActivity) getActivity();
        diagnose.setOnClickListener(this);
        medicalData.setOnClickListener(this);
        caseDb.setOnClickListener(this);
        activation.setOnClickListener(this);
        homeLogicPresenter = new HomeLogicPresenterImpl(this);

        docHeaderImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setItems(mMethods, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                if((checkSelfPermission(mTheFragment.getContext(),
                                        Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED)
                                        || (ContextCompat.checkSelfPermission(mTheFragment.getContext(),
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                                        || (ContextCompat.checkSelfPermission(mTheFragment.getContext(),
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                                        )

                                {
                                    requestPermissions
                                            (new String[]{
                                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                                    Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA
                                            }, REQUEST_EXTERNAL_STORAGE_CAMERA);
                                }
                                else {
                                    CropImage.CropSmallImageFromCamera(mTheFragment);
                                    //CropSmallImageFromCamera();
                                }
                                break;

                            case 1:
                                if((checkSelfPermission(mTheFragment.getContext(),
                                        Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED)
                                        || (ContextCompat.checkSelfPermission(mTheFragment.getContext(),
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED))
                                {
                                    requestPermissions
                                            (new String[]{
                                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                                            }, REQUEST_EXTERNAL_STORAGE);
                                }
                                else {
                                    CropImage.CropSmallImageFromGallery(mTheFragment);
                                    //CropSmallImageFromGallery();
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
            }
        });
        //docHeaderImage.setImageURI(Uri.parse(headerImage));
        ColorMatrix matrix = new ColorMatrix();
        if(mIsActivation !=  1) {
            matrix.setSaturation((float)0.50);
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
            docHeaderImage.setColorFilter(filter);
        }
        else
        {
            matrix.setSaturation((float)0.0);
            ((ImageView)activation).setColorFilter(new ColorMatrixColorFilter(matrix));
            activation.setEnabled(false);

        }
    }

    @Override
    protected void initData() {

    }

    @Override
    public void initBanner(List<String> banners) {
        //如果想从网络获取banner的话实现此方法
    }

    @Override
    public void onClick(View v) {

        switch (mIsActivation) {
            case 1:
                break;
            case 2:
                Toast.makeText(getActivity(), R.string.home_pass_error, Toast.LENGTH_SHORT).show();
                return ;
            case 3:
                if(v.getId() != R.id.home_activation) {
                    Toast.makeText(getActivity(), R.string.home_not_active, Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
            case 4:
                Toast.makeText(getActivity(), R.string.home_not_bangding, Toast.LENGTH_SHORT).show();
                return ;
            case 5:
                Toast.makeText(getActivity(), R.string.home_account_invalid, Toast.LENGTH_SHORT).show();
                return ;
        }

        //if(v.getId() != R.id.home_activation){
        //    if(mIsActivation == 0){
        //        Toast.makeText(getActivity(), R.string.load_no_activation, Toast.LENGTH_LONG).show();
        //        return;
        //    }
        //}
        activity.setIsActivation(mIsActivation);
        switch (v.getId()){
            case R.id.home_diagnose:
                activity.changeFragment(MainActivity.DIAGNOSE_TYPE);
                break;
            case R.id.home_case_db:
                activity.changeFragment(MainActivity.CASE_TYPE);
                break;
            case R.id.home_medical_data:
                activity.changeFragment(MainActivity.MEDICAL_TYPE);
                break;
            case R.id.home_activation:

                new AlertDialog.Builder(getActivity()).setTitle("此账号将绑定此手机？")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                TelephonyManager tm = (TelephonyManager)activity.getSystemService(Context.TELEPHONY_SERVICE);
                                String deviceid = tm.getDeviceId();//获取智能设备唯一编号
                                String te1  = tm.getLine1Number();//获取本机号码
                                //String imei = tm.getSimSerialNumber();//获得SIM卡的序号
                                //String imsi = tm.getSubscriberId();//得到用户Id
                                String userCode = SPDao.getSharedPreferences(SPKey.USER_CODE, "");
                                homeLogicPresenter.activateAccount(userCode, te1, deviceid);
                            }
                        })
                        .setNegativeButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).show();

                break;
        }
    }

    @Override
    public void showErrorDialog() {
        Toast.makeText(getActivity(),R.string.load_fail,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoadProgress() {
        showProgressDialog();
    }

    @Override
    public void hideLoadProgress() {
        hideProgressDialog();
    }

    private void upLoad() {
        if (TextUtils.isEmpty(CropImage.getPath())) {
            return;
        }
        String userCode = SPDao.getSharedPreferences(SPKey.USER_CODE, "");
        UploadFileUtil uploadFileUtil = new UploadFileUtil(Url.URL_UPLOAD_EDITUSERHEAD, CropImage.getPath(), userCode);
        uploadFileUtil.setUploadListener(new UploadFileUtil.UploadListener() {

            @Override
            public void onSuccess(String filePath, String imageUrl) {

                String headerImage = Url.IMAGE_URI_RELEASE + imageUrl;
                showUserImageUrl(headerImage);

                SPDao.saveSharedPreferences(SPKey.HEADER_IMAGE, headerImage);
            }

            @Override
            public void onError(String filePath) {
            }
        });

        uploadFileUtil.upload();
    }

    @Override
    public void onActivityResult(int requestCode, int arg1, Intent data) {
        switch (requestCode) {

            case CropImage.TAKE_SMALL_PICTURE:
                if (!TextUtils.isEmpty(CropImage.getPath())) {
                    CropImage.cropImageUri(mTheFragment, Uri.fromFile(new File(CropImage.getPath())), true, 200, 200,
                            CropImage.CROP_SMALL_PICTURE);
                }
                break;

            case CropImage.CHOOSE_SMALL_PICTURE:
                if (data != null && !TextUtils.isEmpty(CropImage.getPath())) {
                    Bitmap bitmap = data.getParcelableExtra("data");
                    if (bitmap != null) {
                        docHeaderImage.setImageBitmap(bitmap);
                        upLoad();
                    }
                }

                break;

            case CropImage.CROP_SMALL_PICTURE:
                if (!TextUtils.isEmpty(CropImage.getPath())) {
                    Uri imageUri = Uri.fromFile(new File(CropImage.getPath()));
                    if (imageUri != null) {
                        Bitmap bitmap = CropImage.decodeUriAsBitmap(mTheFragment, imageUri);
                        if (bitmap != null) {
                            docHeaderImage.setImageBitmap(bitmap);
                            upLoad();
                        }
                    } else {

                    }
                }

                break;

            case CropImage.CHOOSE_SMALL_PICUTRE_FROM_KAKAT:
                if (data != null) {
                    Uri uri = data.getData();
                    String realPath = ContentUriUtil.getRealPathFromURI(mTheFragment.getContext(), uri);
                    Uri realUri = Uri.fromFile(new File(realPath));
                    CropImage.cropImageUri(mTheFragment, realUri, false, 200, 200, CropImage.CROP_SMALL_PICTURE);
                }

                break;

            default:
                break;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {

        if (requestCode == REQUEST_EXTERNAL_STORAGE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                CropImage.CropSmallImageFromGallery(mTheFragment);
            } else
            {
                // Permission Denied
                Toast.makeText(getActivity(), "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }


        if (requestCode == REQUEST_EXTERNAL_STORAGE_CAMERA)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                CropImage.CropSmallImageFromCamera(mTheFragment);
            } else
            {
                // Permission Denied
                Toast.makeText(getActivity(), "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
