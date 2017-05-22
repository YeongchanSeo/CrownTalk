//package client.controller;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.Socket;
//
//import javax.swing.JComboBox;
//import javax.swing.JOptionPane;
//import javax.swing.JTable;
//import javax.swing.table.DefaultTableModel;
//
//import client.model.SharedArea;
//import client.view.PasswordUI;
//import client.view.TalkRoomUI;
//import client.view.TalkRoomUI.CreateRoomUI;
//import client.view.TalkView;
//
//public class RoomController {
//	private Socket socket;
//	private PrintWriter writer;
//	private JTable table;
//	private JComboBox box;
//	private TalkRoomUI ui;
//	private CreateRoomUI nui;
//
//	public RoomController(JTable table, JComboBox box){
//		try{
//	    socket = new Socket(SharedArea.getIpAddr(),9003);
//		writer = new PrintWriter(socket.getOutputStream());
//		}
//		catch(IOException ioe){
//			
//		}
//		this.table = table;
//		this.box = box;
//		
//		ReceiverThread receiver = new ReceiverThread();
//	    receiver.start();
//	    enter();
//	}
//	
//	private void enter(){
//		writer.println("입장");
//		writer.flush();
//	}
//	
//	public void makeRoom(String title, String pw, int num){
//		writer.println(title);
//		writer.flush();
//		writer.println(pw);
//		writer.flush();
//		writer.println(num);
//		writer.flush();
//		try{
//			Thread.sleep(100);
//		}
//		catch(InterruptedException ie){
//			System.out.println("인터럽트");
//		}
//	}
//
//	private class ReceiverThread extends Thread{
//		 private BufferedReader reader = null;
//         public void run(){
//            try {
//               while(true){
//            	  reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            	  String title = reader.readLine();
//            	  if(title == null)
//            		  break;
//            	  else if(title.equals("방 생성")){
//            		 int num =Integer.parseInt(reader.readLine());
//            		 SharedArea.setRoomNum(num);
//            		 System.out.println("방번호"+num);
//            		 ui.dispose();
//            		 nui.dispose();
//            		 TalkView view = new TalkView();
//    		         view.display();
//    		         writer.println("_@들어간다@_");
//    		         writer.flush();
//    		         break;
//            	  }
//            	  else if(title.equals("방 입장 허가")){
//            		  ui.dispose();
//            		  TalkView view = new TalkView();
//            		  view.display();
//            		  writer.println("_@들어간다@_");
//            		  writer.flush();
//            		  break;
//            	  }
//            	  else if(title.equals("@찾았습니다@")){
//            		  JOptionPane.showMessageDialog(table, 
//            				  "방이 존재합니다.", "검색 완료", JOptionPane.INFORMATION_MESSAGE);
//            		  continue;
//            	  }
//            	  else if(title.equals("@못찾았습니다@")){
//            		  JOptionPane.showMessageDialog(table, 
//            				  "방이 존재하지 않습니다.", "검색 완료", JOptionPane.INFORMATION_MESSAGE);
//            		  continue;
//            	  }
//            	  else if(title.equals("비밀번호 틀림")){
//            		  JOptionPane.showMessageDialog(table, "비밀번호가 틀립니다.","입력오류",JOptionPane.INFORMATION_MESSAGE);
////      				PasswordUI.this.dispose();
//            		  continue;
//            	  }
//            	  else if(title.equals("정원 초과")){
//            		  nui.dispose();
//            		  JOptionPane.showMessageDialog(table, 
//            				  "정원 초과", "에러 메시지", JOptionPane.ERROR_MESSAGE);
//            		  continue;
//            	  }
//            	  else if(title.equals("같은 방 존재")){
//            		  nui.dispose();
//            		  JOptionPane.showMessageDialog(table, 
//            				  "같은 이름의 방이 이미 있습니다.", "에러 메시지", JOptionPane.ERROR_MESSAGE);
//            		  continue;
//            	  }
//            	  else if (title.equals("비밀번호 줄게")){
//            		  String pw = reader.readLine();
//            		  int num =Integer.parseInt(reader.readLine());
//             		  SharedArea.setRoomNum(num);
//            		  System.out.println("방번호:"+num);
//            		  if(pw.equals("")){
//            			 TalkView view = new TalkView();
//            			 ui.dispose();
//            			  view.display();
//            			  continue;
//            		  }
//            		  else{
//            			 new PasswordUI(RoomController.this);
//            			 continue;
//            		  }
//            	  }
//            	  else if(title.equals("_@로그아웃 해라@_")){
//            		  break;
//            	  }
//            	  reader.readLine();
//         		  reader.readLine();
//            	  addTable(title);
//         		  addCombo(title);
//               }
//            }
//            catch (IOException e) {
//               System.out.println(e.getMessage());
//            } 
//            finally{
//            	try{
//            	reader.close();
//            	socket.close();
//            	}
//            	catch(Exception e){
//            	}
//            }
//      }
//   }
//	
//	public void addTable(String title){
//	
//	    String[] str;
//	    DefaultTableModel model = (DefaultTableModel) table.getModel();
//	
//	      str=new String[]{title};
//	      model.addRow(str);
//	}
//	
//	public void addCombo(String title){
//	         box.addItem(title);
//	   }
//	
//	public void setUI(TalkRoomUI ui){
//		this.ui = ui;
//	}
//	
//	public void setNUI(CreateRoomUI nui){
//		this.nui = nui;
//	}
//	
//	public void enterRoom(){
//		writer.println("_#방에 입장하고 싶어요#_");
//		writer.flush();
//		writer.println(table.getSelectedRow());
//		writer.flush();
//	}
//	
//	public void askPwCheck(String pw){
//		writer.println("_#비밀번호 확인해줘#_");
//		writer.flush();
//		writer.println(table.getSelectedRow());
//		writer.flush();
//		writer.println(pw);
//		writer.flush();
//	}
//	
//	public void logOut(){
//		writer.println("__@로그아웃@__");
//		writer.flush();
//	}
//	
//	public void searchTitle(String title){
//		writer.println("##검색할랭##");
//		writer.flush();
//		writer.println(title);
//		writer.flush();
//	}
//}

package client.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import client.model.SharedArea;
import client.view.PasswordUI;
import client.view.TalkRoomUI;
import client.view.TalkRoomUI.CreateRoomUI;
import client.view.TalkView;

public class RoomController {
	private Socket socket;
	private PrintWriter writer;
	private JTable table;
	private JComboBox box;
	private TalkRoomUI ui;
	private CreateRoomUI nui;

	public RoomController(JTable table, JComboBox box){
		try{
	    socket = new Socket(SharedArea.getIpAddr(),9003);
		writer = new PrintWriter(socket.getOutputStream());
		}
		catch(IOException ioe){
			
		}
		this.table = table;
		this.box = box;
		
		ReceiverThread receiver = new ReceiverThread();
	    receiver.start();
	    enter();
	}
	
	private void enter(){
		writer.println("입장");
		writer.flush();
	}
	
	public void makeRoom(String title, String pw, int num){
		writer.println(title);
		writer.flush();
		writer.println(pw);
		writer.flush();
		writer.println(num);
		writer.flush();
		try{
			Thread.sleep(100);
		}
		catch(InterruptedException ie){
			System.out.println("인터럽트");
		}
	}

	private class ReceiverThread extends Thread{
		 private BufferedReader reader = null;
         public void run(){
            try {
               while(true){
            	  reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            	  String title = reader.readLine();
            	  if(title == null)
            		  break;
            	  else if(title.equals("방 생성")){
            		 int num =Integer.parseInt(reader.readLine());
            		 SharedArea.setRoomNum(num);
            		 System.out.println("방번호"+num);
            		 ui.dispose();
            		 nui.dispose();
            		 TalkView view = new TalkView();
    		         view.display();
    		         writer.println("_@들어간다@_");
    		         writer.flush();
    		         break;
            	  }
            	  else if(title.equals("방 입장 허가")){
            		  ui.dispose();
            		  TalkView view = new TalkView();
            		  view.display();
            		  writer.println("_@들어간다@_");
            		  writer.flush();
            		  break;
            	  }
            	  else if(title.equals("@찾았습니다@")){
            		  JOptionPane.showMessageDialog(table, 
            				  "방이 존재합니다.", "검색 완료", JOptionPane.INFORMATION_MESSAGE);
            		  continue;
            	  }
            	  else if(title.equals("@못찾았습니다@")){
            		  JOptionPane.showMessageDialog(table, 
            				  "방이 존재하지 않습니다.", "검색 완료", JOptionPane.INFORMATION_MESSAGE);
            		  continue;
            	  }
            	  else if(title.equals("비밀번호 틀림")){
            		  JOptionPane.showMessageDialog(table, "비밀번호가 틀립니다.","입력오류",JOptionPane.INFORMATION_MESSAGE);
//      				PasswordUI.this.dispose();
            		  continue;
            	  }
            	  else if(title.equals("정원 초과")){
            		  nui.dispose();
            		  JOptionPane.showMessageDialog(table, 
            				  "정원 초과", "에러 메시지", JOptionPane.ERROR_MESSAGE);
            		  continue;
            	  }
            	  else if(title.equals("같은 방 존재")){
            		  nui.dispose();
            		  JOptionPane.showMessageDialog(table, 
            				  "같은 이름의 방이 이미 있습니다.", "에러 메시지", JOptionPane.ERROR_MESSAGE);
            		  continue;
            	  }
            	  else if (title.equals("비밀번호 줄게")){
            		  String pw = reader.readLine();
            		  int num =Integer.parseInt(reader.readLine());
             		  SharedArea.setRoomNum(num);
            		  System.out.println("방번호:"+num);
            		  if(pw.equals("")){
            			 TalkView view = new TalkView();
            			 ui.dispose();
            			  view.display();
            			  continue;
            		  }
            		  else{
            			 new PasswordUI(RoomController.this);
            			 continue;
            		  }
            	  }
            	  else if(title.equals("_@로그아웃 해라@_")){
            		  break;
            	  }
            	  reader.readLine();
         		  reader.readLine();
            	  addTable(title);
         		  addCombo(title);
               }
            }
            catch (IOException e) {
               System.out.println(e.getMessage());
            } 
            finally{
            	try{
            	reader.close();
            	socket.close();
            	}
            	catch(Exception e){
            	}
            }
      }
   }
	
	public void addTable(String title){
	
	    String[] str;
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	
	      str=new String[]{title};
	      model.addRow(str);
	}
	
	public void addCombo(String title){
	         box.addItem(title);
	   }
	
	public void setUI(TalkRoomUI ui){
		this.ui = ui;
	}
	
	public void setNUI(CreateRoomUI nui){
		this.nui = nui;
	}
	
	public void enterRoom(){
		writer.println("_#방에 입장하고 싶어요#_");
		writer.flush();
		writer.println(table.getSelectedRow());
		writer.flush();
	}
	
	public void askPwCheck(String pw){
		writer.println("_#비밀번호 확인해줘#_");
		writer.flush();
		writer.println(table.getSelectedRow());
		writer.flush();
		writer.println(pw);
		writer.flush();
	}
	
	public void logOut(){
		writer.println("__@로그아웃@__");
		writer.flush();
	}
	
	public void searchTitle(String title){
		writer.println("##검색할랭##");
		writer.flush();
		writer.println(title);
		writer.flush();
	}
}
