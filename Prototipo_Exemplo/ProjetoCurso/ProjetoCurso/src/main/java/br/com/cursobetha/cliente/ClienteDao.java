/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cursobetha.cliente;

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
public class ClienteDao {

    public HttpServletRequest requisicao;
    public HttpServletResponse resposta;
    private Banco banco;

    /**
     *
     * @param requisicao
     * @param resposta
     */
    public ClienteDao(HttpServletRequest requisicao, HttpServletResponse resposta) throws ClassNotFoundException, SQLException, IOException {
        this.requisicao = requisicao;
        this.resposta = resposta;

        banco = new Banco();

    }

    public String getAllClientes(String pesquisa) throws IllegalAccessException, SQLException, ClassNotFoundException {
        String where = "";
        StringBuilder json = new StringBuilder();

        if (!pesquisa.isEmpty()) {
            where = "lower(nome) like '%" + pesquisa.toLowerCase() + "%'";
        }

        ResultSet rs = banco.consultar("cliente", where);

        while (rs.next()) {
            Cliente cli = new Cliente();
            cli.setId(rs.getLong("id"));
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

            json.append(UtilsBanco.gerarJson(cli));
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
        ResultSet rs = banco.query("select nextval('seq_cliente')");
        rs.next();
        return rs.getLong(1);
    }

    public String gravarCliente() throws IllegalAccessException, SQLException, ClassNotFoundException {
        Cliente cliente = new Cliente();

        cliente.setId( this.getNovoId() );
        cliente.setNome(this.requisicao.getParameter("nome"));
        cliente.setBairro(this.requisicao.getParameter("bairro"));
        cliente.setEndereco(this.requisicao.getParameter("endereco"));
        cliente.setCep(this.requisicao.getParameter("cep"));
        cliente.setCidade(this.requisicao.getParameter("cidade"));
        cliente.setEstado(this.requisicao.getParameter("estado"));
        cliente.setTelefone(this.requisicao.getParameter("telefone"));
        cliente.setCelular(this.requisicao.getParameter("celular"));
        cliente.setEmail(this.requisicao.getParameter("email"));
        cliente.setObservacao(this.requisicao.getParameter("observacao"));
        
        return banco.inserir(cliente);
    }

    public String atualizarCliente() throws SQLException {
        Cliente cliente = new Cliente();

        cliente.setId(Long.parseLong(this.requisicao.getParameter("id")));
        cliente.setNome(this.requisicao.getParameter("nome"));
        cliente.setBairro(this.requisicao.getParameter("bairro"));
        cliente.setEndereco(this.requisicao.getParameter("endereco"));
        cliente.setCep(this.requisicao.getParameter("cep"));
        cliente.setCidade(this.requisicao.getParameter("cidade"));
        cliente.setEstado(this.requisicao.getParameter("estado"));
        cliente.setTelefone(this.requisicao.getParameter("telefone"));
        cliente.setCelular(this.requisicao.getParameter("celular"));
        cliente.setEmail(this.requisicao.getParameter("email"));
        cliente.setObservacao(this.requisicao.getParameter("observacao"));

        return banco.atualizar(cliente, cliente.getId());
    }

    public String removerCliente() throws SQLException {
        return banco.remover("cliente", this.requisicao.getParameter("id"));
    }

}
