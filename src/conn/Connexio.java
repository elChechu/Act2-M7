/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conn;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author rvallez
 */
public class Connexio {
    	
    public static Connection conectar() throws ClassNotFoundException {
		Connection con = null;
 
		try {
                        Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://79.137.83.198:3306/sakila?user=ferreria&password=Ferreria29!";
			con = DriverManager.getConnection(url);
			if (con != null) {
				System.out.println("Conexió ok");
			}
 
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return con;
	}

    public Statement createStatement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
