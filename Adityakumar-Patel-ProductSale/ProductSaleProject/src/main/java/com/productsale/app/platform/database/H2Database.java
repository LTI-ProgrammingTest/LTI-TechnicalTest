package com.productsale.app.platform.database;

import java.sql.Connection;
import java.sql.DriverManager;

//import org.apache.log4j.BasicConfigurator;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

public class H2Database extends Database {
	private H2Database() {

	}

	public static Database getInstance() {
		if (database == null) {
			synchronized (H2Database.class) {
				if (database == null) {
					database = new H2Database();
					database.prepareDB();
				}

			}
		}
		return database;
	}

	@Override
	public void onInitialize() {

		try {
			Class.forName("org.h2.Driver");
			Connection con = DriverManager.getConnection("jdbc:h2:file:~/Desktop/productsale", "sa", "");

			database.setJdbi(Jdbi.create(con));
			database.jdbi().installPlugin(new SqlObjectPlugin());

			/*
			 * Initialize db by script RunScript.execute(con, new InputStreamReader(
			 * H2Database.class.getClassLoader().getResourceAsStream("schema.sql")));
			 */
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@Override
	public void afterInitialize() {

		super.afterInitialize();
	}

}
