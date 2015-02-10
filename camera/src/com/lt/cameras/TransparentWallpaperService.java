package com.lt.cameras;

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TransparentWallpaperService.java

import android.service.wallpaper.WallpaperService;
import android.util.Log;
import android.view.SurfaceHolder;

public class TransparentWallpaperService extends WallpaperService {
	private class MyWallpaperEngine extends
			android.service.wallpaper.WallpaperService.Engine {

		public void onCreate(SurfaceHolder surfaceholder) {
			Log.v("KM", (new StringBuilder("onCreate called ")).append(GC)
					.toString());
			if (GC == null) {
				try {
					if (TransparentWallpaperService.existing != null)
						TransparentWallpaperService.existing.destroyExisting();
				} catch (Exception exception) {
				}
				GC = new GenericaCamera(surfaceholder, getBaseContext());
				TransparentWallpaperService.existing = GC;
			}
			super.onCreate(surfaceholder);
		}

		GenericaCamera GC;
		final TransparentWallpaperService this$0;

		private MyWallpaperEngine() {
			// super(TransparentWallpaperService.this);
			this$0 = TransparentWallpaperService.this;

		}

		MyWallpaperEngine(MyWallpaperEngine mywallpaperengine) {
			this();
		}
	}

	public TransparentWallpaperService() {
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
	public static GenericaCamera existing;
}
