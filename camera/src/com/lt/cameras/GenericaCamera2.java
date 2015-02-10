package com.lt.cameras;

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GenericaCamera.java

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.Toast;
import java.util.*;

public class GenericaCamera2 implements android.view.SurfaceHolder.Callback {

	public GenericaCamera2(SurfaceHolder surfaceholder, Context context1) {
		cameraDevice = null;
		cameraSurfaceHolder = null;
		randomValue = 0;
		randomValue = (new Random()).nextInt(0x186a0);
		Log.v("KM",
				(new StringBuilder("GenericaCamera called ")).append(
						randomValue).toString());
		context = context1;
		cameraSurfaceHolder = surfaceholder;
		cameraSurfaceHolder.setType(3);
		cameraSurfaceHolder.addCallback(this);
	}

	public static boolean almostEqual(double d, double d1) {
		boolean flag;
		if (Math.abs(d - d1) < 0.050000000000000003D)
			flag = true;
		else
			flag = false;
		return flag;
	}

	private static android.hardware.Camera.Size getBestPreviewSize(int i,
			int j, android.hardware.Camera.Parameters parameters) {
		Size size = null;
		Iterator iterator;
		Log.d("KM",
				(new StringBuilder("Screen width=")).append(i)
						.append(" height=").append(j).toString());
		iterator = parameters.getSupportedPreviewSizes().iterator();
		//
		// _L4:
		// if(iterator.hasNext()) goto _L2; else goto _L1
		// _L1:
		// android.hardware.Camera.Size size =
		// (android.hardware.Camera.Size)parameters.getSupportedPreviewSizes().get(0);
		// _L3:
		// return size;
		// _L2:
		// size = (android.hardware.Camera.Size)iterator.next();
		// Log.d("KM", (new
		// StringBuilder("Size to Use width=")).append(size.width).append(" height=").append(size.height).toString());
		// if(!almostEqual((1D * (double)size.width) / (double)size.height, (1D
		// * (double)j) / (double)i)) goto _L4; else goto _L3
		if (iterator.hasNext()) {

			size = (android.hardware.Camera.Size) iterator.next();
			Log.d("KM",
					(new StringBuilder("Size to Use width="))
							.append(size.width).append(" height=")
							.append(size.height).toString());
		} else {
			size = (android.hardware.Camera.Size) parameters
					.getSupportedPreviewSizes().get(0);
		}
		return size;

	}

	public void destroyExisting() {
		Log.v("KM", (new StringBuilder("surfaceDestroyed Internal called "))
				.append(randomValue).toString());
		if (cameraDevice != null) {
			cameraDevice.stopPreview();
			cameraDevice.setPreviewCallback(null);
			cameraDevice.release();
			cameraDevice = null;
		}
		isPreviewRunning = false;
	}

	public void surfaceChanged(SurfaceHolder surfaceholder, int i, int j, int k) {
		Log.v("KM",
				(new StringBuilder("surfaceChanged called"))
						.append(randomValue).toString());
		if (cameraDevice != null) {
			if (isPreviewRunning)
				cameraDevice.stopPreview();
			android.hardware.Camera.Parameters parameters = cameraDevice
					.getParameters();
			android.hardware.Camera.Size size = getBestPreviewSize(j, k,
					parameters);
			if (size != null)
				parameters.setPreviewSize(size.width, size.height);
			try {
				cameraDevice.setParameters(parameters);
			} catch (Exception exception) {
				Log.v("KM", "Error Setting Parameters", exception);
			}
			cameraDevice.startPreview();
			isPreviewRunning = true;
		}
	}

	public void surfaceCreated(SurfaceHolder surfaceholder) {
		Log.v("KM",
				(new StringBuilder("surfaceCreated called ")).append(
						randomValue).toString());
		try {
			if (cameraDevice == null) {
				if (android.os.Build.VERSION.SDK_INT >= 9) {
					cameraDevice = Camera.open(1);
				} else {
					cameraDevice = Camera.open();
					Toast.makeText(
							context,
							"Front Facing camera unavailable on this phone. Defaulting to rear camera",
							1).show();
				}
				cameraDevice.setDisplayOrientation(90);
				cameraDevice.setPreviewDisplay(cameraSurfaceHolder);
			}
			cameraDevice.startPreview();
		} catch (Exception exception) {
			Toast.makeText(context, "Can't create preview!", 1).show();
			Log.v("KM", "Error creating camera", exception);
		}
	}

	public void surfaceDestroyed(SurfaceHolder surfaceholder) {
		Log.v("KM",
				(new StringBuilder("surfaceDestroyed called ")).append(
						randomValue).toString());
		destroyExisting();
	}

	private static final String TAG = "KM";
	private static boolean isPreviewRunning = false;
	private Camera cameraDevice;
	private SurfaceHolder cameraSurfaceHolder;
	private Context context;
	private int randomValue;

}
