package adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.yichangcityapp.R;

import modle.CustomPicture;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
   private LayoutInflater layoutInflater;
//   private Context mContext;
   private List<CustomPicture> pictures; 
	public CustomAdapter(Context context,int[] titles,int[] images) {
		// TODO Auto-generated constructor stub
		pictures=new ArrayList<CustomPicture>();
		layoutInflater=LayoutInflater.from(context);
		for (int i = 0; i < images.length; i++) {
			CustomPicture picture=new CustomPicture(titles[i], images[i]);
			pictures.add(picture);
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (pictures!=null) {
			return pictures.size();
		}
		else {
			return 0;
		}
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return pictures.get(position);
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
			convertView=layoutInflater.inflate(R.layout.custom_picture, null);
			viewHolder=new ViewHolder();
			viewHolder.title=(TextView)convertView.findViewById(R.id.title);
			viewHolder.image=(ImageView)convertView.findViewById(R.id.image);
			convertView.setTag(viewHolder);
		}
		else {
			viewHolder=(ViewHolder)convertView.getTag();
		}
		viewHolder.title.setText(pictures.get(position).getTitle());
		viewHolder.image.setImageResource(pictures.get(position).getImageId());
		return convertView;
	}
    class ViewHolder{
    	public TextView title;
    	public ImageView image;
    }
}
