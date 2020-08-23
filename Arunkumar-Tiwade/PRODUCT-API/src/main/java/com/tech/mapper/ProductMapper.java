package com.tech.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import com.tech.model.ProductBean;

public class ProductMapper implements RowMapper<ProductBean> {

	@Override
	public ProductBean map(ResultSet rs, StatementContext ctx) throws SQLException {
		ProductBean productBean = new ProductBean();
		productBean.setId(String.valueOf(rs.getInt("id")));
		productBean.setCategory(rs.getString("category"));
		productBean.setCompany(rs.getString("company"));
		productBean.setProduct(rs.getString("product"));
		productBean.setColor(rs.getString("color"));
		productBean.setDescription(rs.getString("description"));
		productBean.setPrice(rs.getString("price"));
		productBean.setDiscount(rs.getString("discount"));
		productBean.setItemsInStock(rs.getString("items_in_stock"));
		return productBean;
	}
}
