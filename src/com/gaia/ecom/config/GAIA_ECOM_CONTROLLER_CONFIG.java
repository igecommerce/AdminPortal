package com.gaia.ecom.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.gaia.util.GAIA_UTILS;

import javax.sql.DataSource;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.gaia.ecom.controller")
@PropertySource(value = { "classpath:datasource.properties","classpath:gaia.properties","classpath:gaiaproductqueries.properties" })
public class GAIA_ECOM_CONTROLLER_CONFIG extends WebMvcConfigurerAdapter{
	@Autowired
    private Environment env;
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		System.out.println("5");
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		/*viewResolver.setViewClass(JstlView.class);*/
		viewResolver.setPrefix("/views/");
		viewResolver.setSuffix(".jsp");
		registry.viewResolver(viewResolver);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}
	

	@Bean
    public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		try {
		
		/*System.out.println("encrypt_driverClass   "+env.getRequiredProperty("jdbc.driverClassName")+"   "+env.getRequiredProperty("jdbc.url")+"   "+
				env.getRequiredProperty("jdbc.username")+"   "+env.getRequiredProperty("jdbc.password")+"   "+env.getRequiredProperty("gaia_key")
				+" username1  "+GAIA_UTILS.encrypt("root", env.getRequiredProperty("gaia_key")) +"  password1 "
				+GAIA_UTILS.encrypt("admin123", env.getRequiredProperty("gaia_key"))+" url1  "
				+GAIA_UTILS.encrypt("jdbc:mysql://localhost:3306/ecommerce_gaia", env.getRequiredProperty("gaia_key"))+"   "
				);*/
			
		String driverClass = env.getRequiredProperty("jdbc.driverClassName");
		String url = env.getRequiredProperty("jdbc.url");
		String username = env.getRequiredProperty("jdbc.username");
		String password = env.getRequiredProperty("jdbc.password");
		
		dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataSource;
    }
 
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }
    
    @Bean(name="multipartResolver") 
    public CommonsMultipartResolver getResolver() throws IOException{
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
         
        //Set the maximum allowed size (in bytes) for each individual file.
        resolver.setMaxUploadSizePerFile(5242880);//5MB
         
        //You may also set other available properties.
         
        return resolver;
    }
}