package com.tech.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tech.model.ProductCreateOrderBean;

public class ProductCreateOrderMapper implements RowMapper<ProductCreateOrderBean> {

	private static final Logger LOG = LoggerFactory.getLogger(ProductCreateOrderMapper.class);

	@Override
	public ProductCreateOrderBean map(ResultSet rs, StatementContext ctx) throws SQLException {
		ProductCreateOrderBean productCreateOrderBean = new ProductCreateOrderBean();
		LOG.info("id={}, productId={}, quantity={}", rs.getInt("id"), rs.getInt("product_id"), rs.getInt("quantity"));
		productCreateOrderBean.setId(String.valueOf(rs.getInt("id")));
		productCreateOrderBean.setProductId(String.valueOf(rs.getInt("product_id")));
		productCreateOrderBean.setQuantity(String.valueOf(rs.getInt("quantity")));
		return productCreateOrderBean;
	}

}
