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
public abstract class BdPostgres {

    protected final String nome_fantasia = "Postgres";
    protected final String baseDeDados = "ProjetoCurso";
    protected final String usuario = "postgres";
    protected final String senha = "postgres";
    protected final String url = "jdbc:postgresql://192.168.0.103:5432/" + baseDeDados;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception ex) {
            Logger.getLogger(BdPostgres.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}