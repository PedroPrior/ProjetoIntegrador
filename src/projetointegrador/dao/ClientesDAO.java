/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetointegrador.dao;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import projetointegrador.jdbc.ConnectionFactory;
import projetointegrador.model.Clientes;



/**
 *
 * @author pedro.ferreira5
 */
public class ClientesDAO {
    
    // Atributos
    private Connection conexao;
    
    // Conexão com o Banco de Dados
    public ClientesDAO() {
        this.conexao = ConnectionFactory.getConnection();
    }
    
     public void cadastrarClientes (Clientes obj){
        try {
            //2º passo: criar uma string de comando SQL
            String sql = "insert into clientes(nome, cpf, telefone, email)"
                    + "values (?,?,?,?)";
            
            //3º passo: preparar o comando SQL com a classe PreparedStament
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, obj.getNome());
            comando.setString(2, obj.getCpf());
            comando.setString(3, obj.getTelefone());
            comando.setString(4, obj.getEmail());
           
            
            //4º passo: executar o comando sql e fechar a conexão
            comando.execute();
            comando.close();
            
            // Atribuir ID ao Cliente
            sql = "select id from clientes where cpf=?";
            comando = conexao.prepareStatement(sql);
            comando.setString(1, obj.getCpf());
            
            // Com o comando preparado, executa o comando
            // Esse comando é de letura do banco do BD, logo ele retorna um ResultSet
            
            ResultSet resultado = comando.executeQuery();
            
            while(resultado.next()) {
                obj.setId(resultado.getInt("id"));
            }
            
            JOptionPane.showMessageDialog(null, "Cadastro de cliente efetuado com sucesso!");
            
            
            //Se chegar aqui o cadastro foi efetuado com sucesso
            JOptionPane.showMessageDialog(null, "Cadastro de cliente efetuado com sucesso");
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
     }
     
     public java.util.List<Clientes> listarClientes() {
        try {
            java.util.List<Clientes> listaClientes = new ArrayList<>();
            

            String sql = "select * from clientes";
            
            
            PreparedStatement comando = conexao.prepareStatement(sql);
           
            ResultSet rs = comando.executeQuery();
            
            while(rs.next()){ //Enquanto(while) conseguir percorrer o próximo (next) item do ResultSet
                //É preciso criar um objeto (obj) do modelo de endereços para 
                //cada item que retorn do ResultSet;
                Clientes obj = new Clientes();
                
                //Nesse objeto preciso salvar cada atributo dos campos do ResultSet
                //em um atributo do objeto do tipo enderecos
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCpf(rs.getString("Bairro"));
                obj.setTelefone(rs.getString("Rua"));
                obj.setEmail(rs.getString("Complemento"));
                
                System.out.println("OBJETO: " + obj);
                
                //Após todos os atributos serem inserido dentro do objeto
                //preciso adicionar esse objeto na minha lista de enderecos
                listaClientes.add(obj);       
            }
           
            return listaClientes;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
 }
}

        
       

