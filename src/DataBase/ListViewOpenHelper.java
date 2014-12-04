package DataBase;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ListViewOpenHelper extends SQLiteOpenHelper {
	//建表list_singht存储网页上得到的景点信息
    public static final String CREATE_SIGHT="create table list_singht(id integer primary key autoincrement,title text,introduce text,address text,time text,content text,_id integer)";
    public static final String CREATE_MSINGHT="create table more_singht(id integer primary key autoincrement,title text,content text)";
	public ListViewOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	public ListViewOpenHelper(Context context, String name,
			CursorFactory factory, int version,
			DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
     db.execSQL(CREATE_SIGHT);
     db.execSQL(CREATE_MSINGHT);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
