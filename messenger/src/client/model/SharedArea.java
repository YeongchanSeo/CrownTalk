package client.model;

public class SharedArea{
	private static String nickName;
	private static int roomNum;
	private static String ipAddr;
	static{
		ipAddr = "127.0.0.1";
	}
	public static void setNickName(String nickName){
		SharedArea.nickName = nickName;
		System.out.println(nickName);
	}
	public static String getNickName(){
		return nickName;
	}
	public static void setRoomNum(int roomNum){
		SharedArea.roomNum = roomNum;
	}
	public static int getRoomNum(){
		return roomNum;
	}
	public static String getIpAddr() {
		return ipAddr;
	}
	public static void setIpAddr(String ipAddr) {
		SharedArea.ipAddr = ipAddr;
	}
}
