package com.tech.config;

import javax.sql.DataSource;

import org.jdbi.v3.core.Jdbi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;

@Configuration
public class ApplicationConfiguration {

	@Bean
	@Primary
	public DataSource inMemoryDataSource() throws Exception {
		DataSource embeddedPostgresDS = EmbeddedPostgres.builder().start().getPostgresDatabase();
		return embeddedPostgresDS;
	}

	@Bean
	public Jdbi jdbi() throws Exception {
		return Jdbi.create(inMemoryDataSource());
	}
}
