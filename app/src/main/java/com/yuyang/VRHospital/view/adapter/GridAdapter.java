package com.yuyang.VRHospital.view.adapter;

import android.widget.BaseAdapter;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;;
import android.widget.ImageView;

import com.yuyang.VRHospital.R;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.Target;
import com.yuyang.VRHospital.network.Url;
import com.yuyang.VRHospital.utils.ImageLoaderUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Created by fanshy on 2016/6/25.
 */
public class GridAdapter extends BaseAdapter {
    private Context    mContext;
    private String[]   mImagePaths;
    private Map<Integer, Boolean> mSelectMap = new HashMap<Integer, Boolean>();

    private int imageIndex = 0;
    private Vector<Integer> mImageIds = new Vector<Integer>();
    private Vector<Boolean> mImage_bs = new Vector<Boolean>();
    private int lastPosition = -1;

    /**
     * 记录是否刚打开程序，用于解决进入程序不滚动屏幕，不会下载图片的问题。
     * 参考http://blog.csdn.net/guolin_blog/article/details/9526203#comments
     */
    public GridAdapter(Context ctx, String[] imagePaths) {
        mContext = ctx;
        mImagePaths  = imagePaths;

        for (String imgPath:imagePaths){
            mImageIds.add(imageIndex++);
            mImage_bs.add(false);
        }
    }

    public int GetSelectItem() {
        for (int i = 0; i < mImage_bs.size(); i++) {
            if (mImage_bs.elementAt(i) == true)
                return i;
        }
        return -1;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mImagePaths.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public void changeState(int position){
        if(lastPosition != -1 &&  position != lastPosition)
            mImage_bs.setElementAt(false, lastPosition);        //取消上一次的选中状态
        mImage_bs.setElementAt(!mImage_bs.elementAt(position), position);     //直接取反即可
        lastPosition = position;
        notifyDataSetChanged();         //通知适配器进行更新
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        View view = View.inflate(mContext, R.layout.layout_form_grid_item, null);
        ImageView imageView   = (ImageView) view.findViewById(R.id.grid_item_img);
        ImageView imgSelectView   = (ImageView) view.findViewById(R.id.grid_item_img_select);

        imgSelectView.setVisibility(View.INVISIBLE);
        String imageUrl = mImagePaths[position];
        imageUrl = Url.IMAGE_URI_RELEASE + imageUrl;

        //imageUrl = "http://img04.tooopen.com/images/20131109/sy_46281282539.jpg";
        ImageLoaderUtils.displayImage(mContext, imageUrl, imageView, R.mipmap.test, new com.bumptech.glide.request.RequestListener<String, GlideDrawable>() {
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
        if(mImage_bs.elementAt(position) == false)
            imgSelectView.setVisibility(View.INVISIBLE);
        else
            imgSelectView.setVisibility(View.VISIBLE);
        return view;

    }
}
