package adapter;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import otherActivity.ImageViewActivity;
import modle.Images;
import untils.ImageLoader;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.yichangcityapp.R;

import android.R.integer;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import android.view.View.OnTouchListener;

public class PictureScrollView extends ScrollView implements OnTouchListener {
	public static final int PAGE_SIZE = 15;
	private int page;
	private int columnWidth;
	private int firstColumnHeight;
	private int secondColumnHeight;
	private int thirdColumnHeight;
	private boolean loadOnce;
	private ImageLoader imageLoader;
	private LinearLayout firstColumn;
	private LinearLayout secondColumn;
	private LinearLayout thirdColumn;
	private static View scrollLayout;
	private static int scrollViewHeight;
	private static int lastScrollY = -1;
	private static Set<LoadImageTask> taskCollection;
	private List<ImageView> imageViewList = new ArrayList<ImageView>();
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			PictureScrollView myScrollView = (PictureScrollView) msg.obj;
			int scrollY = myScrollView.getScrollY();
//			Log.d("scorllY", "scroll=="+scrollY);
			
			// 如果当前的滚动位置和上次相同，表示已停止滚动
			if (scrollY == lastScrollY) {
//				Log.d("scorllY", "scrollY=="+scrollY+scrollViewHeight);
//				Log.d("AllscrollY", "allscroll=="+scrollLayout.getHeight());
//				Log.d("taskCollection", "taskCollection是否为空"+taskCollection.isEmpty());
				// 当滚动的最底部，并且当前没有正在下载的任务时，开始加载下一页的图片
				if (scrollViewHeight + scrollY >= scrollLayout.getHeight()&& taskCollection.isEmpty()) {
					Log.d("LoadMoreImages", "LoadMoreImages执行");
					myScrollView.loadMoreimages();
					
				}
				// myScrollView.checkVisibility();
			} else {
				lastScrollY = scrollY;
				Message message = new Message();
				message.obj = myScrollView;
				// 5毫秒后再次对滚动位置进行判断
				handler.sendMessageDelayed(message, 5);
			}
		};
	};

	public PictureScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		taskCollection = new HashSet<LoadImageTask>();
		imageLoader = ImageLoader.getInstance();
		setOnTouchListener(this);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
		if (changed && !loadOnce) {
			scrollViewHeight = getHeight();
			scrollLayout = getChildAt(0);
			
			firstColumn = (LinearLayout) findViewById(R.id.first_column);
			secondColumn = (LinearLayout) findViewById(R.id.second_column);
			thirdColumn = (LinearLayout) findViewById(R.id.third_column);
			columnWidth = firstColumn.getWidth();
			loadOnce = true;
			loadMoreimages();
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction() == MotionEvent.ACTION_UP) {
			Message message = new Message();
			message.obj = this;
			handler.sendMessageDelayed(message, 5);
		}
		return false;
	}

	public void loadMoreimages() {
		if (hasSDcard()) {
			int startIndex = page * PAGE_SIZE;
			int endIndex = page * PAGE_SIZE + PAGE_SIZE;
			if (startIndex < Images.imageUrls.length) {
				Toast.makeText(getContext(), "正在加载中.....", 1).show();
				if (endIndex > Images.imageUrls.length) {
					endIndex = Images.imageUrls.length;
				}
				for (int i = startIndex; i < endIndex; i++) {
					LoadImageTask task = new LoadImageTask();
					taskCollection.add(task);
					task.execute(Images.imageUrls[i]);
	
				}
				page++;
//				Log.d("Page", "page="+page);
			} else {
				Toast.makeText(getContext(), "没有图片需要加载.....", 1).show();
			}
		} else {
			Toast.makeText(getContext(), "没有发现SD卡", 1).show();
		}
	}

	private void checkVisibilty() {

	}

	private boolean hasSDcard() {
		return Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState());
	}

	class LoadImageTask extends AsyncTask<String, Void, Bitmap> {
		private String mImageUrl;
		

		public LoadImageTask() {
			// TODO Auto-generated constructor stub
		}

	

		@Override
		protected Bitmap doInBackground(String... params) {
			// TODO Auto-generated method stub
			mImageUrl = params[0];
			Bitmap bitmap = ImageLoader.getBitmapFromMermoryCache(mImageUrl);
			if (bitmap == null) {
				bitmap = loadImage(mImageUrl);
			}
			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap bitmap) {
			// TODO Auto-generated method stub
			if (bitmap != null) {
				double ratio = bitmap.getWidth() / (columnWidth * 1.0);
				int scaledHeight = (int) (bitmap.getHeight() / ratio);
				Log.d("width and height", "width ="+columnWidth+"---height="+scaledHeight);
				
				addImage(bitmap, columnWidth, scaledHeight);
			}
			taskCollection.remove(this);
		}

		private void addImage(Bitmap bitmap, int width, int height) {
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
				View view = LayoutInflater.from(getContext()).inflate(R.layout.image, null);
				ImageView ImageView = (ImageView) view.findViewById(R.id.imageView);
				ImageView.setImageBitmap(bitmap);
				ImageView.setLayoutParams(params);
				ImageView.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent=new Intent(getContext(),ImageViewActivity.class);
                        intent.putExtra("image_path", getImagePath(mImageUrl));
                       getContext().startActivity(intent);
					}
				});
				findColumnToAdd(ImageView, height).addView(view);
				
				imageViewList.add(ImageView);
			
		}
		

		private LinearLayout findColumnToAdd(ImageView imageView,
				int imageHeight) {
			if (firstColumnHeight <= secondColumnHeight) {
				if (firstColumnHeight <= thirdColumnHeight) {

					firstColumnHeight += imageHeight;

					return firstColumn;
				}

				thirdColumnHeight += imageHeight;

				return thirdColumn;
			} else {
				if (secondColumnHeight <= thirdColumnHeight) {

					secondColumnHeight += imageHeight;

					return secondColumn;
				}

				thirdColumnHeight += imageHeight;

				return thirdColumn;
			}
		}

		private Bitmap loadImage(String imageurl) {
			File file = new File(getImagePath(imageurl));
			Log.d("file ", "下载的文件是否存在=" + file.exists());
			if (!file.exists()) {
				downLoadImage(imageurl);
			}
			if (imageurl != null) {
				Bitmap bitmap = ImageLoader.decodeBitmap(file.getPath(),
						columnWidth);
				if (bitmap != null) {
					imageLoader.addBitmapToMemoryCache(imageurl, bitmap);
					return bitmap;
				}
			}
			return null;
		}

		private String getImagePath(String url) {
			
			String imagename = null ;
			
			for (int i = 0; i < Images.imageUrls.length; i++) {
				if (url==Images.imageUrls[i]) {
					imagename=i+".jpg";
				}
			}
			String imageDir = Environment.getExternalStorageDirectory()
					.getPath() + "/pictureWall/";
			File file = new File(imageDir);
			if (!file.exists()) {
				file.mkdirs();
			}
			String imagePath = imageDir + imagename;
			Log.d("PATH", "imagepath=" + imagePath);
			return imagePath;
		}

//		private void downLoadImage(String imageurl) {
//			HttpURLConnection con = null;
//			FileOutputStream out = null;
//			BufferedOutputStream bos = null;
//			BufferedInputStream bis = null;
//			File imageFile = null;
//			try {
//				URL url = new URL(imageurl);
//				con = (HttpURLConnection) url.openConnection();
//				con.setConnectTimeout(5 * 1000);
//				con.setReadTimeout(15 * 1000);
//				con.setDoInput(true);
//				con.setDoOutput(true);
//				bis = new BufferedInputStream(con.getInputStream());
//				imageFile = new File(getImagePath(imageurl));
//				Log.d("File path", "file_path=" + getImagePath(imageurl));
//				out = new FileOutputStream(imageFile);
//				bos = new BufferedOutputStream(out);
//				byte[] b = new byte[1024];
//				int length;
//				while ((length = bis.read(b)) != -1) {
//					bos.write(b, 0, length);
//
//					bos.flush();
//				}
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			} finally {
//				try {
//					if (con != null) {
//						con.disconnect();
//					}
//					if (bis != null) {
//						bis.close();
//					}
//					if (bos != null) {
//						bos.close();
//					}
//				} catch (Exception e2) {
//					// TODO: handle exception
//					e2.printStackTrace();
//				}
//			}
//			if (imageFile != null) {
//				Bitmap bitmap = ImageLoader.decodeBitmap(imageFile.getPath(),
//						columnWidth);
//				if (bitmap != null) {
//					imageLoader.addBitmapToMemoryCache(imageurl, bitmap);
//				}
//			}
//		}
		
		
		
//      private void downLoadImage(final String url){
//    	  RequestQueue mQueue=Volley.newRequestQueue(getContext());
//    	 
//    	  
//    	  ImageRequest request=new ImageRequest(url, new Response.Listener<Bitmap>() {
//
//			@Override
//			public void onResponse(Bitmap response) {
//				// TODO Auto-generated method stub
//				 File file=new File(getImagePath(url));
//				 FileOutputStream fos=null;
//				 try {
//					fos=new FileOutputStream(file);
//					response.compress(Bitmap.CompressFormat.JPEG, 100, fos);
//					fos.flush();
//				} catch (Exception e) {
//					// TODO: handle exception
//					e.printStackTrace();
//				}finally{
//					if (fos!=null) {
//						try {
//							fos.close();
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}
//				}
//				 
//				 if (file != null) {
//						Bitmap bitmap = ImageLoader.decodeBitmap(file.getPath(),
//								columnWidth);
//						if (bitmap != null) {
//							imageLoader.addBitmapToMemoryCache(url, bitmap); 
//						}
//				 }
//		    	  
//			}
//		}, 0, 0, Config.ARGB_8888, new Response.ErrorListener() {
//
//			@Override
//			public void onErrorResponse(VolleyError error) {
//				// TODO Auto-generated method stub
//				Toast.makeText(getContext(), "无法获取到图片", 1).show();
//			}
//		});
//    	  mQueue.add(request);
//      }
		
		
		 private void downLoadImage(String url){
			 InputStream response=null;
			 FileOutputStream fos=null;
			 BufferedInputStream bis=null;
			 File file=new File(getImagePath(url));
			 try {
				 HttpClient httpClient=new DefaultHttpClient();
				 HttpGet httpGet=new HttpGet(url);
				 HttpResponse httpResponse=httpClient.execute(httpGet);
				 if (httpResponse.getStatusLine().getStatusCode()==200) {
					HttpEntity entity=httpResponse.getEntity();
					 response=entity.getContent();
					
					 fos=new FileOutputStream(file);
					 bis=new BufferedInputStream(response);
					byte[] b=new byte[1024];
					int length;
					while((length=bis.read(b))!=-1){
						fos.write(b, 0, length);
						fos.flush();
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				try {
					if (response!=null) {
						response.close();
					}
					if (bis!=null) {
						bis.close();
					}
					if (fos!=null) {
						fos.close();
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			 if (file != null) {
					Bitmap bitmap = ImageLoader.decodeBitmap(file.getPath(),
							columnWidth);
					if (bitmap != null) {
						imageLoader.addBitmapToMemoryCache(url, bitmap); 
					}
			 }
			 
		 }
	}
}
