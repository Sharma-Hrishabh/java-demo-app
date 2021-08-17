package com.store.view;

import java.sql.SQLException;
import java.util.Scanner;

import com.store.controller.Controller;
import com.store.exceptions.ProductAlreadyExistsException;
import com.store.exceptions.ProductNotFoundException;
import com.store.util.Factory;

public class View {

	public static void main(String[] args) throws ProductAlreadyExistsException, ProductNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String ss="y";
		
		while(ss.equalsIgnoreCase("y")) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Press");
			System.out.println("1: Add Product");
			System.out.println("2: Update Product");
			System.out.println("3: Show All Products");
			System.out.println("4: Show product by price");
			System.out.println("5: Add two products");
			int c = sc.nextInt();
			Controller control = Factory.createObject("getController");
			switch (c) {
			case 1:
				control.addProduct();
				break;
			case 2:
				control.updateProduct();
			case 3:
				control.getAllProducts();
			case 4:
				control.getProductsByPrice();
				break;
			case 5:
				control.addTwoProducts();
			default:
				break;
			}
			System.out.println("Do you wish to continue?Y/n");
			ss=sc.next();
		}
				

	}

}
