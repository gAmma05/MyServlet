/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;
import utils.enums.AccountRole;

/**
 *
 * @author vothimaihoa
 */
public class AccountDAO {
    
    public boolean create(Account account, int role){
        boolean status = false;
        String sql = "INSERT INTO Account(username, password, role) VALUES(?, ?, ?)";
        try{
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());
            ps.setInt(3, role);
            int rows = ps.executeUpdate();
            if(rows > 0){
                status = true;
            }
        }catch (ClassNotFoundException ex) {
            System.out.println("DBUtils not found.");
        } catch (SQLException ex) {
            System.out.println("SQL Exception in getting product by id. Details: ");
            ex.printStackTrace();
        }
        return status;
    }

    public Account getById(int id) {
        Account a = null;
        String sql = " SELECT id, username, role "
                + " from Account a "
                + " where id = ?";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                if (rs.next()) {
                    a = new Account();
                    a.setId(rs.getInt("id"));
                    a.setUsername(rs.getString("username"));
                    a.setRole((rs.getInt("role") == 1 ? AccountRole.STAFF : AccountRole.CUSTOMER));
                }
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            System.out.println("DBUtils not found.");
        } catch (SQLException ex) {
            System.out.println("SQL Exception in getting product by id. Details: ");
            ex.printStackTrace();
        }
        return a;
    }

    public Account getByUsernamePassword(String username, String password) {
        Account a = null;
        String sql = " SELECT id, username, role "
                + " from Account a "
                + " where username = ? AND password = ?";
        try {
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                if (rs.next()) {
                    a = new Account();
                    a.setId(rs.getInt("id"));
                    a.setUsername(rs.getString("username"));
                    a.setRole((rs.getInt("role") == 1 ? AccountRole.STAFF : AccountRole.CUSTOMER));
                }
            }
            con.close();
        } catch (ClassNotFoundException ex) {
            System.out.println("DBUtils not found.");
        } catch (SQLException ex) {
            System.out.println("SQL Exception in getting product by id. Details: ");
            ex.printStackTrace();
        }
        return a;
    }
    
    public List<String> getAllAccount(){
        List<String> usernameList = new ArrayList<>();
        String sql = "SELECT username FROM Account";
        try{
            Connection con = DBUtils.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                usernameList.add(rs.getString("username"));
            }
        }catch (ClassNotFoundException ex) {
            System.out.println("DBUtils not found.");
        } catch (SQLException ex) {
            System.out.println("SQL Exception in getting product by id. Details: ");
            ex.printStackTrace();
        }
        return usernameList;
        //return null;
    }
}
