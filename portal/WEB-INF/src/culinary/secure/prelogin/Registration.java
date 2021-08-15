package culinary.secure.prelogin;

import java.sql.ResultSet;

import javax.servlet.ServletContext;
import org.apache.struts2.util.ServletContextAware;
import com.opensymphony.xwork2.ActionSupport;

import culinary.utils.SHA256;
import culinary.utils.SQLQuery;
import culinary.utils.SendMailSSL;
 
public class Registration
    extends ActionSupport implements ServletContextAware{
 
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String email;
	private String password1;
	private String password2;
	private String dateOfBirth;
	
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		ResultSet rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT * FROM USERS WHERE EMAIL='"+email+"';");
		if(SQLQuery.getResultSetSize(rs)>0) {
			addFieldError( "email", getText( "registration.err.email.exists" ));
			return INPUT;
		}
		rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT * FROM USERS WHERE NICK='"+username+"';");
		if(SQLQuery.getResultSetSize(rs)>0) {
			addFieldError( "username", getText( "registration.err.username.exists" ));
			return INPUT;
		}
		String password = SHA256.generate(password1);
		String key = new String(SHA256.generate(password1+Math.random())).substring(56);
		int count = new SQLQuery(context.getAttribute("dbConn")).insert("INSERT INTO USERS SET EMAIL='"+email+"', PASSWORD='"+password+"', NICK='"+username+"', DATEOFBIRTH='"+dateOfBirth+"', REGISTRATIONDATE=CURDATE(), REGISTRATIONTIME=CURTIME(), ACTIVATIONKEY='"+key+"';");
		if(count>0) {
			SendMailSSL smssl = new SendMailSSL();
			smssl.setToMail(email);
			smssl.setFromMail("gospodynka.pl@gmail.com");
			smssl.setSubject("Rejestracja do portalu Gospodynka.pl");
			smssl.setBody("Kliknij poni¿szy link, by aktywowaæ konto:\n\nhttps://89.77.32.55:8443/portal/secure/Activate.action?email="+email+"&key="+key+"\n\n\nMo¿esz równie¿ wejœæ na stronê http://portal.mszonline.com/secure/Activate.action i podaæ nastêpuj¹ce dane:\n-Mail: "+email+"\n-Kod aktywacyjny: "+key);
			boolean isSend = smssl.sendMsg();
			if (isSend) return SUCCESS;
			else {
				addFieldError( "email", getText( "registration.err.keynotsent" ));
				return INPUT;
			}
		}
		else {
			addFieldError( "email", getText( "registration.err.generic" ));
			return INPUT;
		}
	}
 
	private ServletContext context;  
	public void setServletContext(ServletContext context) {  
	    this.context = context;  
	} 
}
