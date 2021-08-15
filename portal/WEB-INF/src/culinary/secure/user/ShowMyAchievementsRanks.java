package culinary.secure.user;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;


import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;


import culinary.utils.SQLQuery;
import culinary.utils.User;
import culinary.utils.UserAware;


public class ShowMyAchievementsRanks extends ActionSupport implements
ServletContextAware,UserAware {

	private static final long serialVersionUID = 1L;
	private User user;
	public void setUser(User user) {
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	
	private List achievements;
	private List ranks;
	
	

	public List getAchievements() {
		return achievements;
	}
	public void setAchievements(List achievements) {
		this.achievements = achievements;
	}
	public List getRanks() {
		return ranks;
	}
	public void setRanks(List ranks) {
		this.ranks = ranks;
	}

	private ServletContext context;

	public void setServletContext(ServletContext context) {
		this.context = context;
	}


	
	public String execute() throws Exception {
		
		achievements = new ArrayList();
		
		ResultSet rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT USERACHIEVEMENT.*, ACHIEVEMENTS.* FROM USERACHIEVEMENT,ACHIEVEMENTS WHERE USERACHIEVEMENT.USER_ID='"+user.getId()+"' AND USERACHIEVEMENT.ACHIEVEMENT_ID=ACHIEVEMENTS.ID ORDER BY USERACHIEVEMENT.UADATE, USERACHIEVEMENT.UATIME DESC;");

		while (rs.next()) {
			
			String rName = rs.getString("ACHIEVEMENTS.NAME");
			String rDesc = rs.getString("ACHIEVEMENTS.DESCRIPTION");
			int xp = rs.getInt("ACHIEVEMENTS.XPOINTS");
			Date uadate = rs.getDate("USERACHIEVEMENT.UADATE");
			String uatime = rs.getString("USERACHIEVEMENT.UATIME").substring(0,5);
			
			String[] ach = new String[4];
			ach[0]=rName;
			ach[1]=rDesc;
			ach[2]=xp+"";
			ach[3]=uadate.toString()+" "+uatime;
			
			achievements.add(ach);
	        
	        
	    }
		
		ranks = new ArrayList();
		
		rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT USERRANK.*, RANKS.* FROM USERRANK,RANKS WHERE USERRANK.USER_ID='"+user.getId()+"' AND USERRANK.RANK_ID=RANKS.ID ORDER BY USERRANK.URDATE, USERRANK.URTIME DESC;");

		while (rs.next()) {
			
			String rName = rs.getString("RANKS.NAME");
			String rDesc = rs.getString("RANKS.DESCRIPTION");
			int xp = rs.getInt("RANKS.REQUIREDPOINTS");
			Date uadate = rs.getDate("USERRANK.URDATE");
			String uatime = rs.getString("USERRANK.URTIME").substring(0,5);
			
			String[] ran = new String[4];
			ran[0]=rName;
			ran[1]=rDesc;
			ran[2]=xp+"";
			ran[3]=uadate.toString()+" "+uatime;
			
			ranks.add(ran);
	        
	        
	    }
		
		return SUCCESS;
	}

}
