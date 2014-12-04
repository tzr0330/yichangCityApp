package com.example.yichangcityapp;

import otherActivity.CollectActivity;
import otherActivity.ContactActivity;

import com.example.yichangcityapp.R.layout;

import Fragment.MsgFragment;
import Fragment.OtherFragment;
import Fragment.PlayFragment;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import untils.ActivityCollector;
import untils.BaseActivity;
import widget.SlidingMenu;

public class FragmentActivity extends BaseActivity implements OnClickListener{
	private FragmentManager manger;
    private FragmentTransaction transaction;
    private LinearLayout privatemsg_layout,sendmsg_layout,setting_layout;
    private ImageView newsImage,optionImage,settingImage;
    private TextView  newsText,optionText,settingText,customTitle;
    private MsgFragment Msgfragment;
    private PlayFragment  PlayFragment;
    private OtherFragment OtherFragment;
    private FrameLayout framelayout;
    private TextView menu_text1,menu_text2,menu_text3,menu_text4;
    private Button exit_button;
    private SlidingMenu slidingMenu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_fragment);
//		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);
		manger=getFragmentManager();
		initFragment();
	}
	//在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件
	public void initFragment() {
		privatemsg_layout=(LinearLayout)findViewById(R.id.privatemsg_layout);
		sendmsg_layout=(LinearLayout)findViewById(R.id.sendmsg_layout);
		setting_layout=(LinearLayout)findViewById(R.id.setting_layout);
		newsImage=(ImageView)findViewById(R.id.news_image);
		optionImage=(ImageView)findViewById(R.id.option_image);
		settingImage=(ImageView)findViewById(R.id.setting_image);
		customTitle=(TextView)findViewById(R.id.custom_title);
		newsText=(TextView)findViewById(R.id.new_text);
		optionText=(TextView)findViewById(R.id.option_text);
		settingText=(TextView)findViewById(R.id.setting_text);
		menu_text1=(TextView) findViewById(R.id.menu_text1);
		menu_text2=(TextView) findViewById(R.id.menu_text2);
		menu_text3=(TextView) findViewById(R.id.menu_text3);
		menu_text4=(TextView) findViewById(R.id.menu_text4);
//		ll_menu_1=(LinearLayout) findViewById(R.id.ll_menu_1);
		exit_button=(Button) findViewById(R.id.exit_button);
		slidingMenu=(SlidingMenu) findViewById(R.id.slidingmenu);
		privatemsg_layout.setOnClickListener(this);
		sendmsg_layout.setOnClickListener(this);
		setting_layout.setOnClickListener(this);
//		ll_menu_1.setOnClickListener(this);
		menu_text1.setOnClickListener(this);
		menu_text2.setOnClickListener(this);
		menu_text3.setOnClickListener(this);
		menu_text4.setOnClickListener(this);
		setTabSelection(0);
	}
  
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.privatemsg_layout:
			setTabSelection(0);
			break;
			case R.id.sendmsg_layout:
				setTabSelection(1);
          break;
          case R.id.setting_layout:
        	  setTabSelection(2);
        	  break;
		case R.id.menu_text1:
//			Toast.makeText(this, "you click menu item", Toast.LENGTH_LONG).show();
			Uri uri1=Uri.parse("http://user.qzone.qq.com/529896279/main");
			Intent intent=new Intent(Intent.ACTION_VIEW,uri1);
			startActivity(intent);
			break;
		case R.id.menu_text2:
//			Toast.makeText(this, "you click menu item", Toast.LENGTH_LONG).show();
			Uri uri2=Uri.parse("http://www.yichang.gov.cn/col/col3/index.html");
			Intent intent2=new Intent(Intent.ACTION_VIEW,uri2);
			startActivity(intent2);
			break;
		case R.id.menu_text3:
//			Toast.makeText(this, "you click menu item", Toast.LENGTH_LONG).show();
			
			startActivity(new Intent(FragmentActivity.this, ContactActivity.class));
			break;
		case R.id.menu_text4:
//			Toast.makeText(this, "you click menu item", Toast.LENGTH_LONG).show();
			Uri uri4=Uri.parse("http://www.weibo.com/u/2437256033");
			Intent intent4=new Intent(Intent.ACTION_VIEW,uri4);
			startActivity(intent4);
			break;
		}
	}
	
	public void exit(){
		ActivityCollector.finishAll();
	}
	//根据传入的index参数来设置选中的tab页。
	private void setTabSelection(int index){
		clearSelection();
		transaction=manger.beginTransaction();
		hideFragment(transaction);
		switch (index) {
		case 0:
			newsImage.setImageResource(R.drawable.news_selected);
			newsText.setTextColor(Color.WHITE);
			customTitle.setText("宜昌资讯");
			if (Msgfragment==null) {
				Msgfragment=new MsgFragment();
				transaction.add(R.id.content, Msgfragment);
			}else {
				transaction.show(Msgfragment);
			}
			break;
		case 1:
			optionImage.setImageResource(R.drawable.contacts_selected);
			optionText.setTextColor(Color.WHITE);
			customTitle.setText("热门景点");
			if (PlayFragment==null) {
				PlayFragment=new PlayFragment();
				transaction.add(R.id.content, PlayFragment);
			}else {
				transaction.show(PlayFragment);
			}
			break;
		case 2:
			settingImage.setImageResource(R.drawable.setting_selected);
			settingText.setTextColor(Color.WHITE);
			customTitle.setText("更多信息");
			if (OtherFragment==null) {
				OtherFragment=new OtherFragment();
				transaction.add(R.id.content, OtherFragment);
			}else {
				transaction.show(OtherFragment);
			}
			break;

		default:
			break;
		}
		transaction.commit();
	}
	//隐藏已加载的fragment，防止出现重叠
	private void hideFragment(FragmentTransaction transaction){
		if (Msgfragment!=null) {
			transaction.hide(Msgfragment);
		}
		if (PlayFragment!=null) {
			transaction.hide(PlayFragment);
		}
		if (OtherFragment!=null) {
			transaction.hide(OtherFragment);
		}
	}
	//切换fragment时清除之前的点击效果
	private void clearSelection(){
		newsImage.setImageResource(R.drawable.news_unselected);
		optionImage.setImageResource(R.drawable.contacts_unselected);
		settingImage.setImageResource(R.drawable.setting_unselected);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater=new MenuInflater(this);
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.collect:
			startActivity(new Intent(FragmentActivity.this, CollectActivity.class));
			break;
        case R.id.about:
        	startActivity(new Intent(FragmentActivity.this, StartActivity.class));
        	break;
		}
		return true;
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
//		super.onBackPressed();
		AlertDialog.Builder dialog=new AlertDialog.Builder(this);
		dialog.setTitle("确认离开？");
		dialog.setCancelable(false);
		dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				ActivityCollector.finishAll();
			}
		});
		dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
		dialog.show();
	}
}
