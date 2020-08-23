package com.productsale.app.platform.database;

import org.jdbi.v3.core.Jdbi;

public abstract class Database {

	static Database database;
	private Jdbi jdbi;

	public final void prepareDB() {
		onInitialize();
		afterInitialize();
	}

	public abstract void onInitialize();

	public void afterInitialize() {
		jdbi.useHandle(handle -> {

			handle.execute("DROP TABLE IF EXISTS products");
			handle.execute("DROP TABLE IF EXISTS orders");
			
			handle.execute("CREATE TABLE products (\r\n" + 
					"  id INT AUTO_INCREMENT  PRIMARY KEY,\r\n" + 
					"  category VARCHAR(250) NOT NULL,\r\n" + 
					"  company VARCHAR(250) NOT NULL,\r\n" + 
					"  product VARCHAR(250) NOT NULL,\r\n" + 
					"  color VARCHAR(50) NOT NULL,  \r\n" + 
					"  description Text,\r\n" + 
					"  price DECIMAL(18,2) NOT NULL,\r\n" + 
					"  discount DECIMAL (4, 2) NOT NULL,\r\n" + 
					"  stock INT \r\n" + 
					")");
			
			handle.execute("CREATE TABLE orders (\r\n" + 
					"    id INT AUTO_INCREMENT  PRIMARY KEY,\r\n" + 
					"    productId int,\r\n" + 
					"    price DECIMAL(18,2) NOT NULL,\r\n" + 
					"    quantity int, \r\n" + 
					"    FOREIGN KEY (productId) REFERENCES products(id)\r\n" + 
					")");
			
			
			handle.execute("insert into products (category ,company,product ,color ,description ,price ,discount ,stock ) values ('mobiles','Apple','AP1','Black','description for mobile',70000,13,11)");
			handle.execute("insert into products (category ,company,product ,color ,description ,price ,discount ,stock ) values ('mobiles','Samsung','SP1','Grey','description for mobile',50000,2,2)");
			handle.execute("insert into products (category ,company,product ,color ,description ,price ,discount ,stock ) values ('mobiles','MI','MP1','Black','description for mobile',20000,9,35)");
			handle.execute("insert into products (category ,company,product ,color ,description ,price ,discount ,stock ) values ('Computers','Intel','IL1','Grey','description for computers',67000,0,106)");
			handle.execute("insert into products (category ,company,product ,color ,description ,price ,discount ,stock ) values ('Computers','Intel','IL2','Black','description for computers',74000,0,300)");
			handle.execute("insert into products (category ,company,product ,color ,description ,price ,discount ,stock ) values ('Computers','Lenovo','LL1','Black','description for computers',80000,10,138)");
			handle.execute("insert into products (category ,company,product ,color ,description ,price ,discount ,stock ) values ('Television','LG','LT1','Black','description for television',42500,8,62)");
			handle.execute("insert into products (category ,company,product ,color ,description ,price ,discount ,stock ) values ('Television','Samsung','ST1','Grey','description for television',58360,16,168)");
			
			
		});
	}

	void setJdbi(Jdbi jdbi) {
		this.jdbi = jdbi;
	}

	Jdbi jdbi() {
		return jdbi;
	}

}
