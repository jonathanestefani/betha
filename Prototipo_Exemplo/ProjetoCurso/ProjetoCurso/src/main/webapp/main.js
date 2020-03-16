var operacao = "POST";
var interna = "";
var nome_fantasia = "";
var servidor = "http://localhost:8080/ProjetoCurso";

window.ctrl = function () {
    var registros = new Object();
    var itemAtual = new Object();
    var proximoId = 0;
    var templateTable;

    return {
        registros: registros,
        itemAtual: itemAtual,
        proximoId: proximoId,
        templateTable: templateTable,
        save: function () {}
    }
}

var Utils = function () {

    var _buscar = function () {
        $("#btnBuscar").hide();
        $("#buscandoInfo").removeClass("fade");
        $("#painelBuscar").fadeTo("fast", 0.7);
        DadosRecurso().buscar(interna, 'all' | interna, $("#inputBuscar").val());
    }
    
    var converteStrToFloat = function(val) {
        var preco = val.replace(".", "");
        preco = preco.replace(",", ".");
        return parseFloat(preco);
    }
    
    var formatValor = function(valor) {
        return valor.toFixed(2).replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,");
    }

    var _autocomplete = function (urlData, idList, idDestino, campo, extra) {
        var options = {
            url: urlData,
            getValue: campo,
            template: {
                type: "description",
                fields: {
                    description: campo
                }
            },
            list: {
                match: {
                    enabled: true
                },
                sort: {
                    enabled: true
                },
                onSelectItemEvent: function() {
                    var value = $("#"+idList).getSelectedItemData();
                    extra(value);
                }
            },
            theme: "bootstrap"
        };

        $("#" + idList).easyAutocomplete(options);
        $(".easy-autocomplete").attr("style", "");

    }

    var _replaceTable = function (regs) {
        response = "";
        var modelo = ctrl.templateTable;

        for (var dado in regs) {
            if (!regs.hasOwnProperty(dado))
                continue;

            if (typeof (regs[dado]) == "object") {
                response += _replaceTable(regs[dado]);
            } else {
                modelo = modelo.replace(new RegExp("{{" + dado + "}}", "g"), regs[dado]);
            }
        }

        if (modelo != ctrl.templateTable)
            response += modelo;

        return response;
    }

    var _tabelaRegistros = function () {
        if (ctrl.registros.length > 0) {
            $("#dadosTabela").show();
            $("#nenhumRegistro").hide();
        } else {
            $("#dadosTabela").hide();
            $("#nenhumRegistro").show();
            $("#nenhumRegistro").html('<tr><td class="text-center" colspan="5">Nenhum Registro</td></tr>');
        }
    }

    var _mensagem = function (msg, tipo, localHtml) {
        var html = '<div id="mensagem" class="alert ';

        switch (tipo) {
            case "insert":
                html += 'alert-success fade in" style="margin-top:18px;">' +
                        '<a href="#" class="close" data-dismiss="alert" aria-label="close" title="close">×</a>' +
                        '<strong>Sucesso!';
                break;
            case "update":
                html += 'alert-success fade in" style="margin-top:18px;">' +
                        '<a href="#" class="close" data-dismiss="alert" aria-label="close" title="close">×</a>' +
                        '<strong>Sucesso!';
                break;
            case "delete":
                html += 'alert-success fade in" style="margin-top:18px;">' +
                        '<a href="#" class="close" data-dismiss="alert" aria-label="close" title="close">×</a>' +
                        '<strong>Sucesso!';
                break;
            case "erro":
                html += 'alert-danger fade in" style="margin-top:18px;">' +
                        '<a href="#" class="close" data-dismiss="alert" aria-label="close" title="close">×</a>' +
                        '<strong>Erro';
                break;
        }

        html += '</strong> <h6>' + msg + '</h6></div>';

        $(localHtml).empty();
        $(localHtml).html(html);

        setTimeout(
                function () {
                    $(localHtml).empty();

                    if (tipo == "insert" || tipo == "update") {
                        _removeModal();
                    }
                }, 3000);
    }

    var _removeModal = function () {
        $(".modal").modal('hide');
    }

    var _getDadosCadastro = function () {
        var obj = $("form").serialize() + "&interna=" + interna + "&acao=";

        switch (operacao) {
            case "POST":
                obj += "cadastrar";
                break;
            case "PUT":
                obj += "atualizar";
                break;
        }

        return obj;
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
        pesquisar: _buscar,
        autocomplete: _autocomplete,
        msg: _mensagem,
        getDadosCadastro: _getDadosCadastro,
        atualizaStatus: _atualizaStatus,
        removerModal: _removeModal,
        atualizaTabelaRegistros: _tabelaRegistros,
        replaceTable: _replaceTable,
        converteStrToFloat: converteStrToFloat,
        formatValor: formatValor
    }
}

var DadosRecurso = function (recurso, obj) {

    var _add = function () {
        $("input").val("");
        ctrl.itemAtual = new cadastro(++ctrl.proximoId);
        ctrl.registros.push(ctrl.itemAtual);
        operacao = "POST";
        Utils().atualizaStatus();
        DadosRecurso().preencheCadastro();
    }

    var _edit = function (id) {
        for (var i = 0; i < ctrl.registros.length; i++) {
            if (id == ctrl.registros[i].id) {
                operacao = "PUT";
                Utils().atualizaStatus();
                ctrl.itemAtual = ctrl.registros[i];
                DadosRecurso().preencheCadastro()
                break;
            }
        }
    }

    var _remove = function (recurso, id) {
        for (var i = 0; i < ctrl.registros.length; i++) {
            if (id == ctrl.registros[i].id) {
                if (confirm('Deseja realmente excluir o registro?')) {
                    operacao = "DELETE";
                    ctrl.itemAtual = new Object();
                    ctrl.itemAtual.id = id;
                    DadosRecurso().gravar();
                    break;
                }
            }
        }
    }

    var _preencheTable = function () {
                                             /* || igual a OR */
        ctrl.templateTable = ctrl.templateTable || $('#gerenciar tbody').html();
                
        var qtd = ctrl.registros.length;
        
        $("#qtdRegistros").html((qtd != undefined ? qtd : 0));

        var response = Utils().replaceTable(ctrl.registros);
        $('#gerenciar tbody').html(response);
        Utils().atualizaTabelaRegistros();
    }

    var _preencheForm = function () {
        for (var dado in ctrl.itemAtual) {
            if (typeof (ctrl.itemAtual[dado]) !== "object") {
                $('input[name=' + dado + ']').val(ctrl.itemAtual[dado]);
            }
        }
    }

    var _carrega = function (recurso, acao, pesquisa) {
        if (acao == '') {
            acao = 'all' + recurso;
        }

        var link = servidor + '/api/?interna=' + recurso + '&acao=' + acao;

        if (pesquisa != "" && pesquisa != undefined && pesquisa != null) {
            link += "&pesquisa=" + pesquisa;
        } else {
            link += "&pesquisa=";
        }

        $.ajax({
            url: link,
            dataType: "json",
            success: function (dados) {
                ctrl.registros = dados;
                DadosRecurso().preencheLista();
                $("#btnBuscar").show();
                $("#buscandoInfo").addClass("fade");
                $("#painelBuscar").fadeTo("fast", 10);
            },
            error: function () {
                ctrl.registros = new Object();
                DadosRecurso().preencheLista();
                $("#btnBuscar").show();
                $("#buscandoInfo").addClass("fade");
                $("#painelBuscar").fadeTo("fast", 10);
            }
        });
    }

    var _save = function () {
        DadosRecurso().gravar();
    }

    var _gravar = function () {
        switch (operacao) {
            case "DELETE":
                ctrl.itemAtual.acao = "remover";
                ctrl.itemAtual.interna = interna;
                break;
            default:
                ctrl.itemAtual = Utils().getDadosCadastro();
        }

        $.ajax({
            url: servidor + "/api/" + interna,
            method: "POST",
            data: ctrl.itemAtual,
            success: function () {

                switch (operacao) {
                    case "POST":
                        Utils().msg("Registro gravado com sucesso", "insert", "#msg");
                        break;
                    case "DELETE":
                        Utils().msg("Registro excluído com sucesso.", "delete", "#msgErro");
                        break;
                    case "PUT":
                        Utils().msg("Registro atualizado com sucesso.", "update", "#msg");
                        break;
                }
                DadosRecurso().buscar(interna, "", "");
            },
            error: function () {
                if (operacao == "DELETE") {
                    Utils().msg("Ouve um erro ao se conectar com o servidor. Tente novamente.", "erro", "#msgErro");
                } else {
                    Utils().msg("Ouve um erro ao se conectar com o servidor. Tente novamente.", "erro", "#msg");
                }
                DadosRecurso().buscar(interna, "", "");
            }
        });
    }

    return {
        add: _add,
        gravar: _gravar,
        edit: _edit,
        remove: _remove,
        save: _save,
        preencheLista: _preencheTable,
        preencheCadastro: _preencheForm,
        buscar: _carrega
    }
}

$(function () {

    $(".href").click(function () {
        interna = $(this).attr("interna");

        $.get(servidor + '/' + interna + ".html", function (html) {
            $("#interna").html(html);

            $("#btnSalvar").click(function () {
                DadosRecurso().save();
            });

            $("#btnAdicionar").click(function () {
                DadosRecurso().add();
            });

            DadosRecurso().buscar(interna, "", "");
        });

        $("#internaCSSJS").html('<script src="' + servidor + '/' + interna + '.js"><\/script>');

        location.href = "#?" + interna;

        $.get(servidor + "/buscar.html", function (html) {
            $("#paginaBuscar").html(html);

            $("#btnBuscar").click(function () {
                Utils().pesquisar();
            });

            $("#inputBuscar").keydown(function (e) {
                var keycode = (e.keyCode ? e.keyCode : e.which);
                
                if (keycode == '13') {
                    Utils().pesquisar();
                }
            });
        });

    });
});