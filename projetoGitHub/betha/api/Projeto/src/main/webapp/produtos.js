(function () {

    function Produto(id, nome, preco, descricao) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        return this;
    }

    function produtoControler() {

        var registros = [];
        var itemAtual;
        var proximoId = 0;
        var templateTable;

        var _add = function () {
            itemAtual = new Produto(++proximoId);
            registros.push(itemAtual);
            _preencheForm(itemAtual);
        }

        var _preencheForm = function (objProduto) {
            $('input[name=id]').val(objProduto.id);
            $('input[name=nome]').val(objProduto.nome);
            $('input[name=preco]').val(objProduto.preco);
            $('input[name=descricao]').val(objProduto.descricao);
        }

        var _save = function () {
            itemAtual.id = $('input[name=id]').val();
            itemAtual.nome = $('input[name=nome]').val();
            itemAtual.preco = $('input[name=preco]').val();
            itemAtual.descricao = $('input[name=descricao]').val();
            
            gravarDados("produto", itemAtual);
            
            _preencheTable();
        }

        var _preencheTable = function () {
            templateTable = templateTable || $('table.table tbody').html();

            var response = '';

            for (var i = 0; i < registros.length; i++) {
                var linha = registros[i];
                var modelo = templateTable;

                modelo = modelo.replace(/\{\{id\}\}/g, linha.id);
                modelo = modelo.replace(/\{\{nome\}\}/g, linha.nome);
                modelo = modelo.replace(/\{\{preco\}\}/g, linha.preco);
                modelo = modelo.replace(/\{\{descricao\}\}/g, linha.descricao);

                response += modelo;
            }
            $('table.table tbody').html(response);
        }

        var _edit = function (id) {
            for (var i = 0; i < registros.length; i++) {
                if (id == registros[i].id) {
                    itemAtual = registros[i];
                    _preencheForm(itemAtual);
                    break;
                }
            }
        }

        var _remove = function (id) {
            for (var i = 0; i < registros.length; i++) {
                if (id == registros[i].id) {
                    if (confirm('Deseja realmente excluir o registro?'))
                        registros.splice(i, 1);
                    break;
                }
            }
        }

        var _carrega = function() {
            $.getJSON(servidor + '/produto', function (dados) {
                registros = dados;
                _preencheTable();
            });
        }

        _carrega();

        return {
            add: _add,
            save: _save,
            edit: _edit,
            remove: _remove
        }
    }

    $(function(){
        window.ctrl = produtoControler();
        $('#btnSalvar').click(function(){
            ctrl.save();
        });
        $('#btnAdicionar').click(function(){
            ctrl.add();
        });
    });

})();
