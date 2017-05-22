package server.model;

public class RoomInfo {
	private String title,pw;
	private int num, roomNum;
	
	public RoomInfo(){
		title=pw=null;
		num=0;
	}
	public RoomInfo(String title,String pw,int num, int roomNum){
		this.title=title;
		this.pw=pw;
		this.num=num;
		this.roomNum = roomNum;
	}
	@Override
	public String toString() {
		return "RoomInfo [title=" + title + ", pw=" + pw + ", num=" + num + "]";
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}
	
	
}
