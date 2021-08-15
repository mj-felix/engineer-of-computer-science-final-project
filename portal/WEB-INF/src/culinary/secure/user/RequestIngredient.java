package culinary.secure.user;

import com.opensymphony.xwork2.ActionSupport;


import culinary.utils.SendMailSSL;
import culinary.utils.User;
import culinary.utils.UserAware;


public class RequestIngredient extends ActionSupport implements
UserAware {
	
	private static final long serialVersionUID = 1L;

	private String name;
	private String unit;
	
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String execute() throws Exception {
		SendMailSSL smssl = new SendMailSSL();
		smssl.setToMail("gospodynka.pl@gmail.com");
		smssl.setFromMail(user.getEmail());
		smssl.setSubject("Proœba o nowy sk³adnik - Gospodynka.pl");
		smssl.setBody("Proœba o nowy sk³adnik: "+name+" ("+unit+")\nEmail: "+user.getEmail());
		boolean isSend = smssl.sendMsg();
		if (isSend) return SUCCESS;
		else {
			addFieldError( "email", getText( "requestiongredient.err.generic" ));
			return INPUT;
		}


	}

	private User user;
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user=user;
		
	}

}
