package client.view;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import client.controller.LoginController;
import client.model.SharedArea;
public class MainUI {
	private class ImagePanel extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = -6539403094985115727L;
		private Image image, scaledImage;
		private Toolkit toolkit;
		public ImagePanel(){
			toolkit = getToolkit();
			image = toolkit.getImage("crown.jpg");
			scaledImage = image.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		}
		
		public void paint(Graphics g){
			g.clearRect(0, 0, getWidth(), getHeight());
			g.drawImage(scaledImage, 150, 30, this);
		}
	}
	
	private class LoginActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(controller.askLogin(ID.getText(), password.getText())){
				frame.dispose();
				TalkRoomUI view = new TalkRoomUI();
			}
			else{
				JOptionPane.showMessageDialog(contentPane, "���̵� �Ǵ� ��й�ȣ�� Ʋ���ϴ�.", "�α��� ����", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class JoinListener extends JLabel implements MouseListener{
		public void mouseClicked(MouseEvent e){
			MemberShipUI memberShipUI = new MemberShipUI(address);
			memberShipUI.view();
			} 
	    public void mouseEntered(MouseEvent e){} 
	    public void mouseExited(MouseEvent e){} 
	    public void mousePressed(MouseEvent e){} 
	    public void mouseReleased(MouseEvent e){}
	    public JoinListener(String str, int position){
	    	super(str, position);
	    	addMouseListener(this);
	    }
	}
	
	private class TextListener extends JTextField implements MouseListener{
		public void mouseClicked(MouseEvent e){
			super.setText("");
		} 
	    public void mouseEntered(MouseEvent e){} 
	    public void mouseExited(MouseEvent e){} 
	    public void mousePressed(MouseEvent e){} 
	    public void mouseReleased(MouseEvent e){}
	    public TextListener(String str, int num){
	    	super(str, num);
	    	addMouseListener(this);
	    }
	}
	
	private class TextPasswordListener extends JPasswordField implements MouseListener{
		public void mouseClicked(MouseEvent e){
			super.setText("");
		} 
	    public void mouseEntered(MouseEvent e){} 
	    public void mouseExited(MouseEvent e){} 
	    public void mousePressed(MouseEvent e){} 
	    public void mouseReleased(MouseEvent e){}
	    public TextPasswordListener(String str, int num){
	    	super(str, num);
	    	addMouseListener(this);
	    }
	}
	
	private class FindIdListener extends JLabel implements MouseListener{
	      public void mouseClicked(MouseEvent e){
	         FindIdUI findIdUI = new FindIdUI(address);
	         findIdUI.view();
	         } 
	       public void mouseEntered(MouseEvent e){} 
	       public void mouseExited(MouseEvent e){} 
	       public void mousePressed(MouseEvent e){} 
	       public void mouseReleased(MouseEvent e){}
	       public FindIdListener(String str, int position){
	          super(str, position);
	          addMouseListener(this);
	       }
	   }
	
	private class FindPasswordListener extends JLabel implements MouseListener{
	      public void mouseClicked(MouseEvent e){
	         FindPasswordUI findPasswordUI = new FindPasswordUI(address);
	         findPasswordUI.view();
	         } 
	       public void mouseEntered(MouseEvent e){} 
	       public void mouseExited(MouseEvent e){} 
	       public void mousePressed(MouseEvent e){} 
	       public void mouseReleased(MouseEvent e){}
	       public FindPasswordListener(String str, int position){
	          super(str, position);
	          addMouseListener(this);
	       }
	   }
	
	private LoginController controller;
	private JTextField ID, password;
	private JButton login;
	private JLabel findId, findPassword, join, border1, border2;
	private JCheckBox box1, box2;
	private JFrame frame;
	private Container contentPane;
	private JPanel imagePanel, messagePanel, loginPanel, totalLoginPanel, findPanel, totalPanel, southPanel, boxPanel;
	private String address;
	
	public MainUI(){
		frame = new JFrame("Crown Talk");
		frame.setLocation(500,100);
		contentPane = frame.getContentPane();
		contentPane.setPreferredSize(new Dimension(400,500));
		contentPane.setLayout(new GridLayout(4,1));
		initialize();
		setCenter();
		setSouth();
		login.addActionListener(new LoginActionListener());
	}
	
	private void initialize(){
		initializeImageUI();
		initializeMessage();
		initializeTotalLoginUI();
		try{
			address = SharedArea.getIpAddr();
			controller = new LoginController(new Socket(SharedArea.getIpAddr(), 9000));
		}
		catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
		totalPanel = new JPanel();
		boxPanel = new JPanel();
		southPanel = new JPanel(new GridLayout(2,1));
	}
	
	private void initializeImageUI(){
		imagePanel = new ImagePanel();
		contentPane.add(imagePanel);
	}
	
	private void initializeMessage(){
		messagePanel = new JPanel();
		messagePanel.setLayout(new GridLayout(2,1));
		
		JLabel label1 = new JLabel("�ʶ� ���� ��ȭ��, ũ�����!", SwingConstants.CENTER);
		label1.setFont(new Font("SJ���̽�ũ��", 50, 20));
		JLabel label2 = new JLabel("����� ����ϴ� ���� ����� ǰ���� �ݿ��մϴ�.", SwingConstants.CENTER);
		label2.setFont(new Font("Cre������Ű M", 50, 15));
		messagePanel.add(label1,SwingConstants.CENTER);
		messagePanel.add(label2);
		messagePanel.setBackground(Color.WHITE);
		contentPane.add(messagePanel);
	}
	
	private void initializeTotalLoginUI(){
		initializeLoginUI();
		initializeFindUI();
		totalLoginPanel = new JPanel();
	}
	
	private void initializeLoginUI(){
		ID = new TextListener("���̵�", 12);
		ID.setBackground(new Color(235,235,235));
		password = new TextPasswordListener("��й�ȣ����", 12);
		password.setBackground(new Color(235,235,235));
		login = new JButton("�α���");
		login.setBackground(new Color(230,230,10));
		login.setPreferredSize(new Dimension(80,40));
		loginPanel = new JPanel(new GridLayout(2,1));
	}
	
	private void initializeFindUI(){
		findId = new FindIdListener("���̵� ã��",SwingConstants.CENTER); 
		border1 = new JLabel("|",SwingConstants.CENTER);
		findPassword = new FindPasswordListener("��й�ȣã��",SwingConstants.CENTER);
		border2 = new JLabel("|",SwingConstants.CENTER);
		join = new JoinListener("ȸ������",SwingConstants.CENTER);
		findId.setFont(Font.getFont("���� ���"));
		findPassword.setFont(Font.getFont("���� ���"));
		join.setFont(Font.getFont("���� ���"));
		findPanel = new JPanel();
	}
	
	private void setLoginUI(){
		loginPanel.add(ID);
		loginPanel.add(password);
		totalLoginPanel.add(loginPanel);
		totalLoginPanel.add(login);
		totalLoginPanel.setBackground(Color.WHITE);
		totalPanel.add(totalLoginPanel);
		totalPanel.setBackground(Color.WHITE);
	}
	
	private void setFindUI(){
		findPanel.add(findId);
		findPanel.add(border1);
		findPanel.add(findPassword);
		findPanel.add(border2);
		findPanel.add(join);
		findPanel.setBackground(Color.WHITE);
		totalPanel.add(findPanel);
	}
	
	private void setCenter(){
		setLoginUI();
		setFindUI();
		contentPane.add(totalPanel);
	}
	
	private void setSouth(){
		box1 = new JCheckBox("��й�ȣ ����");
		box2 = new JCheckBox("�ڵ����� �α���");
		box1.setBackground(Color.WHITE);
		box1.setFont(Font.getFont("���� ���"));
		box2.setBackground(Color.WHITE);
		box2.setFont(Font.getFont("���� ���"));
		boxPanel.add(box1);
		boxPanel.add(box2);
		boxPanel.setBackground(Color.WHITE);
		southPanel.add(boxPanel);
		southPanel.add(new JLabel("(��) ũ�����", SwingConstants.CENTER));
		southPanel.setBackground(Color.WHITE);
		contentPane.add(southPanel);
	}
	
	public void run(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args){
		MainUI obj = new MainUI();
		obj.run();
	}
}
