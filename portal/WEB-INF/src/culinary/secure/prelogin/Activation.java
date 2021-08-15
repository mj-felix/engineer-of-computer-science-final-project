package culinary.secure.prelogin;



import javax.servlet.ServletContext;
import org.apache.struts2.util.ServletContextAware;
import com.opensymphony.xwork2.ActionSupport;


import culinary.utils.SQLQuery;

 
public class Activation
    extends ActionSupport implements ServletContextAware{
 
	private static final long serialVersionUID = 2L;
	
	private String email;
	private String key;	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public String execute() throws Exception {
		int count = new SQLQuery(context.getAttribute("dbConn")).update("UPDATE USERS SET STATUS=1 WHERE EMAIL='"+email+"' AND ACTIVATIONKEY='"+key+"';");
		if(count>0) {
			return SUCCESS;
		}
		else {
			addFieldError( "email", getText( "activation.err.generic" ));
			return INPUT;
		}
	}
 
	private ServletContext context;  
	public void setServletContext(ServletContext context) {  
	    this.context = context;  
	} 
}
