package com.productsale.app.modules.product.dao;

import java.util.List;
import java.util.Optional;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.productsale.app.modules.product.model.Product;

@RegisterBeanMapper(Product.class)
public interface ProductDao {

	@SqlQuery("select * from products where id = ?")
	Optional<Product> getProduct(int id);

	@SqlQuery("select * from products order by 1")
	List<Product> getAllProducts();

	@SqlQuery("select * from products order by category")
	List<Product> getAllProductsByCategory();

	@SqlQuery("select * from products where price <= :lesPrice")
	List<Product> getAllProductsLessThanPrice(@Bind("lesPrice") double lessPrice);

	@SqlQuery("select * from products where price >= :grePrice")
	List<Product> getAllProductsGreaterThanPrice(@Bind("grePrice") double grePrice);

	@SqlQuery("select * from products where price BETWEEN :grePrice AND :lesPrice ")
	List<Product> getAllProductsInBetweenPrice(@Bind("grePrice") double greaterPrice,
			@Bind("lesPrice") double lessPrice);

	
	@SqlQuery("select stock from products where id = ?")
	int getRemainStock(int ld);

	@SqlUpdate("update products set stock = ? where id = ?")
	void updateStockForProduct(int remainStock, int id);
}
