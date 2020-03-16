/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cursobetha.produto;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author joesu
 */
public class ProdutoDTO {
    
    public Produto getRsToProduto(ResultSet rs) throws IllegalAccessException, SQLException {
        Produto pro = new Produto();

        pro.setId( rs.getLong("id") );
        pro.setNome(rs.getString("nome"));
        pro.setBarcode(rs.getString("barcode"));
        pro.setDescricao(rs.getString("descricao"));
        pro.setPreco(rs.getDouble("preco"));

        return pro;
    }
    
}
