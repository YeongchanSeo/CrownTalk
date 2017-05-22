package client.controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import client.model.SharedArea;

public class ChatController{
   private static Socket socket;
   private static PrintWriter writer;
   private static BufferedReader reader;
   private JTextArea leftText;
   private DefaultTableModel model;
   private JLabel label;
   
   public ChatController(JTextArea leftText, DefaultTableModel model, JLabel label){
      this.leftText = leftText;
      this.model = model;
      this.label = label;
      setEnvironment();
      ReceiverThread receiver = new ReceiverThread();
      receiver.start();
   }
   private static void setEnvironment(){
      try {
         socket = new Socket(SharedArea.getIpAddr(),9004);
         reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         writer = new PrintWriter(socket.getOutputStream());
      } catch (UnknownHostException e) {
         System.out.println(e.getMessage());
      } catch (IOException e) {
         System.out.println(e.getMessage());
      }
   }
   private class ReceiverThread extends Thread{
         public void run(){
            try {
            int num = 0;
               writer.println(SharedArea.getNickName());
               writer.flush();
               writer.println(SharedArea.getRoomNum());
               writer.flush();
//               String hi = reader.readLine();/////start
//               System.out.println(hi);
//               StringTokenizer nameList = new StringTokenizer(hi, "&", true);
//               while(nameList.hasMoreTokens()){
//            	   String one = nameList.nextToken();
//            	   System.out.println(one);
//            	   if(one.equals("#"))
//            		  break;
//            	   String two = nameList.nextToken();
//            	   System.out.println(two);
//            	   if(Integer.parseInt(one) == SharedArea.getRoomNum()){
//            	   ++num;
//            	   addTable(two);
//            	   }
//               }/////end
               while(true){
                  String message = reader.readLine();
                  if(message == null)
                	  break;
                  else if(message.equals("_#@나가기@#_"))
                	  break;
                  StringTokenizer tokenizer = new StringTokenizer(message, "|");
                  if(Integer.parseInt(tokenizer.nextToken()) == SharedArea.getRoomNum()){
                	  String str = tokenizer.nextToken();
                	  if(str.equals("#새로온사람#")){
                		  label.setText(++num +"/"+"10");
                		  addTable(tokenizer.nextToken());
                	  }
                	  else if(str.equals("#나가는사람#")){
                		  label.setText(--num +"/"+"10");
                		  deleteTable(tokenizer.nextToken());
                	  }
                	  else{
                  leftText.append(str+"\n");
                  leftText.setCaretPosition(leftText.getText().length());
                	  }
                  }
               }
            } catch (IOException e) {
               System.out.println(e.getMessage());
            }
            finally{
            	try{
            		socket.close();
            	}
            	catch(Exception e){
            		
            	}
            }
      }
   }
   
   public static void sendRoomNum(int num){
	   writer.println(num);
	   writer.println();
   }
   
   public void sendText(String text){
	   writer.println(text);
	   writer.flush();
   }
   
   public void clickOutButton(){
	   writer.println("!@#나가기 버튼 클릭#@!");
	   writer.flush();
   }
   
   public void joinRoom(){
	   
   }
   
   public void addTable(String title){
		
	    String[] str;
	
	      str=new String[]{title};
	      model.addRow(str);
	}
   
   public void deleteTable(String title){
		
	    String[] str;
	
	      str=new String[]{title};
	      for(int i=0; i < model.getRowCount(); i++){
	    	  if(model.getValueAt(i, 0).equals(title))
	    		  model.removeRow(i);
	      }
	}
}