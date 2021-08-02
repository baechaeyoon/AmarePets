package kr.or.pets.member.vo;

import org.springframework.stereotype.Component;

/*create table pets_user (
user_ID varchar2(20) primary key,
user_NAME varchar2(20) not null,
user_PW varchar2(20) not null,
user_Email varchar2(50) not null,
user_EmailHash varchar2(2048),
user_Address varchar2(2048) not null,
user_PhoneNumber number(20) not null,
user_IC varchar2(64) not null
);*/

@Component("memberVO")
public class MemberVO {
	private String userID;
	private String userPW;
	private String userNAME;
	private String userEmail;
	private String userAddress;
	private String userPhoneNumber;
	private String userIC;
	
	public MemberVO() {
		// TODO Auto-generated constructor stub
	}

	public MemberVO(String userID, String userPW, String userNAME, String userEmail, String userAddress,
			String userPhoneNumber, String userIC) {
		super();
		this.userID = userID;
		this.userPW = userPW;
		this.userNAME = userNAME;
		this.userEmail = userEmail;
		this.userAddress = userAddress;
		this.userPhoneNumber = userPhoneNumber;
		this.userIC = userIC;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserPW() {
		return userPW;
	}

	public void setUserPW(String userPW) {
		this.userPW = userPW;
	}

	public String getUserNAME() {
		return userNAME;
	}

	public void setUserNAME(String userNAME) {
		this.userNAME = userNAME;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	public String getUserIC() {
		return userIC;
	}

	public void setUserIC(String userIC) {
		this.userIC = userIC;
	}

	
}
