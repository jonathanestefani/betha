(function () {
    window.cadastro = function Encomenda(id, idcliente, idproduto, preco, observacao, itens) {
        this.id = id;
        this.idcliente = idcliente;
        this.preco = preco;
        this.observacao = observacao;
        this.itens = itens;
        return this;
    }

    window.cadastroItens = function EncomendaItens(id, idproduto, nome_produto, quantidade, preco) {
        this.id = id;
        this.idproduto = idproduto;
        this.nome_produto = nome_produto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    function encomendaControler() {
        var registros = [];
        var itemAtual = [];
        var proximoId = 0;
        var templateTable;

        var registrosItens = [];
        var itemAtualItens = [];
        var proximoIdItens = 0;
        var templateTableItens;
        var templateTableItensHidden;

        var _getPositionProduto = function (idproduto) {
            var idx = -1;

            for (var i = 0; i < ctrl.registrosItens.length; i++) {
                if (ctrl.registrosItens[i].idproduto == idproduto) {
                    idx = i;
                    break;
                }
            }

            return idx;
        }

        var _cadastrarItem = function (idproduto, nome_produto, quantidade, preco) {
            var idx = _getPositionProduto(idproduto);

            if (idx >= 0) {
                ctrl.registrosItens[idx].quantidade = quantidade;
            } else {
                ctrl.registrosItens.push(new cadastroItens(++ctrl.proximoIdItens, idproduto, nome_produto, quantidade, preco));
            }
            $("#cadastroItens").find("input").val("");
            _preencheTable();
        }

        var _removerItem = function (idproduto) {
            var idx = _getPositionProduto(idproduto);

            if (confirm("Deseja excluir?")) {
                ctrl.registrosItens.splice(idx, 1);
                _preencheTable();
            }
        }

        var _edit = function (id) {
            var link = servidor + '/api/?interna='+interna+'&acao=allitens';
            
            $.ajax({
                url: link,
                dataType: "json",
                success: function (dados) {
                    ctrl.registrosItens = dados;
                    _preencheTable();
                },
                error: function () {
                    ctrl.registros = new Object();
                    _preencheTable();
                }
            });
        }

        var _tabelaRegistrosUpdate = function () {
            if (ctrl.registrosItens.length > 0) {
                $("#dadosTabelaItens").show();
                $("#nenhumRegistroItens").hide();
            } else {
                $("#dadosTabelaItens").hide();
                $("#nenhumRegistroItens").show();
                $("#nenhumRegistroItens").html('<tr><td class="text-center" colspan="5">Nenhum Registro</td></tr>');
            }
        }

        var _replaceProdutoItens = function (regs) {
            response = "";
            var modelo = ctrl.templateTableItensHidden;
            var values = new Array();

            for (var dado in regs) {
                if (!regs.hasOwnProperty(dado))
                    continue;

                if (typeof (regs[dado]) == "object") {
                    response += _replaceProdutoItens(regs[dado]);
                } else {
                    alert(dado);

                    modelo = modelo.replace(new RegExp("{{" + dado + "}}", "g"), regs[dado]);
                }
            }

            if (modelo != ctrl.templateTableItensHidden)
                response += modelo;

            return response;
        }

        var _replaceTable = function (regs) {
            response = "";
            var modelo = ctrl.templateTableItens;

            for (var dado in regs) {
                if (!regs.hasOwnProperty(dado))
                    continue;

                if (typeof (regs[dado]) == "object") {
                    response += _replaceTable(regs[dado]);
                } else {
                    modelo = modelo.replace(new RegExp("{{" + dado + "}}", "g"), regs[dado]);
                }
            }

            if (modelo != ctrl.templateTableItens)
                response += modelo;

            return response;
        }

        var _preencheTable = function () {
            var response = _replaceTable(ctrl.registrosItens);
            var responseHidden = _replaceProdutoItens(ctrl.registrosItens);

            $('#dadosTabelaItens').html("");
            $('#dadosTabelaItens').html(response);

            $('#formulario-produtos').html("");
            $('#formulario-produtos').html(responseHidden);

            $(".jsUpdateQtd").each(function () {
                var idproduto = $(this).attr("id");
                idproduto = idproduto.split("_")[1];
                var idx = ctrl.getPositionProduto(idproduto);

                var total = ctrl.registrosItens[idx].quantidade * ctrl.registrosItens[idx].preco;
                $("#tot_" + idproduto).html("R$ " + Utils().formatValor(total));
            });

            ctrl.updateTabelaRegistroItens();
        }

        var _atualizaStatus = function () {
            var msg = "";

            if (operacao == "POST") {
                msg = "Inserindo";
            } else {
                msg = "Atualizando";
            }

            msg += " " + nome_fantasia;
            $("#statusCadastro").html(msg);
        }

        return {
            registros: registros,
            itemAtual: itemAtual,
            proximoId: proximoId,
            registrosItens: registrosItens,
            itemAtualItens: itemAtualItens,
            proximoIdItens: proximoIdItens,
            templateTableItens: templateTableItens,
            templateTableItensHidden: templateTableItensHidden,
            cadastrarItem: _cadastrarItem,
            removerItem: _removerItem,
            editarItens: _edit,
            preencheLista: _preencheTable,
            getPositionProduto: _getPositionProduto,
            updateTabelaRegistroItens: _tabelaRegistrosUpdate
        }
    }

    $(function () {
        nome_fantasia = "encomenda";
        window.ctrl = encomendaControler();

        Utils().autocomplete(servidor + "/api/?interna=clientes&acao=allclientes&pesquisa=", "nome_cliente", "idcliente", "nome", function (cliente) {
            if (cliente.id != undefined || cliente.id != null || cliente.id != "") {
                $("#idcliente").val(cliente.id);
            }
        });

        Utils().autocomplete(servidor + "/api/?interna=produtos&acao=allprodutos&pesquisa=", "nome_produto", "idproduto", "nome", function (produto) {
            $("#idproduto").val(produto.id);
            $("#preco_produto").val(produto.preco);
        });

        $("#btnAdd").click(function () {
            ctrl.cadastrarItem($("#idproduto").val(), $("#nome_produto").val(), $("#produto_quantidade").val(), $("#preco_produto").val());
        });

        ctrl.templateTableItens = ctrl.templateTableItens || $('#listaEncomendas tbody').html();
        ctrl.templateTableItensHidden = ctrl.templateTableItensHidden || $('#formulario-produtos').html();

        $(".jsPreco").mask("#.##0,00", {reverse: true});

        ctrl.updateTabelaRegistroItens();
    });

})();