/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cursobetha.cliente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joesu
 */
public class ServiceCliente {

    public HttpServletRequest requisicao;
    public HttpServletResponse resposta;
    public String acao;
    private ClienteDao clienteDao;

    public ServiceCliente(HttpServletRequest requisicao, HttpServletResponse resposta) throws ClassNotFoundException, SQLException, IOException {
        this.requisicao = requisicao;
        this.resposta = resposta;
        this.acao = requisicao.getParameter("acao").toLowerCase();
        clienteDao = new ClienteDao(this.requisicao, this.resposta);
    }

    public void GetServiceCliente() throws IOException, ServletException, ClassNotFoundException, IllegalAccessException, SQLException {
        switch (this.acao) {
            case "allclientes":
                String pesquisa = this.requisicao.getParameter("pesquisa");

//                this.resposta.getWriter().println("TESTE");
                this.resposta.getWriter().println(clienteDao.getAllClientes(pesquisa));

                break;
        }
    }

    public void PostServiceCliente() throws IOException, ServletException, IllegalAccessException, SQLException, ClassNotFoundException {
        switch (this.acao) {
            case "cadastrar":
                this.resposta.getWriter().println(this.clienteDao.gravarCliente());
                break;

            case "atualizar":
                this.resposta.getWriter().println(this.clienteDao.atualizarCliente());
                break;

            case "remover":
                this.resposta.getWriter().println(this.clienteDao.removerCliente());
                break;

        }
    }

}
