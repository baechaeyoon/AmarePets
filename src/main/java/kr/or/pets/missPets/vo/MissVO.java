package kr.or.pets.missPets.vo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("missVO")
public class MissVO {
	private int miss_boardNum;
	private String miss_name;		/* 실종동물 이름 */
	private String user_NAME;
	private String user_Email;
	private String user_Address;
	private String user_PhoneNumber;
	private String miss_kind;		/* 실종동물 종류 */
	private String miss_gender;		/* 실종동물 성별 */
	private String miss_age;			/* 실종동물 나이 */
	private String miss_place;		/* 실종장소 */
	private String miss_missdate;		/* 실종한 날짜 */
	private String miss_character;   /* 특징 */
	private String miss_registNum;		
	private String miss_img;		/* 실종동물 사진 */
	private String user_ID;


	
	public MissVO() {
		// TODO Auto-generated constructor stub
	}
	

	public MissVO(int miss_boardNum, String miss_name, String user_NAME, String user_Email, String user_Address,
			String user_PhoneNumber, String miss_kind, String miss_gender, String miss_age, String miss_place,
			String miss_missdate, String miss_character, String miss_registNum, String miss_img, String user_ID) {
		super();
		this.miss_boardNum = miss_boardNum;
		this.miss_name = miss_name;
		this.user_NAME = user_NAME;
		this.user_Email = user_Email;
		this.user_Address = user_Address;
		this.user_PhoneNumber = user_PhoneNumber;
		this.miss_kind = miss_kind;
		this.miss_gender = miss_gender;
		this.miss_age = miss_age;
		this.miss_place = miss_place;
		this.miss_missdate = miss_missdate;
		this.miss_character = miss_character;
		this.miss_registNum = miss_registNum;
		this.miss_img = miss_img;
		this.user_ID = user_ID;
	}

	public String getUser_NAME() {
		return user_NAME;
	}


	public void setUser_NAME(String user_NAME) {
		this.user_NAME = user_NAME;
	}


	public int getMiss_boardNum() {
		return miss_boardNum;
	}


	public void setMiss_boardNum(int miss_boardNum) {
		this.miss_boardNum = miss_boardNum;
	}


	public String getMiss_name() {
		return miss_name;
	}


	public void setMiss_name(String miss_name) {
		this.miss_name = miss_name;
	}


	public String getUser_Email() {
		return user_Email;
	}


	public void setUser_Email(String user_Email) {
		this.user_Email = user_Email;
	}


	public String getUser_Address() {
		return user_Address;
	}


	public void setUser_Address(String user_Address) {
		this.user_Address = user_Address;
	}


	public String getUser_PhoneNumber() {
		return user_PhoneNumber;
	}


	public void setUser_PhoneNumber(String user_PhoneNumber) {
		this.user_PhoneNumber = user_PhoneNumber;
	}


	public String getMiss_kind() {
		return miss_kind;
	}


	public void setMiss_kind(String miss_kind) {
		this.miss_kind = miss_kind;
	}


	public String getMiss_gender() {
		return miss_gender;
	}


	public void setMiss_gender(String miss_gender) {
		this.miss_gender = miss_gender;
	}


	public String getMiss_age() {
		return miss_age;
	}


	public void setMiss_age(String miss_age) {
		this.miss_age = miss_age;
	}


	public String getMiss_place() {
		return miss_place;
	}


	public void setMiss_place(String miss_place) {
		this.miss_place = miss_place;
	}


	public String getMiss_missdate() {
		return miss_missdate;
	}


	public void setMiss_missdate(String miss_missdate) {
		this.miss_missdate = miss_missdate;
	}


	public String getMiss_character() {
		return miss_character;
	}


	public void setMiss_character(String miss_character) {
		this.miss_character = miss_character;
	}

	public String getMiss_registNum() {
		return miss_registNum;
	}


	public void setMiss_registNum(String miss_registNum) {
		this.miss_registNum = miss_registNum;
	}


	public String getMiss_img() {
		return miss_img;
	}


	public void setMiss_img(String miss_img) {
		this.miss_img = miss_img;
	}

	public String getUser_ID() {
		return user_ID;
	}


	public void setUser_ID(String user_ID) {
		this.user_ID = user_ID;
	}

	
	
	
	

}
