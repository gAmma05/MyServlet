/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Category;
import entities.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author vothimaihoa
 */
public class ProductDAO {

    private String constructSearchQuery(String productName, Integer categoryId, String minPrice, String maxPrice) {
        String sql = " SELECT p.id, p.name, p.price, p.product_year, p.image, p.category_id, c.name as category_name "
                + " from Product p join Category c on p.category_id = c.id where 1 = 1 ";
        if (categoryId != null) {
            sql += " and p.category_id = ? ";
        }
        if (productName != null && !productName.trim().isEmpty()) {
            sql += " and p.name like ? ";
        }
        if (minPrice != null && !minPrice.isEmpty()) {
            sql += " and p.price >= ?";
        }
        if (maxPrice != null && !maxPrice.isEmpty()) {
            sql += " and p.price <= ?";
        }

        return sql;
    }

    public List<Product> list(String productName, Integer categoryId, String minPrice, String maxPrice) {
        List<Product> list = null;
        String sql = constructSearchQuery(productName, categoryId, minPrice, maxPrice);
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            // set params value to query
            int paramIndex = 1;
            if (categoryId != null) {
                ps.setInt(paramIndex++, categoryId);
            }
            if (productName != null
                    && !productName.trim().isEmpty()) {
                ps.setString(paramIndex++, "%" + productName + "%");
            }

            if (minPrice != null && !minPrice.isEmpty()) {
                ps.setFloat(paramIndex++, Float.parseFloat(minPrice));
            }
            if (maxPrice != null && !maxPrice.isEmpty()) {
                ps.setDouble(paramIndex++, Float.parseFloat(maxPrice));
            }

            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                list = new ArrayList<>();
                while (rs.next()) {
                    Product p = new Product();
                    p.setId(rs.getInt("id"));
                    p.setName(rs.getString("name"));
                    p.setPrice(rs.getFloat("price"));
                    p.setProductYear(rs.getInt("product_year"));
                    p.setImage(rs.getString("image"));

                    Category category = new Category(rs.getInt("category_id"), rs.getString("category_name"));
                    p.setCategory(category);
                    list.add(p);
                }
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            System.out.println("DBUtils not found.");
        } catch (SQLException ex) {
            System.out.println("SQL Exception in getting list of products. Details: ");
            ex.printStackTrace();
        }
        return list;
    }

    public Product getById(int id) {
        Product p = null;
        String sql = " SELECT p.id, p.name, price, product_year, image, p.category_id, c.name as category_name "
                + " from Product p join Category c on p.category_id = c.id "
                + " where p.id = ?";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                if (rs.next()) {
                    p = new Product();
                    p.setId(rs.getInt("id"));
                    p.setName(rs.getString("name"));
                    p.setPrice(rs.getFloat("price"));
                    p.setProductYear(rs.getInt("product_year"));
                    p.setImage(rs.getString("image"));

                    Category category = new Category(rs.getInt("category_id"), rs.getString("category_name"));
                    p.setCategory(category);
                }
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            System.out.println("DBUtils not found.");
        } catch (SQLException ex) {
            System.out.println("SQL Exception in getting product by id. Details: ");
            ex.printStackTrace();
        }
        return p;
    }

    public boolean create(Product newProduct) {
        boolean status = false;
        String sql = "INSERT INTO Product(name, price, product_year, image, category_id) "
                + " VALUES(?, ?, ?, ?, ?)";

        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, newProduct.getName());
            ps.setFloat(2, newProduct.getPrice());
            ps.setInt(3, newProduct.getProductYear());
            ps.setString(4, newProduct.getImage());
            ps.setInt(5, newProduct.getCategory().getId());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                status = true;
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            System.out.println("DBUtils not found!");
        } catch (SQLException ex) {
            System.out.println("SQL Exception in inserting new product. Details: ");
            ex.printStackTrace();
        }
        return status;
    }
    String error;
    
    
    public boolean update(Product updProduct){
        boolean status = false;
        String sql = "UPDATE Product SET name = ?, price = ?, product_year = ?, image = ?, category_id = ? WHERE id = ?";
        try{
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, updProduct.getName());
            ps.setFloat(2, updProduct.getPrice());
            ps.setInt(3, updProduct.getProductYear());
            ps.setString(4, updProduct.getImage());
            ps.setInt(5, updProduct.getCategory().getId());
            ps.setInt(6, updProduct.getId());
            int rows = ps.executeUpdate();
            if(rows > 0){
                status = true;
            }
            
        } catch (ClassNotFoundException ex) {
            System.out.println("DBUtils not found!");
        } catch (SQLException ex) {
            System.out.println("SQL Exception in updating an existing product. Details: ");
            System.out.println(ex.getMessage());
            
        }
        return status;
    }
    
    public boolean delete(int productId){
        boolean status = false;
        String sql = "DELETE FROM Product WHERE id = ?";
        try{
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, productId);
            
            int rows = ps.executeUpdate();
            if(rows > 0){
                status = true;
            }
        }catch(ClassNotFoundException ex){
            System.out.println("DBUtils not found");
        }catch (SQLException ex) {
            System.out.println("SQL Exception in updating an existing product. Details: ");
            System.out.println(ex.getMessage());
            
        }
        return status;
    }
}
