package adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;
/**
 * 自定义VideoView控件，是为了实现全屏效果
 * @author Administrator
 *
 */
public class FullScreenVideoView extends VideoView {

	public FullScreenVideoView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public FullScreenVideoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public FullScreenVideoView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    	// TODO Auto-generated method stub
    	int width=getDefaultSize(0, widthMeasureSpec);
    	int height=getDefaultSize(0, heightMeasureSpec);
    	setMeasuredDimension(width, height);
    }
}
