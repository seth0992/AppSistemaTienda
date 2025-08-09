/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import connections.MySQLConnection;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.sql.*;
import java.util.Optional;

/**
 *
 * @author seth
 */
public class LoginServiceImpl implements LoginService{

    private final String SQL_LOGIN = "SELECT count(*) FROM usuarios WHERE username = ? AND password = ?;";
    
    @Override
    public Optional<String> getUsername(HttpServletRequest req) {
      
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        
        if(username != null){
            return Optional.of(username);
        }
        
        return Optional.empty();
    }

    @Override
    public boolean validateLogin(String username, String password) {
        
        try(Connection conn = MySQLConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(SQL_LOGIN)){
            
            stmt.setString(1, username);
            stmt.setString(2, password); // Deberia estar encriptada
            
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return rs.getInt(1) > 0;
                }
            }       
        
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return false;
    }
    
}
