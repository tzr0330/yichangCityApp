package untils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.util.Log;

public class CatchHtml {
	
//   private static Bitmap bitmap;
	public static String getHtmlText(String address,String select,String tag){
		Document doc=null;
		try {
			doc=Jsoup.parse(new URL(address), 5000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Elements texts=doc.select(select);
		for (Element element : texts) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("content", element.getElementsByTag(tag).text());
			Log.i("TAG", element.getElementsByTag(tag).text());
			list.add(map);
		}
		StringBuilder text=new StringBuilder();
	    for (int i = 0; i < list.size(); i++) {
			text.append(list.get(i).get("content"));
		}
	   return text.toString();
	}
	public static Map<String, String> getListHtmlText( String address){
		Document	doc=null;
//		 List<Map<String, String>> list = new ArrayList<Map<String, String>>();
				try {
				doc=Jsoup.parse(new URL(address), 5000);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Element sight=doc.select("div[class=sight_info]").first();
				Map<String, String> map=new HashMap<String, String>();
				String singht_title=sight.getElementsByTag("h1").text();

			     String singht_intro=sight.getElementsByClass("sight_info_intro").text();

			     String singht_address=sight.getElementById("sight-address").text();

			     String singht_time=sight.getElementsByClass("sight_info_hours").text();

			     map.put("singht_title", singht_title);
			     map.put("singht_intro", singht_intro);
			     map.put("singht_address", singht_address);
			     map.put("singht_time", singht_time);
//			    list.add(map);
	     return map;
	}
	
	public static String getMoreHtml(String address) {
		String text="";
		Document	doc=null;
		try {
			doc=Jsoup.parse(new URL(address), 5000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element textElement=doc.select("div[class=product_intro_item clrfix]").first();
	    text=textElement.getElementsByTag("p").text();
	    return text;
	}
	
	public static void sendHttpRequst(final String address,final HttpCallBackListener listener){
		new Thread(new Runnable() {
			@Override
			public void run() {
				HttpURLConnection connection = null;
				try {
					URL url = new URL(address);
					Log.d("address", address);
					connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setConnectTimeout(8000);
					connection.setReadTimeout(8000);
					InputStream in = connection.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(in));
					StringBuilder response = new StringBuilder();
					String line;
					while ((line = reader.readLine()) != null) {
						response.append(line);
					}
//					Log.d("Province_List", response.toString());
					if (listener != null) {
						Log.i("TAG", "执行到listerner.onFinish()");
						listener.onFinish(response.toString());
					}
				} catch (Exception e) {
					if (listener != null) {
						Log.i("TAG", "执行到listerner.onError()");
						listener.onError(e);
					}
				} finally {
					if (connection != null) {
						connection.disconnect();
						
					}
				}
			}
		}).start();
	}
	
	public static String getHtmlText2(String url) {
		Document doc=null;
		try {
			doc=Jsoup.parse(new URL(url),5000);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Element textElement=doc.select("div[id=zoom]").first();
		
	String text=textElement.getElementsByTag("p").text();

   return text;
	}
	
	 public interface HttpCallBackListener{
		  public void onFinish(String response);
		  public void onError(Exception e) ;
	  }
}


