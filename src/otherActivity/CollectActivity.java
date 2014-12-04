package otherActivity;

import java.util.ArrayList;
import java.util.List;

import modle.ListAdrImage;
import modle.ListInfo;

import com.example.yichangcityapp.R;

import DataBase.AppDB;
import adapter.MyListViewAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import untils.BaseActivity;

public class CollectActivity extends BaseActivity {
	private ListView listview;
	private MyListViewAdapter adapter;
	private List<ListInfo> listInfos;
	private AppDB db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view);
		// listInfos=new ArrayList<ListInfo>();
		db = AppDB.getInstance(this);
		listInfos = db.loadSinght();
		for (ListInfo i : listInfos) {
			i.setImage(ListAdrImage.URL_IMAGE[i.getId()]);
		}
		listview = (ListView) findViewById(R.id.listView);
		adapter = new MyListViewAdapter(this, listInfos);
		listview.setAdapter(adapter);
		registerForContextMenu(listview);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(CollectActivity.this,
						NewsSinghtActivity.class);
				Bundle bundle = new Bundle();
				bundle.putInt("postion", position);
				bundle.putString("title", listInfos.get(position).getTitle());
				bundle.putInt("image", listInfos.get(position).getImage());
				bundle.putInt("_id", listInfos.get(position).getId());
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		menu.add(0, 1, 0, "É¾³ý");
		menu.add(0, 2, 0, "È¡Ïû");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item
				.getMenuInfo();
		ListInfo info = (ListInfo) listview
				.getItemAtPosition((int) menuInfo.id);
		switch (item.getItemId()) {
		case 1:
			db.deletData(info.getId());
			// listInfos=db.loadSinght();
			listInfos.remove(info);
			adapter.notifyDataSetChanged();
			break;
		case 2:

			break;
		default:
			break;
		}

		return true;
	}
}
