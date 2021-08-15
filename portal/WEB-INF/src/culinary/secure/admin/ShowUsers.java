package culinary.secure.admin;

import java.sql.Date;
import java.sql.ResultSet;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

import culinary.utils.SQLQuery;
import culinary.utils.User;


public class ShowUsers extends ActionSupport implements
ServletContextAware {
	
	private static final long serialVersionUID = 1L;

	private User[] users;
	

	public User[] getUsers() {
		return users;
	}



	public void setUsers(User[] users) {
		this.users = users;
	}



	private ServletContext context;

	public void setServletContext(ServletContext context) {
		this.context = context;
	}


	
	public String execute() throws Exception {
		ResultSet rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT * FROM USERS WHERE STATUS='1' OR STATUS = '-1';");
		users = new User[SQLQuery.getResultSetSize(rs)];
		int i=0;
		while (rs.next()) {
			long id = rs.getInt("ID");
	        String username = rs.getString("NICK");
	        String email = rs.getString("EMAIL");
	        int privilegeLevel = rs.getInt("PRIVELEGELEVEL");
	        int xpoints = rs.getInt("XPOINTS");
	        int xpointsreedemed = rs.getInt("XPOINTSREEDEMED");
	        Date registrationDate = rs.getDate("REGISTRATIONDATE");
	        String registrationTime = rs.getString("REGISTRATIONTIME").substring(0,5);
	        String country = rs.getString("COUNTRY");
	        String www = rs.getString("WWW");
	        String sex = rs.getString("SEX");
	        int status = rs.getInt("STATUS");
	        Date dob = rs.getDate("DATEOFBIRTH");
	        long imgId = rs.getInt("IMAGE_ID");
	        
	        users[i] = new User(id, email, username, dob, privilegeLevel, xpoints, xpointsreedemed, registrationDate, registrationTime, country, www, sex);
	        users[i].setStatus(status);
	        
	        i++;
	    }
		
		return SUCCESS;
	}

}
