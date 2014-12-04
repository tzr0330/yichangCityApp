package Fragment;

import otherActivity.NewsMain2Activity;
import otherActivity.NewsMainActivity;
import modle.TitleAndIcon;

import com.example.yichangcityapp.R;

import adapter.CustomAdapter;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class MsgFragment extends Fragment {
	private GridView gridview;
	private CustomAdapter adapter;
	private  String MAIN_ADDRESS;
    private  String MAIN_SELECT;
    private  String IMAGE_ADDRESS;
    private String tag;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	  View view=inflater.inflate(R.layout.msg_fragment, null);
	  if (view!=null) {
		gridview=(GridView)view.findViewById(R.id.gridView1);
		adapter=new CustomAdapter(getActivity(), TitleAndIcon.news_titles, TitleAndIcon.news_icons);
	}
		return view;
	}
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onActivityCreated(savedInstanceState);
    	gridview.setAdapter(adapter);
    	gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
//				Toast.makeText(getActivity(), "click on"+position, Toast.LENGTH_SHORT).show();
				switch (position) {
				case 0:
//					startActivity(new Intent(getActivity(), NewsMainActivity.class));
					Intent intent0=new Intent(getActivity(), NewsMainActivity.class);
					Bundle bundle0=new Bundle();
				    MAIN_ADDRESS="http://www.yichang.gov.cn/col/col4281/index.html";
				    MAIN_SELECT="td[height]";
				    IMAGE_ADDRESS="http://www.yichang.gov.cn/picture/562/ycyj-11-30-080811102146062.jpg";
				    tag="p";
					bundle0.putString("MAIN_ADDRESS", MAIN_ADDRESS);
					bundle0.putString("MAIN_SELECT", MAIN_SELECT);
					bundle0.putString("IMAGE_ADDRESS", IMAGE_ADDRESS);
					bundle0.putString("tag", tag);
					bundle0.putString("title", getActivity().getString(TitleAndIcon.news_titles[0]));
					intent0.putExtras(bundle0);
					startActivity(intent0);
					break;
				case 1:
					Intent intent1=new Intent(getActivity(), NewsMainActivity.class);
					Bundle bundle1=new Bundle();
				    MAIN_ADDRESS="http://www.yichang.gov.cn/col/col241/index.html";
				    MAIN_SELECT="td[height]";
				    IMAGE_ADDRESS="http://www.yichang.gov.cn/picture/1/070410220053078.jpg";
				    tag="div";
					bundle1.putString("MAIN_ADDRESS", MAIN_ADDRESS);
					bundle1.putString("MAIN_SELECT", MAIN_SELECT);
					bundle1.putString("IMAGE_ADDRESS", IMAGE_ADDRESS);
					bundle1.putString("tag", tag);
					bundle1.putString("title", getActivity().getString(TitleAndIcon.news_titles[1]));
					intent1.putExtras(bundle1);
					startActivity(intent1);
                    break;
				case 2:
					Intent intent2=new Intent(getActivity(), NewsMain2Activity.class);
					Bundle bundle2=new Bundle();
					bundle2.putIntArray("titles", TitleAndIcon.city_titles);
					bundle2.putIntArray("icons", TitleAndIcon.city_icons);
					intent2.putExtras(bundle2);
					startActivity(intent2);
					break;
					
				case 3:
					Intent intent3=new Intent(getActivity(), NewsMainActivity.class);
					Bundle bundle3=new Bundle();
				    MAIN_ADDRESS="http://www.yichang.gov.cn/col/col241/index.html";
				    MAIN_SELECT="td[height]";
				    IMAGE_ADDRESS="http://www.yichang.gov.cn/picture/1/070413221638687.jpg";
				    tag="div";
					bundle3.putString("MAIN_ADDRESS", MAIN_ADDRESS);
					bundle3.putString("MAIN_SELECT", MAIN_SELECT);
					bundle3.putString("IMAGE_ADDRESS", IMAGE_ADDRESS);
					bundle3.putString("tag", tag);
					bundle3.putString("title", getActivity().getString(TitleAndIcon.news_titles[3]));
					intent3.putExtras(bundle3);
					startActivity(intent3);
                    break;
				case 4:
					Intent intent4=new Intent(getActivity(), NewsMainActivity.class);
					Bundle bundle4=new Bundle();
				    MAIN_ADDRESS="http://www.yichang.gov.cn/col/col6221/index.html";
				    MAIN_SELECT="td[height]";
				    IMAGE_ADDRESS="http://www.yichang.gov.cn/picture/0/100429112605355.JPG";
				    tag="div";
					bundle4.putString("MAIN_ADDRESS", MAIN_ADDRESS);
					bundle4.putString("MAIN_SELECT", MAIN_SELECT);
					bundle4.putString("IMAGE_ADDRESS", IMAGE_ADDRESS);
					bundle4.putString("tag", tag);
					bundle4.putString("title", getActivity().getString(TitleAndIcon.news_titles[4]));
					intent4.putExtras(bundle4);
					startActivity(intent4);
                    break;
				case 5:
					Intent intent5=new Intent(getActivity(), NewsMain2Activity.class);
					Bundle bundle5=new Bundle();
					bundle5.putIntArray("titles", TitleAndIcon.pa_titles);
					bundle5.putIntArray("icons", TitleAndIcon.pa_icons);
					intent5.putExtras(bundle5);
					startActivity(intent5);
					break;
					
				case 6:
					Intent intent6=new Intent(getActivity(), NewsMainActivity.class);
					Bundle bundle6=new Bundle();
				    MAIN_ADDRESS="http://www.yichang.gov.cn/col/col244/index.html";
				    MAIN_SELECT="td[height]";
				    IMAGE_ADDRESS="http://www.yichang.gov.cn/picture/1/070413221858515.jpg";
				    tag="div";
					bundle6.putString("MAIN_ADDRESS", MAIN_ADDRESS);
					bundle6.putString("MAIN_SELECT", MAIN_SELECT);
					bundle6.putString("IMAGE_ADDRESS", IMAGE_ADDRESS);
					bundle6.putString("tag", tag);
					bundle6.putString("title", getActivity().getString(TitleAndIcon.news_titles[6]));
					intent6.putExtras(bundle6);
					startActivity(intent6);
                    break;
					
					
				case 7:
					Intent intent7=new Intent(getActivity(), NewsMainActivity.class);
					Bundle bundle7=new Bundle();
				    MAIN_ADDRESS="http://www.yichang.gov.cn/col/col242/index.html";
				    MAIN_SELECT="td[height]";
				    IMAGE_ADDRESS="http://www.yichang.gov.cn/picture/1/070413221301156.jpg";
				    tag="div";
					bundle7.putString("MAIN_ADDRESS", MAIN_ADDRESS);
					bundle7.putString("MAIN_SELECT", MAIN_SELECT);
					bundle7.putString("IMAGE_ADDRESS", IMAGE_ADDRESS);
					bundle7.putString("tag", tag);
					bundle7.putString("title", getActivity().getString(TitleAndIcon.news_titles[7]));
					intent7.putExtras(bundle7);
					startActivity(intent7);
					break;
				case 8:
					Intent intent8=new Intent(getActivity(), NewsMainActivity.class);
					Bundle bundle8=new Bundle();
				    MAIN_ADDRESS="http://www.yichang.gov.cn/art/2010/4/29/art_18306_232041.html";
				    MAIN_SELECT="td[height]";
				    IMAGE_ADDRESS="http://www.yichang.gov.cn/picture/0/120228165551438.jpg";
				    tag="div";
					bundle8.putString("MAIN_ADDRESS", MAIN_ADDRESS);
					bundle8.putString("MAIN_SELECT", MAIN_SELECT);
					bundle8.putString("IMAGE_ADDRESS", IMAGE_ADDRESS);
					bundle8.putString("tag", tag);
					bundle8.putString("title", "四大美人----王昭君");
					intent8.putExtras(bundle8);
					startActivity(intent8);
					break;
				
				default:
					break;
				}
			}
		});
    }
    
}
