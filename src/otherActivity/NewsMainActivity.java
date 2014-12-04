package otherActivity;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.yichangcityapp.R;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

public class NewsMainActivity extends BaseActivity {
    private  String MAIN_ADDRESS;
    private  String MAIN_SELECT;
    private  String IMAGE_ADDRESS;
    private String tag;
    private String title;
    private TextView titleTextView;
    private TextView text;
    private ImageView image;
    private String getTextString;
   
    private ProgressDialog progressDialog;
    private Handler handler=new Handler(){
    	public void handleMessage(android.os.Message msg) {
    		if (msg.what>0) {
    			titleTextView.setText(title);
    			text.setText(getTextString);
    			getPictureFormHtml(IMAGE_ADDRESS);
    			closeProgressDialog();
			}
    	};
    };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_main);
		text=(TextView)findViewById(R.id.text1);
		image=(ImageView)findViewById(R.id.image);
		titleTextView=(TextView)findViewById(R.id.title);
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		MAIN_ADDRESS=bundle.getString("MAIN_ADDRESS");
		MAIN_SELECT=bundle.getString("MAIN_SELECT");
		IMAGE_ADDRESS=bundle.getString("IMAGE_ADDRESS");
		tag=bundle.getString("tag");
		title=bundle.getString("title");
		showProgressDialog();
		startThread();
//		String getText=CatchHtml.getHtmlText(MAIN_ADDRESS, MAIN_SELECT);
//		text.setText(getText);
	}
	
	private    void startThread(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Message msg=Message.obtain();
				try {
					getTextString=CatchHtml.getHtmlText(MAIN_ADDRESS, MAIN_SELECT,tag);
					msg.what=1;
					
				} catch (Exception e) {
					// TODO: handle exception
					msg.what=-1;
				}
				Log.d("what", "what="+msg.what);
				handler.sendEmptyMessage(msg.what);
			}
		}).start();
	}
	
	
	public void getPictureFormHtml(String address){
		
		RequestQueue mQueue=Volley.newRequestQueue(NewsMainActivity.this);
    	ImageRequest imageRequest=new ImageRequest(address, new Response.Listener<Bitmap>() {

			@Override
			public void onResponse(Bitmap response) {
				// TODO Auto-generated method stub
				Log.i("BITAM", response.toString());
			   image.setImageBitmap(response);
			}
		}, 0, 0, Config.ARGB_8888, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				image.setImageResource(R.drawable.defalse);
			}
		});
    	mQueue.add(imageRequest);
    	
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
