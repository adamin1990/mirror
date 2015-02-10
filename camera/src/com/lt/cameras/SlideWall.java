package com.lt.cameras;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.AsyncTask;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.TextView;
import cn.waps.AdInfo;
import cn.waps.AppConnect;
import cn.waps.SDKUtils;

public class SlideWall {
	
	private final Handler mHandler = new Handler();
	//自定义的抽屉（获取该对象时，slidewall.xml中使用的抽屉对象的名称，必须和SlideWallDrawer类的全限定类名匹配）
	public SlideWallDrawer slideWallDrawer;
	
	private static SlideWall slideWall;
	
	public SlideWall(){
	}
	
	/**
	 * 构建实例
	 * @return
	 */
	public static SlideWall getInstance(){
		if(slideWall == null){
			slideWall = new SlideWall();
		}
		return slideWall;
	}
	
	/**
	 * 关闭打开的抽�?
	 */
	public void closeSlidingDrawer(){
		if(slideWallDrawer != null && slideWallDrawer.isOpened()){
			slideWallDrawer.animateClose();
		}
	}

	/**
	 * 获取从下�?上样式的抽屉的整体布�?
	 * @param context
	 * @return
	 */
	public LinearLayout getView(final Context context){
		return getView(context, 0, 0, 0);
	}
	/**
	 * 获取从下�?上样式的抽屉的整体布�?
	 * @param context
	 * @param handleMarginLeft handle距离屏幕左边的距�?
	 * @return
	 */
	public LinearLayout getView(final Context context, int handleMarginLeft){
		return getView(context, handleMarginLeft, 0, 0);
	}
	/**
	 * 获取从下�?上样式的抽屉的整体布�?
	 * @param context
	 * @param itemWidth 抽屉内容中每个Item的宽�?
	 * @param itemHeight 抽屉内容中每个Item的高�?
	 * @return
	 */
	public LinearLayout getView(final Context context, int itemWidth, int itemHeight){
		return getView(context, 0, itemWidth, itemHeight);
	}
	
	/**
	 * 获取从下�?上样式的抽屉的整体布�?
	 * @param context
	 * @param handleMarginLeft handle距离屏幕左边的距�?
	 * @param itemWidth 抽屉内容中每个Item的宽�?
	 * @param itemHeight 抽屉内容中每个Item的高�?
	 * @return
	 */
	public LinearLayout getView(final Context context, int handleMarginLeft, int itemWidth, int itemHeight){
		
		LinearLayout slidingDrawerLayout = null;
		
		try {
			// 其中获取id的方式相当于 R.layout.slidewall
			// slidewall.xml布局文件的id
			int layout_id = context.getResources().getIdentifier("slidewall", "layout", context.getPackageName());
			// slidewall.xml布局文件中最外层布局的id
			int slidingDrawerLayout_id = context.getResources().getIdentifier("slidingdrawerLayout", "id", context.getPackageName());
			// SlidingDrawer布局id
			int slidingDrawer_id = context.getResources().getIdentifier("slidingDrawer", "id", context.getPackageName());
			// SlidingDrawer中content的id（此处即是ListView的id�?
			int drawerContent_id = context.getResources().getIdentifier("drawerContent", "id", context.getPackageName());
			// SlidingDrawer中handle的id
			int drawerHandle_id = context.getResources().getIdentifier("drawerHandle", "id", context.getPackageName());
			// 如果以上任何�?个id�?0,则返回空�?
			if(layout_id == 0 || slidingDrawerLayout_id == 0 || slidingDrawer_id == 0 || drawerContent_id == 0){
				return null;
			}
			View view = View.inflate(context, layout_id, null);
			
			slidingDrawerLayout = (LinearLayout)view.findViewById(slidingDrawerLayout_id);
			
			try {
				slideWallDrawer = (SlideWallDrawer) view.findViewById(slidingDrawer_id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			slideWallDrawer.setVisibility(View.INVISIBLE);
			if(handleMarginLeft != 0){
				// 设置handle据左边边�?
				slideWallDrawer.setmHandleMarginLeft(handleMarginLeft);
			}
			//抽屉的content的相关设�?
			ListView drawerContent = (ListView) view.findViewById(drawerContent_id);
			drawerContent.setBackgroundColor(Color.WHITE);
			drawerContent.setCacheColorHint(0);
			//设置ListView每个Item间的间隔线的颜色渐变
			GradientDrawable divider_gradient = new GradientDrawable(Orientation.TOP_BOTTOM, 
				new int[] {Color.parseColor("#cccccc"), Color.parseColor("#ffffff"), Color.parseColor("#cccccc")}); 
			drawerContent.setDivider(divider_gradient);
			
			int displaySize = SDKUtils.getDisplaySize(context);
			int line_size = 4;
			if(displaySize == 240){
				line_size = 2;
			}
			drawerContent.setDividerHeight(line_size);
			
			//抽屉的handle的相关设�?
			final int handle_up_img_id = context.getResources().getIdentifier("handle_up", "drawable", context.getPackageName());
			final int handle_down_img_id = context.getResources().getIdentifier("handle_down", "drawable", context.getPackageName());
			final TextView drawHandle = (TextView) view.findViewById(drawerHandle_id);
			
			drawHandle.setText("精品应用推荐");
			drawHandle.setTextSize(12);
			drawHandle.setTextColor(Color.BLACK);
			drawHandle.setGravity(Gravity.RIGHT);
			// 根据抽屉的打�?和关闭设置不同的handle的背景图
			slideWallDrawer.setOnDrawerOpenListener(new OnDrawerOpenListener(){
				@Override
				public void onDrawerOpened() {
					drawHandle.setBackgroundResource(handle_down_img_id);
				}
			});
			
			slideWallDrawer.setOnDrawerCloseListener(new OnDrawerCloseListener(){
				@Override
				public void onDrawerClosed() {
					drawHandle.setBackgroundResource(handle_up_img_id);
				}
			});
			
			// 异步展示抽屉广告
			new GetDiyAdTask(context, slideWallDrawer, drawerContent, itemWidth, itemHeight).execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return slidingDrawerLayout;
	}
	
	private class MyAdapter extends BaseAdapter{
    	Context context;
    	List<AdInfo> list;
    	int itemWidth;
    	int itemHeight;
    	public MyAdapter(Context context, List<AdInfo> list, int itemWidth, int itemHeight){
    		this.context = context;
    		this.list = list;
    		this.itemWidth = itemWidth;
    		this.itemHeight = itemHeight;
    	}
		@Override
		public int getCount() {
			return list.size();
		}
		@Override
		public Object getItem(int position) {
			return list.get(position);
		}
		@Override
		public long getItemId(int position) {
			return position;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
//			final AdInfo adInfo = list.get(position);
//			
//			View adatperView = null;
//			
//			try {
//				adatperView = AppItemView.getInstance().getAdapterView(context, adInfo, itemWidth, itemHeight);
//					
//				convertView = adatperView;
//				convertView.setTag(adatperView);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//            return adatperView;
			final AdInfo adInfo = list.get(position);
			
			try {
				if(convertView == null){
					convertView = AppItemView.getInstance().getAdapterView(context, adInfo, 0, 0);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
            return convertView;
		}
    }
	
	private class GetDiyAdTask extends AsyncTask<Void, Void, Boolean>{
		
		Context context;
		SlideWallDrawer mySlidingDrawer;
		ListView drawerContent;
		List<AdInfo> list;
		int itemWidth;
		int itemHeight;
		
		GetDiyAdTask(Context context, SlideWallDrawer mySlidingDrawer, ListView drawerContent, int itemWidth, int itemHeight){
			this.context = context;
			this.mySlidingDrawer = mySlidingDrawer;
			this.drawerContent = drawerContent;
			this.itemWidth = itemWidth;
			this.itemHeight = itemHeight;
		}
		
		@SuppressWarnings("unchecked")
		@Override
		protected Boolean doInBackground(Void... params) {
			
			try {
				int i = 0;
				while(true){
					if(i > 10){
						i = 0;
						break;
					}
					list = AppConnect.getInstance(context).getAdInfoList();
					if(list != null && !list.isEmpty()){
						
						mHandler.post(new Runnable(){
							
							@Override
							public void run() {
								drawerContent.setAdapter(new MyAdapter(context, list, itemWidth, itemHeight));
								mySlidingDrawer.setVisibility(View.VISIBLE);
							}
						});	
						
						break;
					}
					i++;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}
}
