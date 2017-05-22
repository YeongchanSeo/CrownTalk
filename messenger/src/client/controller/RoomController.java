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
//		writer.println("����");
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
//			System.out.println("���ͷ�Ʈ");
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
//            	  else if(title.equals("�� ����")){
//            		 int num =Integer.parseInt(reader.readLine());
//            		 SharedArea.setRoomNum(num);
//            		 System.out.println("���ȣ"+num);
//            		 ui.dispose();
//            		 nui.dispose();
//            		 TalkView view = new TalkView();
//    		         view.display();
//    		         writer.println("_@����@_");
//    		         writer.flush();
//    		         break;
//            	  }
//            	  else if(title.equals("�� ���� �㰡")){
//            		  ui.dispose();
//            		  TalkView view = new TalkView();
//            		  view.display();
//            		  writer.println("_@����@_");
//            		  writer.flush();
//            		  break;
//            	  }
//            	  else if(title.equals("@ã�ҽ��ϴ�@")){
//            		  JOptionPane.showMessageDialog(table, 
//            				  "���� �����մϴ�.", "�˻� �Ϸ�", JOptionPane.INFORMATION_MESSAGE);
//            		  continue;
//            	  }
//            	  else if(title.equals("@��ã�ҽ��ϴ�@")){
//            		  JOptionPane.showMessageDialog(table, 
//            				  "���� �������� �ʽ��ϴ�.", "�˻� �Ϸ�", JOptionPane.INFORMATION_MESSAGE);
//            		  continue;
//            	  }
//            	  else if(title.equals("��й�ȣ Ʋ��")){
//            		  JOptionPane.showMessageDialog(table, "��й�ȣ�� Ʋ���ϴ�.","�Է¿���",JOptionPane.INFORMATION_MESSAGE);
////      				PasswordUI.this.dispose();
//            		  continue;
//            	  }
//            	  else if(title.equals("���� �ʰ�")){
//            		  nui.dispose();
//            		  JOptionPane.showMessageDialog(table, 
//            				  "���� �ʰ�", "���� �޽���", JOptionPane.ERROR_MESSAGE);
//            		  continue;
//            	  }
//            	  else if(title.equals("���� �� ����")){
//            		  nui.dispose();
//            		  JOptionPane.showMessageDialog(table, 
//            				  "���� �̸��� ���� �̹� �ֽ��ϴ�.", "���� �޽���", JOptionPane.ERROR_MESSAGE);
//            		  continue;
//            	  }
//            	  else if (title.equals("��й�ȣ �ٰ�")){
//            		  String pw = reader.readLine();
//            		  int num =Integer.parseInt(reader.readLine());
//             		  SharedArea.setRoomNum(num);
//            		  System.out.println("���ȣ:"+num);
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
//            	  else if(title.equals("_@�α׾ƿ� �ض�@_")){
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
//		writer.println("_#�濡 �����ϰ� �;��#_");
//		writer.flush();
//		writer.println(table.getSelectedRow());
//		writer.flush();
//	}
//	
//	public void askPwCheck(String pw){
//		writer.println("_#��й�ȣ Ȯ������#_");
//		writer.flush();
//		writer.println(table.getSelectedRow());
//		writer.flush();
//		writer.println(pw);
//		writer.flush();
//	}
//	
//	public void logOut(){
//		writer.println("__@�α׾ƿ�@__");
//		writer.flush();
//	}
//	
//	public void searchTitle(String title){
//		writer.println("##�˻��ҷ�##");
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
		writer.println("����");
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
			System.out.println("���ͷ�Ʈ");
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
            	  else if(title.equals("�� ����")){
            		 int num =Integer.parseInt(reader.readLine());
            		 SharedArea.setRoomNum(num);
            		 System.out.println("���ȣ"+num);
            		 ui.dispose();
            		 nui.dispose();
            		 TalkView view = new TalkView();
    		         view.display();
    		         writer.println("_@����@_");
    		         writer.flush();
    		         break;
            	  }
            	  else if(title.equals("�� ���� �㰡")){
            		  ui.dispose();
            		  TalkView view = new TalkView();
            		  view.display();
            		  writer.println("_@����@_");
            		  writer.flush();
            		  break;
            	  }
            	  else if(title.equals("@ã�ҽ��ϴ�@")){
            		  JOptionPane.showMessageDialog(table, 
            				  "���� �����մϴ�.", "�˻� �Ϸ�", JOptionPane.INFORMATION_MESSAGE);
            		  continue;
            	  }
            	  else if(title.equals("@��ã�ҽ��ϴ�@")){
            		  JOptionPane.showMessageDialog(table, 
            				  "���� �������� �ʽ��ϴ�.", "�˻� �Ϸ�", JOptionPane.INFORMATION_MESSAGE);
            		  continue;
            	  }
            	  else if(title.equals("��й�ȣ Ʋ��")){
            		  JOptionPane.showMessageDialog(table, "��й�ȣ�� Ʋ���ϴ�.","�Է¿���",JOptionPane.INFORMATION_MESSAGE);
//      				PasswordUI.this.dispose();
            		  continue;
            	  }
            	  else if(title.equals("���� �ʰ�")){
            		  nui.dispose();
            		  JOptionPane.showMessageDialog(table, 
            				  "���� �ʰ�", "���� �޽���", JOptionPane.ERROR_MESSAGE);
            		  continue;
            	  }
            	  else if(title.equals("���� �� ����")){
            		  nui.dispose();
            		  JOptionPane.showMessageDialog(table, 
            				  "���� �̸��� ���� �̹� �ֽ��ϴ�.", "���� �޽���", JOptionPane.ERROR_MESSAGE);
            		  continue;
            	  }
            	  else if (title.equals("��й�ȣ �ٰ�")){
            		  String pw = reader.readLine();
            		  int num =Integer.parseInt(reader.readLine());
             		  SharedArea.setRoomNum(num);
            		  System.out.println("���ȣ:"+num);
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
            	  else if(title.equals("_@�α׾ƿ� �ض�@_")){
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
		writer.println("_#�濡 �����ϰ� �;��#_");
		writer.flush();
		writer.println(table.getSelectedRow());
		writer.flush();
	}
	
	public void askPwCheck(String pw){
		writer.println("_#��й�ȣ Ȯ������#_");
		writer.flush();
		writer.println(table.getSelectedRow());
		writer.flush();
		writer.println(pw);
		writer.flush();
	}
	
	public void logOut(){
		writer.println("__@�α׾ƿ�@__");
		writer.flush();
	}
	
	public void searchTitle(String title){
		writer.println("##�˻��ҷ�##");
		writer.flush();
		writer.println(title);
		writer.flush();
	}
}
