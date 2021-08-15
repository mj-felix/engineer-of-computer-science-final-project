package culinary.secure.user;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;


import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;


import culinary.utils.SQLQuery;
import culinary.utils.User;
import culinary.utils.UserAware;


public class ShowRewards extends ActionSupport implements
ServletContextAware, UserAware, SessionAware {
	
	private User user;

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	private static final long serialVersionUID = 1L;

	
	private List rewards;
	

	private Map session;

	public void setSession(Map session) {
		this.session = session;
	}



	private ServletContext context;

	public void setServletContext(ServletContext context) {
		this.context = context;
	}


	
	public String execute() throws Exception {
		
		rewards = new ArrayList();
		
		ResultSet rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT * FROM REWARDS;");

		while (rs.next()) {
			long rewId = rs.getLong("ID");
			String rewDesc = rs.getString("DESCRIPTION");
			int rewPoints = rs.getInt("XPOINTSCOST");
			
			String[] rew = new String[2];
			rew[0]=rewId+"";

			rew[1]=rewDesc +" - " + rewPoints + " punktów";
	
			
			rewards.add(rew);
	        
	        
	    }
		session.put("rewards", rewards);

		
		return SUCCESS;
	}

}
