package br.com.cursobetha.encomenda;

import br.com.cursobetha.banco.Coluna;
import br.com.cursobetha.banco.Tabela;

@Tabela("encomenda_itens")
public class EncomendaItens {

    @Coluna(nome = "id")
    private Long id;
    @Coluna(nome = "idproduto")
    private Long idproduto;
    @Coluna(nome = "idencomenda")
    private Long idencomenda;
    @Coluna(nome = "quantidade")
    private Integer quantidade;

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

    public Long getIdencomenda() {
        return idencomenda;
    }

    public void setIdencomenda(Long idencomenda) {
        this.idencomenda = idencomenda;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

}
