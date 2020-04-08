/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author HP
 */
public class koneksi {
    String host ="127.0.0.1";
    String db ="test";
    String username ="root";
    String password ="";
    String driver ="com.mysql.jdbc.Driver"; //driver database
    //jdbc:mysql://127.0.0.1/crud?user=root$password=
    String connect ="jdbc:mysql://" + host + "/" + db +"?user="+ username + "&password="+ password;
    Connection conn;
    Statement stmt;
    ResultSet rs;

    public koneksi() {
    }
    
    public koneksi(String _host, 
            String _db, 
            String _username,
            String _password){
        this.host = _host;
        this.db = _db;
        this.username = _username;
        this.password = _password;
    }
    public  boolean connect(){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(connect);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            System.out.println("koneksi berhasil");
            return  true;
        } catch (Exception e) {
            System.out.println("koneksi database gagal"+e.getMessage());
            return false;
        }
    }
    public boolean close(){
        try {
            conn.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean executeQuery(String _q){
        try {
            stmt.execute(_q);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public ResultSet showQuery(String _sql){
        try {
            rs =stmt.executeQuery(_sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }
}
