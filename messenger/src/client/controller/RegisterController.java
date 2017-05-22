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
			System.out.println("���ͷ�Ʈ");
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
				if(str.equals("ȸ������ �Ϸ�")){
					JOptionPane.showMessageDialog(ui, "ȸ�������� �����մϴ�.", "���� �Ϸ�", JOptionPane.INFORMATION_MESSAGE);
						ui.dispose();
					}
				else if(str.equals("ȸ������ ����")){
					JOptionPane.showMessageDialog(ui, "������� ���̵��Դϴ�.", "���� ����", JOptionPane.ERROR_MESSAGE);
				}
				else if(str.equals("�̸�����")){
					JOptionPane.showMessageDialog(ui, "�̸��� �������ּ���.", "���� ����", JOptionPane.ERROR_MESSAGE);
				}
				else if(str.equals("�ֹε�Ϲ�ȣ����")){
					JOptionPane.showMessageDialog(ui, "�ֹε�Ϲ�ȣ�� �������ּ���.", "���� ����", JOptionPane.ERROR_MESSAGE);
				}
				else if(str.equals("���̵����")){
					JOptionPane.showMessageDialog(ui, "���̵� �������ּ���.", "���� ����", JOptionPane.ERROR_MESSAGE);
				}
				else if(str.equals("�г��Ӱ���")){
					JOptionPane.showMessageDialog(ui, "�г����� �������ּ���.", "���� ����", JOptionPane.ERROR_MESSAGE);
				}
				else if(str.equals("��й�ȣ����")){
					JOptionPane.showMessageDialog(ui, "��й�ȣ�� �������ּ���.", "���� ����", JOptionPane.ERROR_MESSAGE);
				}
			}
			catch(IOException ioe){
				System.out.println("ȸ������ ����");
			}
			return false;
	}
}