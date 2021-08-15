package culinary.utils;

import java.sql.Date;

public class User {
	
	private Long id;	
	private String email;
	private String username;
	private Date dob;
	private Integer privilegeLevel;
	private Integer xpoints;
	private Integer xpointsreedemed;
	private Date registrationDate;
	private String registrationTime;
	private String country;
	private String www;
	private String sex;
	private String iPath;
	private Long imgId;
	private int status;
	private String rank;
	
	
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Long getImgId() {
		return imgId;
	}
	public void setImgId(Long imgId) {
		this.imgId = imgId;
	}
	public String getiPath() {
		return iPath;
	}
	public void setiPath(String iPath) {
		this.iPath = iPath;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Integer getXpoints() {
		return xpoints;
	}
	public void setXpoints(Integer xpoints) {
		this.xpoints = xpoints;
	}
	public Integer getXpointsreedemed() {
		return xpointsreedemed;
	}
	public void setXpointsreedemed(Integer xpointsreedemed) {
		this.xpointsreedemed = xpointsreedemed;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getWww() {
		return www;
	}
	public void setWww(String www) {
		this.www = www;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getPrivilegeLevel() {
		return privilegeLevel;
	}
	public void setPrivilegeLevel(Integer privilegeLevel) {
		this.privilegeLevel = privilegeLevel;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getRegistrationTime() {
		return registrationTime;
	}
	public void setRegistrationTime(String registrationTime) {
		this.registrationTime = registrationTime;
	}
	public User(Long id, String email, String username, Date dob,
			Integer privilegeLevel, Integer xpoints, Integer xpointsreedemed,
			Date registrationDate, String registrationTime, String country,
			String www, String sex) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.dob = dob;
		this.privilegeLevel = privilegeLevel;
		this.xpoints = xpoints;
		this.xpointsreedemed = xpointsreedemed;
		this.registrationDate = registrationDate;
		this.registrationTime = registrationTime;
		this.country = country;
		this.www = www;
		this.sex = sex;
	}
	

	



}
