
package projetointegrador.dao;

import projetointegrador.model.Enderecos;

public class TesteDAO {
    public static void main(String[] args) {
        Enderecos enderecoTeste = new Enderecos();
        
        enderecoTeste.setCep("88283924");
        enderecoTeste.setBairro("São Pedro");
        enderecoTeste.setComplemento("E");
        enderecoTeste.setCidade("Chapecó");
        enderecoTeste.setNumero(986);
        enderecoTeste.setRua("Rui Barbosa");
        enderecoTeste.setUF("SC");
        
        EnderecosDAO daoObj = new EnderecosDAO();
        daoObj.cadastrarEndereco(enderecoTeste);
        
        
    }
   
    
}
