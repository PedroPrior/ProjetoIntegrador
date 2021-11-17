/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetointegrador.dao;

import java.awt.HeadlessException;
import java.awt.List;
import java.sql.*;
import javax.swing.JOptionPane;
import projetointegrador.jdbc.ConnectionFactory;
import projetointegrador.model.Enderecos;
import java.util.ArrayList;

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
            String sql = "insert into enderecos(cep, bairro, rua, complemento, cidade, uf, numero, clientes_id_clientes)"
                    + "values (?,?,?,?,?,?,?,?)";
            
            //3º passo: preparar o comando SQL com a classe PreparedStament
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, obj.getCep());
            comando.setString(2, obj.getBairro());
            comando.setString(3, obj.getRua());
            comando.setString(4, obj.getComplemento());
            comando.setString(5, obj.getCidade());
            comando.setString(6, obj.getUF ());
            comando.setInt(7, obj.getNumero());
            comando.setInt(8, obj.getCliente().getId());
            
            //4º passo: executar o comando sql e fechar a conexão
            comando.execute();
            comando.close();
            
            //Se chegar aqui o cadastro foi efetuado com sucesso
            JOptionPane.showMessageDialog(null, "Cadastro de endereço efetuado com sucesso");
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public java.util.List<Enderecos> listarEnderecos() {
        try {
            java.util.List<Enderecos> listaEnderecos = new ArrayList<>();
            

            String sql = "select * from enderecos";
            
            
            PreparedStatement comando = conexao.prepareStatement(sql);
           
            ResultSet rs = comando.executeQuery();
            
            while(rs.next()){ //Enquanto(while) conseguir percorrer o próximo (next) item do ResultSet
                //É preciso criar um objeto (obj) do modelo de endereços para 
                //cada item que retorn do ResultSet;
                Enderecos obj = new Enderecos();
                
                //Nesse objeto preciso salvar cada atributo dos campos do ResultSet
                //em um atributo do objeto do tipo enderecos
                obj.setId(rs.getInt("id"));
                obj.setCep(rs.getString("CEP"));
                obj.setBairro(rs.getString("Bairro"));
                obj.setRua(rs.getString("Rua"));
                obj.setComplemento(rs.getString("Complemento"));
                obj.setCidade(rs.getString("Cidade"));
                obj.setUF(rs.getString("UF"));
                obj.setNumero(rs.getInt("Numero"));
                
                System.out.println("OBJETO: " + obj);
                
                //Após todos os atributos serem inserido dentro do objeto
                //preciso adicionar esse objeto na minha lista de enderecos
                listaEnderecos.add(obj);       
            }
           
            return listaEnderecos;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
  
    
}
