package com.narender.demo.repository;

import java.math.BigDecimal;
import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.narender.demo.entity.Product;
import com.narender.demo.entity.mapiing.ProductMapper;

@RegisterMapper(ProductMapper.class)
public interface ProductDao {

	@SqlUpdate("create table PUBLIC.PRODUCT (ID INTEGER NOT NULL, "
			+ " CATEGORY VARCHAR(25) NOT NULL, "
			+ " COMPANY VARCHAR(25) NOT NULL, "
			+ " PRODUCT VARCHAR(25) NOT NULL, "
			+ " COLOR VARCHAR(25) NOT NULL, "
			+ " DESCRIPTION VARCHAR(225) NOT NULL,"
			+ " PRICE DECIMAL(15,2), "
			+ " DISCOUNT DECIMAL(15,2), "
			+ " ITEMINSTOCK INTEGER, "
			+ " PRIMARY KEY (ID))")
	void createProductTable();

	@SqlUpdate("INSERT INTO PUBLIC.PRODUCT (ID, CATEGORY, COMPANY, PRODUCT, COLOR, DESCRIPTION, PRICE, DISCOUNT, ITEMINSTOCK) "
			+ " VALUES(:id, :category, :company, :product, :color, :description, :price, :discount, :itemInStock)")
	void add(@Bind("id") Long id, @Bind("category") String category,
			@Bind("company") String company, @Bind("product") String product,
			@Bind("color") String color, @Bind("description") String description,
			@Bind("price") BigDecimal price, @Bind("discount") BigDecimal discount, @Bind("itemInStock") Integer itemInStock);
	
	@SqlQuery("Select * from PUBLIC.PRODUCT where ID = :id")
	List<Product> findById(@Bind("id") Long id);
	
	@SqlQuery("select * from PUBLIC.PRODUCT where category = :category")
	List<Product> findByCategory(@Bind("category") String category);
	
	@SqlQuery("select * from PUBLIC.PRODUCT where category = :category and price <= :price ")
	List<Product> getProductByCategoryForLesserPrice(@Bind("category") String category,@Bind("price") BigDecimal price);
	
	@SqlQuery("select * from PUBLIC.PRODUCT where category = :category and price >= :price ")
	List<Product> getProductByCategoryForGreaterPrice(@Bind("category") String category,@Bind("price") BigDecimal price);
}
