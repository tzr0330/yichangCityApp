package widget;

import java.util.HashMap;
import java.util.Map;

import modle.ListAdrImage;
import modle.ListInfo;
import untils.CatchHtml;

import com.example.yichangcityapp.R;

import DataBase.AppDB;
import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TitleLayout extends LinearLayout {
   private int imageId;
   private String address;
   private int id;
   private String contentString;
   private AppDB db;
  TextView titleTextView;
	public TitleLayout(final Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		LayoutInflater.from(context).inflate(R.layout.title, this);
		Button backButton=(Button)findViewById(R.id.button1);
		Button editButton=(Button)findViewById(R.id.button2);
		titleTextView=(TextView)findViewById(R.id.textView1);
		db=AppDB.getInstance(context);
		backButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//			 ActivityCollector.removeActivity((Activity)getContext());
				((Activity)getContext()).finish();
			}
		});
		
		editButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startThread();
			      Toast.makeText(getContext(), " ’≤ÿ≥…π¶", 1).show();
			}
		});
	}
	
	private void startThread(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				 Map<String, String> map=new HashMap<String, String>();
				 map=CatchHtml.getListHtmlText(address);
				 ListInfo listInfo=new ListInfo();
				  listInfo.setTitle(map.get("singht_title"));
				  listInfo.setIntroduce(map.get("singht_intro"));
				  listInfo.setAddress(map.get("singht_address"));
				  listInfo.setTime(map.get("singht_time"));
				  listInfo.setId(id);
				  db.saveSinght(listInfo,contentString);
			}
		}).start();
	}
	
   public void setTitletView(String text) {
	titleTextView.setText(text);
}
   public void getContent(String content) {
	contentString=content;
}
	public void setImage(int imageId) {
		// TODO Auto-generated method stub
     this.imageId=imageId;
	}
    public void setaddress(String text) {
		address=text;
	}
    public void setPostion(int postion) {
		id=postion;
	}
}

