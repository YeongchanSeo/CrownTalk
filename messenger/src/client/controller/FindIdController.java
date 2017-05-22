package client.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

import client.view.FindIdUI;

public class FindIdController {
   private Socket socket;
   private PrintWriter writer;
   private FindIdUI ui;
   public FindIdController(Socket socket, FindIdUI ui){
      this.socket = socket;
      this.ui = ui;
      try{
      writer = new PrintWriter(socket.getOutputStream());
      }
      catch(Exception e){
      }
   }
   
   public boolean askRegister(String name, String memberNum){
      writer.println(name);
      writer.flush();
      writer.println(memberNum);
      writer.flush();
      System.out.println(name);
      System.out.println(memberNum);
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
   
            if(str.equals("�̸�����")){
               JOptionPane.showMessageDialog(ui, "�̸��� �������ּ���.", "���̵�ã�� ����", JOptionPane.ERROR_MESSAGE);
            }
            else if(str.equals("�ֹε�Ϲ�ȣ����")){
               JOptionPane.showMessageDialog(ui, "�ֹε�Ϲ�ȣ�� �������ּ���.", "���̵�ã�� ����", JOptionPane.ERROR_MESSAGE);
            }
            else if(str.equals("���̵�ã�����")){
               JOptionPane.showMessageDialog(ui, "��ġ�ϴ� ���̵� �����ϴ�..", "���̵�ã�� ����", JOptionPane.ERROR_MESSAGE);
            }
            else{
               JOptionPane.showMessageDialog(ui,"���̵�� " + str + "�Դϴ�.", "���̵� Ȯ��", JOptionPane.ERROR_MESSAGE);
            }

         }
         catch(IOException ioe){
            System.out.println("���̵�ã�� ����");
         }
         return false;
   }
}