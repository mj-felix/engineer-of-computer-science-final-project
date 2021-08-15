package culinary.secure.user;



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




public class GetDishesTitle extends ActionSupport implements
 ServletContextAware {
	
	
	private ServletContext context;

	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	
	private static final long serialVersionUID = 1L;

	private String txt;

	


	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
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
		
		String patternStr = "^[¹æê³ñóœŸ¿¥ÆÊ£ÑÓŒ¯a-z A-Z]*$";
		Pattern pattern = Pattern.compile(patternStr);
		
		Matcher matcher = pattern.matcher(txt);
		boolean matchFound = matcher.matches(); 
		
		if(matchFound){
			
			ResultSet rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT * FROM DISHES WHERE STATUS='1' AND UPPER(NAME) LIKE UPPER('%"+txt+"%');");
			if(SQLQuery.getResultSetSize(rs)>0) {
				result=new String[SQLQuery.getResultSetSize(rs)][];
				int i=0;
				while (rs.next()) {

			        long id = rs.getInt("ID");
			        String name = rs.getString("NAME");

			       	result[i] = new String[2];
			       	result[i][0]= id+"";
			       	result[i][1]= name;
			        
			       
			        
			        i++;
			    }
				
			}
			else{
				result=new String[1][1];
				result[0][0]=getText("getdishestitle.nodishes");
			}
			

		}
		
		else{
			result=new String[1][1];
			result[0][0]=getText("getdishestitle.nodishes");
		}



		
		setJsonModel(result);
		
		return SUCCESS;
	}

}
