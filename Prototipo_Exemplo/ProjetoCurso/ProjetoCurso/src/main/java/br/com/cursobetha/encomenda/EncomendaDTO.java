/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cursobetha.encomenda;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author joesu
 */
public class EncomendaDTO {

    public Encomenda getRsToEncomenda(ResultSet rs) throws IllegalAccessException, SQLException {
        Encomenda pro = new Encomenda();

        pro.setId( rs.getLong("id") );
        pro.setIdCliente(Long.parseLong(rs.getString("idCliente")));
        pro.setObservacao(rs.getString("observacao"));
        pro.setPreco(rs.getDouble("preco"));

        return pro;
    }

}
