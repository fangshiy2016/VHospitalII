package com.yuyang.VRHospital.view.fragment.iFragment;

import com.yuyang.VRHospital.IBaseInterface;

import java.util.List;

/**
 * Created by yuyang on 16/4/20.
 */
public interface IHomeFragment extends IBaseInterface {
    void initBanner(List<String> banners);

    void setIsActivation(int isActivation);
}
