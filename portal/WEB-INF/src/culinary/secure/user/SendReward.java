package culinary.secure.user;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;


import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;


import culinary.utils.SQLQuery;
import culinary.utils.User;
import culinary.utils.UserAware;


public class SendReward extends ActionSupport implements
ServletContextAware, UserAware {

	private static final long serialVersionUID = 1L;
	private User user;
	public void setUser(User user) {
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	private long reward;
	private String address;
	
	private String msg;
	
	public String getMsg() {
		return msg;
	}



	public void setMsg(String msg) {
		this.msg = msg;
	}

	private int cost;

	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public long getReward() {
		return reward;
	}



	public void setReward(long reward) {
		this.reward = reward;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	private ServletContext context;

	public void setServletContext(ServletContext context) {
		this.context = context;
	}


	
	public String execute() throws Exception {
	
		int count =new SQLQuery(context.getAttribute("dbConn")).insert("INSERT INTO USERREWARD SET USER_ID='"+user.getId()+"', REWARD_ID='"+reward+"', URDATE=CURDATE(), URTIME=CURTIME(), ADDRESS='"+address+"';");
		
		ResultSet rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT XPOINTSREEDEMED FROM USERS WHERE ID='"+user.getId()+"';");
		if(SQLQuery.getResultSetSize(rs)>0) {
			while(rs.next()){
				user.setXpointsreedemed(rs.getInt("XPOINTSREEDEMED"));
			}

			
		}
		

		msg="Zamówiono nagrodê.";
		return SUCCESS;
	}
	
	public void validate(){

		if (getReward() == 0) {
			addFieldError("reward", getText("sendReward.selectRew"));
		}
		try{
		ResultSet rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT * FROM REWARDS WHERE ID='"+reward+"';");
		cost = 0;
		while (rs.next()) {
			cost = rs.getInt("XPOINTSCOST");
	    }
		
		
		if(cost>user.getXpoints()-user.getXpointsreedemed()){
			addFieldError("reward", getText("sendReward.points"));
			
		}
		}catch(Exception e){}

	}

}
