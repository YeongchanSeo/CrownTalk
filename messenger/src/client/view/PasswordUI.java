package client.view;	//바꿈

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import client.controller.RoomController;

public class PasswordUI extends JFrame{
	private Container contentPane;
	private JLabel label;
	private JTextField textField;
	private JButton button;
	private JPanel panel;
	private String pw;
	private RoomController controller;
	
	private class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String field=textField.getText();
			controller.askPwCheck(field);
//			if(pw.equals(field)){
//				TalkView view = new TalkView();
//				PasswordUI.this.dispose();
//			    view.display();
//			}else{
//				JOptionPane.showMessageDialog(contentPane, "비밀번호가 틀립니다.","입력오류",JOptionPane.INFORMATION_MESSAGE);
//				PasswordUI.this.dispose();
//			}
		}
	}
	public PasswordUI(RoomController controller){
		super("비밀번호 입력창");
		this.setPreferredSize(new Dimension(250,120));
		this.setLocation(580,130);
		contentPane=this.getContentPane();
		this.controller = controller;
		panel=new JPanel();
		panel.setLayout(new FlowLayout());
		label=new JLabel("비밀번호 입력");
		textField=new JPasswordField(8);
		button=new JButton("확인");
		
		panel.add(label);
		panel.add(textField);
		contentPane.add(new JPanel(), BorderLayout.NORTH);
		contentPane.add(panel,BorderLayout.CENTER);
		contentPane.add(button,BorderLayout.SOUTH);
		
		button.addActionListener(new ButtonHandler());
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
}
