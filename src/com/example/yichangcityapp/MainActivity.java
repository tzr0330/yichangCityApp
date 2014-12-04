package com.example.yichangcityapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import untils.BaseActivity;

public class MainActivity extends BaseActivity {
    private SharedPreferences spf;
    private boolean flag=false;
    private Handler handler=new Handler(){
    	public void handleMessage(android.os.Message msg) {
    		if (msg.what==0x123) {
    			startActivity(new Intent(MainActivity.this, FragmentActivity.class));
    			finish();
			}
    	};
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.main);
    	spf=this.getSharedPreferences("check_isfirst", 0);
    	flag=spf.getBoolean("first_start", true);
    	Log.d("flag","flah="+ flag);
    	if (flag) {
			firstStart();
		}
    	else {
			sleepTime();
		}
    }
    private void sleepTime(){
    	new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
			    try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    Message msg=Message.obtain();
			    msg.what=0x123;
			    handler.sendEmptyMessage(msg.what);
			}
		}).start();
    }
    private void firstStart(){
    	SharedPreferences.Editor editor=this.getSharedPreferences("check_isfirst", 0).edit();
    	editor.putBoolean("first_start", false);
    	Log.d("First_Start", "÷¥–––¥»ÎFALSE");
    	editor.commit();
    	startActivity(new Intent(MainActivity.this,StartActivity.class));
    	finish();
    }
}
