/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cursobetha.banco;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author joesu
 */
public class Banco extends BdMySql {

    private Statement stm;

    public Banco() throws IOException, SQLException {
        this.stm = getConnection().createStatement();
    }

    public Connection getConnection() throws IOException {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            System.out.println("Falha na conexão com o "+ nome_fantasia);
            e.printStackTrace();
        }

        if (connection != null) {
            System.out.println("Conexão com o "+ nome_fantasia  +" realizada com sucesso!");
        }

        return connection;
    }

    public String inserir(Object obj) throws ClassNotFoundException, SQLException {
        String sql = UtilsBanco.gerarInsert(obj);
        
        //return sql;
        stm.executeUpdate(sql);
        return "ok";
    }
    
    public String atualizar(Object obj, Long id) throws SQLException {
        String sql = UtilsBanco.gerarUpdate(obj, id);
        stm.executeUpdate(sql);
        return "ok";
    }
    
    public String remover(String tabela, String id) throws SQLException {
        String sql = UtilsBanco.gerarDelete(tabela, id);
        stm.executeUpdate(sql);
        return "ok";
    }

    public ResultSet consultar(String tabela, String where) throws SQLException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
        String sql = UtilsBanco.getSelectByTable(tabela, where);
        return stm.executeQuery(sql);
    }
    
    public ResultSet query(String sql) throws SQLException {
        return stm.executeQuery(sql);
    }

}
