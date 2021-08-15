package culinary.secure.admin;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

import culinary.utils.SQLQuery;
import culinary.utils.User;


public class ShowBlockDish extends ActionSupport implements
ServletContextAware {
	
	private static final long serialVersionUID = 1L;

	private ServletContext context;

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	private List dishes;
	
	public List getDishes() {
		return dishes;
	}

	public void setDishes(List dishes) {
		this.dishes = dishes;
	}

	public String execute() throws Exception {
		
		dishes=new ArrayList();
		
		ResultSet rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT DISHES.*, USERS.* FROM DISHES, USERS WHERE DISHES.USER_ID=USERS.ID ORDER BY USERS.NICK, DISHES.NAME ASC;");
		
		while (rs.next()) {
						
			long id = rs.getLong("DISHES.ID");
			String name = rs.getString("DISHES.NAME");
			int status = rs.getInt("DISHES.STATUS");
			long userId = rs.getLong("DISHES.USER_ID");
	        String username = rs.getString("USERS.NICK");
	        
	        String[] dish = new String[5];
	        dish[0]=id+"";
	        dish[1]=name;
	        dish[2]=status+"";
	        dish[3]=userId+"";
	        dish[4]=username;
	        
	        dishes.add(dish);
	        
	       
	    }
		
		return SUCCESS;
	}

}
