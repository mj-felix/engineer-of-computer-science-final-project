package culinary.secure.user;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

import culinary.utils.SQLQuery;
import culinary.utils.User;
import culinary.utils.UserAware;




public class SendComment extends ActionSupport implements
 ServletContextAware, UserAware {
	
	private User user;

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
	
	private ServletContext context;

	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	
	private static final long serialVersionUID = 1L;

	private long id;
	private String comment;
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	


	private String result;

	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}


	private Object jsonModel;

	public Object getJsonModel() {
		return jsonModel;
	}

	public void setJsonModel(Object jsonModel) {
		this.jsonModel = jsonModel;
	}

	
	public String execute() throws Exception {
		result="false";
		
		if(comment.equals("")) {
			setJsonModel(result);
			return SUCCESS;
		}
		
		String patternStr = "^[\r\nπÊÍ≥ÒÛúüø•∆ £—”åèØa-z ,.A-Z0-9_-]*$";
		Pattern pattern = Pattern.compile(patternStr);
		
		Matcher matcher = pattern.matcher(comment);
		boolean matchFound = matcher.matches(); 
		
		if(matchFound){
			
			int count = new SQLQuery(context.getAttribute("dbConn"))
			.insert("INSERT INTO COMMENTS SET " +
					"USER_ID='" + user.getId() + "', " +
					"DISH_ID='" + id + "', " +
					"CTEXT='"	+ comment + "', " + 
					"CDATE=CURDATE(), CTIME=CURTIME();");
			
			result="true";
		}
		
		else
			result="false";
		
		setJsonModel(result);
		
		return SUCCESS;
	}

}
