package com.filter;

import java.io.IOException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.model.User;

public class LoginCheckFilter extends AbstractFilter implements Filter {
	private static List<String> allowedURIs;

	public void init(FilterConfig fConfig) throws ServletException {
		if(allowedURIs == null){
			allowedURIs = new ArrayList<String>();
			allowedURIs.add(fConfig.getInitParameter("loginActionURI"));
			allowedURIs.add("/JSFCrudApp/javax.faces.resource/main.css.xhtml");
			allowedURIs.add("/JSFCrudApp/javax.faces.resource/theme.css.xhtml");
			allowedURIs.add("/JSFCrudApp/javax.faces.resource/primefaces.js.xhtml");
			allowedURIs.add("/JSFCrudApp/javax.faces.resource/primefaces.css.xhtml");
			allowedURIs.add("/JSFCrudApp/javax.faces.resource/jquery/jquery.js.xhtml");
			allowedURIs.add("/JSFCrudApp/javax.faces.resource/messages/messages.png.xhtml");
			allowedURIs.add("/JSFCrudApp/javax.faces.resource/images/ui-icons_2e83ff_256x240.png.xhtml");
			allowedURIs.add("/JSFCrudApp/javax.faces.resource/images/ui-icons_38667f_256x240.png.xhtml");
        }
    }
	
	public void destroy() {}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		if (session.isNew()) {
			doLogin(request, response, req);
			return;
		}

		User user = (User) session.getAttribute("user");

		if (user == null && !allowedURIs.contains(req.getRequestURI())) {
			System.out.println(req.getRequestURI());
			doLogin(request, response, req);
			return;
		}

		chain.doFilter(request, response);
	}

}
