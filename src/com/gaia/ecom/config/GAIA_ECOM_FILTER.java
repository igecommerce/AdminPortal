package com.gaia.ecom.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GAIA_ECOM_FILTER implements Filter {

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		/*System.out.println("6");*/
		
		HttpServletResponse response = (HttpServletResponse) res;
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "X-requested-with, Content-Type");
		chain.doFilter(req, res);
		
		HttpServletRequest request = (HttpServletRequest) req;
		
		/*HttpSession session = request.getSession();
		
		String applicationContextURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";
		
		String url = request.getRequestURL().toString();*/
		
		/*if(url != null && applicationContextURL != null && url.equalsIgnoreCase(applicationContextURL))
		{
			chain.doFilter(req, res);
		}
		else
		{
			if(url != null)
			{
				String loginURL = applicationContextURL+"validdohlogin";
				
				if(loginURL != null && url.equalsIgnoreCase(loginURL))
				{
					chain.doFilter(req, res);
				}
				else
				{
					if(session != null && session.getAttribute("userid") != null)
					{
						chain.doFilter(req, res);
					}
					else
					{
						
						if(url.contains("static/resources"))
						{
							System.out.println("Session Expired");
							System.out.println("Session Userid   "+session.getAttribute("userid")+"   "+applicationContextURL+"   "+url);
							System.out.println("loginURL  "+url+"   "+loginURL);
							
							chain.doFilter(req, res);
						}
						else
						{
							response.sendRedirect(applicationContextURL);
						}
						
					}
				}
			}
		}*/
	}

	public void init(FilterConfig filterConfig) {
		/*System.out.println("7");*/
	}

	public void destroy() {}

}
