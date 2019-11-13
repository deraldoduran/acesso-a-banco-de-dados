/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import com.mysql.jdbc.PreparedStatement;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.beans.ProdutoBeans;
import sun.util.logging.PlatformLogger;


/**
 *
 * @author duran
 */
public class ProdutoDAO {
    public void create(ProdutoBeans p){
    Connection con = null;
        try {
            con = ConnectionFactory.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    PreparedStatement stmt = null;
    try{
    stmt = (PreparedStatement) con.prepareStatement("INSERT INTO pessoas(nome,nascimento,sexo,peso,altura,nacionalidade,profissao)"
            + " VALUES (?,?,?,?,?,?,?)");
    stmt.setString(1, p.getNome());
    stmt.setString(2,p.getNascimento());
    stmt.setString(3,p.getSexo());
    stmt.setDouble(4,p.getPeso());
    stmt.setDouble(5,p.getAltura());
    stmt.setString(6,p.getNacionalidade());
    stmt.setString(7,p.getProfissao());
    stmt.executeUpdate();
        JOptionPane.showMessageDialog(null,"salvo");
    
            }catch(SQLException ex){
                Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE,null,ex);
                JOptionPane.showMessageDialog(null, ex);
            }finally{
                ConnectionFactory.closeConnection(con,stmt);
}
}
    
    public List<ProdutoBeans> read() throws SQLException{
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt=null;
        ResultSet rs=null;
        List<ProdutoBeans> produtos=new ArrayList<>();
        
        try {
            stmt=(PreparedStatement) con.prepareStatement("SELECT * FROM cadastro");
            rs=stmt.executeQuery();
            while(rs.next()){
                ProdutoBeans produto=new ProdutoBeans();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
              //   produto.setNascimento(rs.getDate("nascimento"));
                produtos.add(produto);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        ConnectionFactory.closeConnection(con, stmt, rs);
    }
        return produtos;
    }
    
}
