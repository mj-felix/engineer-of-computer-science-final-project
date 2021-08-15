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


public class ShowThisUser extends ActionSupport implements
ServletContextAware {

	private static final long serialVersionUID = 1L;
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private long userId;
	private User user;

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
	
	private List dishesT;
	
	

	public List getDishesT() {
		return dishesT;
	}
	public void setDishesT(List dishesT) {
		this.dishesT = dishesT;
	}

	
	public String execute() throws Exception {
		
		ResultSet rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT * FROM USERS WHERE ID='"+userId+"' AND STATUS='1';");
		if(SQLQuery.getResultSetSize(rs)==1) {
			while (rs.next()) {

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
		
		        Date dob = rs.getDate("DATEOFBIRTH");
		        long imgId = rs.getInt("IMAGE_ID");
		        
		           	user = new User(id, email, username, dob, privilegeLevel, xpoints, xpointsreedemed, registrationDate, registrationTime, country, www, sex);
		        	rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT * FROM IMAGES WHERE ID='"+imgId+"' AND STATUS='1';");
		    		String path = "";
		        	if(SQLQuery.getResultSetSize(rs)==1) {
		        		while (rs.next()) {
		        			path = rs.getString("IPATH");
		        		}
		    		}
		     
		        	if(path.equals("")){
			        	rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT IMAGES.* FROM IMAGES, USERRANK, RANKS WHERE USERRANK.USER_ID='"+userId+"' AND USERRANK.RANK_ID=RANKS.ID AND RANKS.IMAGE_ID=IMAGES.ID ORDER BY USERRANK.URDATE, USERRANK.URTIME DESC LIMIT 1;");
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
		        	

		        }
		    }
		
		dishes = new ArrayList();
		
		rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT DISHES.*, CATEGORIES.* FROM DISHES,CATEGORIES WHERE USER_ID='"+user.getId()+"' AND DISHES.CATEGORY_ID=CATEGORIES.ID AND DISHES.STATUS='1';");

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
		
		dishesT = new ArrayList();
		
		rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT DISHES.*, CATEGORIES.* FROM DISHES,CATEGORIES,USERDISHTODO WHERE USERDISHTODO.USER_ID='"+user.getId()+"' AND DISHES.CATEGORY_ID=CATEGORIES.ID AND USERDISHTODO.DISH_ID=DISHES.ID AND DISHES.STATUS='1';");

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
			
			dishesT.add(dish);
	        
	        
	    }
		
		return SUCCESS;
	}

}
