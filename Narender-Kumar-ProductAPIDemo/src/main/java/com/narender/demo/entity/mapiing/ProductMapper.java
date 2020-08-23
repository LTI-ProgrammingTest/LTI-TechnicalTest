package com.narender.demo.entity.mapiing;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.narender.demo.entity.Product;

public class ProductMapper implements ResultSetMapper<Product> {

	public Product map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		// TODO Auto-generated method stub
		return new Product(
				r.getLong("ID"),
				r.getString("CATEGORY"),
				r.getString("COMPANY"),
				r.getString("PRODUCT"),
				r.getString("COLOR"),
				r.getString("DESCRIPTION"),
				r.getBigDecimal("PRICE"),
				r.getBigDecimal("DISCOUNT"),
				r.getInt("ITEMINSTOCK"));
	}

}
