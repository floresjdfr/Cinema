
package cine.cliente;


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


public class ClienteDAO {
    
    private Database db;
    private static ClienteDAO instancia;
    
    
    ClienteDAO(){
        db = Database.instance();
    }
    
    public static ClienteDAO obtenerInstancia(){
        if (instancia == null)
            instancia = new ClienteDAO();
        return instancia;
    }
    
    public void crear(Cliente p) throws Exception {

        PreparedStatement stm = Database.instance().prepareStatement(ClienteCRUD.CMD_AGREGAR);

        stm.setString(1, p.getId());
        stm.setString(2, p.getPassword());
        stm.setString(3, p.getNombre());
        stm.setString(4, p.getApellido());
        stm.setString(5, p.getNumero_cuenta());      
        int count = Database.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("duplicado");
        }
    }
    
    public HashMap listarCli(){
        Cliente resultado = null;
        HashMap<String,Cliente> peliculas = new HashMap<>();
        try {
            try (Connection cnx = db.getConnection();
                    PreparedStatement stm = cnx.prepareStatement(ClienteCRUD.CMD_LISTAR)) {
                stm.clearParameters();
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        resultado = new Cliente(
                                rs.getString("id_Cliente"),
                                rs.getString("password"),
                                rs.getString("nombre"),
                                rs.getString("apellido"),
                                rs.getString("numero_Cuenta") 
                                
                        );
                        peliculas.put(resultado.getId(), resultado);
                    }
                }
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                return peliculas;
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            return peliculas;
        }
        return peliculas;
    
    }
    
    public Cliente recuperar(String id) {
        Cliente resultado = null;
        try {
            try (Connection cnx = db.getConnection();
                    PreparedStatement stm = cnx.prepareStatement(ClienteCRUD.CMD_RECUPERAR)) {
                stm.clearParameters();
                stm.setInt(1, Integer.parseInt(id));
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        resultado = new Cliente(
                                rs.getString("id_Cliente"),
                                rs.getString("password"),
                                rs.getString("nombre"),
                                rs.getString("apellido"),
                                null//Numero de cuenta  
                        );
                    }
                }
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                return resultado;
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            return resultado;
        }
        return resultado;
    }
    
    public void eliminar(String p) throws Exception {
        PreparedStatement stm = Database.instance().prepareStatement(ClienteCRUD.CMD_ELIMINAR);
        stm.setString(1, p);
        int count = Database.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("duplicado");
        }
    }
    
}