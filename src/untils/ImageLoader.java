package untils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

public class ImageLoader {
  private static LruCache<String, Bitmap> mMemoryCashCache;
  private static ImageLoader mLoader;
	private ImageLoader() {
		// TODO Auto-generated constructor stub
		int maxMemory=(int) Runtime.getRuntime().maxMemory();
		int cacheSize=maxMemory/8;
		mMemoryCashCache=new LruCache<String, Bitmap>(cacheSize){
			@Override
			protected int sizeOf(String key, Bitmap value) {
				// TODO Auto-generated method stub
				return value.getByteCount();
			}
		};
	}
	
  public static ImageLoader getInstance() {
	if (mLoader==null) {
		mLoader=new ImageLoader();
	}
	return mLoader;
}	
  public void addBitmapToMemoryCache(String key,Bitmap bitmap) {
	if (getBitmapFromMermoryCache(key)==null) {
		mMemoryCashCache.put(key, bitmap);
	}
}
  public static Bitmap getBitmapFromMermoryCache(String key){
	  return mMemoryCashCache.get(key);
  }
   public static int getInsampleSize(BitmapFactory.Options options,int reqWidth) {
	final int width=options.outWidth;
	int insampleSize=1;
	if (width>reqWidth) {
		final int widthRatio=Math.round((float)width/(float)reqWidth);
		insampleSize=widthRatio;
	}
	return insampleSize;
}
   
   public static Bitmap decodeBitmap(String path,int reqWidth) {
	BitmapFactory.Options options=new BitmapFactory.Options();
	options.inJustDecodeBounds=true;
	BitmapFactory.decodeFile(path, options);
	options.inSampleSize=getInsampleSize(options, reqWidth);
	options.inJustDecodeBounds=false;
	return BitmapFactory.decodeFile(path, options);
}
 
}
