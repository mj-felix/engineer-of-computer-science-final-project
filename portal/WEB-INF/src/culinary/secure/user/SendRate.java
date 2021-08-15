package culinary.secure.user;



import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

import culinary.utils.SQLQuery;
import culinary.utils.User;
import culinary.utils.UserAware;




public class SendRate extends ActionSupport implements
 ServletContextAware, UserAware {
	
	private User user;

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
	
	private ServletContext context;

	public void setServletContext(ServletContext context) {
		this.context = context;
	}
	
	private static final long serialVersionUID = 1L;

	private long id;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	private int rate;


	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	private String[] result;

	
	public String[] getResult() {
		return result;
	}

	public void setResult(String[] result) {
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
		result = new String[2];
		
		ResultSet rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT * FROM RATES WHERE USER_ID='"+user.getId()+"' AND DISH_ID='"+id+"';");
		if(SQLQuery.getResultSetSize(rs)>0) {
			result[0]="0";
			result[1]="Nie mo¿esz oceniaæ dwa razy tej samej potrawy";			
			setJsonModel(result);
			return SUCCESS;
			
		}
		if(rate>5 || rate<1){
			result[0]="0";
			result[1]="Ocena nie mieœci sie w zasiêgu.";			
			setJsonModel(result);
			return SUCCESS;
			
		}
		

		

			
			int count = new SQLQuery(context.getAttribute("dbConn"))
			.insert("INSERT INTO RATES SET " +
					"USER_ID='" + user.getId() + "', " +
					"RATE='" + rate + "', " +
					"DISH_ID='" + id + "', " +
					"RDATE=CURDATE(), RTIME=CURTIME();");
		if (count==1) {
			
			rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT * FROM DISHES WHERE ID='"+id+"';");
			if(SQLQuery.getResultSetSize(rs)>0) {
				while(rs.next()){
					result[0]=rs.getFloat("AVERAGERATE")+"";
				}

				
			}
			
			
			result[1]=""+rate;	
		}

		
		setJsonModel(result);
		
		return SUCCESS;
	}

}
