package adapter;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.ls.LSInput;

import com.example.yichangcityapp.R;

import modle.ListInfo;
import adapter.CustomAdapter.ViewHolder;
import android.R.color;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyListViewAdapter extends BaseAdapter {
   private LayoutInflater layoutInflater;
  
   private List<ListInfo> listInfo;
   public MyListViewAdapter(Context context, List<ListInfo> listInfo) {
	// TODO Auto-generated constructor stub
	   layoutInflater=LayoutInflater.from(context);
      this.listInfo=listInfo;
	
}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listInfo.size();
	}
   
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listInfo.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder;
		if (convertView==null) {
			convertView=layoutInflater.inflate(R.layout.list_sight, null);
			viewHolder=new ViewHolder();
			viewHolder.imageView=(ImageView)convertView.findViewById(R.id.image_sight);
			viewHolder.titleView=(TextView)convertView.findViewById(R.id.title_sight);
			viewHolder.introduceView=(TextView)convertView.findViewById(R.id.introduce_sight);
			viewHolder.addressView=(TextView)convertView.findViewById(R.id.address_sight);
			viewHolder.timeView=(TextView)convertView.findViewById(R.id.time_sight);
			convertView.setTag(viewHolder);
		}else {
			viewHolder=(ViewHolder)convertView.getTag();
		}
		viewHolder.imageView.setImageResource(listInfo.get(position).getImage());
		viewHolder.titleView.setText(listInfo.get(position).getTitle());
		viewHolder.introduceView.setText(listInfo.get(position).getIntroduce());
		viewHolder.addressView.setText(listInfo.get(position).getAddress());
		viewHolder.timeView.setText(listInfo.get(position).getTime());
		return convertView;
	}
    class ViewHolder{
    	public ImageView imageView;
    	public TextView titleView;
    	public TextView introduceView;
    	public TextView addressView;
    	public TextView timeView;
    }
}
