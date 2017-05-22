package server.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import server.model.RoomInfo;

public class RoomManageServer {
	private static class ChatThread extends Thread{
		static List<PrintWriter> writerlist = 
				Collections.synchronizedList(new ArrayList<PrintWriter>());
		private Socket socket;
		private PrintWriter writer;
		public ChatThread(Socket socket) throws IOException{
			this.socket = socket;
			try{
				writer = new PrintWriter(socket.getOutputStream());
				writerlist.add(writer);
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		public void run(){
			try{
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
				String str = reader.readLine();
				sendAllRoom();
				while(true){
					String title = reader.readLine();
					if(title== null)
						break;
					else if(title.equals("_#방에 입장하고 싶어요#_")){
						int index = Integer.parseInt(reader.readLine());
						if(index == -1)
							continue;
						String pw = searchRoom(index);
						writer.println("비밀번호 줄게");
						writer.flush();
						writer.println(pw);
						writer.flush();
						writer.println(getRoomNum(index));
						writer.flush();
						continue;
					}
					else if(title.equals("##검색할랭##")){
						title = reader.readLine();
						if(checkRoom(title)){
							writer.println("@찾았습니다@");
							writer.flush();
						}
						else{
							writer.println("@못찾았습니다@");
							writer.flush();
						}
						continue;
					}
					else if(title.equals("_#비밀번호 확인해줘#_")){
						int index = Integer.parseInt(reader.readLine());
						String pw = reader.readLine();
						if(checkPw(index, pw)){
							writer.println("방 입장 허가");
							writer.flush();
						}
						else{
							writer.println("비밀번호 틀림");
						}
						writer.flush();
						continue;
					}
					else if(title.equals("_@들어간다@_")){
						break;
					}
					else if(title.equals("__@로그아웃@__")){
						writer.println("_@로그아웃 해라@_");
						writer.flush();
						Thread.sleep(100);
						break;
					}
					String pw = reader.readLine();
					int num = Integer.parseInt(reader.readLine());
					if(roomCount <5){
						if(checkRoom(title)){
							writer.println("같은 방 존재");
							writer.flush();
						}
						else{
						createRoom(title, pw, num);
						writer.println("방 생성");
						writer.flush();
						writer.println(roomNum);
						writer.flush();
						sendRoom(roomCount);
						}
					}
					else{
						writer.println("정원 초과");
						writer.flush();
					}
				}
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
			finally{
				writerlist.remove(writer);
				try{
					//socket.close();
				}
				catch(Exception ignored){
					
				}
			}
		}
		private void sendRoom(int num){
			for(PrintWriter writer:writerlist){
				RoomInfo i = list.get(num);
					writer.println(i.getTitle());
					writer.flush();
					writer.println(i.getPw());
					writer.flush();
					writer.println(i.getNum());
					writer.flush();
			}
		}
		
		private void sendAllRoom() throws Exception{
			for(RoomInfo i: list){
				writer.println(i.getTitle());
				writer.flush();
				//System.out.println(i.getTitle());
				writer.println(i.getPw());
				//System.out.println(i.getPw());
				writer.flush();
				writer.println(i.getNum());
				writer.flush();
				Thread.sleep(50);
			}
		}
		
		private String searchRoom(int index){
			return list.get(index).getPw();
		}
				
		private boolean checkRoom(String title){
			for(int cnt = 0; cnt<list.size(); cnt++){
				if(list.get(cnt).getTitle().equals(title)){
					return true;
				}
			}
			return false;
		}
		
		private int getRoomNum(int index){
			return list.get(index).getRoomNum();
		}

		private boolean checkPw(int index, String pw){
			if(list.get(index).getPw().equals(pw))
				return true;
			return false;
				
		}
	}
	
   private static List<RoomInfo> list;
   
   static{
      list=Collections.synchronizedList(new ArrayList<RoomInfo>());
   }

   public static List<RoomInfo> getList(){
      return list;
   }
   private static int roomCount = -1;
   private static int roomNum = 0;
   public static void createRoom(String title,String pw,int num){
	   roomNum++;
       list.add(new RoomInfo(title,pw,num, roomNum));
       roomCount++;
   }

   public static void main(String[] args){
	   ServerSocket serverSocket = null;
		try{
		serverSocket = new ServerSocket(9003);
			while(true){
				Socket socket = serverSocket.accept();
				Thread thread = new ChatThread(socket);
				thread.start();
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
   }
}