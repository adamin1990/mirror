package com.lt.cameras;

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WelcomeActivity.java

import cn.waps.AdInfo;
import cn.waps.AppConnect;

import com.lt.desktopcamera.R;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WelcomeActivity extends Activity {
	// 抽屉广告布局
		private View slidingDrawerView;
	public WelcomeActivity() {
	}

//	public void onAd1(View view) {
//		//显示推荐列表（游戏）
////		AppConnect.getInstance(this).showGameOffers(this);
//	}
//
//	public void onAd2(View view) {
//		//显示推荐列表（软件）
////		AppConnect.getInstance(this).showAppOffers(this);
//	}
//
//	public void onAd3(View view) {
////		AppConnect.getInstance(this).showPopAd(this);
//	}
//
//	public void onAd4(View view) {
//		//获取一条自定义广告数据
////		AdInfo adInfo = AppConnect.getInstance(WelcomeActivity.this).getAdInfo();
////		AppDetail.getInstanct().showAdDetail(WelcomeActivity.this,adInfo);
//	}

	public void onAd5(View view) {
		//调用功能广告接口（使用浏览器接口）
		String uriStr = "http://user.qzone.qq.com/14846869";
		AppConnect.getInstance(this).showBrowser(this, uriStr);
	}

//	public void onAd6(View view) {
//		//根据指定的应用app_id展示其详情
////		AppConnect.getInstance(this).showMore(this, "e50b93cf3f78c52387a4018eb088edea");
//	}

	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_main);
		
//		// 初始化统计器，并通过代码设置APP_ID, APP_PID
//		AppConnect
//				.getInstance("e50b93cf3f78c52387a4018eb088edea", "huawei", this);
		if (android.os.Build.VERSION.SDK_INT >= 16)
			((TextView) findViewById(R.id.editText1))
					.setText("1. 通过点击下面的按钮启动.\n2. 选择镜像壁纸作为你的动态壁纸。");
//		// 预加载自定义广告内容（仅在使用了自定义广告、抽屉广告或迷你广告的情况，才需要添加）
//				AppConnect.getInstance(this).initAdInfo();
//				
//				// 预加载插屏广告内容（仅在使用到插屏广告的情况，才需要添加）
//				AppConnect.getInstance(this).initPopAd(this);
//				
//				// 预加载功能广告内容（仅在使用到功能广告的情况，才需要添加）
//		    	AppConnect.getInstance(this).initFunAd(this);
//		    	// 带有默认参数值的在线配置，使用此方法，程序第一次启动使用的是"defaultValue"，之后再启动则是使用的服务器端返回的参数值
//				String showAd = AppConnect.getInstance(this).getConfig("showAd", "defaultValue");
//				// 互动广告调用方式
//				LinearLayout layout = (LinearLayout) this.findViewById(R.id.adLayout3);
//				AppConnect.getInstance(this).showBannerAd(this, layout);
//				LinearLayout miniLayout = (LinearLayout) findViewById(R.id.adlayout1);
//				AppConnect.getInstance(this).showMiniAd(this, miniLayout, 10);// 10秒刷新一次
//				// 抽屉式应用墙
//				// 1,将drawable-hdpi文件夹中的图片全部拷贝到新工程的drawable-hdpi文件夹中
//				// 2,将layout文件夹中的detail.xml和slidewall.xml两个文件，拷贝到新工程的layout文件夹中
//				// 获取抽屉样式的自定义广告
//		    	slidingDrawerView = SlideWall.getInstance().getView(this);
//		    	if(slidingDrawerView != null){
//		    		this.addContentView(slidingDrawerView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
//		    	}
//		    	
//		    	// 初始化卸载广告
//		    	AppConnect.getInstance(this).initUninstallAd(this);
		    	
				
	}

	protected void onResume() {
		super.onResume();
	}

	public void onWallpaper(View view) {
		Intent intent = new Intent();
		if (android.os.Build.VERSION.SDK_INT >= 16) {
			intent.setAction("android.service.wallpaper.CHANGE_LIVE_WALLPAPER");
			Intent putExtra = intent.putExtra(
					"android.service.wallpaper.extra.LIVE_WALLPAPER_COMPONENT",
					new ComponentName(this,
							"com.lt.cameras.TransparentWallpaperService"));
		} else {
			intent.setAction("android.service.wallpaper.LIVE_WALLPAPER_CHOOSER");
		}
		startActivity(intent);
	}
	public void onWallpaper2(View view) {
		Intent intent = new Intent();
		if (android.os.Build.VERSION.SDK_INT >= 16) {
			intent.setAction("android.service.wallpaper.CHANGE_LIVE_WALLPAPER");
			Intent putExtra = intent.putExtra(
					"android.service.wallpaper.extra.LIVE_WALLPAPER_COMPONENT",
					new ComponentName(this,
							"com.lt.cameras.TransparentWallpaperService2"));
		} else {
			intent.setAction("android.service.wallpaper.LIVE_WALLPAPER_CHOOSER");
		}
		startActivity(intent);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
//			if(SlideWall.getInstance().slideWallDrawer != null
//					&& SlideWall.getInstance().slideWallDrawer.isOpened()){				
//				// 如果抽屉式应用墙展示中，则关闭抽屉
//				SlideWall.getInstance().closeSlidingDrawer();
//			}
//			else{
//				// 调用退屏广告
//				QuitPopAd.getInstance().show(this);
//			}
//			
		}
		return true;

	}

}
