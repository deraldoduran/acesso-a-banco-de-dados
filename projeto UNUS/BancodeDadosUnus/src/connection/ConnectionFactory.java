/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duran
 */
public class ConnectionFactory {
    private static final String DRIVER="com.mysql.jdbc.Driver";
    private static final String URL="jdbc:mysql://localhorst:3306/cadastro";
    private static final String USER="root";
    private static final String PASS="";
    
    public static  Connection getConnection() throws SQLException {
        
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("não houve conexão", ex);
        }
             
        
    }
    
    public static void closeConnection(Connection con){
       
            try {
                if(con!=null){
                con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
     public static void closeConnection(Connection con, PreparedStatement stmt ){
         closeConnection(con);
         
            try {
                if(stmt!=null){
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
      public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs  ){
         //closeConnection(con);
          closeConnection(con, stmt);
         
            try {
                if(rs!=null){
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }

   // private static Exception RuntimeException(String erro_na_conexão, java.sql.SQLException | java.lang.ClassNotFoundException ex) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}
}
