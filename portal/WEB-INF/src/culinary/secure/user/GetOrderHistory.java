package culinary.secure.user;



import java.sql.Date;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

import culinary.utils.Ingredient;
import culinary.utils.SQLQuery;
import culinary.utils.User;
import culinary.utils.UserAware;




public class GetOrderHistory extends ActionSupport implements
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



	private String[][] result;

	



	public String[][] getResult() {
		return result;
	}

	public void setResult(String[][] result) {
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
		

			
			ResultSet rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT USERREWARD.*, REWARDS.* FROM USERREWARD, REWARDS WHERE USERREWARD.USER_ID='"+user.getId()+"' AND USERREWARD.REWARD_ID=REWARDS.ID;");
			if(SQLQuery.getResultSetSize(rs)>0) {
				result=new String[SQLQuery.getResultSetSize(rs)][];
				int i=0;
				while (rs.next()) {

			        
			        String name = rs.getString("REWARDS.NAME");
			        String desc = rs.getString("USERREWARD.ADDRESS");
			        Date date = rs.getDate("USERREWARD.URDATE");
					String time = rs.getString("USERREWARD.URTIME").substring(0,5);
					int cost = rs.getInt("REWARDS.XPOINTSCOST");

			       	result[i] = new String[5];
			       	result[i][0]= name;
			       	result[i][1]= desc;
			       	result[i][2]= date.toString();
			       	result[i][3]= time;
			       	result[i][4]= cost+"";
			        
			       
			        
			        i++;
			    }
				
			}
			else{
				result=new String[1][1];
				result[0][0]=getText("getorderhistory.nohistory");
			}
			




		
		setJsonModel(result);
		
		return SUCCESS;
	}

}
