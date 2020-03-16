package br.com.cursobetha.produto;

import br.com.cursobetha.banco.Coluna;
import br.com.cursobetha.banco.Tabela;

@Tabela("produto")
public class Produto {

    @Coluna(nome = "id", tipo = "primary")
    private Long id;
    @Coluna(nome = "nome")
    private String nome;
    @Coluna(nome = "barcode")
    private String barcode;
    @Coluna(nome = "descricao")
    private String descricao;
    @Coluna(nome = "preco")
    private Number preco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = (nome != null ? nome.trim() : "");
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }    
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = (descricao != null ? descricao.trim() : "");
    }

    public Number getPreco() {
        return preco;
    }

    public void setPreco(Number preco) {
        this.preco = preco;
    }

}