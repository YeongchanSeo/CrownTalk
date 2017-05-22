package server.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;



public class FindIdServer {
   private static class FindIdThread extends Thread{
      private static MemberInfoManager manager;
      private static Socket socket;
      private PrintWriter writer;
      public FindIdThread(Socket socket){
         FindIdThread.socket = socket;
         try{
            manager = new MemberInfoManager();
            writer = new PrintWriter(socket.getOutputStream());
         }
         catch(Exception e){
            System.out.println(e.getMessage());
         }
      }
      public void run(){
         String name = null;
         String memberNum = null;
         String ID = null;
         String nickName = null;
         String password = null;
         try{
            BufferedReader reader = new BufferedReader(
                  new InputStreamReader(socket.getInputStream()));
            while(true){
            name = reader.readLine();
            System.out.println(name);
            if(name == null)
               break;
            memberNum = reader.readLine();
            System.out.println(memberNum);
//            password = reader.readLine();
//            System.out.println(password);
            if(name.equals("")){
               writer.println("이름공백");
               writer.flush();
            }
            else if(memberNum.equals("")){
               writer.println("주민등록번호공백");
               writer.flush();
            }
            else if(!manager.search(name, memberNum).equals("")){
               writer.println(manager.search(name, memberNum));
               writer.flush();               
            }
            else{
               writer.println("아이디찾기실패");
               writer.flush();
            }
            }
         }
         catch(Exception e){
            System.out.println("아이디찾기 에러");
         }
         finally{
            try{
               socket.close();
            }
            catch(Exception ignored){
               
            }
         }
      }
   }
   
   public static void main(String[] args){
      ServerSocket serverSocket = null;
      try{
      serverSocket = new ServerSocket(9005);
         while(true){
            Socket socket = serverSocket.accept();
            Thread thread = new FindIdThread(socket);
            thread.start();
         }
      }
      catch(Exception e){
         System.out.println(e.getMessage());
      }
   }
}