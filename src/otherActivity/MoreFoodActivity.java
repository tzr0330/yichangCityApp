package otherActivity;

import modle.ListAdrImage;
import modle.TitleAndIcon;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.yichangcityapp.R;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import untils.BaseActivity;
import untils.CatchHtml;

public class MoreFoodActivity extends BaseActivity {
	private String MAIN_ADDRESS;
	private int IMAGE_ADDRESS;
	private String title;
	private TextView titleTextView;
	private TextView text;
	private ImageView image;
	private String getTextString;
	private ProgressDialog progressDialog;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
             if (msg.what>0) {
				titleTextView.setText(title);
				text.setText(getTextString);
				image.setImageResource(IMAGE_ADDRESS);
				closeProgressDialog();
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.news_main);
		int postion = getIntent().getIntExtra("postion", -1);
		MAIN_ADDRESS = ListAdrImage.URL_FOOD[postion];
		IMAGE_ADDRESS = ListAdrImage.IMAGE_FOOD[postion];
		title = getResources().getString(TitleAndIcon.food_titles[postion]);
		text = (TextView) findViewById(R.id.text1);
		image = (ImageView) findViewById(R.id.image);
		titleTextView = (TextView) findViewById(R.id.title);
		showProgressDialog();
		startThread();
	}
	
	private void startThread() {
		// TODO Auto-generated method stub
         new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				getTextString=CatchHtml.getHtmlText2(MAIN_ADDRESS);
				Message msg=Message.obtain();
				if (getTextString!=null) {
					msg.what=1;
				}else {
					msg.what=-1;
				}
				handler.sendEmptyMessage(msg.what);
			}
		}).start();
	}

	
	private void showProgressDialog() {
		// TODO Auto-generated method stub
         if (progressDialog==null) {
			progressDialog=new ProgressDialog(this);
			progressDialog.setMessage("正在加载中.......");
			progressDialog.setCanceledOnTouchOutside(false);
		}
         progressDialog.show();
	}
	
	private void closeProgressDialog() {
		// TODO Auto-generated method stub
      if (progressDialog!=null) {
		progressDialog.dismiss();
	}
	}
}
