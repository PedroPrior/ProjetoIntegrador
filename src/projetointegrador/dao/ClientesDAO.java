/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetointegrador.dao;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import projetointegrador.jdbc.ConnectionFactory;

import projetointegrador.model.Clientes;


public class ClientesDAO {
    //Atributos

    private Connection conexao;

    //Construtor
    public ClientesDAO() {
        //1º passo: disponibilizar uma conexão com o BD
        this.conexao = ConnectionFactory.getConnection();
    }

    public void cadastrarCliente(Clientes obj) {
        try {
            //2º passo: criar uma string de comando SQL
            String sql = "insert into tb_clientes(nome, email, cpf, telefone)"
                    + "values (?,?,?,?)";

            //3º passo: preparar o comando SQL para o driver
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, obj.getNome());
            comando.setString(2, obj.getEmail());
            comando.setString(3, obj.getCpf());
            comando.setString(4, obj.getTelefone());

            //4º passo: executar o comando sql e fechar a conexão
            comando.execute();
            comando.close();

            //5º passo: pegar o id gerado pelo banco de dados através do CPF
            sql = "select id from tb_clientes where cpf=?";
            comando = conexao.prepareStatement(sql);
            comando.setString(1, obj.getCpf());

            //Com o comando preparado, executo o comando
            //Esse comando é de leitura do BD, logo ele retorna um ResultSet
            ResultSet resultado = comando.executeQuery();

            //Percorro o resultado até achar o campo "id"
            while (resultado.next()) {
                obj.setId(resultado.getInt("id"));
            }
            //Sevchegar aqui mostra a janela de cadastro com sucesso;
            JOptionPane.showMessageDialog(null, "Cadastro de cliente efetuado com sucesso!");

        } catch (SQLException e) {
        }
    }

    public List<Clientes> listarClientes() {
        try {
            //1º passo: criar uma lista para armazenar os clientes
            List<Clientes> lista = new ArrayList<>();

            //2º passo: criar o comando sql que seleciona todos os itens da
            //tabela de endereços
            String sql = "select * from tb_clientes";

            //3º passo: preparar o comando colocando na conexao que será
            //utilizada para executá-lo no BD
            PreparedStatement comando = conexao.prepareStatement(sql);

            //4º passo: quando usamos JDBC, o resultado de um comando select 
            //precisa ser armazenado em um objeto do tipo ResultSet
            ResultSet rs = comando.executeQuery();

            //5º passo: criar um laço de repetição para adicionar os itens do
            //ResultSet na lista criada no primeiro passo.
            while (rs.next()) { //Enquanto(while) conseguir percorrer o próximo (next) item do ResultSet
                //É preciso criar um objeto (obj) do modelo de endereços para 
                //cada item que retorn do ResultSet;
                Clientes obj = new Clientes();

                //Nesse objeto preciso salvar cada atributo dos campos do ResultSet
                //em um atributo do objeto do tipo enderecos
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));

                //Após todos os atributos serem inserido dentro do objeto
                //preciso adicionar esse objeto na minha lista de enderecos
                lista.add(obj);
            }
            //6º passo: após a lista ser criada, agora eu retorno como resultado
            // do meu método a lista pronta.
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    public void deletarCliente(Clientes obj) {
        try {
            int opcao = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o cliente?", "CONFIRMAÇÃO DE EXCLUSÃO", JOptionPane.OK_CANCEL_OPTION);
            if (opcao == 0) {
                //2º passo: criar uma string de comando SQL
                String sql = "delete from tb_clientes where id=?";

                //3º passo: preparar o comando SQL para o driver
                PreparedStatement comando = conexao.prepareStatement(sql);
                comando.setInt(1, obj.getId());

                //4º passo: executar o comando sql e fechar a conexão
                comando.execute();
                comando.close();

                //Se chegar aqui mostra a janela de exclusao com sucesso;
                JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void atualizarCliente(Clientes obj) {
        try {
            int opcao = JOptionPane.showConfirmDialog(null, "Deseja realmente atualizar o cliente?", "CONFIRMAÇÃO DE ATUALIZAÇÃO", JOptionPane.OK_CANCEL_OPTION);
            if (opcao == 0) {
                //2º passo: criar uma string de comando SQL
                String sql = "update tb_clientes set nome=?, email=?, cpf=?, telefone=? where id=?";

                //3º passo: preparar o comando SQL para o driver
                PreparedStatement comando = conexao.prepareStatement(sql);
                comando.setString(1, obj.getNome());
                comando.setString(2, obj.getEmail());
                comando.setString(3, obj.getCpf());
                comando.setString(4, obj.getTelefone());
                comando.setInt(5, obj.getId());

                //4º passo: executar o comando sql e fechar a conexão
                comando.execute();
                comando.close();

                //Se chegar aqui mostra a janela de cadastro com sucesso;
                JOptionPane.showMessageDialog(null, "Cadastro de cliente atualizado com sucesso!");
            }
        } catch (SQLException e) {
        }
    }

     public List<Clientes> buscarClientes(String valorDeBusca) {
        try {
            //1º passo: criar uma lista para armazenar os clientes
            List<Clientes> lista = new ArrayList<>();

            //2º passo: criar o comando sql que seleciona todos os itens da
            //tabela de endereços
            String sql = "select * from tb_clientes where concat_ws(id,nome,cpf,email,telefone) like ?";

            //3º passo: preparar o comando colocando na conexao que será
            //utilizada para executá-lo no BD
            PreparedStatement comando = conexao.prepareStatement(sql);
            comando.setString(1, "%"+ valorDeBusca+ "%");

            //4º passo: quando usamos JDBC, o resultado de um comando select 
            //precisa ser armazenado em um objeto do tipo ResultSet
            ResultSet rs = comando.executeQuery();

            //5º passo: criar um laço de repetição para adicionar os itens do
            //ResultSet na lista criada no primeiro passo.
            while (rs.next()) { //Enquanto(while) conseguir percorrer o próximo (next) item do ResultSet
                //É preciso criar um objeto (obj) do modelo de endereços para 
                //cada item que retorn do ResultSet;
                Clientes obj = new Clientes();

                //Nesse objeto preciso salvar cada atributo dos campos do ResultSet
                //em um atributo do objeto do tipo enderecos
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));

                //Após todos os atributos serem inserido dentro do objeto
                //preciso adicionar esse objeto na minha lista de enderecos
                lista.add(obj);
            }
            //6º passo: após a lista ser criada, agora eu retorno como resultado
            // do meu método a lista pronta.
            return lista;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
