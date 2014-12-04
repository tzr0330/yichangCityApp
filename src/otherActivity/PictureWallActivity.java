package otherActivity;

import com.example.yichangcityapp.R;

import android.os.Bundle;
import android.view.Window;
import untils.BaseActivity;

public class PictureWallActivity extends BaseActivity {
   @Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.picture_scroll);
}
}
