package server.model;

public class MemberInfo {
	private String name;
	private String memberNum;
	private String ID;
	private String nickName;
	private String password;
	
	public MemberInfo(){
		name = "�̸� ����";
		memberNum = "�ֹε�Ϲ�ȣ ����";
		ID = "���̵� ����";
		nickName = "��ȭ�� ����";
		password = "��й�ȣ ����";
	}
	
	public MemberInfo(String name, String memberNum, String ID, String nickName, String password){
		this.name = name;
		this.memberNum = memberNum;
		this.ID = ID;
		this.nickName = nickName;
		this.password = password;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(String memberNum) {
		this.memberNum = memberNum;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean equals(Object obj){
		if(!(obj instanceof MemberInfo))
			return false;
		MemberInfo memberInfo = (MemberInfo)obj;
		if(!ID.equals(memberInfo.ID))
			return false;
		else if(!password.equals(memberInfo.password))
			return false;
		return true;
	}
}
