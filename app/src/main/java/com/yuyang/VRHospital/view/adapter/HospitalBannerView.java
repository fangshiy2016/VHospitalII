package com.yuyang.VRHospital.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.yuyang.VRHospital.R;

/**
 * Created by yuyang on 16/4/20.
 * 网络加载banner时需要把泛型改为String
 */
public class HospitalBannerView implements Holder<Integer> {
    private View view;

    @Override
    public View createView(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.hospital_banner_view, null);
        return view;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        ImageView img = (ImageView) view.findViewById(R.id.hospital_banner_view);
        img.setImageResource(data);
    }
}
