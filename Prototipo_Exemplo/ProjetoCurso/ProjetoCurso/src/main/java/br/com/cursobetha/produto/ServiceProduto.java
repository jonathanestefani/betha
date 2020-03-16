/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cursobetha.produto;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joesu
 */
public class ServiceProduto {

    public HttpServletRequest requisicao;
    public HttpServletResponse resposta;
    public String acao;
    private ProdutoDao produtoDao;

    public ServiceProduto(HttpServletRequest requisicao, HttpServletResponse resposta) throws ClassNotFoundException, SQLException, IOException {
        this.requisicao = requisicao;
        this.resposta = resposta;
        this.acao = requisicao.getParameter("acao").toLowerCase();
        produtoDao = new ProdutoDao(this.requisicao, this.resposta);
    }

    public void GetServiceProduto() throws IOException, ServletException, ClassNotFoundException, IllegalAccessException, SQLException {
        switch (this.acao) {
            case "allprodutos":
                String pesquisa = this.requisicao.getParameter("pesquisa");

//                this.resposta.getWriter().println("TESTE");
                this.resposta.getWriter().println(produtoDao.getAllProdutos(pesquisa));

                break;
        }
    }

    public void PostServiceProduto() throws IOException, ServletException, IllegalAccessException, SQLException, ClassNotFoundException {
        switch (this.acao) {
            case "cadastrar":
                this.resposta.getWriter().println(this.produtoDao.gravarProduto());
                break;

            case "atualizar":
                this.resposta.getWriter().println(this.produtoDao.atualizarProduto());
                break;

            case "remover":
                this.resposta.getWriter().println(this.produtoDao.removerProduto());
                break;

        }
    }

}
