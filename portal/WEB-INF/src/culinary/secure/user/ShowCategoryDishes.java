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


public class ShowCategoryDishes extends ActionSupport implements
ServletContextAware {

	private static final long serialVersionUID = 1L;
	
	
	private long catId;
	
	public long getCatId() {
		return catId;
	}
	public void setCatId(long catId) {
		this.catId = catId;
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

	private String catName;
	
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String execute() throws Exception {
		
		dishes = new ArrayList();
		
		ResultSet rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT DISHES.* FROM DISHES WHERE DISHES.CATEGORY_ID='"+catId+"' AND DISHES.STATUS='1' ORDER BY DISHES.NAME ASC;");

		while (rs.next()) {
			long dishId = rs.getLong("DISHES.ID");
			String dishName = rs.getString("DISHES.NAME");

			
			String[] dish = new String[2];
			dish[0]=dishId+"";
			dish[1]=dishName;

			
			dishes.add(dish);
	        
	        
	    }
		

			rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT * FROM CATEGORIES WHERE ID='"+catId+"';");

			while (rs.next()) {

				catName = rs.getString("NAME");

		        
		        
		    }
			
		
		
		return SUCCESS;
	}

}
