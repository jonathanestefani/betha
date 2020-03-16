(function () {
    window.cadastro = function Produto(id, nome, preco, descricao) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        return this;
    }

    function produtoControler() {
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
        nome_fantasia = "produto";
        window.ctrl = produtoControler();
        $(".jsPreco").mask("#.##0,00", {reverse: true});
    });

})();