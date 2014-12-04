package untils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DataBase.AppDB;
import android.util.Log;
import modle.ListInfo;

public class GetData {
    
   public static void saveListInfo( String address,AppDB db){

	   
	   String singht_title;
	   String singht_intro;
	   String singht_address;
	   String singht_time;
		   Map<String, String> map=new HashMap<String, String>();
				map=CatchHtml.getListHtmlText(address);
					   ListInfo listInfo=new ListInfo();
					   singht_title=map.get("singht_title");
					   Log.d("singht_title", singht_title);
					   singht_intro=map.get("singht_intro");
					   Log.d("singht_intro", singht_intro);
					   singht_address=map.get("singht_address");
					   Log.d("singht_address", singht_address);
					   singht_time=map.get("singht_time");
					   Log.d("singht_time", singht_time);
					   listInfo.setTitle(singht_title);
					   listInfo.setIntroduce(singht_intro);
					   listInfo.setAddress(singht_address);
					   listInfo.setTime(singht_time);
//					   db.saveSinght(listInfo);
	}  
	 
   
public static List<ListInfo> getListInfo(int[] images,AppDB db) {
	List<ListInfo> listInfos=new ArrayList<ListInfo>();
	listInfos=db.loadSinght();
	int i=0;
	for (ListInfo listInfo : listInfos) {
		
		listInfo.setImage(images[i]);
		i++;
	}
	return listInfos;
}
}
