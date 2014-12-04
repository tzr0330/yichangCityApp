package untils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class HandleResponse {
	public static boolean handleCurrentWeatherResponse(Context context,String response){
		try {
			JSONObject jsonObject=new JSONObject(response);
			JSONObject weatherinfo=jsonObject.getJSONObject("weatherinfo");
			String cityname=weatherinfo.getString("city");
			String temp=weatherinfo.getString("temp");
			String weathercode=weatherinfo.getString("cityid");
			String time=weatherinfo.getString("time");
			Log.d("current_time", "最后更新的时间是："+time);
			saveCurrentWeathreInfo(context,cityname,time,weathercode,temp);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static boolean handleDayWeatherResponse(Context context,String response){
		try {
			JSONObject jsonObject=new JSONObject(response);
			JSONObject weatherinfo=jsonObject.getJSONObject("weatherinfo");
			String weather=weatherinfo.getString("weather");
			Log.d("weather", weather);
			String min_temp=weatherinfo.getString("temp1");
			Log.d("min_temp", min_temp);
			String max_temp=weatherinfo.getString("temp2");
			Log.d("max_temp", max_temp);
			saveDayWeatherInfo(context,min_temp,max_temp,weather);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static void saveCurrentWeathreInfo(Context context,String cityname,String time,String weathercode,String temp){
		SimpleDateFormat sdf=new SimpleDateFormat("yyy年M月d日", Locale.CHINA);
		SharedPreferences.Editor editor=context.getSharedPreferences("currentWeaher", 0).edit();
		editor.putBoolean("city_select", true);
		editor.putString("cityname", cityname);
		
		editor.putString("time", time);
	
		editor.putString("weathercode", weathercode);
	
		editor.putString("temp", temp);
		
		
		editor.putString("current_day", sdf.format(new Date()));
		editor.commit();
	}
	
	public static void saveDayWeatherInfo(Context context,String min_temp,String max_temp,String weather){
		SharedPreferences.Editor editor=context.getSharedPreferences("dayWeather", 0).edit();
		
		
		editor.putString("min_temp", min_temp);
		editor.putString("max_temp", max_temp);
		
		editor.putString("weather", weather);
		editor.commit();
	}
	
	
	public static void handleListWeatherResponse(Context context,String response){
		try {
			JSONObject jsonObject=new JSONObject(response);
			JSONObject weatherinfo=jsonObject.getJSONObject("weatherinfo");
			String  week=weatherinfo.getString("week");
			String temp1=weatherinfo.getString("temp1");
			String temp2=weatherinfo.getString("temp2");
			String temp3=weatherinfo.getString("temp3");
			String temp4=weatherinfo.getString("temp4");
			String temp5=weatherinfo.getString("temp5");
			String temp6=weatherinfo.getString("temp6");
			String weather1=weatherinfo.getString("weather1");
			String weather2=weatherinfo.getString("weather2");
			String weather3=weatherinfo.getString("weather3");
			String weather4=weatherinfo.getString("weather4");
			String weather5=weatherinfo.getString("weather5");
			String weather6=weatherinfo.getString("weather6");
			saveListWeatherInfo(context, week, temp1, temp2, temp3, temp4, temp5, temp6, weather1, weather2, weather3, weather4, weather5, weather6);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public static void saveListWeatherInfo(Context context ,String week,String temp1,String temp2,String temp3,String temp4,String temp5,String temp6,String weather1,String weather2,String weather3,String weather4,String weather5,String weather6) {
		// TODO Auto-generated method stub
      SharedPreferences.Editor editor=context.getSharedPreferences("list_weather", 0).edit();
      editor.putString("week", week);
      editor.putString("temp1", temp1);
      editor.putString("temp2", temp2);
      editor.putString("temp3", temp3);
      editor.putString("temp4", temp4);
      editor.putString("temp5", temp5);
      editor.putString("temp6", temp6);
      editor.putString("weather1", weather1);
      editor.putString("weather2", weather2);
      editor.putString("weather3", weather3);
      editor.putString("weather4", weather4);
      editor.putString("weather5", weather5);
      editor.putString("weather6", weather6);
      editor.commit();
	}
	
}
