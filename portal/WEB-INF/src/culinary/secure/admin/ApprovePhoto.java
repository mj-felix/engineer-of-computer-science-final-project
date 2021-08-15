package culinary.secure.admin;



import java.sql.ResultSet;

import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

import culinary.utils.SQLQuery;
import culinary.utils.User;
import culinary.utils.UserAware;




public class ApprovePhoto extends ActionSupport implements
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
	private String action;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
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
		
		if (action.equals("1")){
			int count = new SQLQuery(context.getAttribute("dbConn"))
			.update("UPDATE IMAGES SET STATUS='1' WHERE ID='"
					+ id + "';");
			
			result[0]="0";
			result[1]=getText("approvephoto.approved");
			
		}
		else{
			int count = new SQLQuery(context.getAttribute("dbConn"))
			.update("UPDATE IMAGES SET STATUS='-1' WHERE ID='"
					+ id + "';");
			result[0]="1";
			result[1]=getText("approvephoto.banned");
		}
		
		ResultSet rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT XPOINTS FROM USERS WHERE ID='"+user.getId()+"';");
		if(SQLQuery.getResultSetSize(rs)>0) {
			while(rs.next()){
				user.setXpoints(rs.getInt("XPOINTS"));
			}

			
		}
		
		setJsonModel(result);
		
		return SUCCESS;
	}

}
