package com.gaia.ecom.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class GAIA_ECOM_SERVICE_INITIALIZER extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	
    @Override
    protected Class<?>[] getRootConfigClasses() {
    	/*System.out.println("1");*/
    	
        return new Class[] { GAIA_ECOM_CONTROLLER_CONFIG.class };
    }
  
    @Override
    protected Class<?>[] getServletConfigClasses() {
    	/*System.out.println("2");*/
        return null;
    }
  
    @Override
    protected String[] getServletMappings() {
    	/*System.out.println("3");*/
        return new String[] { "/" };
    }
    
    @Override
    protected Filter[] getServletFilters() {
    	
    	/*System.out.println("4");*/
    	CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter(); 
    	characterEncodingFilter.setEncoding("UTF-8"); 
    	characterEncodingFilter.setForceEncoding(true); 
    	/*return new Filter[] {characterEncodingFilter};*/
    	
    	Filter [] singleton = { new GAIA_ECOM_FILTER(),characterEncodingFilter };
    	return singleton;
	}
 
}
