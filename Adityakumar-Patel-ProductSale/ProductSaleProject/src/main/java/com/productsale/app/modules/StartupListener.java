package com.productsale.app.modules;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.productsale.app.platform.database.JDBIConnection;

@WebListener
public class StartupListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		
		// DB Initialize here
		JDBIConnection.jdbi();
	}
	
}
