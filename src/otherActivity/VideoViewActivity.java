package otherActivity;

import com.example.yichangcityapp.R;

import adapter.FullScreenVideoView;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.widget.MediaController;
import untils.BaseActivity;

public class VideoViewActivity extends BaseActivity {
	private FullScreenVideoView videoview;
	private MediaController m;
   @Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);//Òþ²Ø±êÌâÀ¸   
	setContentView(R.layout.video);
	videoview=(FullScreenVideoView)findViewById(R.id.video);
	m=new MediaController(this);
	Uri uri=Uri.parse("http://vod.yichang.gov.cn/2009/%E6%97%85%E6%B8%B8%E7%AF%87%E7%BD%91%E7%BB%9C%E7%89%88.wmv");

	videoview.setMediaController(m);
	m.setMediaPlayer(videoview);
	videoview.setVideoURI(uri);
	videoview.requestFocus();
}
}
