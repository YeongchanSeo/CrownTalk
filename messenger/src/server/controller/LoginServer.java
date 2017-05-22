package server.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

import server.model.MemberInfo;

public class LoginServer {
	private static class LoginThread extends Thread{
		private Socket socket;
		private PrintWriter writer;
//		private static List<String> list;
//		static{
//			list = new LinkedList<String>();
//		}
		public LoginThread(Socket socket) throws IOException{
			this.socket = socket;
			try{
				writer = new PrintWriter(socket.getOutputStream());
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		public void run(){
			String ID = null;
			String password = null;
			try{
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
				while(true){
				ID = reader.readLine();
				if(ID== null)
					break;
				password = reader.readLine();
//				if(isLogin(ID)){
//					writer.println("로그인중");
//					writer.flush();
//				}
				if(manager.login(ID, password)){
//					list.add(ID);
					writer.println("로그인 완료");
					writer.flush();
					writer.println(manager.search(ID));
					writer.flush();
					break;
				}
				else{
					writer.println("로그인 실패");
					writer.flush();
				}
				}
			}
			catch(Exception e){
				System.out.println("로그인 에러");
			}
			finally{
				try{
					socket.close();
				}
				catch(Exception ignored){
					
				}
			}
		}
		
//		private boolean isLogin(String ID){
//			for(int i=0; i<list.size(); i++){
//				if(list.get(i).equals(ID))
//					return true;
//			}
//			return false;
//		}
	}
	
	private static class ServerCommunicationThread extends Thread{
		private Socket socket;
		private PrintWriter writer;
		public ServerCommunicationThread(Socket socket){
			this.socket = socket;
			try{
				writer = new PrintWriter(socket.getOutputStream());
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		public void run(){
			String name = null;
			String memberNum = null;
			String ID = null;
			String nickName = null;
			String password = null;
			try{
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
				while(true){
					name = reader.readLine();
					if(name == null)
						break;
					memberNum = reader.readLine();
					ID = reader.readLine();
					nickName = reader.readLine();
					password = reader.readLine();
					manager.getMemberInfo().add(new MemberInfo(name, memberNum, ID, nickName, password));			
				}
			}
			catch(Exception e){
				System.out.println("로그인 에러");
			}
			finally{
				try{
					socket.close();
				}
				catch(Exception ignored){
					
				}
			}
		}
	}
	
	private static MemberInfoManager manager;
	
	public static void main(String[] args){
		ServerSocket serverSocket = null;
		try{
			LoginServer.manager = new MemberInfoManager();
		serverSocket = new ServerSocket(9000);
			while(true){
				Socket socket = serverSocket.accept();
				Thread thread = new LoginThread(socket);
				Thread thread2 = new ServerCommunicationThread(new Socket("127.0.0.1", 9002));
				thread.start();
				thread2.start();
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}