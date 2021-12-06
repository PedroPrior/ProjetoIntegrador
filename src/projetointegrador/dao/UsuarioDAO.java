/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetointegrador.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import projetointegrador.model.Usuarios;
import java.awt.HeadlessException;
import java.awt.List;
import java.sql.*;
import javax.swing.JOptionPane;
import projetointegrador.jdbc.ConnectionFactory;
import projetointegrador.model.Enderecos;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author pedro
 */
public class UsuarioDAO {
    
    
     // Atributos
    private Connection conexao;
    
    // Conexão com o Banco de Dados
    public UsuarioDAO() {
        this.conexao = ConnectionFactory.getConnection();
    }
    
     public void cadastrarUsuarios (Usuarios obj){
        try {
            //2º passo: criar uma string de comando SQL
            String sql = "insert into tb_usuarios(login, senha)"
                    + "values (?,?)";
            
            //3º passo: preparar o comando SQL com a classe PreparedStament
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, obj.getLogin());
            comando.setString(2, obj.getSenha());
           
            
            
            //4º passo: executar o comando sql e fechar a conexão
            comando.execute();
            comando.close();
            
            //Se chegar aqui o cadastro foi efetuado com sucesso
            JOptionPane.showMessageDialog(null, "Cadastro de usuários efetuado com sucesso");
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
     public void logarUsuarios (Usuarios obj){
        try {
            //2º passo: criar uma string de comando SQL
            String sql = "SELECT * FROM tb_usuarios";
                 
            
            //3º passo: preparar o comando SQL com a classe PreparedStament
            PreparedStatement comando = conexao.prepareStatement(sql);
            
           
            
            
            //4º passo: executar o comando sql e fechar a conexão
            comando.execute();
            comando.close();
            
            //Se chegar aqui o cadastro foi efetuado com sucesso
            JOptionPane.showMessageDialog(null, "Login efetuado com sucesso!");
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    

     
     
}
