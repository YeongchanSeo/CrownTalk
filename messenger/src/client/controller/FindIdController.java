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
   
            if(str.equals("이름공백")){
               JOptionPane.showMessageDialog(ui, "이름을 기입해주세요.", "아이디찾기 실패", JOptionPane.ERROR_MESSAGE);
            }
            else if(str.equals("주민등록번호공백")){
               JOptionPane.showMessageDialog(ui, "주민등록번호를 기입해주세요.", "아이디찾기 실패", JOptionPane.ERROR_MESSAGE);
            }
            else if(str.equals("아이디찾기실패")){
               JOptionPane.showMessageDialog(ui, "일치하는 아이디가 없습니다..", "아이디찾기 실패", JOptionPane.ERROR_MESSAGE);
            }
            else{
               JOptionPane.showMessageDialog(ui,"아이디는 " + str + "입니다.", "아이디 확인", JOptionPane.ERROR_MESSAGE);
            }

         }
         catch(IOException ioe){
            System.out.println("아이디찾기 에러");
         }
         return false;
   }
}