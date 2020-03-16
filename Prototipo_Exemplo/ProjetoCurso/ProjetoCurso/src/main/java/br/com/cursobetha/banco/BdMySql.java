/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cursobetha.banco;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joesu
 */
public abstract class BdMySql {

    protected final String nome_fantasia = "MySql";
    protected final String baseDeDados = "CursoBetha";
    protected final String usuario = "root";
    protected final String senha = "b40th34infoadx";
    protected final String url = "jdbc:mysql://localhost:3306/" + baseDeDados;

    static {
        try {
            Class.forName("org.mysql.Driver");
        } catch (Exception ex) {
            Logger.getLogger(BdPostgres.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
