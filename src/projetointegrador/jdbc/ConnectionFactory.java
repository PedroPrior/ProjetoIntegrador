/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetointegrador.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    
   static public Connection getConnection(){
        try {
            //URL para conexão com o BD
            final String url = "jdbc:mysql://localhost:3306/projetointegrador"
                   
                    + "?verifyServerCertificate=false&useSSL=true";
            final String user = "root";
            final String password = "";
            
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            //Lança a exceção na tela em tempo de execução para sabermos o que aconteceu
            throw new RuntimeException(e);
        }
    }
}
