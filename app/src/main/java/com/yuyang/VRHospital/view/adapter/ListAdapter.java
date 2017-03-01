package com.yuyang.VRHospital.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.Target;
import com.yuyang.VRHospital.R;
import com.yuyang.VRHospital.network.Url;
import com.yuyang.VRHospital.utils.ImageLoaderUtils;

/**
 * Created by fanshy on 2016/6/26.
 */
public class ListAdapter extends BaseAdapter{
    private Context mContext;
    private String[] mImagePaths;

    public ListAdapter(Context context, String[] imagePaths) {
        // TODO Auto-generated constructor stub
        this.mContext = context;
        this.mImagePaths  = imagePaths;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mImagePaths.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View arg1, ViewGroup arg2) {
        // TODO Auto-generated method stub
        ViewHolder holder;
        holder = new ViewHolder();
        if (arg1 == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            arg1 = inflater.inflate(R.layout.layout_form_list_item, null);
            holder.imagView = (ImageView) arg1.findViewById(R.id.item_img_view);
            holder.tv_title = (TextView) arg1.findViewById(R.id.item_title_view);
            holder.tv_content = (TextView) arg1.findViewById(R.id.item_content_view);
            arg1.setTag(holder);
        } else {
            holder = (ViewHolder) arg1.getTag();
        }
        String imageUrl = Url.BASE_URI_RELEASE + mImagePaths[position];
        imageUrl = "http://e.hiphotos.baidu.com/image/h%3D200/sign=15c942e80dfa513d4eaa6bde0d6d554c/64380cd7912397dd25024f305c82b2b7d0a2878f.jpg";
        ImageLoaderUtils.displayImage(mContext, imageUrl, holder.imagView, R.mipmap.test, new com.bumptech.glide.request.RequestListener<String, GlideDrawable>() {

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

        holder.tv_title.setText("");
        holder.tv_content.setText("");
        return arg1;
    }

    private static class ViewHolder {
        private ImageView imagView;
        private TextView tv_title;
        private TextView tv_content;

    }


}
