/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.produto;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author joesu
 */
@Path("/produto")
@Consumes({"application/json"})
@Produces("text/json")
public class ProdutoDAO {

    private class FormProduto {

//        @FormParam("id")
        String id;
//        @FormParam("nome")
        String nome;
//        @FormParam("preco")
        String preco;
    }

    @GET // utilizando apenas o verbo GET, ou seja, vou apenas ler o recurso
    @Path("/")
    public String ExibirProdutos() {

        String cliente = null;

        cliente = "[\n"
                + "	{\n"
                + "		\"id\": 1,\n"
                + "		\"nome\": \"Nome do Produto 1\",\n"
                + "		\"preco\": \"12.90\",\n"
                + "		\"descricao\": \"Descrição do produto\"\n"
                + "	},\n"
                + "	{\n"
                + "		\"id\": 2,\n"
                + "		\"nome\": \"Nome do Produto 2\",\n"
                + "		\"preco\": \"9.90\",\n"
                + "		\"descricao\": \"Descrição do produto 2\"\n"
                + "	}]";
        return cliente;
    }

    @POST
    @Path("/send")
    public Response gravarProduto(produtoJson req) {

//        EntityManagerFactory factory
//                = Persistence.createEntityManagerFactory("coopag");
//        EntityManager em = factory.createEntityManager();
//
//        Produto p = new Produto();
//        p.setTitulo("camiseta");
//        p.setDescricao("teste");
//
//        em.getTransaction().begin();
//
//        em.persist(p);
//
//        em.getTransaction().commit();

        /*
        String output = req.toString();
        return Response.status(200).entity(output).build();
         */
        return Response.status(200).entity("ok").build();
    }

}
