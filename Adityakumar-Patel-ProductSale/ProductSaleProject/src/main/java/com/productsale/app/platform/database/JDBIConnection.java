package com.productsale.app.platform.database;

import org.jdbi.v3.core.Jdbi;

public class JDBIConnection {

	public static Jdbi jdbi() {
		return H2Database.getInstance().jdbi();

		// return PostgreSqlEmbadded.getInstance().jdbi()
	}

}
