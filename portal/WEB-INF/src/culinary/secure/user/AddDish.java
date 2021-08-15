package culinary.secure.user;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

import culinary.utils.SQLQuery;
import culinary.utils.User;
import culinary.utils.UserAware;


public class AddDish extends ActionSupport implements
ServletContextAware, UserAware {
	
	private static final long serialVersionUID = 4L;

	private User user;

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
	

	private List<Long> ingredient;
	private List<Long> iamount;
	
	
	public List getIamount() {
		return iamount;
	}



	public void setIamount(List iamount) {
		this.iamount = iamount;
	}



	public List getIngredient() {
		return ingredient;
	}



	public void setIngredient(List ingredient) {
		this.ingredient = ingredient;
	}
	private long category;


	public long getCategory() {
		return category;
	}



	public void setCategory(long category) {
		this.category = category;
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



	public ServletContext getContext() {
		return context;
	}



	public void setContext(ServletContext context) {
		this.context = context;
	}

	private String name;
	private String description;
	private int amountofcalories;
	private int preptime;
	public int getPreptime() {
		return preptime;
	}



	public void setPreptime(int preptime) {
		this.preptime = preptime;
	}

	private int difficultylevel;
	private int dsize;




	private ServletContext context;

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

	public String execute() throws Exception {
		System.out.println("INSERT INTO DISHES SET " +
				"NAME='" + name + "', " +
				"DESCRIPTION='"	+ description.replace("\r\n", "<br/ >") + "', " + 
				"AMOUNTOFCALORIES='"	+ amountofcalories + "', " +
				"PREPTIME='"	+ preptime + "', " +
				"DIFICULTYLEVEL='"	+ difficultylevel + "', " +
				"DSIZE='"	+ dsize + "', " +
				"USER_ID='"	+ user.getId() + "', " +
				"CATEGORY_ID='"	+ category + "', " +
				"ADDITIONDATE=CURDATE(), ADDITIONTIME=CURTIME();");
		dishKey = new SQLQuery(context.getAttribute("dbConn"))
		.insert("INSERT INTO DISHES SET " +
				"NAME='" + name + "', " +
				"DESCRIPTION='"	+ description.replace("\r\n", "<br/ >") + "', " + 
				"AMOUNTOFCALORIES='"	+ amountofcalories + "', " +
				"PREPTIME='"	+ preptime + "', " +
				"DIFICULTYLEVEL='"	+ difficultylevel + "', " +
				"DSIZE='"	+ dsize + "', " +
				"USER_ID='"	+ user.getId() + "', " +
				"CATEGORY_ID='"	+ category + "', " +
				"ADDITIONDATE=CURDATE(), ADDITIONTIME=CURTIME();",
				true);
		
		
		Long[] ingrLong = (Long[]) ingredient.toArray(new Long[0]);
		Long[] iamLong = (Long[]) iamount.toArray(new Long[0]);
		
		for (int i=0; i<ingrLong.length;i++) {
			if(ingrLong[i].longValue() == -1) continue;
			
			int count = new SQLQuery(context.getAttribute("dbConn"))
			.insert("INSERT INTO DISHINGREDIENT SET " +
					"DISH_ID='" + dishKey + "', " +
					"INGREDIENT_ID='"	+ ingrLong[i].longValue() + "', " +
					"AMOUNT='"	+ iamLong[i].longValue() + "';"
					);
		}
		
		ResultSet rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT XPOINTS FROM USERS WHERE ID='"+user.getId()+"';");
		if(SQLQuery.getResultSetSize(rs)>0) {
			while(rs.next()){
				user.setXpoints(rs.getInt("XPOINTS"));
			}

			
		}
		
		return SUCCESS;
	}
	
	public void validate() {

		if (getCategory()<0) {
			addFieldError("category", getText("addDish.selectCat"));
		}
		
		if (getAmountofcalories()<0){
			setAmountofcalories(0);
			addFieldError("amountofcalories", getText("addDish.negCal"));
			
		}
		
		if (getPreptime()<0){
			setPreptime(0);
			addFieldError("preptime", getText("addDish.negTime"));
			
		}
		
		if (getDifficultylevel()<0 || getDifficultylevel()>3){
			setDifficultylevel(0);
			addFieldError("difficultylevel", getText("addDish.errDiff"));
			
		}
		
		if (getDsize()<0){
			setDsize(0);
			addFieldError("dsize", getText("addDish.negSize"));
			
		}
		
		Long[] ingrLong = (Long[]) ingredient.toArray(new Long[0]);
		boolean isIngr = false;
		for (int i=0; i<ingrLong.length;i++) {
			if(ingrLong[i].longValue() != -1){
				isIngr = true;
				break;
			}
		}
		if(!isIngr){
			addFieldError("ingredient[0]", getText("addDish.noIngr"));
			
		}
		
		
		

	}

}
