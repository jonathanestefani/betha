(function () {
    function Cliente(id, nome, telefone, celular, endereco, cidade, bairro, estado, cep, email, observacao) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.celular = celular;
        this.endereco = endereco;
        this.cidade = cidade;
        this.bairro = bairro;
        this.estado = estado;
        this.cep = cep;
        this.email = email;
        this.observacao = observacao;
        return this;
    }

    function clienteControler() {

        var registros = [];
        var itemAtual;
        var proximoId = 0;
        var templateTable;

        var _add = function () {
            itemAtual = new Cliente(++proximoId);
            registros.push(itemAtual);
            _preencheForm(itemAtual);
            operacao = "POST";
        }

        var _preencheForm = function (objCliente) {
            $('input[name=id]').val(objCliente.id);
            $('input[name=nome]').val(objCliente.nome);
            $('input[name=telefone]').val(objCliente.telefonej);
            $('input[name=celular]').val(objCliente.celular);
            $('input[name=endereco]').val(objCliente.endereco);
            $('input[name=cidade]').val(objCliente.cidade);
            $('input[name=bairro]').val(objCliente.bairro);
            $('input[name=estado]').val(objCliente.estado);
            $('input[name=cep]').val(objCliente.cep);
            $('input[name=email]').val(objCliente.email);
            $('input[name=observacao]').val(objCliente.observacao);
        }

        var _save = function () {
            itemAtual.id = $('input[name=id]').val();
            itemAtual.nome = $('input[name=nome]').val();
            itemAtual.telefone = $('input[name=telefone]').val();
            itemAtual.celular = $('input[name=celular]').val();
            itemAtual.endereco = $('input[name=endereco]').val();
            itemAtual.cidade = $('input[name=cidade]').val();
            itemAtual.bairro = $('input[name=bairro]').val();
            itemAtual.estado = $('input[name=estado]').val();
            itemAtual.cep = $('input[name=cep]').val();
            itemAtual.email = $('input[name=email]').val();
            itemAtual.observacao = $('input[name=observacao]').val();

            gravarDados("cliente", itemAtual);

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
                modelo = modelo.replace(/\{\{telefone\}\}/g, linha.telefone);
                modelo = modelo.replace(/\{\{celular\}\}/g, linha.celular);
                modelo = modelo.replace(/\{\{endereco\}\}/g, linha.endereco);
                modelo = modelo.replace(/\{\{cidade\}\}/g, linha.cidade);
                modelo = modelo.replace(/\{\{bairro\}\}/g, linha.bairro);
                modelo = modelo.replace(/\{\{estado\}\}/g, linha.estado);
                modelo = modelo.replace(/\{\{cep\}\}/g, linha.cep);
                modelo = modelo.replace(/\{\{email\}\}/g, linha.email);
                modelo = modelo.replace(/\{\{observacao\}\}/g, linha.observacao);

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
            operacao = "PUT";
        }

        var _remove = function (id) {
            for (var i = 0; i < registros.length; i++) {
                if (id == registros[i].id) {
                    if (confirm('Deseja realmente excluir o registro?'))
                        _edit(id);
                    operacao = "DELETE";
                    gravarDados("cliente", itemAtual);
                    registros.splice(i, 1);
                    break;
                }
            }
        }

        var _carrega = function (pesquisa) {
            $.ajax({
                url: servidor + '/cliente',
                dataType: "json",
                data: pesquisa,
                success: function (dados) {
                    registros = dados;
                    _preencheTable();
                    $("#btnBuscar").show();
                    $("#buscandoInfo").addClass("fade");
                    $("#painelBuscar").fadeTo("fast", 10);
                }
            });
        }

        _carrega();

        return {
            add: _add,
            save: _save,
            edit: _edit,
            remove: _remove,
            buscar: _carrega
        }
    }

    $(function () {
        window.ctrl = clienteControler();
        $('#btnSalvar').click(function () {
            ctrl.save();
        });
        $('#btnAdicionar').click(function () {
            ctrl.add();
        });
        $("#btnBuscar").click(function () {
            $(this).hide();
            $("#buscandoInfo").removeClass("fade");
            $("#painelBuscar").fadeTo("fast", 0.7);
            ctrl.buscar($("#inputBuscar").text());
        });
    });

})();
