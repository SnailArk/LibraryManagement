package com.mbm.librarrymanagement.interceptor;
import java.util.Map;

import com.mbm.librarymanagement.model.UserVO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

	public class AuthenticationInterceptor implements Interceptor {
		 
		private static final long serialVersionUID = 1L;

		@Override
	    public void destroy() {
	        //release resources here
	    }
	 
	    @Override
	    public void init() {
	        // create resources here
	    }
	 
	    @Override
	    public String intercept(ActionInvocation actionInvocation)
	            throws Exception {
	        Map<String, Object> session = actionInvocation.getInvocationContext().getSession();
	        if(session != null) {
	        	UserVO userVO = (UserVO) session.get("user");
		         
		        if(userVO == null){
		            return Action.LOGIN;
		        }else{
		            String result = actionInvocation.invoke();
		            // post processing ...
		            // or send LOGIN
		            return result;
		        }
	        } else {
	        	return Action.LOGIN;
	        }
	        
	    }
}
