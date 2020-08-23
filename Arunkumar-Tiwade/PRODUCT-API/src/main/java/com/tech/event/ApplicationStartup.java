package com.tech.event;

import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

	private static final Logger LOG = LoggerFactory.getLogger(ApplicationStartup.class);
	
	@Autowired
	private Jdbi jdbi;

	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {
		
		LOG.info("Creating Products Table - Start");
		jdbi.useHandle(handle -> {
			handle.execute("create table products (id int primary key, category varchar(100), company varchar(100), product varchar(100), color varchar(100), description varchar(500), price varchar(100), discount varchar(100), items_in_stock varchar(100))");
		});
		LOG.info("Creating Products Table - End");
		
		LOG.info("Loading data in Products Table - Start");
		jdbi.useHandle(handle -> {			
			handle.execute("insert into products (id, category, company, product, color, description, price, discount, items_in_stock) values (?, ?, ?, ?, ?, ?, ?, ?, ?)", 1, "Mobiles", "Apple", "AP1", "Black", "Some description about AP1", "70000", "13", "11");
			handle.execute("insert into products (id, category, company, product, color, description, price, discount, items_in_stock) values (?, ?, ?, ?, ?, ?, ?, ?, ?)", 2, "Mobiles","Samsung","SP1","Grey","Some description about SP1","50000","2","2");
			handle.execute("insert into products (id, category, company, product, color, description, price, discount, items_in_stock) values (?, ?, ?, ?, ?, ?, ?, ?, ?)", 3, "Mobiles","MI","MP1","Black","Some description about MP1","20000","9","35");
			handle.execute("insert into products (id, category, company, product, color, description, price, discount, items_in_stock) values (?, ?, ?, ?, ?, ?, ?, ?, ?)", 4, "Computers","Intel","IL1","Grey","Some description about IL1","67000","0","106");
			handle.execute("insert into products (id, category, company, product, color, description, price, discount, items_in_stock) values (?, ?, ?, ?, ?, ?, ?, ?, ?)", 5, "Computers","Intel","IL2","Black","Some description about IL2","74000","6","300");
			handle.execute("insert into products (id, category, company, product, color, description, price, discount, items_in_stock) values (?, ?, ?, ?, ?, ?, ?, ?, ?)", 6, "Computers","Lenovo","LL1","Black","Some description about LL2","80000","10","138");
			handle.execute("insert into products (id, category, company, product, color, description, price, discount, items_in_stock) values (?, ?, ?, ?, ?, ?, ?, ?, ?)", 7, "Television","LG","LT1","Black","Some description about LT1","42500","8","62");
			handle.execute("insert into products (id, category, company, product, color, description, price, discount, items_in_stock) values (?, ?, ?, ?, ?, ?, ?, ?, ?)", 8, "Television","Samsung","ST1","Grey","Some description about ST1","58360","16","168");
		});
		LOG.info("Loading data in Products Table - End");
		
		LOG.info("Creating orders Table - Start");
		jdbi.useHandle(handle -> {
			handle.execute("CREATE TABLE orders(id INT GENERATED ALWAYS AS IDENTITY, product_id INT, quantity INT, PRIMARY KEY(id), CONSTRAINT fk_product FOREIGN KEY(product_id) REFERENCES products(id))");
		});
		LOG.info("Creating orders Table - End");
		
		return;
	}
}