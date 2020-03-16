/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cursobetha.encomenda;

import br.com.cursobetha.banco.Banco;
import br.com.cursobetha.banco.UtilsBanco;
import br.com.cursobetha.encomenda.EncomendaItens;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joesu
 */
public class EncomendaDao {

    public HttpServletRequest requisicao;
    public HttpServletResponse resposta;
    private Banco banco;

    /**
     *
     * @param requisicao
     * @param resposta
     */
    public EncomendaDao(HttpServletRequest requisicao, HttpServletResponse resposta) throws ClassNotFoundException, SQLException, IOException {
        this.requisicao = requisicao;
        this.resposta = resposta;

        banco = new Banco();

    }

    public String getAllEncomendas(String pesquisa) throws IllegalAccessException, SQLException, ClassNotFoundException {
        String where = "";
        StringBuilder json = new StringBuilder();

        if (!pesquisa.isEmpty()) {
            where = "lower(nome_cliente) like '%" + pesquisa.toLowerCase() + "%' or lower(nome_produto) like '%" + pesquisa.toLowerCase() + "%'";
        }

        ResultSet rs = banco.consultar("vw_encomenda", where);

        while (rs.next()) {
            VwEncomenda enc = new VwEncomenda();
            enc.setId(rs.getLong("id"));
            enc.setIdCliente(Long.parseLong(rs.getString("idcliente")));
            enc.setNome_cliente(rs.getString("nome_cliente"));
            enc.setPreco(rs.getDouble("preco"));
            enc.setObservacao(rs.getString("observacao"));

            json.append(UtilsBanco.gerarJson(enc));
            json.append(",");
        }

        if (json.length() > 0) {
            json.insert(0, "[");
            json.deleteCharAt(json.length() - 1);
            json.append("]");
        }

        return json.toString();
    }

    public String getAllItensEncomendas() throws IllegalAccessException, SQLException, ClassNotFoundException {
        String where = "";
        StringBuilder json = new StringBuilder();

        ResultSet rs = banco.consultar("vw_itens_encomenda", where);

        while (rs.next()) {
            VwItensEncomenda enc = new VwItensEncomenda();
            enc.setId(rs.getLong("id"));
            enc.setIdproduto(Long.parseLong(rs.getString("idproduto")));
            enc.setNome_produto(rs.getString("nome_produto"));
            enc.setPreco(rs.getDouble("preco"));
            enc.setQuantidade(rs.getInt("quantidade"));
            enc.setTotal(rs.getDouble("total"));

            json.append(UtilsBanco.gerarJson(enc));
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
        ResultSet rs = banco.query("select nextval('seq_encomenda')");
        rs.next();
        return rs.getLong(1);
    }

    public Long getNovoIdItens() throws IllegalAccessException, SQLException, ClassNotFoundException {
        ResultSet rs = banco.query("select nextval('seq_encomenda')");
        rs.next();
        return rs.getLong(1);
    }

    public String gravarEncomenda() throws IllegalAccessException, SQLException, ClassNotFoundException {
        Encomenda enc = new Encomenda();

//        return "ok";
        Long idEncomenda = this.getNovoId();

        enc.setId(idEncomenda);
        enc.setIdCliente(Long.parseLong(this.requisicao.getParameter("idcliente")));
        enc.setPreco(Double.parseDouble(this.requisicao.getParameter("preco").replaceAll("\\.", "").replace(",", ".")));
        enc.setObservacao(this.requisicao.getParameter("observacao"));

        String resp = banco.inserir(enc);

        if (resp.equals("ok")) {

            for (String b : this.requisicao.getParameterMap().keySet()) {
                if (b.contains("produtos")) {
                    EncomendaItens encItens = new EncomendaItens();
                    encItens.setId(this.getNovoIdItens());
                    encItens.setIdencomenda(idEncomenda);
                    encItens.setIdproduto(Long.parseLong(b.replaceAll("[^0-9]", "").toString()));
                    encItens.setQuantidade(Integer.parseInt(this.requisicao.getParameter(b)));
                    banco.inserir(encItens);
                }
            }
        }

        return resp;
    }

    public String atualizarEncomenda() throws SQLException {
        Encomenda encomenda = new Encomenda();

        Long idEncomenda = Long.parseLong(this.requisicao.getParameter("id"));
        
        encomenda.setId(idEncomenda);
        encomenda.setIdCliente(Long.parseLong(this.requisicao.getParameter("idcliente")));
        encomenda.setPreco(Double.parseDouble(this.requisicao.getParameter("preco").replaceAll("\\.", "").replace(",", ".")));
        encomenda.setObservacao(this.requisicao.getParameter("observacao"));

        String resp = banco.atualizar(encomenda, encomenda.getId());
        
//        if (resp.equals("ok")) {
//
//            for (String b : this.requisicao.getParameterMap().keySet()) {
//                if (b.contains("produtos")) {
//                    EncomendaItens encItens = new EncomendaItens();
//                    encItens.setId(this.getNovoIdItens());
//                    encItens.setIdencomenda(idEncomenda);
//                    encItens.setIdproduto(Long.parseLong(b.replaceAll("[^0-9]", "").toString()));
//                    encItens.setQuantidade(Integer.parseInt(this.requisicao.getParameter(b)));
//                    banco.atualizar(encItens, );
//                }
//            }
//        }

        return resp;
    }

    public String removerEncomenda() throws SQLException {
        return banco.remover("encomenda", this.requisicao.getParameter("id"));
    }

}
