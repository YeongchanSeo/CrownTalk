package client.view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
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

import client.model.SharedArea;


public class FindPasswordUI extends JFrame{
//   private class FindIdActionListener implements ActionListener{
//      public void actionPerformed(ActionEvent e){
//         String name = nameField.getText();
//         String memberNum = memberNumField.getText();
//         String password = pwField.getText();
////         if(controller.askRegister(name, memberNum, ID, nickName, password)){
////            JOptionPane.showMessageDialog(contentPane, "ȸ�������� �����մϴ�.", "���� �Ϸ�", JOptionPane.INFORMATION_MESSAGE);
////            MemberShipUI.this.dispose();
////         }
////         else
////            JOptionPane.showMessageDialog(contentPane, "���̵� Ȯ�����ּ���.", "���� ����", JOptionPane.ERROR_MESSAGE);
//         controller.askRegister(name, memberNum);
//      }
//   }
   
//   private FindIdController controller;
   private JLabel name, memberNum, id;
   private JTextField nameField, memberNumField, idField;
   private JButton findButton;
   private JPanel panel;
   private Container contentPane;
   
   public FindPasswordUI(String address){
      super("��й�ȣ ã�� ȭ��");
//      try{
//      controller = new FindIdController(new Socket(address, 9001), this);
//      }
//      catch(IOException ioe){
//         System.out.println(ioe.getMessage());
//      }
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
      panel.setLayout(new GridLayout(3,2));
      
      id=new JLabel("���̵� �Է�",JLabel.CENTER);
      name = new JLabel("�̸� �Է�", JLabel.CENTER);
      memberNum = new JLabel("�ֹε�Ϲ�ȣ �Է�", JLabel.CENTER);

      idField = new JTextField(10);
      nameField = new JTextField(10);
      memberNumField = new JTextField(10);
      
      //idField.setBackground(new Color(255,255,200));
      //idField=new JPasswordField(10);

      panel.add(id);
      panel.add(idField);
      panel.add(name);
      panel.add(nameField);
      panel.add(memberNum);
      panel.add(memberNumField);

      panel.setBackground(new Color(245,245,245));
      contentPane.add(panel,BorderLayout.CENTER);
   }
   public void createButton(){
      findButton=new JButton("��й�ȣ ã��");
      findButton.setBackground(new Color(255,255,200));
      contentPane.add(findButton,BorderLayout.SOUTH);
   }
   public void view(){
      //ActionListener listener = new FindIdActionListener();
      //findButton.addActionListener(listener);
      this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      this.pack();
      this.setVisible(true);
   }
   
   public static void main(String[] args){
      FindPasswordUI obj = new FindPasswordUI(SharedArea.getIpAddr());
      obj.view();
   }
}