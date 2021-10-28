/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetointegrador.dao;

import java.awt.HeadlessException;
import java.sql.*;
import javax.swing.JOptionPane;
import projetointegrador.jdbc.ConnectionFactory;
import projetointegrador.model.Enderecos;

public class EnderecosDAO {
    //Atributos
    private Connection conexao;
    
    //Construtor
    public EnderecosDAO(){
        //1º passo: disponibilizar uma conexão com o BD
        this.conexao = ConnectionFactory.getConnection();
    }
    
    public void cadastrarEndereco(Enderecos obj){
        try {
            //2º passo: criar uma string de comando SQL
            String sql = "insert into tb_enderecos(cep, bairro, rua, complemento, cidade, uf, numero)"
                    + "values (?,?,?,?,?,?,?)";
            
            //3º passo: preparar o comando SQL com a classe PreparedStament
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, obj.getCep());
            comando.setString(2, obj.getBairro());
            comando.setString(3, obj.getRua());
            comando.setString(4, obj.getComplemento());
            comando.setString(5, obj.getCidade());
            comando.setString(6, obj.getUF ());
            comando.setInt(7, obj.getNumero());
            
            //4º passo: executar o comando sql e fechar a conexão
            comando.execute();
            comando.close();
            
            //Se chegar aqui o cadastro foi efetuado com sucesso
            JOptionPane.showMessageDialog(null, "Cadastro de endereço efetuado com sucesso");
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
}
