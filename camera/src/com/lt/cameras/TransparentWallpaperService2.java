package com.lt.cameras;

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TransparentWallpaperService.java

import android.service.wallpaper.WallpaperService;
import android.util.Log;
import android.view.SurfaceHolder;

public class TransparentWallpaperService2 extends WallpaperService {
	private class MyWallpaperEngine extends
			android.service.wallpaper.WallpaperService.Engine {

		public void onCreate(SurfaceHolder surfaceholder) {
			Log.v("KM", (new StringBuilder("onCreate called ")).append(GC)
					.toString());
			if (GC == null) {
				try {
					if (TransparentWallpaperService2.existing != null)
						TransparentWallpaperService2.existing.destroyExisting();
				} catch (Exception exception) {
				}
				GC = new GenericaCamera2(surfaceholder, getBaseContext());
				TransparentWallpaperService2.existing = GC;
			}
			super.onCreate(surfaceholder);
		}

		GenericaCamera2 GC;
		final TransparentWallpaperService2 this$0;

		private MyWallpaperEngine() {
			// super(TransparentWallpaperService.this);
			this$0 = TransparentWallpaperService2.this;

		}

		MyWallpaperEngine(MyWallpaperEngine mywallpaperengine) {
			this();
		}
	}

	public TransparentWallpaperService2() {
	}

	public void onCreate() {
		super.onCreate();
	}

	public android.service.wallpaper.WallpaperService.Engine onCreateEngine() {
		return new MyWallpaperEngine(null);
	}

	public void onDestroy() {
		super.onDestroy();
	}

	private static final String TAG = "KM";
	public static GenericaCamera2 existing;
}
