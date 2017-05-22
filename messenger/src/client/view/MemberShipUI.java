package client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import client.controller.RegisterController;
import client.model.SharedArea;

public class MemberShipUI extends JFrame{
	private class RegisterActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String name = nameField.getText();
			String memberNum = memberNumField.getText();
			String ID = idField.getText();
			String nickName = nickNameField.getText();
			String password = pwField.getText();
//			if(controller.askRegister(name, memberNum, ID, nickName, password)){
//				JOptionPane.showMessageDialog(contentPane, "ȸ�������� �����մϴ�.", "���� �Ϸ�", JOptionPane.INFORMATION_MESSAGE);
//				MemberShipUI.this.dispose();
//			}
//			else
//				JOptionPane.showMessageDialog(contentPane, "���̵� Ȯ�����ּ���.", "���� ����", JOptionPane.ERROR_MESSAGE);
			controller.askRegister(name, memberNum, ID, nickName, password);
		}
	}
	
	private RegisterController controller;
	private JLabel name, memberNum, id,nickName,pw;
	private JTextField nameField, memberNumField, idField,nickNameField,pwField;
	private JButton createMember;
	private JPanel panel;
	private Container contentPane;
	
	public MemberShipUI(String address){
		super("���Խ�û ȭ��");
		try{
		controller = new RegisterController(new Socket(SharedArea.getIpAddr(), 9001), this);
		}
		catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
		createFrame();
		createPanel();
		createButton();
	}
	public void createFrame(){
		this.setPreferredSize(new Dimension(300,200));
		this.setLocation(560,150);
		contentPane=this.getContentPane();
	}
	public void createPanel(){
		panel=new JPanel();
		panel.setLayout(new GridLayout(5,2));
		name = new JLabel("�̸�", JLabel.CENTER);
		memberNum = new JLabel("�ֹε�Ϲ�ȣ", JLabel.CENTER);
		id=new JLabel("���̵�(�̸���)",JLabel.CENTER);
		nickName=new JLabel("��ȭ��(�г���)",JLabel.CENTER);
		pw=new JLabel("��й�ȣ",JLabel.CENTER);
		nameField = new JTextField(10);
		memberNumField = new JTextField(10);
		idField=new JTextField(10);
		nickNameField=new JTextField(10);
//		name.setFont(Font.getFont("���� ���"));
//		memberNum.setFont(Font.getFont("���� ���"));
//		id.setFont(Font.getFont("���� ���"));
//		nickName.setFont(Font.getFont("���� ���"));
//		pw.setFont(Font.getFont("���� ���"));
		pwField=new JPasswordField(10);
		panel.add(name);
		panel.add(nameField);
		panel.add(memberNum);
		panel.add(memberNumField);
		panel.add(id);
		panel.add(idField);
		panel.add(nickName);
		panel.add(nickNameField);
		panel.add(pw);
		panel.add(pwField);
		panel.setBackground(new Color(245,245,245));
		contentPane.add(panel,BorderLayout.CENTER);
	}
	public void createButton(){
		createMember=new JButton("��ȭ�� ��Ͻ�û");
		createMember.setBackground(new Color(255,255,200));
		contentPane.add(createMember,BorderLayout.SOUTH);
	}
	public void view(){
		ActionListener listener = new RegisterActionListener();
		createMember.addActionListener(listener);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	public static void main(String[] args){
		MemberShipUI obj = new MemberShipUI(SharedArea.getIpAddr());
		obj.view();
	}
}
