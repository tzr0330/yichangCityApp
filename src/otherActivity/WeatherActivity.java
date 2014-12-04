package otherActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.example.yichangcityapp.R;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import untils.BaseActivity;
import untils.CatchHtml;
import untils.HandleResponse;

public class WeatherActivity extends BaseActivity implements OnClickListener{
	private TextView cityname,publishTime,currentTime,currentWeather,min_temp,max_temp,temp;
	private Button switchCity,refreshWeather;
	private ListView listWeather;
	private RelativeLayout layout;
	private ProgressDialog progressDialog;
	private SimpleAdapter adapter;
	private List<Map<String, String>> datalist = new ArrayList<Map<String, String>>();
	public static final String ADDRESS_1="http://www.weather.com.cn/data/cityinfo/101200901.html";
	public static final String ADDRESS_2="http://www.weather.com.cn/data/sk/101200901.html";
	public static final String ADDRESS_3="http://m.weather.com.cn/data/101200901.html";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	setContentView(R.layout.weather_layout);
    	
    	layout=(RelativeLayout)findViewById(R.id.weatherinfo);
    	cityname=(TextView)findViewById(R.id.city_name);
    	temp=(TextView)findViewById(R.id.current_tem);
    	publishTime=(TextView)findViewById(R.id.publish_time);
    	listWeather=(ListView)findViewById(R.id.list_weather);
    	currentTime=(TextView)findViewById(R.id.current_time);
    	currentWeather=(TextView)findViewById(R.id.current_weather);
    	min_temp=(TextView)findViewById(R.id.max_tem);
    	max_temp=(TextView)findViewById(R.id.min_tem);
    	switchCity=(Button)findViewById(R.id.switch_city);
    	refreshWeather=(Button)findViewById(R.id.refresh_weather);
    	adapter=new SimpleAdapter(this, datalist, R.layout.list_weather, new String[]{"date","week","weather","temp"}, new int[]{R.id.date_mon,R.id.date_week,R.id.date_weather,R.id.date_temp});
    	listWeather.setAdapter(adapter);
    	switchCity.setOnClickListener(this);
    	refreshWeather.setOnClickListener(this);
		cityname.setText("同步中........");
		layout.setVisibility(View.INVISIBLE);
		queryWeatherInfo();
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.switch_city:
			finish();
			break;
        case R.id.refresh_weather:
        	queryWeatherInfo();
        	break;
		default:
			break;
		}
	}
	
	private void queryWeatherInfo(){
		CatchHtml.sendHttpRequst(ADDRESS_2, new CatchHtml.HttpCallBackListener() {
			
			@Override
			public void onFinish(String response) {
				// TODO Auto-generated method stub
				HandleResponse.handleCurrentWeatherResponse(WeatherActivity.this, response);
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						showWeather();
					}
				});
			}
			
			@Override
			public void onError(Exception e) {
				// TODO Auto-generated method stub
				Toast.makeText(WeatherActivity.this, "加载失败，请检查网络",1).show();
			}
		});
		
		CatchHtml.sendHttpRequst(ADDRESS_1, new CatchHtml.HttpCallBackListener() {
			
			@Override
			public void onFinish(String response) {
				// TODO Auto-generated method stub
				HandleResponse.handleDayWeatherResponse(WeatherActivity.this, response);
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						showWeather();
					}
				});
			}
			
			@Override
			public void onError(Exception e) {
				// TODO Auto-generated method stub
				Toast.makeText(WeatherActivity.this, "加载失败，请检查网络",1).show();
			}
		});
		
		CatchHtml.sendHttpRequst(ADDRESS_3, new CatchHtml.HttpCallBackListener() {
			
			@Override
			public void onFinish(String response) {
				// TODO Auto-generated method stub
				HandleResponse.handleListWeatherResponse(WeatherActivity.this, response);
//				runOnUiThread(new Runnable() {
//					
//					@Override
//					public void run() {
//						// TODO Auto-generated method stub
//						
//					}
//				});
			}
			
			@Override
			public void onError(Exception e) {
				// TODO Auto-generated method stub
				Toast.makeText(WeatherActivity.this, "加载失败，请检查网络",1).show();
			}
		});
	}
	
	 private String getWeek(Date pTime) {

		  String Week = "周";
		  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		  Calendar c = Calendar.getInstance();
		  c.setTime(pTime);
		  if (c.get(Calendar.DAY_OF_WEEK) == 1) {
		   Week += "日";
		  }
		  if (c.get(Calendar.DAY_OF_WEEK) == 2) {
		   Week += "一";
		  }
		  if (c.get(Calendar.DAY_OF_WEEK) == 3) {
		   Week += "二";
		  }
		  if (c.get(Calendar.DAY_OF_WEEK) == 4) {
		   Week += "三";
		  }
		  if (c.get(Calendar.DAY_OF_WEEK) == 5) {
		   Week += "四";
		  }
		  if (c.get(Calendar.DAY_OF_WEEK) == 6) {
		   Week += "五";
		  }
		  if (c.get(Calendar.DAY_OF_WEEK) == 7) {
		   Week += "六";
		  }
		  return Week;
		 }
	 
	 
		private void listWeatherData(){
	          SharedPreferences list_spf=this.getSharedPreferences("list_weather", 0);
	          SimpleDateFormat sdf=new SimpleDateFormat("yyy年M月d日", Locale.CHINA);
	          Date date=new Date();
	          Calendar cal = Calendar.getInstance();
	          cal.setTime(date);
	          cal.add(cal.DATE ,1);  //第2天，第x天，照加。如果是负数，表示前n天。
	          Date one = cal.getTime();
	          cal.add(cal.DATE, 1);
	          Date two = cal.getTime();
	          cal.add(cal.DATE, 1);
	          Date three = cal.getTime();
	          cal.add(cal.DATE, 1);
	          Date four = cal.getTime();
	          cal.add(cal.DATE, 1);
	          Date five = cal.getTime();
	          cal.add(cal.DATE, 1);
	          Date six = cal.getTime();
			  datalist.clear();
			  Date[] datelist=new Date[]{one,two,three,four,five,six};
			  String[] weeklist=new String[]{getWeek(one),getWeek(two),getWeek(three),getWeek(four),getWeek(five),getWeek(six) };
			for (int i = 1; i < 7; i++) {	
				Map<String, String> list_item=new HashMap<String, String>();
				list_item.put("date",  sdf.format(datelist[i-1]));

				list_item.put("week", weeklist[i-1]);
				list_item.put("temp" ,list_spf.getString("temp"+i+"", "没有"));

				list_item.put("weather", list_spf.getString("weather"+i+"", "错误"));

				datalist.add(list_item);
			}
			adapter.notifyDataSetChanged();
		}
		
		private void showWeather(){
			SharedPreferences current_spf=this.getSharedPreferences("currentWeaher", 0);
			SharedPreferences day_spf=this.getSharedPreferences("dayWeather", 0);
			listWeatherData();
			
			layout.setVisibility(View.VISIBLE);
			cityname.setText(current_spf.getString("cityname", "FALSE"));
			publishTime.setText(current_spf.getString("time", "FALSE"));
			temp.setText(current_spf.getString("temp", "?????"));
			currentWeather.setText(day_spf.getString("weather", "FALSE")+",");
			min_temp.setText(day_spf.getString("min_temp", "FALSE"));
			max_temp.setText(day_spf.getString("max_temp", "FALSE"));
			currentTime.setText(current_spf.getString("current_day", "XX")+",");
		
		}
		
		private void showProgressDialog() {
			// TODO Auto-generated method stub
	         if (progressDialog==null) {
				progressDialog=new ProgressDialog(this);
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
}
