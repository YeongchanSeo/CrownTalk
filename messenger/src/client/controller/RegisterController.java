package client.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

import client.view.MemberShipUI;

public class RegisterController {
	private Socket socket;
	private PrintWriter writer;
	private MemberShipUI ui;
	public RegisterController(Socket socket, MemberShipUI ui){
		this.socket = socket;
		this.ui = ui;
		try{
		writer = new PrintWriter(socket.getOutputStream());
		}
		catch(Exception e){
		}
	}
	
	public boolean askRegister(String name, String memberNum, 
			String ID, String nickName, String  password){
		writer.println(name);
		writer.flush();
		writer.println(memberNum);
		writer.flush();
		writer.println(ID);
		writer.flush();
		writer.println(nickName);
		writer.flush();
		writer.println(password);
		writer.flush();
		System.out.println(name);
		try{
			Thread.sleep(100);
		}
		catch(InterruptedException ie){
			System.out.println("인터럽트");
		}
		return responseRegister();
	}
	
	private boolean responseRegister(){
		try{
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
				String str = reader.readLine();
				System.out.println(str);
				if(str == null)
					return false;
				if(str.equals("회원가입 완료")){
					JOptionPane.showMessageDialog(ui, "회원가입을 축하합니다.", "가입 완료", JOptionPane.INFORMATION_MESSAGE);
						ui.dispose();
					}
				else if(str.equals("회원가입 실패")){
					JOptionPane.showMessageDialog(ui, "사용중인 아이디입니다.", "가입 실패", JOptionPane.ERROR_MESSAGE);
				}
				else if(str.equals("이름공백")){
					JOptionPane.showMessageDialog(ui, "이름을 기입해주세요.", "가입 실패", JOptionPane.ERROR_MESSAGE);
				}
				else if(str.equals("주민등록번호공백")){
					JOptionPane.showMessageDialog(ui, "주민등록번호를 기입해주세요.", "가입 실패", JOptionPane.ERROR_MESSAGE);
				}
				else if(str.equals("아이디공백")){
					JOptionPane.showMessageDialog(ui, "아이디를 기입해주세요.", "가입 실패", JOptionPane.ERROR_MESSAGE);
				}
				else if(str.equals("닉네임공백")){
					JOptionPane.showMessageDialog(ui, "닉네임을 기입해주세요.", "가입 실패", JOptionPane.ERROR_MESSAGE);
				}
				else if(str.equals("비밀번호공백")){
					JOptionPane.showMessageDialog(ui, "비밀번호를 기입해주세요.", "가입 실패", JOptionPane.ERROR_MESSAGE);
				}
			}
			catch(IOException ioe){
				System.out.println("회원가입 에러");
			}
			return false;
	}
}