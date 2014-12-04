package modle;

public class ListInfo {
  private int image;
  private String title;
  private String introduce;
  private String address;
  private String time;
  private int id;
  public ListInfo(){
	  
  }
  public  ListInfo(int image,String title,String introduce,String address,String time,int id) {
	this.image=image;
	this.title=title;
	this.introduce=introduce;
	this.address=address;
	this.time=time;
	this.id=id;
}
  public void setImage(int image) {
	this.image = image;
}
  public int getImage() {
	return image;
}
  public void setTitle(String title) {
	this.title = title;
}
  public String getTitle() {
	return title;
}
  public void setIntroduce(String introduce) {
	this.introduce = introduce;
}
  public String getIntroduce() {
	return introduce;
}
  public void setAddress(String address) {
	this.address = address;
}
  public String getAddress() {
	return address;
}
  public void setTime(String time) {
	this.time = time;
}
  public String getTime() {
	return time;
}
  public void setId(int id) {
	this.id = id;
}
  public int getId() {
	return id;
}
}
