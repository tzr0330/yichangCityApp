package otherActivity;

import modle.TitleAndIcon;

import com.example.yichangcityapp.R;

import adapter.CustomAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import untils.BaseActivity;

public class FoodActivity extends BaseActivity {
	private GridView gridView;
	private CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	setContentView(R.layout.msg_fragment);
    	gridView=(GridView)findViewById(R.id.gridView1);
    	adapter=new CustomAdapter(FoodActivity.this, TitleAndIcon.food_titles, TitleAndIcon.food_icons);
    	gridView.setAdapter(adapter);
    	gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(FoodActivity.this, MoreFoodActivity.class);
                intent.putExtra("postion", position);
                startActivity(intent);
			}
		});
    }
}
