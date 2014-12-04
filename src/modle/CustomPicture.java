package modle;

import android.R.integer;

public class CustomPicture {
  private int title;
  private int imageId;
  public CustomPicture(int title,int  imageId ) {
	// TODO Auto-generated constructor stub
	  this.title=title;
	  this.imageId=imageId;
}
  public void setImageId(int imageId) {
	this.imageId = imageId;
}
  public int getImageId() {
	return imageId;
}
  public void setTitle(int title) {
	this.title = title;
}
  public int getTitle() {
	return title;
}
}
