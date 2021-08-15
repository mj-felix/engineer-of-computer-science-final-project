package culinary.utils;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class SessionInterceptor implements Interceptor {

	private static final long serialVersionUID = 1L;

	public void destroy() {
	}

	public void init() {
	}

	public String intercept(ActionInvocation actionInvocation) throws Exception {
		Map<String, Object> session = actionInvocation.getInvocationContext()
				.getSession();
		if (session.isEmpty()){
			
			String actionName = actionInvocation.getInvocationContext().getName();
			System.out.println(actionName);
			if(actionName.equals("ShowSearch")) return "showSearch";
			else if(actionName.equals("ShowSearchTitle")) return "showSearchTitle";
			else if(actionName.equals("ShowCatalogue")) return "showCatalogue";
			
			else return "sessionExpired";
		}
			
		return actionInvocation.invoke();

	}


}
