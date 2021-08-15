package culinary.secure.admin;



import javax.servlet.ServletContext;
import org.apache.struts2.util.ServletContextAware;
import com.opensymphony.xwork2.ActionSupport;


import culinary.utils.SQLQuery;


public class EditUser extends ActionSupport implements ServletContextAware {

	private static final long serialVersionUID = 1L;

	private String username;
	private String email;

	private String dateOfBirth;
	private String country;
	private String www;
	private String sex;
	private String msg;
	private long id;
	private int privilegeLevel;



	public int getPrivilegeLevel() {
		return privilegeLevel;
	}

	public void setPrivilegeLevel(int privilegeLevel) {
		this.privilegeLevel = privilegeLevel;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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
						+ "PRIVELEGELEVEL='" + privilegeLevel + "',"
						+ "DATEOFBIRTH='" + dateOfBirth + "'"
						+ " WHERE ID='" + id + "';");
		if ( count==1 ){
			

			
			msg="U¿ytkownik <i>" +username+ "</i> zaktualizowany.";
			
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
	

}
