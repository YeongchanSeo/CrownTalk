package server.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class RegisterServer {
	private static class RegisterThread extends Thread{
		private static MemberInfoManager manager;
		private static Socket socket;
		private PrintWriter writer, writer2;
		public RegisterThread(Socket socket, Socket socket2){
			RegisterThread.socket = socket;
			try{
				manager = new MemberInfoManager();
				writer = new PrintWriter(socket.getOutputStream());
				writer2 = new PrintWriter(socket2.getOutputStream());
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
				if(name.equals("")){
					writer.println("�̸�����");
					writer.flush();
				}
				else if(memberNum.equals("")){
					writer.println("�ֹε�Ϲ�ȣ����");
					writer.flush();
				}
				else if(ID.equals("")){
					writer.println("���̵����");
					writer.flush();
				}
				else if(nickName.equals("")){
					writer.println("�г��Ӱ���");
					writer.flush();
				}
				else if(password.equals("")){
					writer.println("��й�ȣ����");
					writer.flush();
				}
				else if(manager.registerMember(name, memberNum, ID, nickName, password)){
					writer.println("ȸ������ �Ϸ�");
					writer.flush();
					writer2.println(name);
					writer2.flush();
					writer2.println(memberNum);
					writer2.flush();
					writer2.println(ID);
					writer2.flush();
					writer2.println(nickName);
					writer2.flush();
					writer2.println(password);
					writer2.flush();					
				}
				else{
					writer.println("ȸ������ ����");
					writer.flush();
				}
				}
			}
			catch(Exception e){
				System.out.println("ȸ������ ����");
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
	
	public static void main(String[] args){
		ServerSocket serverSocket = null;
		ServerSocket serverSocket2 = null;
		try{
		serverSocket = new ServerSocket(9001);
		serverSocket2 = new ServerSocket(9002);
		Socket socket2 = serverSocket2.accept();
			while(true){
				Socket socket = serverSocket.accept();
				Thread thread = new RegisterThread(socket, socket2);
				thread.start();
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
