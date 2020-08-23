package com.narender.demo.repository.config;

import org.h2.jdbcx.JdbcConnectionPool;
import org.skife.jdbi.v2.DBI;

public final class DatabaseConfig {

	public static DBI getDBI() {
				
		JdbcConnectionPool jdbcConnectionPool = JdbcConnectionPool.create("jdbc:h2:mem:test", "sa", "");
		return new DBI(jdbcConnectionPool);
	}

}
