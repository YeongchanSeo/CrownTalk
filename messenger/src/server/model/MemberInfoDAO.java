package server.model;
import java.util.*;
import java.io.*;

public class MemberInfoDAO {
	private static List<MemberInfo> list;
	private static File file;
	
	static{
		file = new File("MemberList.txt");
		list = Collections.synchronizedList(new LinkedList<MemberInfo>());
	}
	
	public MemberInfoDAO() throws IOException{
		if(!file.exists())
			file.createNewFile();
		else
			readFile();
	}
	
	private void readFile() throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(file));
		while(true){
			String name = reader.readLine();
			if(name == null)
				break;
			String memberNum = reader.readLine();
			String ID = reader.readLine();
			String nickName = reader.readLine();
			String password = reader.readLine();
			list.add(new MemberInfo(name, memberNum, ID, nickName, password));
		}
		reader.close();
	}
	
	public void writeFile(){
		FileWriter writer = null;
		try{
			writer = new FileWriter(file);
			writer.flush();
		}
		catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
		finally{
			try{
				writer.close();
			}
			catch(Exception e){
			}
		}
		Iterator<MemberInfo> iterator = list.iterator();
		while(iterator.hasNext()){
			writeFile(iterator.next());
		}
	}
	
	public void writeFile(MemberInfo info){
		PrintWriter writer = null;
		try{
			writer = new PrintWriter(new FileWriter(file, true));
			String name = info.getName();
			String memberNum = info.getMemberNum();
			String ID = info.getID();
			String nickName = info.getNickName();
			String password = info.getPassword();
			list.add(new MemberInfo(name, memberNum, ID, nickName, password));
			writer.println(name);
			writer.println(memberNum);
			writer.println(ID);
			writer.println(nickName);
			writer.println(password);			
		}
		catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
		finally{
			writer.close();
		}
	}
	
	public boolean registerMember(MemberInfo info){
		for(MemberInfo i : list){
			if(i.getID().equals(info.getID())){
				return false;
			}
		}
		list.add(info);
		writeFile(info);
		return true;
	}
	
	public boolean login(String ID, String password){
		MemberInfo info = new MemberInfo();
		info.setID(ID);
		info.setPassword(password);
		for(MemberInfo i : list){
			if(i.equals(info)){
				return true;
			}
		}
		return false;
	}
	
	public String search(String ID){
		for(MemberInfo i : list){
			if(i.getID().equals(ID))
				return i.getNickName();
		}
		return "";
	}
	
	 public String search(String name, String memberNum){ ////
	      for(MemberInfo i : list){
	    	  System.out.println(i.getName());
	    	  System.out.println("아이디"+name);
	    	  System.out.println(i.getMemberNum());
	    	  System.out.println("주민번호"+memberNum);
	         if(i.getName().equals(name) &&i.getMemberNum().equals(memberNum))
	            return i.getID();
	      }
	      return "";
	   }
	
	public List<MemberInfo> getMemberInfo(){
		return list;
	}
}
