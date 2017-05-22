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
import java.util.StringTokenizer;

public class ChattingServer {
	private static class ChatThread extends Thread{
		static List<PrintWriter> list = 
				Collections.synchronizedList(new ArrayList<PrintWriter>());
		
		private Socket socket;
		private PrintWriter writer;
		private static List<String> nameList = Collections.synchronizedList(new ArrayList<String>());
		public ChatThread(Socket socket) throws IOException{
			this.socket = socket;
	
			try{
				writer = new PrintWriter(socket.getOutputStream());
				list.add(writer);
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		public void run(){
			String nickName = null;
			int roomNum = 0;
			try{
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
				nickName = reader.readLine();
				roomNum = Integer.parseInt(reader.readLine());
//				String nlist = "";///start
//				for(int i=0; i<nameList.size(); i++){
//				    nlist += nameList.get(i);
//				}
//				nameList.add(roomNum + "&" + nickName + "&");
//				sendAll(nlist+"#");
//				System.out.println(nlist);
//				Thread.sleep(100);///end
				sendAll(roomNum + "|" +nickName + "님이 들어오셨습니다.");
				Thread.sleep(100);
				if(peopleNum[roomNum] == 0){
					sendAll(roomNum + "|" + "#새로온사람#" + "| ★ " +nickName);
				}
				else{
					sendAll(roomNum + "|" + "#새로온사람#" + "| " +nickName);
				}
				System.out.println(peopleNum[roomNum]);
				peopleNum[roomNum]++;
				System.out.println(peopleNum[roomNum]);
				while(true){
					String str = reader.readLine();
					if(str == null)
						break;
					if(str.equals("!@#나가기 버튼 클릭#@!"))
						break;
					sendAll(roomNum + "|" + nickName + ">" + str);
				}
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
			finally{
				list.remove(writer);
				nameList.remove(nickName);
				sendAll(roomNum + "|"+nickName+ "님이 나갔습니다.");
				peopleNum[roomNum]--;
				sendAll(roomNum + "|" + "#나가는사람#" + "| " +nickName);
				try{
					socket.close();
				}
				catch(Exception ignored){
				}
			}
		}
		
		private void sendAll(String str){
			for(PrintWriter writer:list){
				writer.println(str);
				writer.flush();
			}
		}
		
		private void showRoom(int row){
			if(RoomManageServer.getList().get(row).getPw().equals("")){
				writer.println("입장 허가");
			}else{	
				writer.println(RoomManageServer.getList().get(row).getPw());
			}
		}
	}
	private static int[] peopleNum;
	public static void main(String[] args){
		ServerSocket serverSocket = null;
		peopleNum = new int[100];
		try{
		serverSocket = new ServerSocket(9004);
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
