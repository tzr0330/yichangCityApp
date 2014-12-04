package com.example.yichangcityapp;

import untils.BaseActivity;
import widget.MyScrollLayout;
import widget.OnViewChangeListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class StartActivity extends BaseActivity implements
		OnViewChangeListener {
	private MyScrollLayout mScrollLayout;
	private int count;
	private int currentItem;
	private Button startbn;
	private LinearLayout line_layout;
	private ImageView images[];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		startService(new Intent(this, LoadDataService.class));
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		mScrollLayout = (MyScrollLayout) findViewById(R.id.scrolllayout);
		line_layout = (LinearLayout) findViewById(R.id.line_layout);
		startbn = (Button) findViewById(R.id.start_app);
		startbn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(StartActivity.this, MainActivity.class));
				finish();
			}
		});
		count = mScrollLayout.getChildCount();
		images = new ImageView[count];
		for (int i = 0; i < count; i++) {
			images[i] = (ImageView) line_layout.getChildAt(i);
			images[i].setEnabled(true);
			images[i].setTag(i);
		}
		currentItem = 0;
		images[currentItem].setEnabled(false);
		mScrollLayout.SetOnViewChangeListener(this);
	}

	@Override
	public void OnViewChange(int view) {
		// TODO Auto-generated method stub
          setcurrentPoint(view);
	}

	private void setcurrentPoint(int position) {
		if (position < 0 || position > count - 1 || currentItem == position) {
			return;
		}
		images[currentItem].setEnabled(true);
		images[position].setEnabled(false);
		currentItem = position;
	}
}
