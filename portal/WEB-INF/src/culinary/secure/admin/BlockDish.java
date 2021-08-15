package culinary.secure.admin;



import javax.servlet.ServletContext;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

import culinary.utils.SQLQuery;




public class BlockDish extends ActionSupport implements
 ServletContextAware {
	
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
			.update("UPDATE DISHES SET STATUS='1' WHERE ID='"
					+ id + "';");
			
			result[0]="0";
			result[1]=getText("blockdish.unblocked");
			
		}
		else{
			int count = new SQLQuery(context.getAttribute("dbConn"))
			.update("UPDATE DISHES SET STATUS='0' WHERE ID='"
					+ id + "';");
			result[0]="1";
			result[1]=getText("blockdish.blocked");
		}
		
		
		
		setJsonModel(result);
		
		return SUCCESS;
	}

}
