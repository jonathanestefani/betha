/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cursobetha.produto;

import br.com.cursobetha.banco.Banco;
import br.com.cursobetha.banco.UtilsBanco;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joesu
 */
public class ProdutoDao {

    public HttpServletRequest requisicao;
    public HttpServletResponse resposta;
    private Banco banco;

    /**
     *
     * @param requisicao
     * @param resposta
     */
    public ProdutoDao(HttpServletRequest requisicao, HttpServletResponse resposta) throws ClassNotFoundException, SQLException, IOException {
        this.requisicao = requisicao;
        this.resposta = resposta;

        banco = new Banco();

    }

    public String getAllProdutos(String pesquisa) throws IllegalAccessException, SQLException, ClassNotFoundException {
        String where = "";
        StringBuilder json = new StringBuilder();

        if (!pesquisa.isEmpty()) {
            where = "lower(nome) like '%" + pesquisa.toLowerCase() + "%' or lower(descricao) like '%"+ pesquisa.toLowerCase() +"%'";
        }

        ResultSet rs = banco.consultar("produto", where);

        while (rs.next()) {
            Produto pro = new Produto();
            pro.setId(rs.getLong("id"));
            pro.setNome(rs.getString("nome"));
            pro.setBarcode(rs.getString("barcode"));
            pro.setDescricao(rs.getString("descricao"));
            pro.setPreco(rs.getFloat("preco"));

            json.append(UtilsBanco.gerarJson(pro));
            json.append(",");
        }

        if (json.length() > 0) {
            json.insert(0, "[");
            json.deleteCharAt(json.length() - 1);
            json.append("]");
        }
        
        return json.toString();
    }

    public Long getNovoId() throws IllegalAccessException, SQLException, ClassNotFoundException {
        ResultSet rs = banco.query("select nextval('seq_produto')");
        rs.next();
        return rs.getLong(1);
    }

    public String gravarProduto() throws IllegalAccessException, SQLException, ClassNotFoundException {
        Produto produto = new Produto();

        //produto.setId( this.getNovoId() );
        produto.setNome(this.requisicao.getParameter("nome"));
        produto.setBarcode(this.requisicao.getParameter("barcode"));
        produto.setDescricao(this.requisicao.getParameter("descricao"));
        
        String preco = this.requisicao.getParameter("preco").replaceAll("\\.","").replace(",",".");
        
        produto.setPreco(Double.parseDouble(preco));

        return banco.inserir(produto);
    }

    public String atualizarProduto() throws SQLException {
        Produto produto = new Produto();

        produto.setId(Long.parseLong(this.requisicao.getParameter("id")));
        produto.setNome(this.requisicao.getParameter("nome"));
        produto.setBarcode(this.requisicao.getParameter("barcode"));
        produto.setDescricao(this.requisicao.getParameter("descricao"));
        
        String preco = this.requisicao.getParameter("preco").replaceAll("\\.","").replace(",",".");
        
        produto.setPreco(Double.parseDouble(preco));

        return banco.atualizar(produto, produto.getId());
    }

    public String removerProduto() throws SQLException {
        return banco.remover("produto", this.requisicao.getParameter("id"));
    }

}