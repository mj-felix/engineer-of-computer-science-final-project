package culinary.secure.user;



import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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




public class GetDishes extends ActionSupport implements
 ServletContextAware {
	
	
	private ServletContext context;

	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	
	private static final long serialVersionUID = 1L;

	private String params;

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

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
		
		String patternStr = "^[0-9|]*$";
		Pattern pattern = Pattern.compile(patternStr);
		
		Matcher matcher = pattern.matcher(params);
		boolean matchFound = matcher.matches(); 
		//boolean matchFound=true;
		
		
		if(matchFound && !params.equals("")){
			
			String[] paramsArr = params.split("\\|");
			String queryStr = "SELECT DISHES.*, DISHINGREDIENT.* FROM DISHES, DISHINGREDIENT, INGREDIENTS WHERE DISHES.STATUS='1' AND DISHES.ID=DISHINGREDIENT.DISH_ID AND DISHINGREDIENT.INGREDIENT_ID=INGREDIENTS.ID;";

			System.out.println(queryStr);
			
			ResultSet rs = new SQLQuery(context.getAttribute("dbConn")).select( queryStr );
			if(SQLQuery.getResultSetSize(rs)>0) {
				result=new String[SQLQuery.getResultSetSize(rs)][];
				result[1]=new String[2];
				result[1][0]= "-1";
				int i=0;
				long id=0;
				String name="";
				List ingredientsDB = new ArrayList();
				while (rs.next()) {
					
					long idDB = rs.getInt("DISHES.ID");
					String nameDB = rs.getString("DISHES.NAME");
					
					if (id != idDB){
						
						boolean isBeingAdded = true;
						
						Iterator iterator = ingredientsDB.iterator();
		                while(iterator.hasNext()){
		                        if(params.indexOf(iterator.next()+"|")==-1) isBeingAdded=false;
		                }
						
						if (isBeingAdded){
							result[i] = new String[2];
					       	result[i][0]= id+"";
					       	result[i][1]= name;
					       	i++;
						}
						
						
						id=idDB;
						name=nameDB;
						ingredientsDB = new ArrayList();
						ingredientsDB.add(rs.getLong("DISHINGREDIENT.INGREDIENT_ID"));
						
						if(rs.isLast()){
							isBeingAdded = true;
							
							iterator = ingredientsDB.iterator();
			                while(iterator.hasNext()){
			                        if(params.indexOf(iterator.next()+"|")==-1) isBeingAdded=false;
			                }
							
							if (isBeingAdded){
								result[i] = new String[2];
						       	result[i][0]= id+"";
						       	result[i][1]= name;
						       	i++;
							}
						}
					}
					else{
						id=idDB;
						name=nameDB;
						ingredientsDB.add(rs.getLong("DISHINGREDIENT.INGREDIENT_ID"));
						
					}
       
			        
			        
			    }
				if(result[1][0].equals("-1")){
					result=new String[1][1];
					result[0][0]=getText("getdishes.nodishes");

					
				}
	
				
				
			}
			else{
				result=new String[1][1];
				result[0][0]=getText("getdishes.nodishes");
			}
			

		}
		
		else{
			result=new String[1][1];
			result[0][0]=getText("getdishes.nodishes");
		}



		
		setJsonModel(result);
		
		return SUCCESS;
	}

}
