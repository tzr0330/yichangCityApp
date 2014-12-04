package otherActivity;

import com.example.yichangcityapp.R;

import DataBase.AppDB;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import untils.BaseActivity;
import untils.CatchHtml;
import widget.TitleLayout;

public class NewsSinghtActivity extends BaseActivity {
    
	private TitleLayout titleLayout;
	private ImageView imageView;
	private TextView textView;
	private TextView title_collect;
	private String title;
	private String address;
	private int imageId;
	private int postion;
	private int id;
	private String text_intro;
	private ProgressDialog progressDialog;
	private AppDB db;
	private Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			if (msg.what==1) {
				titleLayout.setTitletView(title);
				titleLayout.setImage(imageId);
				titleLayout.setaddress(address);
				titleLayout.setPostion(postion);
				titleLayout.getContent(text_intro);
				textView.setText(text_intro);
				imageView.setImageResource(imageId);
				closeProgressDialog();
			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.news_singht);
		titleLayout=(TitleLayout)findViewById(R.id.title_layout);
		imageView=(ImageView)findViewById(R.id.singht_image);
		textView=(TextView)findViewById(R.id.singht_text);
		title_collect=(TextView)findViewById(R.id.title_collect);
		db=AppDB.getInstance(this);
		Bundle bundle=getIntent().getExtras();
		postion=bundle.getInt("postion");
		title=bundle.getString("title");
		address=bundle.getString("address");
		imageId=bundle.getInt("image");
		id=bundle.getInt("_id");
		if (db.queryId(id)) {
			text_intro=db.queryContent(id);
			textView.setText(text_intro);
			imageView.setImageResource(imageId);
			titleLayout.setVisibility(View.GONE);
			title_collect.setVisibility(View.VISIBLE);
			title_collect.setText(title);
		}else {
			showProgressDialog();
			startThread();
		}
	
	}
	
	private void startThread() {
		// TODO Auto-generated method stub
     new Thread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			text_intro=CatchHtml.getMoreHtml(address);
			Message message=Message.obtain();
			if (!text_intro.equals("")) {
				message.what=1;
			}else {
				message.what=-1;
			}
			handler.sendEmptyMessage(message.what);
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
