package client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import client.controller.RoomController;

public class TalkRoomUI extends JFrame{
	public class CreateRoomUI extends JFrame{   //방생성 UI
		   private JLabel title,pw,countLabel;
		   private JComboBox countBox;
		   private JTextField titleField,pwField;
		   private JButton createRoom;
		   private JPanel panel;
		   private Container contentPane;
		   private RoomController controller;
		   
		   private class ButtonHandler implements ActionListener{
		      private JTable table;
		      private JComboBox talkBox;
		      public ButtonHandler(JTable table,JComboBox talkBox){
		         this.table=table;
		         this.talkBox=talkBox;
		      }
		      public void actionPerformed(ActionEvent e){
		         String title=titleField.getText();
		         String pw=pwField.getText();
		         Integer num=(Integer)countBox.getSelectedItem();
		        controller.makeRoom(title, pw, num);
//		        TalkView view = new TalkView();
//		        view.display();
		        //CreateRoomUI.this.dispose();
		        //TalkRoomUI.this.dispose();
		      }
		   }
		   
		   public CreateRoomUI(RoomController controller, JTable table,JComboBox talkBox){
		      super("채팅방 만들기");
		      this.setBackground(new Color(200,200,150));
		      createFrame();
		      createPanel();
		      createButton();
		    
		     this.controller =  controller;
		     this.controller.setNUI(this);
		      startListener(table,talkBox);
		      view();
		   }
		   public void startListener(JTable table,JComboBox talkBox){
			   ActionListener listener = new ButtonHandler(table,talkBox);
		      createRoom.addActionListener(listener);
		   }
		   public void createFrame(){
		      this.setPreferredSize(new Dimension(300,150));
		      this.setLocation(500,100);
		      contentPane=this.getContentPane();
		   }
		   public void createPanel(){
		      panel=new JPanel();
		      panel.setLayout(new GridLayout(3,2));
		      title=new JLabel("채팅방 제목",JLabel.CENTER);
		      pw=new JLabel("비밀번호 설정",JLabel.CENTER);
		      countLabel=new JLabel("채팅방 인원 설정",JLabel.CENTER);
		      titleField=new JTextField(10);
		      pwField=new JPasswordField(10);
		      countBox=new JComboBox();
		      countBox.setPreferredSize(new Dimension(150,30));
		      countBox.addItem(1);
		      countBox.addItem(2);
		      countBox.addItem(3);
		      countBox.addItem(4);
		      countBox.addItem(5);
		      
		      panel.add(title);
		      panel.add(titleField);
		      panel.add(pw);
		      panel.add(pwField);
		      panel.add(countLabel);
		      panel.add(countBox);
		      panel.setBackground(Color.WHITE);
		      contentPane.add(panel,BorderLayout.CENTER);
		   }
		   public void createButton(){
		      createRoom=new JButton("대화방 등록");
//		      createRoom.setBackground(new Color(200,200,150));
		      contentPane.add(createRoom,BorderLayout.SOUTH);
//		      contentPane.setBackground(new Color(200,200,150));
		   }
		   public void view(){
		      this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		      this.pack();
		      this.setVisible(true);
		   }
		}
	private static final long serialVersionUID = 1L;
	private JComboBox talkBox;
	private JTextField searchField;
	private JButton searchButton,createButton,joinButton, logOut;
	private JPanel panel,panel2;
	private Container contentPane;
	private JTable gameTable;
	private CreateRoomUI createRoom;
	private RoomController controller;
	
	private class CreateButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			createRoom=new CreateRoomUI(controller, gameTable,talkBox);
		}
	}
	
	public static void main(String args[]){
		TalkRoomUI ui = new TalkRoomUI();	
	}
	
	private class SearchButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String str=searchField.getText();
			controller.searchTitle(str);
//			for(int cnt=0;cnt<RoomManageServer.getList().size();cnt++){
//				if(str.equals(RoomManageServer.getList().get(cnt).getTitle())){
//					JOptionPane.showMessageDialog(contentPane, "찾았습니다.","검색",JOptionPane.INFORMATION_MESSAGE);
//				}
//			}
		}
	}
	
	private class LogOutHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			controller.logOut();
			TalkRoomUI.this.dispose();
			MainUI ui=new MainUI();
			ui.run();
		}
	}
	
	private class JoinButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			controller.enterRoom();
		}
	}
	
	public TalkRoomUI(){
		super("Crown talk");
		createFrame();
		createPanel();
		createTable();
		addButton();
		startListener();
		view();
		controller = new RoomController(gameTable, talkBox);
		controller.setUI(this);
	}
	public void startListener(){
		createButton.addActionListener(new CreateButtonHandler());
		searchButton.addActionListener(new SearchButtonHandler());
		joinButton.addActionListener(new JoinButtonHandler());
		logOut.addActionListener(new LogOutHandler());
	}
	public void createFrame(){
		this.setPreferredSize(new Dimension(400,500));
		this.setLocation(500,100);
		contentPane=this.getContentPane();
	}
	public void createPanel(){
		panel=new JPanel();
		panel.setLayout(new FlowLayout());
		talkBox=new JComboBox();
		talkBox.setPreferredSize(new Dimension(150,30));
		
		searchField=new JTextField();
		searchField.setPreferredSize(new Dimension(150,30));
		searchButton=new JButton("검색");
		searchButton.setBackground(new Color(170,170,170));////////////////
		talkBox.setBackground(new Color(170,170,170));////////////////
		panel.add(talkBox);
		panel.add(searchField);
		panel.add(searchButton);
		panel.setBackground(Color.WHITE);
		contentPane.add(panel,BorderLayout.NORTH);
	}
	public void createTable(){
		String colNames[]={"방제목"};
		Object data[][]={};
		DefaultTableModel model=new DefaultTableModel(colNames,0);
		gameTable=new JTable(model);
		gameTable.setBackground(Color.WHITE);
		JScrollPane scrollPane=new JScrollPane(gameTable);
		scrollPane.setBackground(new Color(100,100,100));
		contentPane.add(scrollPane,BorderLayout.CENTER);
	}
	public void addButton(){
		panel2=new JPanel();
		panel2.setLayout(new GridLayout(1,3));
		createButton=new JButton("채팅방 만들기");
		createButton.setBackground(new Color(250,200,200));
		joinButton=new JButton("입장");
		joinButton.setBackground(new Color(250,250,150));
		logOut=new JButton("로그아웃");
		logOut.setBackground(new Color(200,250,250));
		panel2.add(createButton);
		panel2.add(joinButton);
		panel2.add(logOut);
		contentPane.add(panel2,BorderLayout.SOUTH);
	}
	public void view(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
}

