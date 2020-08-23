package com.narender.demo.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.modelmapper.ModelMapper;
import org.skife.jdbi.v2.DBI;

import com.narender.demo.entity.Product;
import com.narender.demo.entity.dto.DiscountedProductDTO;
import com.narender.demo.entity.dto.ProductDTO;
import com.narender.demo.repository.ProductDao;
import com.narender.demo.repository.config.DatabaseConfig;

@Path("/product")
public class ProductController {

	static DBI dbi = null;
	static ProductDao productDao = null;
	ModelMapper modelMapper = new ModelMapper();
	
	static {
		dbi = DatabaseConfig.getDBI();
		productDao = dbi.open(ProductDao.class);
		productDao.createProductTable();
		productDao.add(101L,"Mobiles","Apple","AP1","Black","Some description about AP1",new BigDecimal(70000),new BigDecimal(13),11);
		productDao.add(102L,"Mobiles","Samsung","SP1","Grey","Some description about SP1",new BigDecimal(50000),new BigDecimal(2),2);
		productDao.add(103L,"Mobiles","MI","MP1","Black","Some description about MP1",new BigDecimal(20000),new BigDecimal(9),35);
		productDao.add(104L,"Computers","Intel","IL1","Grey","Some description about IL1",new BigDecimal(67000),new BigDecimal(0),106);
		productDao.add(105L,"Computers","Intel","IL2","Black","Some description about IL2",new BigDecimal(74000),new BigDecimal(6),300);
		productDao.add(106L,"Computers","Lenovo","LL1","Black","Some description about LL2",new BigDecimal(80000),new BigDecimal(10),138);
		productDao.add(107L,"Television","LG","LT1","Black","Some description about LT1",new BigDecimal(42500),new BigDecimal(8),62);
		productDao.add(108L,"Television","Samsung","ST1","Grey","Some description about ST1",new BigDecimal(58360),new BigDecimal(16),168);
	}
	
	public ProductController() {} 

	// 1. I want to get the list of all the products across all the categories
	@GET
	@Path("/{category}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProduct(@PathParam(value = "category") String category) {
		
		if (null != category) {
			
			List<Product> products =productDao.findByCategory(category);
			List<ProductDTO> productDTOs = products
					  .stream()
					  .map(product -> modelMapper.map(product, ProductDTO.class))
					  .collect(Collectors.toList());
			if(null != productDTOs)
				return Response.status(200).entity(productDTOs).build();
			else
				return Response.status(404).entity("Category Not Found").build();
		} else {
				return Response.status(400).entity("Bad Request").build();
		}
		
	}

	//2. I want to get the list of all the products by category or of a price less than or greater than a specified price for that product
	@GET
	@Path("/category/{category}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductByCategory(
			@PathParam(value = "category") String category,
			@QueryParam(value = "price") BigDecimal price,
			@QueryParam(value = "isPriceLess") Boolean isPriceLess
			) {
		if (null == category || null == price || null == isPriceLess) {
			return Response.status(400).entity("Bad Request").build();
		}
		else if (isPriceLess) {
			List<Product> products =productDao.getProductByCategoryForLesserPrice(category,price);
			List<ProductDTO> productDTOs = products
					  .stream()
					  .map(product -> modelMapper.map(product, ProductDTO.class))
					  .collect(Collectors.toList());
			if(null != productDTOs)
				return Response.status(200).entity(productDTOs).build();
			else
				return Response.status(404).entity("No record found").build();
		} else if(!isPriceLess) {
			List<Product> products =productDao.getProductByCategoryForGreaterPrice(category,price);
			List<ProductDTO> productDTOs = products
					  .stream()
					  .map(product -> modelMapper.map(product, ProductDTO.class))
					  .collect(Collectors.toList());
			if(null != productDTOs)
				return Response.status(200).entity(productDTOs).build();
			else
				return Response.status(404).entity("No record found").build();
		} else {
			return Response.status(400).entity("Bad Request").build();
		}
		
	}
	//3. I want to get the list of all the products by company or of a price less than or greater than a specified price for that company
	@GET
	@Path("/company/{company}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductByCompany(
			@PathParam(value = "company") String company,
			@QueryParam(value = "price") BigDecimal price,
			@QueryParam(value = "isPriceLess") Boolean isPriceLess
			) {
		if (null == company || null == price || null == isPriceLess) {
			return Response.status(400).entity("Bad Request").build();
		}
		else if (isPriceLess) {
			List<Product> products =productDao.getProductByCompanyForLesserPrice(company,price);
			List<ProductDTO> productDTOs = products
					  .stream()
					  .map(product -> modelMapper.map(product, ProductDTO.class))
					  .collect(Collectors.toList());
			if(null != productDTOs)
				return Response.status(200).entity(productDTOs).build();
			else
				return Response.status(404).entity("No record found").build();
		} else if(!isPriceLess) {
			List<Product> products =productDao.getProductByCompanyForGreaterPrice(company,price);
			List<ProductDTO> productDTOs = products
					  .stream()
					  .map(product -> modelMapper.map(product, ProductDTO.class))
					  .collect(Collectors.toList());
			if(null != productDTOs)
				return Response.status(200).entity(productDTOs).build();
			else
				return Response.status(404).entity("No record found").build();
		} else {
			return Response.status(400).entity("Bad Request").build();
		}
		
	}

	//4. I want to get the discounted price of all the products by category
	@GET
	@Path("/discountedPrice/{category}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDiscountedProductByCategory(@PathParam(value = "category") String category) {
		if (null != category) {

			List<Product> products = productDao.findByCategory(category);
			List<DiscountedProductDTO> discountedProductDTOs = products
					.stream()
					.map(product -> modelMapper.map(product, DiscountedProductDTO.class))
					.collect(Collectors.toList());
			if (null != discountedProductDTOs)
				return Response.status(200).entity(discountedProductDTOs).build();
			else
				return Response.status(404).entity("Category Not Found").build();
		} else {
			return Response.status(400).entity("Bad Request").build();
		}
	}
}
