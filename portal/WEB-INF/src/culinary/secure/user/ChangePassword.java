package culinary.secure.user;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

import culinary.utils.SHA256;
import culinary.utils.SQLQuery;
import culinary.utils.User;
import culinary.utils.UserAware;

public class ChangePassword extends ActionSupport implements
ServletContextAware, UserAware {
	
	private static final long serialVersionUID = 1L;

	private String password1;
	private String password2;

	private User user;
	
	private ServletContext context;

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() { 
		return user;
	}
	
	public String execute() throws Exception {

		String password = SHA256.generate(password1);
		int count = new SQLQuery(context.getAttribute("dbConn"))
				.update("UPDATE USERS SET PASSWORD='" + password
						+ "' WHERE ID='" + user.getId() + "';");
		if (count > 0) {
			return SUCCESS;
		} else {
			addFieldError("password1", getText("changepassword.err.generic"));
			return INPUT;
		}
	}

}
