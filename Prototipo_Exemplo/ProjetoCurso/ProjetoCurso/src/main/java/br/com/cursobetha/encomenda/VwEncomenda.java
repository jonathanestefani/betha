package br.com.cursobetha.encomenda;

import br.com.cursobetha.banco.Coluna;
import br.com.cursobetha.banco.Tabela;

@Tabela("vw_encomenda")
public class VwEncomenda {

    @Coluna(nome = "id")
    private Long id;
    @Coluna(nome = "idcliente")
    private Long idcliente;
    @Coluna(nome = "observacao")
    private String observacao;
    @Coluna(nome = "preco")
    private Double preco;

    @Coluna(nome = "nome_cliente")
    private String nome_cliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCliente() {
        return idcliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idcliente = idCliente;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = (observacao != null ? observacao.trim() : "");
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

}
