package otherActivity;

import com.example.yichangcityapp.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import untils.BaseActivity;

public class ImageViewActivity extends BaseActivity {
  @Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.image_view);
	String path=getIntent().getStringExtra("image_path");
	Bitmap bitmap=BitmapFactory.decodeFile(path);
	ImageView imageView=(ImageView)findViewById(R.id.image_YC);
	imageView.setImageBitmap(bitmap);
}
}
