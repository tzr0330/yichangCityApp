package DataBase;

import java.util.ArrayList;
import java.util.List;

import modle.ListInfo;
import android.R.integer;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class AppDB {
    public static final String DB_NAME="singht.db";	
    private SQLiteDatabase db;
    private static AppDB appDB;
    private  AppDB(Context context) {
		// TODO Auto-generated constructor stub
    	ListViewOpenHelper dbOpenHelper=new ListViewOpenHelper(context, DB_NAME, null, 1);
    	db=dbOpenHelper.getWritableDatabase();
	}
    public synchronized  static AppDB  getInstance(Context context) {
		if (appDB==null) {
			appDB=new AppDB(context);
		}
		return appDB;
	}
    public void saveSinght(ListInfo info,String content) {
		if (info!=null&&!queryId(info.getId())) {
			db.execSQL("insert  into list_singht(title,introduce,address,time,content,_id) values(?,?,?,?,?,?)",new Object[]{info.getTitle(),info.getIntroduce(),info.getAddress(),info.getTime(),content,info.getId()});
			
		}
	}
    
    public List<ListInfo> loadSinght(){
    	List<ListInfo> list=new ArrayList<ListInfo>();
    	Cursor cursor=db.query("list_singht", null, null, null, null, null, null);
    	while (cursor.moveToNext()) {
			ListInfo info=new ListInfo();
			info.setTitle(cursor.getString(cursor.getColumnIndex("title")));
			Log.d("title", cursor.getString(cursor.getColumnIndex("title")));
			info.setIntroduce(cursor.getString(cursor.getColumnIndex("introduce")));
			info.setAddress(cursor.getString(cursor.getColumnIndex("address")));
			info.setTime(cursor.getString(cursor.getColumnIndex("time")));
			info.setId(cursor.getInt(cursor.getColumnIndex("_id")));
			list.add(info);
		}
    	cursor.close();
    	return list;
    }
    
    public boolean queryId(int id) {
    	Cursor cursor=db.rawQuery("select *from list_singht where _id=?",new String[]{String.valueOf(id)});
    	if (cursor.moveToFirst()) {
    		cursor.close();
			return true;
    	}
    	cursor.close();
		return false;
	}
    
    public void deletData(int id) {
		db.execSQL("delete from list_singht where _id=?",new Object[]{id});
	}
    public String queryContent(int id) {
		String content=null;
		Cursor cursor=db.rawQuery("select *from list_singht where _id=?", new String[]{String.valueOf(id)});
		if (cursor.moveToFirst()) {
			content=cursor.getString(cursor.getColumnIndex("content"));
			
			return content;
		}else {
			cursor.close();
			return null;
		}
	}
}
