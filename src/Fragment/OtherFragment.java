package Fragment;

import otherActivity.FoodActivity;
import otherActivity.PictureWallActivity;
import otherActivity.VideoViewActivity;
import otherActivity.WeatherActivity;

import com.example.yichangcityapp.R;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class OtherFragment extends Fragment implements OnClickListener{
	private LinearLayout wea_lay,food_lay,picture_lay,video_lay;
   @Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	   View view=inflater.inflate(R.layout.other_frag, null);
	   if (view!=null) {
		wea_lay=(LinearLayout)view.findViewById(R.id.wea_lay);
		food_lay=(LinearLayout)view.findViewById(R.id.food_lay);
		picture_lay=(LinearLayout)view.findViewById(R.id.picture_lay);
		video_lay=(LinearLayout)view.findViewById(R.id.video_lay);
		wea_lay.setOnClickListener(this);
		food_lay.setOnClickListener(this);
		picture_lay.setOnClickListener(this);
		video_lay.setOnClickListener(this);
	}
	return view;
}
@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	switch (v.getId()) {
	case R.id.wea_lay:
		startActivity(new Intent(getActivity(), WeatherActivity.class));
		break;
	case R.id.food_lay:
//		Toast.makeText(getActivity(), "you click wea_lay", 1).show();
		startActivity(new Intent(getActivity(), FoodActivity.class));
		break;
	case R.id.picture_lay:
		startActivity(new Intent(getActivity(), PictureWallActivity.class));
		break;
	case R.id.video_lay:
		startActivity(new Intent(getActivity(), VideoViewActivity.class));
		break;
	default:
		break;
	}
}
}
