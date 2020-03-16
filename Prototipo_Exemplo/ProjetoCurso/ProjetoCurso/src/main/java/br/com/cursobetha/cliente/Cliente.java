package br.com.cursobetha.cliente;

import br.com.cursobetha.banco.Coluna;
import br.com.cursobetha.banco.Tabela;

@Tabela("cliente")
public class Cliente {

    @Coluna(nome = "id")
    private Long id;
    @Coluna(nome = "nome")
    private String nome;
    @Coluna(nome = "bairro")
    private String bairro;
    @Coluna(nome = "endereco")
    private String endereco;
    @Coluna(nome = "cep")
    private String cep;
    @Coluna(nome = "cidade")
    private String cidade;
    @Coluna(nome = "estado")
    private String estado;
    @Coluna(nome = "telefone")
    private String telefone;
    @Coluna(nome = "celular")
    private String celular;
    @Coluna(nome = "email")
    private String email;
    @Coluna(nome = "observacao")
    private String observacao;

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

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = (bairro != null ? bairro.trim() : "");
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = (endereco != null ? endereco.trim() : "");
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = (cep != null ? cep.trim() : "");
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = (cidade != null ? cidade.trim() : "");
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = (estado != null ? estado.trim() : "");
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = (telefone != null ? telefone.trim() : "");
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = (celular != null ? celular.trim() : "");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = (email != null ? email.trim() : "");
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = (observacao != null ? observacao.trim() : "");
    }

}