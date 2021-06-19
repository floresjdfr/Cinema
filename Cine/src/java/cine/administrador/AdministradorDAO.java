package cine.administrador;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.Database;

public class AdministradorDAO {

    private Database db;
    private static AdministradorDAO instancia;

    AdministradorDAO() {
        db = Database.instance();
    }

    public static AdministradorDAO obtenerInstancia() {
        if (instancia == null) {
            instancia = new AdministradorDAO();
        }
        return instancia;
    }

    
    public void crear(Administrador p) throws Exception {

        PreparedStatement stm = Database.instance().prepareStatement(AdministradorCRUD.CMD_AGREGAR);

        stm.setString(1, p.getId());
        stm.setString(2, p.getPassword());
        int count = Database.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("duplicado");
        }
    }
    
    public HashMap listarAdmin(){
        Administrador resultado = null;
        HashMap<String,Administrador> peliculas = new HashMap<>();
        try {
            try (Connection cnx = db.getConnection();
                    PreparedStatement stm = cnx.prepareStatement(AdministradorCRUD.CMD_LISTAR)) {
                stm.clearParameters();
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        resultado = new Administrador(
                                rs.getString("id_Administrador"),
                                rs.getString("password")    
                        );
                        peliculas.put(resultado.getId(), resultado);
                    }
                }
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
                return peliculas;
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            return peliculas;
        }
        return peliculas;
    
    }
    
    public Administrador recuperar(String cedula) {
        int id = Integer.parseInt(cedula);
        Administrador resultado = null;
        try {
            try (Connection cnx = db.getConnection();
                    PreparedStatement stm = cnx.prepareStatement(AdministradorCRUD.CMD_RECUPERAR)) {
                stm.clearParameters();
                stm.setInt(1, id);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        resultado = new Administrador(
                                rs.getString("id_Administrador"),
                                rs.getString("password")
                        );
                    }
                }
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
                return resultado;
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            return resultado;
        }
        return resultado;
    }

    
    public void eliminar(String p) throws Exception {
        PreparedStatement stm = Database.instance().prepareStatement(AdministradorCRUD.CMD_ELIMINAR);
        stm.setString(1, p);
        int count = Database.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("duplicado");
        }
    }

    
}
