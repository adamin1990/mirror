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
	// �����沼��
		private View slidingDrawerView;
	public WelcomeActivity() {
	}

//	public void onAd1(View view) {
//		//��ʾ�Ƽ��б���Ϸ��
////		AppConnect.getInstance(this).showGameOffers(this);
//	}
//
//	public void onAd2(View view) {
//		//��ʾ�Ƽ��б������
////		AppConnect.getInstance(this).showAppOffers(this);
//	}
//
//	public void onAd3(View view) {
////		AppConnect.getInstance(this).showPopAd(this);
//	}
//
//	public void onAd4(View view) {
//		//��ȡһ���Զ���������
////		AdInfo adInfo = AppConnect.getInstance(WelcomeActivity.this).getAdInfo();
////		AppDetail.getInstanct().showAdDetail(WelcomeActivity.this,adInfo);
//	}

	public void onAd5(View view) {
		//���ù��ܹ��ӿڣ�ʹ��������ӿڣ�
		String uriStr = "http://user.qzone.qq.com/14846869";
		AppConnect.getInstance(this).showBrowser(this, uriStr);
	}

//	public void onAd6(View view) {
//		//����ָ����Ӧ��app_idչʾ������
////		AppConnect.getInstance(this).showMore(this, "e50b93cf3f78c52387a4018eb088edea");
//	}

	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_main);
		
//		// ��ʼ��ͳ��������ͨ����������APP_ID, APP_PID
//		AppConnect
//				.getInstance("e50b93cf3f78c52387a4018eb088edea", "huawei", this);
		if (android.os.Build.VERSION.SDK_INT >= 16)
			((TextView) findViewById(R.id.editText1))
					.setText("1. ͨ���������İ�ť����.\n2. ѡ�����ֽ��Ϊ��Ķ�̬��ֽ��");
//		// Ԥ�����Զ��������ݣ�����ʹ�����Զ����桢�����������������������Ҫ��ӣ�
//				AppConnect.getInstance(this).initAdInfo();
//				
//				// Ԥ���ز���������ݣ�����ʹ�õ������������������Ҫ��ӣ�
//				AppConnect.getInstance(this).initPopAd(this);
//				
//				// Ԥ���ع��ܹ�����ݣ�����ʹ�õ����ܹ������������Ҫ��ӣ�
//		    	AppConnect.getInstance(this).initFunAd(this);
//		    	// ����Ĭ�ϲ���ֵ���������ã�ʹ�ô˷����������һ������ʹ�õ���"defaultValue"��֮������������ʹ�õķ������˷��صĲ���ֵ
//				String showAd = AppConnect.getInstance(this).getConfig("showAd", "defaultValue");
//				// ���������÷�ʽ
//				LinearLayout layout = (LinearLayout) this.findViewById(R.id.adLayout3);
//				AppConnect.getInstance(this).showBannerAd(this, layout);
//				LinearLayout miniLayout = (LinearLayout) findViewById(R.id.adlayout1);
//				AppConnect.getInstance(this).showMiniAd(this, miniLayout, 10);// 10��ˢ��һ��
//				// ����ʽӦ��ǽ
//				// 1,��drawable-hdpi�ļ����е�ͼƬȫ���������¹��̵�drawable-hdpi�ļ�����
//				// 2,��layout�ļ����е�detail.xml��slidewall.xml�����ļ����������¹��̵�layout�ļ�����
//				// ��ȡ������ʽ���Զ�����
//		    	slidingDrawerView = SlideWall.getInstance().getView(this);
//		    	if(slidingDrawerView != null){
//		    		this.addContentView(slidingDrawerView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
//		    	}
//		    	
//		    	// ��ʼ��ж�ع��
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
//				// �������ʽӦ��ǽչʾ�У���رճ���
//				SlideWall.getInstance().closeSlidingDrawer();
//			}
//			else{
//				// �����������
//				QuitPopAd.getInstance().show(this);
//			}
//			
		}
		return true;

	}

}
