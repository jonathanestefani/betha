/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author joesu
 */
@Path("/cliente")
@Consumes({"application/json"})

public class ClienteDAO {

    @POST
    @Path("/")
    @Produces("text/plain")
    public String gravarCliente(Cliente cliParam) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProjetoBetha");
        EntityManager gerenciador = factory.createEntityManager();

        Cliente cli = new Cliente();
        cli.setEndereco(cliParam.getEndereco());
        cli.setBairro(cliParam.getBairro());
        cli.setEstado(cliParam.getEstado());
        cli.setCidade(cliParam.getCidade());
        cli.setCep(cliParam.getCep());
        cli.setCelular(cliParam.getCelular());
        cli.setTelefone(cliParam.getTelefone());
        cli.setObservacao(cliParam.getObservacao());

        gerenciador.getTransaction().begin();
        gerenciador.persist(cli);
        gerenciador.getTransaction().commit();

        gerenciador.close();
        factory.close();

        return "ok";
    }

    @GET // utilizando apenas o verbo GET, ou seja, vou apenas ler o recurso
    @Path("/")
    @Produces("text/json")
    public String ExibirClientes() {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ProjetoBetha");
        EntityManager gerenciador = factory.createEntityManager();

//        QCustomer customer = QCustomer.customer;
//        JPQLQuery query = new HibernateQuery(session);
//        Customer bob = query.from(customer)
//                .where(customer.firstName.eq("Bob"))
//                .uniqueResult(customer);

//        Query query = gerenciador.createQuery("select c from cliente as c");
//        List<Cliente> clientes = query.getResultList();
//        List<Cliente> clientes = gerenciador.createQuery("select c from cliente as c").getResultList();
        //Cliente cli = gerenciador.find(Cliente.class, 1L);
        gerenciador.close();
        factory.close();

//        String cliente = new Gson().toJson(cli);
//        System.out.println(json);
//        return json;
        String cliente = null;

        cliente = "[\n"
                + "	{\n"
                + "		\"id\": 1,\n"
                + "		\"documento\": \"1677051347699\",\n"
                + "		\"nome\": \"Tellus Limited\",\n"
                + "		\"telefone\": \"(25) 3264-3574\",\n"
                + "		\"celular\": \"2629-5927\",\n"
                + "		\"email\": \"natoque@orciUtsemper.co.uk\"\n"
                + "	},\n"
                + "	{\n"
                + "		\"id\": 2,\n"
                + "		\"documento\": \"1671052650699\",\n"
                + "		\"nome\": \"At Velit Pellentesque LLC\",\n"
                + "		\"telefone\": \"(65) 3211-0154\",\n"
                + "		\"celular\": \"9386-3752\",\n"
                + "		\"email\": \"id.libero@posuere.co.uk\"\n"
                + "	},\n"
                + "	{\n"
                + "		\"id\": 3,\n"
                + "		\"documento\": \"1617042485799\",\n"
                + "		\"nome\": \"Augue Corp.\",\n"
                + "		\"telefone\": \"(16) 2705-9146\",\n"
                + "		\"celular\": \"(26) 1686-1642\",\n"
                + "		\"email\": \"molestie.dapibus@dolorsit.ca\"\n"
                + "	},\n"
                + "	{\n"
                + "		\"id\": 4,\n"
                + "		\"documento\": \"1627112727899\",\n"
                + "		\"nome\": \"Vulputate Risus Corp.\",\n"
                + "		\"telefone\": \"3255-3238\",\n"
                + "		\"celular\": \"(38) 4520-8462\",\n"
                + "		\"email\": \"lacus.Quisque@ut.co.uk\"\n"
                + "	},\n"
                + "	{\n"
                + "		\"id\": 5,\n"
                + "		\"documento\": \"1604030271499\",\n"
                + "		\"nome\": \"Vivamus Molestie Dapibus Company\",\n"
                + "		\"telefone\": \"(92) 7376-9571\",\n"
                + "		\"celular\": \"8064-3242\",\n"
                + "		\"email\": \"ultrices.sit@bibendum.edu\"\n"
                + "	}]";
        return cliente;
    }

    @POST
    @Produces("text/plain")
    @Path("/")
    public String gravarCliente() {
        /*
        Cliente cliente = new Cliente();
        cliente.setNome("Eduardo");
        cliente.setBairro("bairro");
        cliente.setCelular("celular");
        cliente.setCep("cep");
        cliente.setEmail("jonathan@gmail.com");
        cliente.setEndereco("endereco");
        cliente.setEstado("SC");
        cliente.setTelefone("telefone");

        EntityManager manager = Persistence.createEntityManagerFactory("entityManager").createEntityManager();
        
        manager.getTransaction().begin();

        manager.persist(cliente);

        manager.getTransaction().commit();
        
        int id = cliente.getId();
        //System.out.println("ID do cliente: " + cliente.getId());

        manager.close();
         */
        //instancia um novo JSONObject
//        JSONObject my_obj = new JSONObject();
//        my_obj.getString(msg);
//        
//        return my_obj.toString();
        return "ok";
    }

}
