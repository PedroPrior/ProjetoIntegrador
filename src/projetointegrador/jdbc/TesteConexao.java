/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetointegrador.jdbc;

import javax.swing.JOptionPane;

/**
 *
 */
public class TesteConexao {
    public static void main(String[] args) {
        try {
            ConnectionFactory.getConnection();
            JOptionPane.showMessageDialog(null, "Conexão criada com sucesso.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
