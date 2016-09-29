# Banner [![](https://jitpack.io/v/Brioal/Banner.svg)](https://jitpack.io/#Brioal/Banner)
仿知乎日报首页轮播图
效果对比演示

![](https://github.com/Brioal/Banner/blob/master/art/1.gif)|![](https://github.com/Brioal/Banner/blob/master/art/2.gif)

#usage
##Step 1. Add the JitPack repository to your build file

###Add it in your root build.gradle at the end of repositories:
```

	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
	
```
##Step 2. Add the dependency
```
	dependencies {
	        compile 'com.github.Brioal:Banner:1.0'
	}
	
```

```
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
```
