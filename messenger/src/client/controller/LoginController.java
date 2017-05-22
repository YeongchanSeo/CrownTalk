package client.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import client.model.SharedArea;

public class LoginController {
	private Socket socket;
	private PrintWriter writer;
	
	public LoginController(Socket socket){
		this.socket = socket;
		try{
		writer = new PrintWriter(socket.getOutputStream());
		}
		catch(Exception e){
		}
	}
	
	public boolean askLogin(String ID, String password){
		writer.println(ID);
		writer.flush();
		writer.println(password);
		writer.flush();
		try{
			Thread.sleep(100);
		}
		catch(InterruptedException ie){
			System.out.println("���ͷ�Ʈ");
		}
		return responseLogin();
	}
	
	private boolean responseLogin(){
		BufferedReader reader = null;
		try{
			reader = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
				String str = reader.readLine();
				if(str == null)
					return false;
				if(str.equals("�α��� �Ϸ�")){
					SharedArea.setNickName(reader.readLine());
					return true;
				}
//				else if(str.equals("�α�����")){
//					
//				}
				return false;
			}
			catch(IOException ioe){
				System.out.println("�α��� ����");
			}
//		finally{
//			try{
//				reader.close();
//			}
//			catch(Exception e){
//			}
//		}
			return false;
	}
}
