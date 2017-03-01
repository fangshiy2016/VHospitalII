package com.yuyang.VRHospital.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.yuyang.VRHospital.R;
import com.yuyang.VRHospital.cache.sp.SPDao;
import com.yuyang.VRHospital.cache.sp.SPKey;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by yuyang on 16/4/7.
 */
public class LeadActivity extends AppCompatActivity {

    @Bind(R.id.lead_viewpager)
    ViewPager mViewPager;
    @Bind(R.id.lead_indicator)
    CircleIndicator mIndicator;
    @Bind(R.id.lead_btn)
    Button mBtn;


    private int[] leads = new int[]{
            R.mipmap.lead_01,
            R.mipmap.lead_02,
            R.mipmap.lead_03,
            R.mipmap.lead_04 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead);
        ButterKnife.bind(this);

        final Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);

        mViewPager.setAdapter(new LeadAdapter());
        mIndicator.setViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == (leads.length - 1)) {
                    mBtn.setVisibility(View.VISIBLE);
                    mBtn.startAnimation(fadeIn);
                } else {
                    mBtn.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//滑动完了就去登录
                Intent intent = new Intent(LeadActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        SPDao.saveSharedPreferences(SPKey.NEW_VERSION, false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private class LeadAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return leads.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(LeadActivity.this);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(leads[position]);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }
}
