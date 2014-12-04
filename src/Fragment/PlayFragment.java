package Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import otherActivity.NewsSinghtActivity;
import untils.CatchHtml;
import untils.GetData;
import modle.ListAdrImage;
import modle.ListInfo;

import com.example.yichangcityapp.R;

import DataBase.AppDB;
import adapter.MyListViewAdapter;
import android.R.integer;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputFilter.LengthFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class PlayFragment extends Fragment implements OnScrollListener{
	private ListView listview;
	
	private  ProgressBar pg;
	private View moreview;
	private int maxDataNum;
	private int lastVisibleIndex;
	private MyListViewAdapter adapter;
	private List<ListInfo> listInfos;
	private ProgressDialog progressDialog;
	private AppDB db;
	 Handler handler=new Handler(){
		public void handleMessage(Message msg) {
			if (msg.what==1) {
				Log.i("TAG", "加载adapter");
//				listInfos=GetData.getListInfo(ListAdrImage.URL_IMAGE, db);
				adapter=new MyListViewAdapter(getActivity(), listInfos);
				listview.addFooterView(moreview);
				listview.setAdapter(adapter);
				closeProgressDialog();
			}
			if (msg.what==2) {
				
                pg.setVisibility(View.GONE);
                adapter.notifyDataSetChanged();
			}
		};
	};
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		showProgressDialog();
		listInfos=new ArrayList<ListInfo>();
		maxDataNum=ListAdrImage.URL_ADDRESS.length;
		db=AppDB.getInstance(getActivity());
		queryData();

	}
  @Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	  View view=inflater.inflate(R.layout.list_view, null);
	  if (view!=null) {
		listview=(ListView)view.findViewById(R.id.listView);
		moreview=getActivity().getLayoutInflater().inflate(R.layout.moredata, null);
		
		pg=(ProgressBar)moreview.findViewById(R.id.pg);
	}
	return view;
}
  private void queryData() {
	// TODO Auto-generated method stub
    new Thread(new Runnable() {
		
		@Override
		public void run() {
//			 TODO Auto-generated method stub

		    initData();
			Message message=Message.obtain();
			
			message.what=1;
		
			handler.sendEmptyMessage(message.what);
		}
	}).start();
}
   private void initData() {
	// TODO Auto-generated method stub
     for (int i = 0; i < 5; i++) {
    	 Map<String, String> map=new HashMap<String, String>();
		  map=CatchHtml.getListHtmlText(ListAdrImage.URL_ADDRESS[i]);
		  ListInfo listInfo=new ListInfo();
		  listInfo.setImage(ListAdrImage.URL_IMAGE[i]);
		  listInfo.setTitle(map.get("singht_title"));
		  listInfo.setIntroduce(map.get("singht_intro"));
		  listInfo.setAddress(map.get("singht_address"));
		  listInfo.setTime(map.get("singht_time"));
		  listInfo.setId(i);
		  listInfos.add(listInfo);
	}
}
  @Override
public void onActivityCreated(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onActivityCreated(savedInstanceState);
	 
     listview.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
		
				Intent intent=new Intent(getActivity(),NewsSinghtActivity.class);
				Bundle bundle=new Bundle();
				bundle.putInt("postion", position);
				bundle.putString("address", ListAdrImage.URL_ADDRESS[position]);
				bundle.putString("title", listInfos.get(position).getTitle());
				bundle.putInt("image", listInfos.get(position).getImage());
				bundle.putInt("_id", listInfos.get(position).getId());
				intent.putExtras(bundle);
				startActivity(intent);
		}
	});
     
     listview.setOnScrollListener(this);

}
  
  private void loadmore(){
	  int count=adapter.getCount();
	  if (count+5<maxDataNum) {
		  for (int i=count;i<count+5;i++) {
			  Map<String, String> map=new HashMap<String, String>();
			  map=CatchHtml.getListHtmlText(ListAdrImage.URL_ADDRESS[i]);
			  ListInfo listInfo=new ListInfo();
			  listInfo.setImage(ListAdrImage.URL_IMAGE[i]);
			  listInfo.setTitle(map.get("singht_title"));
			  listInfo.setIntroduce(map.get("singht_intro"));
			  listInfo.setAddress(map.get("singht_address"));
			  listInfo.setTime(map.get("singht_time"));
			  listInfo.setId(i);
			  listInfos.add(listInfo);
			}
	}else {
		for (int i = count; i < maxDataNum; i++) {
			 Map<String, String> map=new HashMap<String, String>();
			  map=CatchHtml.getListHtmlText(ListAdrImage.URL_ADDRESS[i]);
			  ListInfo listInfo=new ListInfo();
			  listInfo.setImage(ListAdrImage.URL_IMAGE[i]);
			  listInfo.setTitle(map.get("singht_title"));
			  listInfo.setIntroduce(map.get("singht_intro"));
			  listInfo.setAddress(map.get("singht_address"));
			  listInfo.setTime(map.get("singht_time"));
			  listInfo.setId(i);
			  listInfos.add(listInfo);
		}
	}
	  
  }
  
  private void showProgressDialog() {
		// TODO Auto-generated method stub
       if (progressDialog==null) {
			progressDialog=new ProgressDialog(getActivity());
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
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		if (scrollState==OnScrollListener.SCROLL_STATE_IDLE&&lastVisibleIndex==adapter.getCount()) {
			pg.setVisibility(View.VISIBLE);// 将进度条可见
           
           new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					loadmore();
					Message message=Message.obtain();
					
					message.what=2;
				
					handler.sendEmptyMessage(message.what);
				}
			}).start();
		}
	}
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		lastVisibleIndex=firstVisibleItem+visibleItemCount-1;
		if (totalItemCount==maxDataNum) {
			listview.removeFooterView(moreview);
			Toast.makeText(getActivity(), "数据全部加载完成，没有更多数据！", Toast.LENGTH_LONG).show();
		}
	}
  
  
}
