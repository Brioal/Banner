package com.brioal.brioalbanner.entity;

import java.io.Serializable;

/**ViewPager Content Entity
 * Created by Brioal on 2016/8/31.
 */

public class BannerEntity implements Serializable{
    private int mIndex; //序号
    private String mImageUrl; //图片链接
    private String mTip; //提示信息
    private String mClickUrl; //内容链接


    public BannerEntity(int index, String imageUrl, String tip, String clickUrl) {
        mIndex = index;
        mImageUrl = imageUrl;
        mTip = tip;
        mClickUrl = clickUrl;
    }

    public int getIndex() {
        return mIndex;
    }

    public void setIndex(int index) {
        mIndex = index;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public String getTip() {
        return mTip;
    }

    public void setTip(String tip) {
        mTip = tip;
    }

    public String getClickUrl() {
        return mClickUrl;
    }

    public void setClickUrl(String clickUrl) {
        mClickUrl = clickUrl;
    }
}
