package com.store.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


import com.store.exceptions.ProductAlreadyExistsException;
import com.store.exceptions.ProductNotFoundException;
import com.store.product.Product;

public class DAO {
	Scanner sc = new Scanner(System.in);
	Connection con;
	public void addProduct(Product pr) throws ProductAlreadyExistsException {
		try {
			// step 1 load driver
			Class.forName("com.mysql.jdbc.Driver");

			// step 2 create connection with database

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hsbc", "root", "Iamsql");

			PreparedStatement ps1 = con.prepareStatement("select * from products where productid=?");
			ps1.setInt(1, pr.getId());
			
			ResultSet res = ps1.executeQuery();
			if(res.next()) {

				if(pr.getId()==res.getInt(1)) {
					throw new ProductAlreadyExistsException();
				
				}
			}
			
			PreparedStatement ps = con.prepareStatement("insert into products values(?,?,?,?)");
			ps.setInt(1, pr.getId());
			ps.setString(2, pr.getName());
			ps.setString(3, pr.getDesc());
			ps.setInt(4, pr.getPrice());
			ps.executeUpdate();
		
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateProduct(int pid, int choice) throws ProductNotFoundException {
		// TODO Auto-generated method stub
		try {
			// step 1 load driver
			Class.forName("com.mysql.jdbc.Driver");

			// step 2 create connection with database

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hsbc", "root", "Iamsql");

			PreparedStatement ps = con.prepareStatement("select * from products where productid=?");
			ps.setInt(1, pid);
			
			ResultSet res = ps.executeQuery();
			if(res==null) throw new ProductNotFoundException();
			else {
				switch(choice) {
					case 1:
						//name update
						System.out.println("Enter new name");
						String newname = sc.next();
						PreparedStatement ps1 = con.prepareStatement("update products set name=? where productid=?");
						ps1.setString(1, newname);
						ps1.setInt(2, pid);
						ps1.executeUpdate();
						break;
					case 2:
						System.out.println("Enter new description");
						String newdesc = sc.next();
						PreparedStatement ps2 = con.prepareStatement("update products set description=? where productid=?");
						ps2.setString(1, newdesc);
						ps2.setInt(2, pid);
						ps2.executeUpdate();
						break;
					case 3:
						System.out.println("Enter new price");
						int newprice = sc.nextInt();
						PreparedStatement ps3 = con.prepareStatement("update products set price=? where productid=?");
						ps3.setInt(1, newprice);
						ps3.setInt(2, pid);
						ps3.executeUpdate();
						break;
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	public ArrayList<Product> getAllProducts() {
		ArrayList<Product> pr = new ArrayList<Product>();
		// TODO Auto-generated method stub
		try {
			// step 1 load driver
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hsbc", "root", "Iamsql");

			PreparedStatement ps = con.prepareStatement("select * from products");
		
			ResultSet res = ps.executeQuery();

			while(res.next()) {
				Product cc = new Product();
				cc.setId(res.getInt(1));
				cc.setName(res.getString(2));
				cc.setDesc(res.getString(3));
				cc.setPrice(res.getInt(4));
				pr.add(cc);
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return pr;
	}

	public ArrayList<Product> getProductsByPrice(int price) {
		// TODO Auto-generated method stub
		ArrayList<Product> pr = new ArrayList<Product>();
		// TODO Auto-generated method stub
		try {
			// step 1 load driver
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hsbc", "root", "Iamsql");

			PreparedStatement ps = con.prepareStatement("select * from products where price=?");
			ps.setInt(1, price);
			ResultSet res = ps.executeQuery();

			while(res.next()) {
				Product cc = new Product();
				cc.setId(res.getInt(1));
				cc.setName(res.getString(2));
				cc.setDesc(res.getString(3));
				cc.setPrice(res.getInt(4));
				pr.add(cc);
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return pr;
	}

	public void addTwoProducts(Product pr1, Product pr2) throws SQLException, ProductAlreadyExistsException {
		// TODO Auto-generated method stub
		try {
			// step 1 load driver
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hsbc", "root", "Iamsql");
			con.setAutoCommit(false);
			//for product-1
			PreparedStatement ps = con.prepareStatement("select * from products where productid=?");
			ps.setInt(1, pr1.getId());
			
			ResultSet res = ps.executeQuery();
			if(res.next()) {

				if(pr1.getId()==res.getInt(1)) {
					throw new ProductAlreadyExistsException();
				
				}
			}
			
			PreparedStatement ps1 = con.prepareStatement("insert into products values(?,?,?,?)");
			ps1.setInt(1, pr1.getId());
			ps1.setString(2, pr1.getName());
			ps1.setString(3, pr1.getDesc());
			ps1.setInt(4, pr1.getPrice());
			ps1.executeUpdate();
			
			//for product-2
			PreparedStatement ps0 = con.prepareStatement("select * from products where productid=?");
			ps0.setInt(1, pr1.getId());
			
			ResultSet res0 = ps.executeQuery();
			if(res0.next()) {

				if(pr2.getId()==res0.getInt(1)) {
					throw new ProductAlreadyExistsException();
				
				}
			}
			
			PreparedStatement ps2 = con.prepareStatement("insert into products values(?,?,?,?)");
			ps2.setInt(1, pr2.getId());
			ps2.setString(2, pr2.getName());
			ps2.setString(3, pr2.getDesc());
			ps2.setInt(4, pr2.getPrice());
			ps2.executeUpdate();

			con.commit();
			

		} catch (SQLException | ClassNotFoundException e) {
	         System.err.print("Transaction is being rolled back");
			con.rollback();
			e.printStackTrace();
		}
		
	}
	
}
