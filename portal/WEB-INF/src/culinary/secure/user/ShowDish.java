package culinary.secure.user;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

import culinary.utils.SQLQuery;
import culinary.utils.User;
import culinary.utils.UserAware;

public class ShowDish extends ActionSupport implements ServletContextAware,
		UserAware {

	private static final long serialVersionUID = 4L;

	private User user;

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	private ServletContext context;

	public ServletContext getContext() {
		return context;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;

	}

	private long dishKey;

	public long getDishKey() {
		return dishKey;
	}

	public void setDishKey(long dishKey) {
		this.dishKey = dishKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAmountofcalories() {
		return amountofcalories;
	}

	public void setAmountofcalories(int amountofcalories) {
		this.amountofcalories = amountofcalories;
	}

	public int getPreptime() {
		return preptime;
	}

	public void setPreptime(int preptime) {
		this.preptime = preptime;
	}

	public int getDifficultylevel() {
		return difficultylevel;
	}

	public void setDifficultylevel(int difficultylevel) {
		this.difficultylevel = difficultylevel;
	}

	public int getDsize() {
		return dsize;
	}

	public void setDsize(int dsize) {
		this.dsize = dsize;
	}

	public float getAveragerate() {
		return averagerate;
	}

	public void setAveragerate(float averagerate) {
		this.averagerate = averagerate;
	}

	public Date getAdditiondate() {
		return additiondate;
	}

	public void setAdditiondate(Date additiondate) {
		this.additiondate = additiondate;
	}

	public String getAdditiontime() {
		return additiontime;
	}

	public void setAdditiontime(String additiontime) {
		this.additiontime = additiontime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getCatId() {
		return catId;
	}

	public void setCatId(long catId) {
		this.catId = catId;
	}

	private String name;
	private String description;
	private int amountofcalories;
	private int preptime;
	private int difficultylevel;
	private int dsize;
	private float averagerate;
	private Date additiondate;
	private String additiontime;
	private long userId;
	private String userName;
	private long catId;
	private String catName;

	private List ingredients;
	private List comments;

	private List photos;

	private int canBeToDo;
	
	private int rate;

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getCanBeToDo() {
		return canBeToDo;
	}

	public void setCanBeToDo(int canBeToDo) {
		this.canBeToDo = canBeToDo;
	}

	public List getPhotos() {
		return photos;
	}

	public void setPhotos(List photos) {
		this.photos = photos;
	}

	public List getComments() {
		return comments;
	}

	public void setComments(List comments) {
		this.comments = comments;
	}

	public List getIngredients() {
		return ingredients;
	}

	public void setIngredients(List ingredients) {
		this.ingredients = ingredients;
	}

	public String execute() throws Exception {
		ResultSet rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT DISHES.*, USERS.*, CATEGORIES.* FROM DISHES, USERS, CATEGORIES WHERE DISHES.STATUS='1' AND DISHES.ID='"+dishKey+"' AND DISHES.USER_ID=USERS.ID AND DISHES.CATEGORY_ID=CATEGORIES.ID;");

		while (rs.next()) {
			name = rs.getString("DISHES.NAME");
			description = rs.getString("DISHES.DESCRIPTION");
			amountofcalories = rs.getInt("DISHES.AMOUNTOFCALORIES");
			preptime = rs.getInt("DISHES.PREPTIME");
			difficultylevel = rs.getInt("DISHES.DIFICULTYLEVEL");
			dsize = rs.getInt("DISHES.DSIZE");
			averagerate = rs.getFloat("DISHES.AVERAGERATE");
			additiondate = rs.getDate("DISHES.ADDITIONDATE");
			additiontime = rs.getString("DISHES.ADDITIONTIME").substring(0,5);
			userId = rs.getInt("DISHES.USER_ID");
			userName = rs.getString("USERS.NICK");
			catId = rs.getInt("DISHES.CATEGORY_ID");
			catName = rs.getString("CATEGORIES.NAME");
	        
	        
	    }
		
		ingredients = new ArrayList();
		
		rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT INGREDIENTS.*, DISHINGREDIENT.* FROM INGREDIENTS, DISHINGREDIENT WHERE DISHINGREDIENT.DISH_ID='"+dishKey+"' AND DISHINGREDIENT.INGREDIENT_ID=INGREDIENTS.ID;");

		while (rs.next()) {
			String name = rs.getString("INGREDIENTS.NAME");
			String unit = rs.getString("INGREDIENTS.UNIT");
			int amount = rs.getInt("DISHINGREDIENT.AMOUNT");

			
			String[] ingr = new String[3];
			ingr[0]=name;
			ingr[1]=unit;
			ingr[2]=amount+"";
			
			ingredients.add(ingr);
	        
	        
	    }
		
		comments = new ArrayList();
		
		rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT COMMENTS.*, USERS.* FROM COMMENTS, USERS WHERE COMMENTS.DISH_ID='"+dishKey+"' AND COMMENTS.STATUS='1' AND COMMENTS.USER_ID=USERS.ID ORDER BY COMMENTS.ID DESC;");

		while (rs.next()) {
			String userName = rs.getString("USERS.NICK");
			long userId = rs.getInt("COMMENTS.USER_ID");
			String commentText = rs.getString("COMMENTS.CTEXT").replace("\\r\\n", "<br />");
			Date cdate = rs.getDate("COMMENTS.CDATE");
			String ctime = rs.getString("COMMENTS.CTIME").substring(0,5);
			long commId = rs.getInt("COMMENTS.ID");
			
			
			String[] comm = new String[6];
			comm[0]=userName;
			comm[1]=userId +"";
			comm[2]=commentText;
			comm[3]=cdate.toString();
			comm[4]=ctime;
			comm[5]=commId+"";
			
			
			comments.add(comm);
	        
	        
			
	    }
		
		photos = new ArrayList();
		
		rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT DISHIMAGEUSER.*, IMAGES.*, USERS.* FROM DISHIMAGEUSER, IMAGES, USERS WHERE DISHIMAGEUSER.DISH_ID='"+dishKey+"' AND IMAGES.STATUS='1' AND DISHIMAGEUSER.IMAGE_ID=IMAGES.ID AND DISHIMAGEUSER.USER_ID=USERS.ID ORDER BY IMAGES.ADDITIONDATE, IMAGES.ADDITIONTIME ASC;");

		while (rs.next()) {
			String ipath = rs.getString("IMAGES.IPATH");
			Date pdate = rs.getDate("IMAGES.ADDITIONDATE");
			String ptime = rs.getString("IMAGES.ADDITIONTIME").substring(0,5);
			long p_userId = rs.getInt("USERS.ID");
			String p_userName = rs.getString("USERS.NICK");
			
	
			
			
			String[] photo = new String[5];
			photo[0]=ipath;
			photo[1]=pdate.toString();
			photo[2]=ptime;
			photo[3]=p_userId+"";
			photo[4]=p_userName;
							
			
			photos.add(photo);
		}
		
		canBeToDo=1;
		
		if(userId == user.getId()) canBeToDo = 0;
		
		if(canBeToDo != 0){
			
			rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT * FROM USERDISHTODO WHERE USER_ID='"+user.getId()+"' AND DISH_ID='"+dishKey+"';");
			if(SQLQuery.getResultSetSize(rs)>0) canBeToDo = 2;
			
		}
		
		rate = -1;
		
		rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT * FROM RATES WHERE USER_ID='"+user.getId()+"' AND DISH_ID='"+dishKey+"';");
		if(SQLQuery.getResultSetSize(rs)>0){
			while (rs.next()) {
				
				rate=rs.getInt("RATE");
			} 
		}
		
		
		
		
		
		return SUCCESS;
	}

}
