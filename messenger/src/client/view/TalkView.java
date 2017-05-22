//package client.view;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Container;
//import java.awt.Dimension;
//import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.GridLayout;
//import java.awt.Image;
//import java.awt.Toolkit;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.JTextArea;
//import javax.swing.JTextField;
//import javax.swing.SwingConstants;
//import javax.swing.border.EtchedBorder;
//import javax.swing.table.DefaultTableModel;
//
//import client.controller.ChatController;
//
//
//public class TalkView {
//   
//   private JFrame frame;
//   private JPanel centerPanel, leftPanel, bottomPanel, rightPanel,rightTitlePanel, bottom;
//   private ImagePanel imagePanel;
//   private JLabel leftLabel , rightNameLabel, rightCountLabel;
//   private JTextArea leftText;
//   private JTable rightTable;
//   private JTextField bottomText;
//   private JScrollPane leftTextPane;
//   private JButton sendButton, getOutButton, outButton;
//   private DefaultTableModel model;
//   private ChatController chatController;
//   //private RoomInfo roomInfo;
//   
//   
//   //===============================================================================
//   
//   public TalkView(){
//      frame = new JFrame("Crown Talk");
//      frame.setLocation(450,50);
//      frame.setPreferredSize(new Dimension(500, 600));
//      Container contentPane = frame.getContentPane();
//      
//      centerPanel = new JPanel();
//      
//      makeLeftPanel();
//      
//      makeRightPanel();
//         
//      centerPanel.add(leftPanel);
//      centerPanel.add(rightPanel);
//   
//      makeBottomPanel();                 
//      
//      makeTopPanel();
//      
//      sendButton.addActionListener(new SendMessageHandler());
//      getOutButton.addActionListener(new GetOutHandler());
//      outButton.addActionListener(new LeaveHandler());
//      
//      contentPane.add(imagePanel, BorderLayout.NORTH);
//      contentPane.add(centerPanel, BorderLayout.CENTER);
//      contentPane.add(bottom , BorderLayout.SOUTH);
//      
//      //======================================================
//      
//      chatController = new ChatController(leftText);   
//      //this.roomInfo = roomInfo;
//   }
//   
//   
//   public void makeLeftPanel(){
//      leftPanel = new JPanel(new BorderLayout()); // ��ȭ ��ü ���
//      leftPanel.setBorder(new EtchedBorder(Color.lightGray, Color.BLACK));
//      leftLabel = new JLabel("5", SwingConstants.CENTER);
//      leftLabel.setFont(new Font("���", 20, 20));
//      leftText = new JTextArea();
//      leftText.setBackground(new Color(255,255,230));
//      leftTextPane = new JScrollPane(leftText);
//      
//      bottomPanel = new JPanel(new GridLayout(1,2));  // Bottom ������ â
//      bottomText = new JTextField(40);
//      bottomText.setBackground(new Color(255,255,255));
//      sendButton = new JButton("������");
//      bottomPanel.add(bottomText);
//      bottomPanel.add(sendButton);
//      
//      leftPanel.add(leftLabel, BorderLayout.NORTH);
//      leftPanel.add(leftTextPane, BorderLayout.CENTER);
//      leftPanel.add(bottomPanel, BorderLayout.SOUTH);
//      leftPanel.setPreferredSize(new Dimension(350,420));
//   }
//   public void makeRightPanel(){
//      rightPanel = new JPanel(new BorderLayout());
//      rightPanel.setBorder(new EtchedBorder(Color.lightGray, Color.BLACK));
//      rightTitlePanel = new JPanel(new GridLayout(2,1));
//      //rightNameLabel = new JLabel("���ο���",new ImageIcon("�հ�1.png"),SwingConstants.CENTER);//////
//      rightNameLabel = new JLabel("���ο���",SwingConstants.CENTER);//////
//      
//      rightNameLabel.setPreferredSize(new Dimension(100,50));
//      //rightCountLabel = new JLabel("3/10",new ImageIcon("�հ�2.png"),SwingConstants.CENTER);//////
//      rightCountLabel = new JLabel("3/10",SwingConstants.CENTER);//////
//
//      rightTitlePanel.add(rightNameLabel);
//      rightTitlePanel.add(rightCountLabel);
//      
//      String colNames[] = {""};
//      model = new DefaultTableModel(colNames, 0);
//      rightTable = new JTable(model);
//      rightTable.setRowHeight(50);
//  
//      rightTable.setFont(Font.getFont("���"));
//      rightTable.setBackground(new Color(255,255,230));
//      
//      //========================================= //addString()�޼ҵ� ������  //addClassPresidentMark
//      String[] arr = new String[]{"����"};
//      String[] arr1 = new String[]{"����"};
//      String[] arr2 = new String[]{"���"};
//      
//      model.addRow(arr);
//      model.addRow(arr1);
//      model.addRow(arr2);
//      //=========================================
//      
//      getOutButton = new JButton("�����ϱ�");
//      rightPanel.add(rightTitlePanel, BorderLayout.NORTH);
//      rightPanel.add(new JScrollPane(rightTable), BorderLayout.CENTER);
//      rightPanel.add(getOutButton, BorderLayout.SOUTH);
//      //rightPanel.add(button10, BorderLayout.SOUTH);
//      rightPanel.setPreferredSize(new Dimension(110,420));
//   }
////   public void addAllName(List<Memv>nameArray){
////      for(String str : nameArray){
////         
////      }
////   }
//   public void addString(String name){
//      String[] str = new String[]{name};
//      
//      DefaultTableModel model1 = (DefaultTableModel) rightTable.getModel();
//      model1.addRow(str);
//   }
//   public String addClassPresidentMark(String name){ // ������ ��� �� �߰�
//      String str = "��";
//      str = str.concat(name);
//      return str;
//   }
//   public void makeBottomPanel(){
//      bottom = new JPanel();
//      bottom.setBorder(new EtchedBorder(Color.lightGray, Color.BLACK));
//      outButton = new JButton("������");
//      outButton.setLocation(450, 550);
//      bottom.add(outButton);
//   }
//   public void makeTopPanel(){
//      imagePanel = new ImagePanel();
//      imagePanel.setPreferredSize(new Dimension(250,100));
//      imagePanel.setSize(250, 100);
//   }
//   private class ImagePanel extends JPanel{
//      private Image image, scaledImage;
//      Toolkit toolkit;
//      public ImagePanel(){
//         toolkit = getToolkit();
//         image = toolkit.getImage("crown.jpg");
//         scaledImage = image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
//      }
//      public void paint(Graphics g){
//         g.clearRect(0, 0, getWidth(), getHeight());
//         if(image != null)
//            g.drawImage(image, 20, 0, this);
//      }
//   }
//   private class SendMessageHandler implements ActionListener{
//     
//     public void actionPerformed(ActionEvent e){
//        String text = bottomText.getText();
//        chatController.sendText(text);
//        bottomText.setText("");
//     }
//  }
//  
//   private class GetOutHandler implements ActionListener{ // getoutButton�� ������ ���
//      public void actionPerformed(ActionEvent e){
//         
//         //�����϶��� ���ð��� �ƴҰ�쿡�� X
//         
//         int row = rightTable.getSelectedRow();
//         if(row == -1)
//            return;
//         DefaultTableModel model = (DefaultTableModel) rightTable.getModel();
//         model.removeRow(row);
//      }
//   }
//   private class LeaveHandler implements ActionListener{ // getoutButton�� ������ ���
//      public void actionPerformed(ActionEvent e){
//    	  chatController.clickOutButton();
//         frame.dispose();
//          TalkRoomUI talkRommUI = new TalkRoomUI();
//      }
//   }
//   
//   public void display(){
//      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//      frame.pack();
//      frame.setResizable(false);
//      frame.setVisible(true);
//   }
////   public static void main(String[] args) {
////      TalkView view = new TalkView();
////      view.display();
////   }
//}


package client.view;

import java.awt.BorderLayout;
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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import client.controller.ChatController;
import java.util.*;

public class TalkView {
   
   private JFrame frame;
   private JPanel centerPanel, leftPanel, bottomPanel, rightPanel,rightTitlePanel, bottom;
   private ImagePanel imagePanel;
   private JLabel leftLabel , rightNameLabel, rightCountLabel;
   private JTextArea leftText;
   private JTable rightTable;
   private JTextField bottomText;
   private JScrollPane leftTextPane;
   private JButton sendButton, getOutButton, outButton;
   private DefaultTableModel model;
   private ChatController chatController;
   //private RoomInfo roomInfo;
   
   private LinkedList<PracticeModel> modelArray;
   
   //===============================================================================
   
   public TalkView(){
      frame = new JFrame("Crown Talk");
      frame.setLocation(450,50);
      frame.setPreferredSize(new Dimension(500, 600));
      Container contentPane = frame.getContentPane();
      
      centerPanel = new JPanel();
      
      makeLeftPanel();
      
      makeRightPanel();
         
      centerPanel.add(leftPanel);
      centerPanel.add(rightPanel);
   
      makeBottomPanel();                 
      
      makeTopPanel();
      
      sendButton.addActionListener(new SendMessageHandler());
      getOutButton.addActionListener(new GetOutHandler());
      outButton.addActionListener(new LeaveHandler());
      
      contentPane.add(imagePanel, BorderLayout.NORTH);
      contentPane.add(centerPanel, BorderLayout.CENTER);
      contentPane.add(bottom , BorderLayout.SOUTH);
      
      //======================================================
      
      chatController = new ChatController(leftText, model, rightCountLabel);   
      //this.roomInfo = roomInfo;
   }
   
   
   public void makeLeftPanel(){
      leftPanel = new JPanel(new BorderLayout()); // ��ȭ ��ü ���
      leftPanel.setBorder(new EtchedBorder(Color.lightGray, Color.BLACK));
      leftLabel = new JLabel("5", SwingConstants.CENTER);
      leftLabel.setFont(new Font("���", 20, 20));
      leftText = new JTextArea();
      leftText.setBackground(new Color(250,250,240));
      leftTextPane = new JScrollPane(leftText);
      
      bottomPanel = new JPanel(new GridLayout(1,2));  // Bottom ������ â
      bottomText = new JTextField(40);
      bottomText.setBackground(new Color(255,255,255));
      sendButton = new JButton("������");
      sendButton.setBackground(new Color(255,255,200));
      bottomPanel.add(bottomText);
      bottomPanel.add(sendButton);
      
      leftPanel.add(leftLabel, BorderLayout.NORTH);
      leftPanel.add(leftTextPane, BorderLayout.CENTER);
      leftPanel.add(bottomPanel, BorderLayout.SOUTH);
      leftPanel.setPreferredSize(new Dimension(350,420));
   }
   public void makeRightPanel(){
      rightPanel = new JPanel(new BorderLayout());
      rightPanel.setBorder(new EtchedBorder(Color.LIGHT_GRAY, Color.BLACK));
      rightTitlePanel = new JPanel(new GridLayout(2,1));
      //rightNameLabel = new JLabel("���ο���",new ImageIcon("�հ�1.png"),SwingConstants.CENTER);//////
      rightNameLabel = new JLabel("���ο���",SwingConstants.CENTER);//////
      
      rightNameLabel.setPreferredSize(new Dimension(100,50));
      //rightCountLabel = new JLabel("3/10",new ImageIcon("�հ�2.png"),SwingConstants.CENTER);//////
      rightCountLabel = new JLabel("3/10",SwingConstants.CENTER);//////
      
      rightTitlePanel.add(rightNameLabel);
      rightTitlePanel.add(rightCountLabel);
      rightTitlePanel.setBackground(new Color(215,215,215));
      String colNames[] = {""};
      model = new DefaultTableModel(colNames, 0);
      rightTable = new JTable(model);
      rightTable.setRowHeight(50);
      //rightTable.setfont("���");
      rightTable.setBackground(new Color(250,250,250));
      
      //========================================= //
//      PracticeModel model1 = new PracticeModel("����");  // ���Ƿ� ���� ��
//      PracticeModel model2 = new PracticeModel("����");
//      PracticeModel model3 = new PracticeModel("���");
//      
//      model1.setPresident(true); // model1 ���� ���� ������ �� // ���� ���� ���� ��� // ó�� �����ڿ����� ������� false
//      
//
//      modelArray = new LinkedList<PracticeModel>();
//      modelArray.add(model1);
//      modelArray.add(model2);
//      modelArray.add(model3);
      
//      addAllName(modelArray);

      //=========================================
      
      getOutButton = new JButton("�����ϱ�");
      getOutButton.setBackground(new Color(255,100,100));
      rightPanel.add(rightTitlePanel, BorderLayout.NORTH);
      rightPanel.add(new JScrollPane(rightTable), BorderLayout.CENTER);
      rightPanel.add(getOutButton, BorderLayout.SOUTH);
      //rightPanel.add(button10, BorderLayout.SOUTH);
      rightPanel.setPreferredSize(new Dimension(110,420));
   }
   public void addAllName( LinkedList<PracticeModel> modelArray){
     String name;
      for(PracticeModel model : modelArray){
         name = model.getID();
         if(model.getPresident()){
            addString(addClassPresidentMark(name));
         }
         else{
           addString("      "+name);
         }      
      }
   }
   public void addString(String name){
      String[] str = new String[]{name};
      
      model.addRow(str);
   }
   public String addClassPresidentMark(String name){ // ������ ��� �� �߰�
      String str = " �� ";
      str = str.concat(name);
      return str;
   }
   public void makeBottomPanel(){
      bottom = new JPanel();
      bottom.setBorder(new EtchedBorder(Color.lightGray, Color.BLACK));
      outButton = new JButton("������");
      outButton.setBackground(new Color(180,180,180));
      outButton.setLocation(450, 550);
      bottom.add(outButton);
      bottom.setBackground(new Color(225,225,225));
   }
   public void makeTopPanel(){
      imagePanel = new ImagePanel();
      imagePanel.setPreferredSize(new Dimension(250,100));
      imagePanel.setSize(250, 100);
   }
   private class ImagePanel extends JPanel{
      private Image image, scaledImage;
      Toolkit toolkit;
      public ImagePanel(){
         toolkit = getToolkit();
         image = toolkit.getImage("crown.jpg");
         scaledImage = image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
      }
      public void paint(Graphics g){
         g.clearRect(0, 0, getWidth(), getHeight());
         if(image != null)
            g.drawImage(image, 20, 0, this);
      }
   }
   private class SendMessageHandler implements ActionListener{
     
     public void actionPerformed(ActionEvent e){
        String text = bottomText.getText();
        chatController.sendText(text);
        bottomText.setText("");
     }
  }
   
   private class GetOutHandler implements ActionListener{ // getoutButton�� ������ ���
      public void actionPerformed(ActionEvent e){
         
         int row = rightTable.getSelectedRow();
         if(row == -1 ) 
            return;
         
         model.removeRow(row);
      }
   }
   private class LeaveHandler implements ActionListener{ // getoutButton�� ������ ���
      public void actionPerformed(ActionEvent e){
         chatController.clickOutButton();
         frame.dispose();
          TalkRoomUI talkRommUI = new TalkRoomUI();
      }
   }
   
   public void display(){
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setResizable(false);
      frame.setVisible(true);
   }
   public static void main(String[] args) {
      TalkView view = new TalkView();
      view.display();
   }
}

 class PracticeModel {
	   private String id;
	   private boolean president;
	   
	   public PracticeModel(String id){
	      this.id = id;
	      president = false;
	   }
	   public String getID(){
	      return id;
	   }
	   public void setPresident(boolean president){
	      this.president = president;
	   }
	   public boolean getPresident(){
	      return president;
	   }
 }