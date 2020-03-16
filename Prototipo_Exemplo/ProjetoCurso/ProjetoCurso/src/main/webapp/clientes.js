(function () {
    window.cadastro = function Cliente(id, nome, telefone, celular, endereco, cidade, bairro, estado, cep, email, observacao) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
        this.celular = celular;
        this.email = email;
        this.observacao = observacao;
        return this;
    }

    function clienteControler() {
        var registros = [];
        var itemAtual = [];
        var proximoId = 0;
        var templateTable;

        return {
            registros: registros,
            itemAtual: itemAtual,
            proximoId: proximoId,
            templateTable: templateTable
        }
    }

    $(function () {
        nome_fantasia = "cliente";
        window.ctrl = clienteControler();
        $(".jsPhone").mask("(00) 0000-0000");
        $('.jsCep').mask('00000-000');
    });

})();