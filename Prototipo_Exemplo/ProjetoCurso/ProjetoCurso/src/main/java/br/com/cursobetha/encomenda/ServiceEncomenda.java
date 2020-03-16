/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cursobetha.encomenda;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joesu
 */
public class ServiceEncomenda {

    public HttpServletRequest requisicao;
    public HttpServletResponse resposta;
    public String acao;
    private EncomendaDao encomendaDao;

    public ServiceEncomenda(HttpServletRequest requisicao, HttpServletResponse resposta) throws ClassNotFoundException, SQLException, IOException {
        this.requisicao = requisicao;
        this.resposta = resposta;
        this.acao = requisicao.getParameter("acao").toLowerCase();
        encomendaDao = new EncomendaDao(this.requisicao, this.resposta);
    }

    public void GetServiceEncomenda() throws IOException, ServletException, ClassNotFoundException, IllegalAccessException, SQLException {
        switch (this.acao) {
            case "allencomendas":
                String pesquisa = this.requisicao.getParameter("pesquisa");

//                this.resposta.getWriter().println("TESTE");
                this.resposta.getWriter().println(encomendaDao.getAllEncomendas(pesquisa));

                break;
            case "allitens":
                
//                this.resposta.getWriter().println("TESTE");
                this.resposta.getWriter().println(encomendaDao.getAllItensEncomendas());

                break;
        }
    }

    public void PostServiceEncomenda() throws IOException, ServletException, IllegalAccessException, SQLException, ClassNotFoundException {
        switch (this.acao) {
            case "cadastrar":
//                return "as";
                
//                this.resposta.getWriter().println(">>");
                this.resposta.getWriter().println(this.encomendaDao.gravarEncomenda());
                break;

            case "atualizar":
                this.resposta.getWriter().println(this.encomendaDao.atualizarEncomenda());
                break;

            case "remover":
                this.resposta.getWriter().println(this.encomendaDao.removerEncomenda());
                break;

        }
    }

}