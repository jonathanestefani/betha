/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cursobetha.cliente;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author joesu
 */
public class ClienteDTO {
    
    public Cliente getRsToCliente(ResultSet rs) throws IllegalAccessException, SQLException {
        Cliente cli = new Cliente();

        cli.setId( rs.getLong("id") );
        cli.setNome(rs.getString("nome"));
        cli.setEndereco(rs.getString("endereco"));
        cli.setBairro(rs.getString("bairro"));
        cli.setCep(rs.getString("cep"));
        cli.setCidade(rs.getString("cidade"));
        cli.setEstado(rs.getString("estado"));
        cli.setTelefone(rs.getString("telefone"));
        cli.setCelular(rs.getString("celular"));
        cli.setEmail(rs.getString("email"));
        cli.setObservacao(rs.getString("observacao"));

        return cli;
    }
    
}
