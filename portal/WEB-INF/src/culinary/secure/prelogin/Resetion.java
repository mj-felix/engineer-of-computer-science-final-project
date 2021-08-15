package culinary.secure.prelogin;

import java.sql.ResultSet;
import java.util.Random;

import javax.servlet.ServletContext;
import org.apache.struts2.util.ServletContextAware;
import com.opensymphony.xwork2.ActionSupport;

import culinary.utils.RandomString;
import culinary.utils.SHA256;
import culinary.utils.SQLQuery;
import culinary.utils.SendMailSSL;
 
public class Resetion
    extends ActionSupport implements ServletContextAware{
 
	private static final long serialVersionUID = 3L;
	
	private String username;
	private String email;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
 
	
	public String execute() throws Exception {
		ResultSet rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT * FROM USERS WHERE EMAIL='"+email+"';");
		if(SQLQuery.getResultSetSize(rs)==0) {
			addFieldError( "email", getText( "resetion.err.emailusername.notexists" ));
			return INPUT;
		}
		rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT * FROM USERS WHERE NICK='"+username+"';");
		if(SQLQuery.getResultSetSize(rs)==0) {
			addFieldError( "username", getText( "resetion.err.emailusername.notexists" ));
			return INPUT;
		}
		
		Random rnd = new Random();
		String newPassword = RandomString.generate(rnd,"asdfghjkl",3)+RandomString.generate(rnd,"QWERTYUIOP",3)+RandomString.generate(rnd,"1234567890",1)+RandomString.generate(rnd,"$!@#?",1);
		String password = SHA256.generate(newPassword);
	
		int count = new SQLQuery(context.getAttribute("dbConn")).update("UPDATE USERS SET PASSWORD='"+password+"' WHERE EMAIL='"+email+"' AND NICK='"+username+"';");
		if(count>0) {
			SendMailSSL smssl = new SendMailSSL();
			smssl.setToMail(email);
			smssl.setFromMail("gospodynka.pl@gmail.com");
			smssl.setSubject("Resetowanie has³a do portalu Gospodynka.pl");
			smssl.setBody("Has³o zosta³o zresetowane.\n\nNowe has³o: "+newPassword);
			boolean isSend = smssl.sendMsg();
			if (isSend) return SUCCESS;
			else {
				addFieldError( "email", getText( "resetion.err.newpassnotsent" ));
				return INPUT;
			}
		}
		else {
			addFieldError( "email", getText( "resetion.err.generic" ));
			return INPUT;
		}
	}
 
	private ServletContext context;  
	public void setServletContext(ServletContext context) {  
	    this.context = context;  
	} 
}
