package server.controller;

import java.io.IOException;
import java.util.List;

import server.model.MemberInfo;
import server.model.MemberInfoDAO;

public class MemberInfoManager {
	private MemberInfoDAO dao;
	
	public MemberInfoManager() throws IOException{
		dao = new MemberInfoDAO();
	}
	
	public boolean registerMember(String name, String memberNum, String ID, String nickName, String password){
		return dao.registerMember(new MemberInfo(name, memberNum, ID, nickName, password));
	}
	
	public void exit(){
		
	}
	
	public List<MemberInfo> getMemberInfo(){
		return dao.getMemberInfo();
	}
	
	public boolean login(String ID, String password){
		return dao.login(ID, password);
	}
	
	public String search(String ID){
		return dao.search(ID);
	}
	
	public String search(String ID,String memberNum){ ////
	      return dao.search(ID,memberNum);
	}
}
