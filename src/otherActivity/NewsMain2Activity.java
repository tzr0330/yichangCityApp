package otherActivity;

import modle.TitleAndIcon;

import com.example.yichangcityapp.R;

import adapter.CustomAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import untils.BaseActivity;

public class NewsMain2Activity extends BaseActivity {
	private GridView gridview;
	private CustomAdapter adapter;
   @Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.news2);
	gridview=(GridView)findViewById(R.id.gridView);
	initAdapter();
	gridview.setAdapter(adapter);
	gridview.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			
		}
	});
}
   
   private void initAdapter() {
	// TODO Auto-generated method stub
    Bundle bundle=getIntent().getExtras();
    int[] titles=bundle.getIntArray("titles");
    int[] icons=bundle.getIntArray("icons");
    adapter=new CustomAdapter(this, titles, icons);
}
}
