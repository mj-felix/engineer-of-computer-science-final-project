package culinary.secure.admin;

import java.sql.ResultSet;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

import culinary.utils.SQLQuery;


public class ShowApprovePhoto extends ActionSupport implements
ServletContextAware {
	
	private static final long serialVersionUID = 1L;

	private String[][] avatarPhotos;
	private String[][] dishPhotos;
	
	public String[][] getAvatarPhotos() {
		return avatarPhotos;
	}

	public void setAvatarPhotos(String[][] avatarPhotos) {
		this.avatarPhotos = avatarPhotos;
	}

	public String[][] getDishPhotos() {
		return dishPhotos;
	}

	public void setDishPhotos(String[][] dishPhotos) {
		this.dishPhotos = dishPhotos;
	}

	private ServletContext context;

	public void setServletContext(ServletContext context) {
		this.context = context;
	}


	
	public String execute() throws Exception {
		ResultSet rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT * FROM IMAGES WHERE STATUS='0' AND IPATH LIKE '%avatar%';");
		avatarPhotos = new String[SQLQuery.getResultSetSize(rs)][2];
		int i=0;
		while (rs.next()) {
	        // Get the data from the row using the column index
	        long id = rs.getInt("ID");
	        String path = rs.getString("IPATH");
	        System.out.println(id+"-"+path);
	        avatarPhotos[i][0] = "" + id;
	        avatarPhotos[i][1] = path;
	        
	        
	        
	        i++;
	    }
		
		rs = new SQLQuery(context.getAttribute("dbConn")).select("SELECT * FROM IMAGES WHERE STATUS='0' AND IPATH LIKE '%dishes%';");
		dishPhotos = new String[SQLQuery.getResultSetSize(rs)][2];
		i=0;
		while (rs.next()) {
	        // Get the data from the row using the column index
	        long id = rs.getInt("ID");
	        String path = rs.getString("IPATH");
	        System.out.println(id+"-"+path);
	        dishPhotos[i][0] = "" + id;
	        dishPhotos[i][1] = path;
	        
	        
	        
	        i++;
	    }
		
		return SUCCESS;
	}

}
