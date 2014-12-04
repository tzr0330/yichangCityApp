package otherActivity;

import com.example.yichangcityapp.R;

import android.os.Bundle;
import android.view.Window;
import untils.BaseActivity;

public class ContactActivity extends BaseActivity {
  @Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	  requestWindowFeature(Window.FEATURE_NO_TITLE);
	super.onCreate(savedInstanceState);
	
	setContentView(R.layout.contacts);
}
}
