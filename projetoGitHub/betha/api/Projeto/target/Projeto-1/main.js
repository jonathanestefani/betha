var servidor = "http://localhost:8080/Projeto/api";
var operacao = "POST";
var gravarDados = function (recurso, obj) {

    var _gravar = function (recurso, obj) {
        $.ajax({
            url: servidor + "/" + recurso,
            method: operacao,
            dataType: "text",
            contentType: "application/json",
            data: JSON.stringify(obj),
            success: function () {

                switch (operacao) {
                    case "POST":
                        _mensagem("Registro gravado com sucesso", "insert", "#msg");
                        break;
                    case "DELETE":
                        _mensagem("Registro excluído com sucesso.", "delete", "#msgErro");
                        break;
                    case "PUT":
                        _mensagem("Registro atualizado com sucesso.", "update", "#msg");
                        break;
                }
            },
            error: function () {
                if (operacao == "DELETE") {
                    _mensagem("Ouve um erro ao se conectar com o servidor. Tente novamente.", "erro", "#msgErro");
                } else {
                    _mensagem("Ouve um erro ao se conectar com o servidor. Tente novamente.", "erro", "#msg");
                }
            }
        });
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
                    
                    if (tipo == "insert" || tipo == "update" ) {
                        _removeModal();
                    }
                }, 3000);
    }

    var _removeModal = function () {
        $(".modal-backdrop").remove();
        $("#cadastro").removeClass("in");
    }

    return {
        gravar: _gravar(recurso, obj)
    }
}

$(function () {
    $.get('menu-lateral.html', function (data) {
        $('#menu-lateral').html(data);
    });
    $.get('menu-superior.html', function (resposta) {
        $('#menu-superior').html(resposta);
    });
});