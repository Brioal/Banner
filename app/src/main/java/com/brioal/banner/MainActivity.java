package com.brioal.banner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.brioal.brioalbanner.entity.BannerEntity;
import com.brioal.brioalbanner.interfaces.OnPagerClickListener;
import com.brioal.brioalbanner.view.Banner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Banner mBanner;
    private List<BannerEntity> mList;
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBanner = (Banner) findViewById(R.id.main_banner);
        mList = new ArrayList<>();
        mList.add(new BannerEntity(1, "http://123.206.20.217/testimage/img_1.png", "第一个提示", "第一个链接"));
        mList.add(new BannerEntity(2, "http://123.206.20.217/testimage/img_2.png", "第二个提示", "第二个链接"));
        mList.add(new BannerEntity(3, "http://123.206.20.217/testimage/img_3.png", "第三个提示", "第三个链接"));
        mList.add(new BannerEntity(4, "http://123.206.20.217/testimage/img_4.png", "第四个提示", "第四个链接"));
        mList.add(new BannerEntity(5, "http://123.206.20.217/testimage/img_5.png", "第五个提示", "第五个链接"));
        mBanner.setList(mList);
        mBanner.setPagerClickListener(new OnPagerClickListener() {
            @Override
            public void onClick(BannerEntity entity, int position) {
                if (mToast == null) {
                    mToast = Toast.makeText(MainActivity.this, entity.getTip(), Toast.LENGTH_SHORT);
                } else {
                    mToast.setText(entity.getClickUrl());
                }
                mToast.show();
            }
        });
    }
}
