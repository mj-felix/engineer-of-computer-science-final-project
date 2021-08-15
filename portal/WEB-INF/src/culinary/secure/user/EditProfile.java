package culinary.secure.user;

import java.sql.Date;
import java.sql.ResultSet;

import javax.servlet.ServletContext;
import org.apache.struts2.util.ServletContextAware;
import com.opensymphony.xwork2.ActionSupport;

import culinary.utils.SHA256;
import culinary.utils.SQLQuery;
import culinary.utils.SendMailSSL;
import culinary.utils.User;
import culinary.utils.UserAware;

public class EditProfile extends ActionSupport implements ServletContextAware,UserAware {

	private static final long serialVersionUID = 1L;

	private String username;
	private String email;

	private String dateOfBirth;
	private String country;
	private String www;
	private String sex;
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
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

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String execute() throws Exception {
		int count = new SQLQuery(context.getAttribute("dbConn"))
				.update("UPDATE USERS SET "
						+ "EMAIL='" + email + "',"
						+ "NICK='" + username + "',"
						+ "WWW='" + www + "',"
						+ "COUNTRY='" + country + "',"
						+ "SEX='" + sex + "',"
						+ "DATEOFBIRTH='" + dateOfBirth + "'"
						+ " WHERE ID='" + user.getId() + "';");
		if ( count==1 ){
			
			user.setEmail(email);
			user.setUsername(username);
			user.setDob(Date.valueOf(dateOfBirth));
			user.setCountry(country);
			user.setWww(www);
			user.setSex(sex);
			
			msg="Profil zaktualizowany.";
			
			return SUCCESS;
		
		}
		else{
			addFieldError( "email", getText( "edit.profile.dbnotupdated" ));
			return INPUT;
		}
		
	}

	private ServletContext context;

	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	
	private User user;
	@Override
	public void setUser(User user) {
		this.user=user;
		
	}
	public User getUser() {
		return user;
	}
}
