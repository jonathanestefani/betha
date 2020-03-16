package br.com.cliente;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCliente is a Querydsl query type for Cliente
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCliente extends EntityPathBase<Cliente> {

    private static final long serialVersionUID = -1328111397L;

    public static final QCliente cliente = new QCliente("cliente");

    public final StringPath bairro = createString("bairro");

    public final StringPath celular = createString("celular");

    public final StringPath cep = createString("cep");

    public final StringPath cidade = createString("cidade");

    public final StringPath email = createString("email");

    public final StringPath endereco = createString("endereco");

    public final StringPath estado = createString("estado");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath observacao = createString("observacao");

    public final StringPath telefone = createString("telefone");

    public QCliente(String variable) {
        super(Cliente.class, forVariable(variable));
    }

    public QCliente(Path<? extends Cliente> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCliente(PathMetadata<?> metadata) {
        super(Cliente.class, metadata);
    }

}

