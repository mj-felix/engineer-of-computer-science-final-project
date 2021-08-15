package culinary.utils;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class AuthenticationInterceptorAdmin implements Interceptor {
	
	private static final long serialVersionUID = 1L;

	public void destroy() {
	}

	public void init() {
	}

	public String intercept(ActionInvocation actionInvocation) throws Exception {
		Map session = actionInvocation.getInvocationContext().getSession();

		User user = (User) session.get("user");
		if (user.getPrivilegeLevel() == 0) {
			session.remove("user");
			return "sessionExpired";
		} else {
			return actionInvocation.invoke();
		}
	}
	
}
