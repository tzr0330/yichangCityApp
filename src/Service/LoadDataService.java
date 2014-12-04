package Service;

import DataBase.AppDB;
import android.app.IntentService;
import android.content.Intent;

public class LoadDataService extends IntentService {
	private AppDB db;
	public LoadDataService() {
		super("LoadDataService");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
//      GetData.saveListInfo(ListAdrImage.URL_ADDRESS, db);
	}
  @Override
public void onDestroy() {
	// TODO Auto-generated method stub
	super.onDestroy();
}
}
