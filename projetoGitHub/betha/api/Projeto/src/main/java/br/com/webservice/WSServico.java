package br.com.webservice;

import br.com.cliente.ClienteDAO;
import br.com.produto.ProdutoDAO;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class WSServico extends Application {

    private Set<Object> singletons = new HashSet<Object>();
    private Set<Class<?>> empty = new HashSet<Class<?>>();

    public WSServico() {
        singletons.add(new ClienteDAO());
        singletons.add(new ProdutoDAO());
    }

    public Set<Class<?>> getClasses() {
        return empty;
    }

    public Set<Object> getSingletons() {
        return singletons;
    }
}
