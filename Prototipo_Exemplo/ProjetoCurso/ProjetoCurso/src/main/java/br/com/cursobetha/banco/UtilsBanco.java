package br.com.cursobetha.banco;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UtilsBanco {

    public static Map<String, Object> gerarMapFields(Object objeto)
            throws IllegalArgumentException, IllegalAccessException {
        Map<String, Object> dados = new HashMap<String, Object>();

        Field[] fields = objeto.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            String chave = field.getName();
            Object valor = field.get(objeto);
            dados.put(chave, valor);
        }

        return dados;
    }

    public static String gerarJson(Object obj) throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();

        Map<String, Object> valores = gerarMapAnotacao(obj);

        sb.append("{");
        for (String chave : valores.keySet()) {
            sb.append(String.format("\"%s\":\"%s\",", chave.toLowerCase(), valores.get(chave)));
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        return sb.toString();
    }

    public static Map<String, Object> gerarMapAnotacao(Object objeto)
            throws IllegalArgumentException, IllegalAccessException {

        Map<String, Object> dados = new HashMap<String, Object>();

        Field[] fields = objeto.getClass().getDeclaredFields();

        for (Field field : fields) {

            field.setAccessible(true);

            String chave = field.getName();
            Object valor = field.get(objeto);

            if (field.isAnnotationPresent(Coluna.class)) {
                Coluna col = field.getAnnotation(Coluna.class);
                chave = col.nome();
            }
            dados.put(chave, valor);
        }

        return dados;
    }

    public static String gerarInsert(Object obj) {
        StringBuilder sb = new StringBuilder();
        try {
            String tabela;
            if (obj.getClass().isAnnotationPresent(Tabela.class)) {
                Tabela tab = obj.getClass().getAnnotation(Tabela.class);
                tabela = tab.value();
            } else {
                tabela = obj.getClass().getSimpleName().toUpperCase();
            }

            Map<String, Object> valores = gerarMapAnotacao(obj);

            sb.append("INSERT INTO ");
            sb.append(tabela.toLowerCase());
            sb.append(" (");

            for (String chave : valores.keySet()) {
                if (!chave.toLowerCase().equals("id")) {
                    sb.append(chave.toLowerCase());
                    sb.append(",");
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(") VALUES (");
            for (String chave : valores.keySet()) {
                if (!chave.toLowerCase().equals("id")) {
                    sb.append("'" + valores.get(chave) + "'");
                    sb.append(",");
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(")");
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(UtilsBanco.class.getName()).log(Level.SEVERE,
                    "Deu erro!!!", ex);
        }
        return sb.toString();
    }

    public static String gerarUpdate(Object obj, Long id) {
        StringBuilder sb = new StringBuilder();
        try {
            String tabela;

            if (obj.getClass().isAnnotationPresent(Tabela.class)) {
                Tabela tab = obj.getClass().getAnnotation(Tabela.class);
                tabela = tab.value();
            } else {
                tabela = obj.getClass().getSimpleName().toUpperCase();
            }

            Map<String, Object> valores = gerarMapAnotacao(obj);

            sb.append("UPDATE ");
            sb.append(tabela);
            sb.append(" SET ");

            for (String chave : valores.keySet()) {
                sb.append(chave.toLowerCase() + " = '" + valores.get(chave) + "'");
                sb.append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(" WHERE id = " + id + ";");
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(UtilsBanco.class.getName()).log(Level.SEVERE,
                    "Deu erro!!!", ex);
        }
        return sb.toString();
    }

    public static String gerarDelete(String tabela, String id) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM " + tabela + " WHERE id = '" + id + "'");
        return sb.toString();
    }

    public static String getSelectByTable(String table, String where) {
        String sql = "select * from " + table;

        if (!where.isEmpty()) {
            sql += " WHERE " + where;
        }

        return sql;
    }

}
