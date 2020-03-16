/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.produto;

/**
 *
 * @author joesu
 */
public class produtoJson {

    private int id;
    private String nome;
    private String preco;
    private String descricao;

// No-argument constructor
    public produtoJson() {

    }

    public produtoJson(int id, String nome, String preco, String descricao) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
//getters and settters
    @Override
    public String toString() {
        return new StringBuffer(" id : ").append(this.id)
                .append(" nome : ").append(this.nome)
                .append(" preco : ").append(this.preco).toString();
    }

}
