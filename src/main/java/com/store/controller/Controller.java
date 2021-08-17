package com.store.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.store.dao.DAO;
import com.store.exceptions.ProductAlreadyExistsException;
import com.store.exceptions.ProductNotFoundException;
import com.store.product.Product;
import com.store.util.DAOFactory;
import com.store.dao.*;

public class Controller {
	
	private DAO dao;
	public Controller() {
		dao = DAOFactory.createObject("getDAO");
	}
	Scanner sc = new Scanner(System.in);

	public void addProduct() throws ProductAlreadyExistsException {
		//adds a product
		System.out.println("Enter:");
		System.out.println("Product Id");
		int id = sc.nextInt();
		System.out.println("Name");
		String name = sc.next();
		System.out.println("Description");
		String desc = sc.next();
		System.out.println("Price");
		int price = sc.nextInt();
		Product pr = new Product();
		pr.setId(id);
		pr.setName(name);
		pr.setDesc(desc);
		pr.setPrice(price);
		dao.addProduct(pr);	
	}
	
	public void updateProduct() throws ProductNotFoundException {
		System.out.println("Enter Product Id");
		int pid = sc.nextInt();
		System.out.println("What field do you want to update?");
		System.out.println("Press:");
		System.out.println("1: To update product name");
		System.out.println("2: To update product description");
		System.out.println("3: To update product price");
		int choice = sc.nextInt();
		dao.updateProduct(pid,choice);
	}

	public void getAllProducts() {
		// TODO Auto-generated method stub
		ArrayList<Product>products = dao.getAllProducts();
		for(Product p:products) {
			System.out.println("Product Id:"+p.getId()+", Product Name: "+p.getName()+", Product Description: "+p.getDesc()+", Price: "+p.getPrice());;
		}
	}

	public void getProductsByPrice() {
		// TODO Auto-generated method stub
		System.out.println("Enter price:");
		int price = sc.nextInt();
		ArrayList<Product>products = dao.getProductsByPrice(price);
		for(Product p:products) {
			System.out.println("Product Id:"+p.getId()+", Product Name: "+p.getName()+", Product Description: "+p.getDesc()+", Price: "+p.getPrice());;
		}
		
	}

	public void addTwoProducts() throws SQLException, ProductAlreadyExistsException {
		// TODO Auto-generated method stub
		System.out.println("Enter Product-1");
		System.out.println("Product Id");
		int id1 = sc.nextInt();
		System.out.println("Name");
		String name1 = sc.next();
		System.out.println("Description");
		String desc1 = sc.next();
		System.out.println("Price");
		int price1 = sc.nextInt();
		Product pr1 = new Product();
		pr1.setId(id1);
		pr1.setName(name1);
		pr1.setDesc(desc1);
		pr1.setPrice(price1);
		
		System.out.println("Enter Product-2");
		System.out.println("Product Id");
		int id2 = sc.nextInt();
		System.out.println("Name");
		String name2 = sc.next();
		System.out.println("Description");
		String desc2 = sc.next();
		System.out.println("Price");
		int price2 = sc.nextInt();
		Product pr2 = new Product();
		pr2.setId(id2);
		pr2.setName(name2);
		pr2.setDesc(desc2);
		pr2.setPrice(price2);
		
		dao.addTwoProducts(pr1,pr2);
		
	}
}
