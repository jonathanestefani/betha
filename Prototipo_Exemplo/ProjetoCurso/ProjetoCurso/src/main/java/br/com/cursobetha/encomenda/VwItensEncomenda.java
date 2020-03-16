package br.com.cursobetha.encomenda;

import br.com.cursobetha.banco.Coluna;
import br.com.cursobetha.banco.Tabela;

@Tabela("vw_itens_encomenda")
public class VwItensEncomenda {

    @Coluna(nome = "id")
    private Long id;
    @Coluna(nome = "idproduto")
    private Long idproduto;
    @Coluna(nome = "nome_produto")
    private String nome_produto;
    @Coluna(nome = "preco")
    private Double preco;
    @Coluna(nome = "quantidade")
    private Integer quantidade;
    @Coluna(nome = "total")
    private Double total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(Long idproduto) {
        this.idproduto = idproduto;
    }

    public String getNome_produto() {
        return nome_produto;
    }

    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

}
