package widget;

import com.nineoldandroids.view.ViewHelper;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class SlidingMenu extends HorizontalScrollView {
	/*
	 * 屏幕的宽度
	 */
   private int mScreenWidth;
   /*
    * menu距离右侧的距离
    */
   private int mMenuRightPadding=50;
   
   private int mMenuWidth;
   private LinearLayout warpLayout;
   private ViewGroup mMenu;
   private ViewGroup mContent;
   private boolean flag=false;
   
   
	public SlidingMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	  WindowManager  wManager=(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
	  DisplayMetrics dm=new DisplayMetrics();
	  wManager.getDefaultDisplay().getMetrics(dm);
	  mScreenWidth=dm.widthPixels;
	  //把DP转化为px
	  mMenuRightPadding=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources().getDisplayMetrics());
	  
	}
   /**
    * 设置子View的宽和高
    * 设置自己的宽和高
    */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		
		if (!flag) {
			warpLayout=(LinearLayout) getChildAt(0);
			mMenu=(ViewGroup) warpLayout.getChildAt(0);
			mContent=(ViewGroup) warpLayout.getChildAt(1);
			mMenuWidth=mMenu.getLayoutParams().width=mScreenWidth-mMenuRightPadding;
			mContent.getLayoutParams().width=mScreenWidth;
			flag=true;
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	/**
	 * 通过设置偏移量，将menu隐藏
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
		if (changed) {
			this.scrollTo(mMenuWidth, 0);
		}
	}
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		int action=ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_UP:
			//隐藏在左边的宽度
			int scrollX=getScrollX();
			if (scrollX>=mMenuWidth/2) {
				this.smoothScrollTo(mMenuWidth, 0);
			}else {
				this.smoothScrollTo(0, 0);
			}
			return true;
		}
		
		return super.onTouchEvent(ev);
	}
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
    	// TODO Auto-generated method stub
    	super.onScrollChanged(l, t, oldl, oldt);
    	float scale = l * 1.0f / mMenuWidth;  
        float leftScale = 1 - 0.3f * scale;  
        float rightScale = 0.8f + scale * 0.2f;  
          
        ViewHelper.setScaleX(mMenu, leftScale);  
        ViewHelper.setScaleY(mMenu, leftScale);  
        ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));  
        ViewHelper.setTranslationX(mMenu, mMenuWidth * scale * 0.6f);  
  
        ViewHelper.setPivotX(mContent, 0);  
        ViewHelper.setPivotY(mContent, mContent.getHeight() / 2);  
        ViewHelper.setScaleX(mContent, rightScale);  
        ViewHelper.setScaleY(mContent, rightScale); 
    	
    }
}
