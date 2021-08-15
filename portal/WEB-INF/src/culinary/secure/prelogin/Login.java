package culinary.secure.prelogin;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import com.opensymphony.xwork2.ActionSupport;

import culinary.utils.Ingredient;
import culinary.utils.SHA256;
import culinary.utils.SQLQuery;
import culinary.utils.User;

 
public class Login
    extends ActionSupport implements ServletContextAware, SessionAware{
 
	private static final long serialVersionUID = 4L;
	
	private String email;
	private String password;
	private List<Ingredient> ingr;

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String execute() throws Exception {
		ResultSet rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT * FROM USERS WHERE EMAIL='"+email+"';");
		if(SQLQuery.getResultSetSize(rs)==0) {
			addFieldError( "email", getText( "login.err.email.notexists" ));
			return INPUT;
		}
		String dbPassword = SHA256.generate(password);
		rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT * FROM USERS WHERE EMAIL='"+email+"' AND PASSWORD='"+dbPassword+"';");
		if(SQLQuery.getResultSetSize(rs)==1) {
			while (rs.next()) {
		        // Get the data from the row using the column index
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
		        
		        if (status == 0){
		        	addFieldError( "email", getText( "login.err.user.notactivated" ));
					return INPUT;
		        }
		        else if (status == -1){
		        	addFieldError( "email", getText( "login.err.user.banned" ));
					return INPUT;
		        }
		        else{
		        	User user = new User(id, email, username, dob, privilegeLevel, xpoints, xpointsreedemed, registrationDate, registrationTime, country, www, sex);
		        	rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT * FROM IMAGES WHERE ID='"+imgId+"' AND STATUS='1';");
		    		String path = "";
		        	if(SQLQuery.getResultSetSize(rs)==1) {
		        		while (rs.next()) {
		        			path = rs.getString("IPATH");
		        		}
		    		}
		        	if(path.equals("")){
			        	rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT IMAGES.* FROM IMAGES, USERRANK, RANKS WHERE USERRANK.USER_ID='"+id+"' AND USERRANK.RANK_ID=RANKS.ID AND RANKS.IMAGE_ID=IMAGES.ID ORDER BY USERRANK.URDATE, USERRANK.URTIME DESC LIMIT 1;");
			        	while (rs.next()) {
		        			path = rs.getString("IPATH");
		        		}
		        	}
		        	user.setiPath(path);
		        	user.setImgId(imgId);
		        	
		        	String rank="";
		        	rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT RANKS.* FROM USERRANK, RANKS WHERE USERRANK.USER_ID='"+id+"' AND USERRANK.RANK_ID=RANKS.ID ORDER BY USERRANK.URDATE, USERRANK.URTIME DESC LIMIT 1;");
		        	while (rs.next()) {
	        			rank = rs.getString("NAME");
	        		}
		        	user.setRank(rank);
		        	
		        	session.put("user", user);
		        	
		        	}
		    }
			
			rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT * FROM INGREDIENTS ORDER BY NAME ASC;");
			ingr = new ArrayList<Ingredient>();
			int i=0;
			while (rs.next()) {

		        long id = rs.getLong("ID");
		        String name = rs.getString("NAME");
		
		       	ingr.add(new Ingredient(id,name));
		        
		       
		        
		        i++;
		    }
			session.put("ingredientsLogin", ingr);
			
			return SUCCESS;
		}
		else {
			addFieldError( "email", getText( "login.err.generic" ));
			return INPUT;
		}
	}
 
	private ServletContext context;  
	public void setServletContext(ServletContext context) {  
	    this.context = context;  
	}
	
	private Map session;

	public void setSession(Map session) {
		this.session = session;
	} 
}
