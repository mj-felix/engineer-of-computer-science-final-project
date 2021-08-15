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


public class ShowMyDishesToDo extends ActionSupport implements
ServletContextAware,UserAware {

	private static final long serialVersionUID = 1L;
	private User user;
	public void setUser(User user) {
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	
	private List dishes;
	
	

	public List getDishes() {
		return dishes;
	}
	public void setDishes(List dishes) {
		this.dishes = dishes;
	}

	private ServletContext context;

	public void setServletContext(ServletContext context) {
		this.context = context;
	}


	
	public String execute() throws Exception {
		
		dishes = new ArrayList();
		
		ResultSet rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT DISHES.*, CATEGORIES.* FROM DISHES,CATEGORIES,USERDISHTODO WHERE USERDISHTODO.USER_ID='"+user.getId()+"' AND DISHES.CATEGORY_ID=CATEGORIES.ID AND USERDISHTODO.DISH_ID=DISHES.ID AND DISHES.STATUS='1';");

		while (rs.next()) {
			long dishId = rs.getLong("DISHES.ID");
			String dishName = rs.getString("DISHES.NAME");
			long catId = rs.getLong("DISHES.CATEGORY_ID");
			String catName = rs.getString("CATEGORIES.NAME");
			
			String[] dish = new String[4];
			dish[0]=dishId+"";
			dish[1]=dishName;
			dish[2]=catId+"";
			dish[3]=catName;
			
			dishes.add(dish);
	        
	        
	    }
		
		return SUCCESS;
	}

}
