package cine.sala;

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

public class SalaDAO {

    private final Database db;
    private static SalaDAO instancia;

    SalaDAO() {
        db = Database.instance();
    }

    public static SalaDAO obtenerInstancia() {
        if (instancia == null) {
            instancia = new SalaDAO();
        }
        return instancia;
    }


    public void crear(Sala p) throws SQLException, Exception{

        
            PreparedStatement stm = Database.instance().prepareStatement(SalaCRUD.CMD_AGREGAR);
            
            stm.setString(1, p.getSala());
            stm.setInt(2, Integer.parseInt(p.getButacas()));
            
            
            int count = Database.instance().executeUpdate(stm);
            if (count == 0) {
                throw new Exception("duplicado");
            }
       
    }

    public HashMap listarSala() {
        Sala resultado = null;
        HashMap<String, Sala> salas = new HashMap<>();
        try {
            try (Connection cnx = db.getConnection();
                    PreparedStatement stm = cnx.prepareStatement(SalaCRUD.CMD_LISTAR)) {
                stm.clearParameters();
                try (ResultSet rs = stm.executeQuery()) {
                    while (rs.next()) {
                        resultado = new Sala(
                                rs.getString("id_Sala"),
                                rs.getString("total_Butacas")
                        );
                        salas.put(resultado.getSala(), resultado);
                    }
                }
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(SalaDAO.class.getName()).log(Level.SEVERE, null, ex);
                return salas;
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            return salas;
        }
        return salas;

    }

    public Sala recuperar(String id) {
        Sala resultado = null;
        try {
            try (Connection cnx = db.getConnection();
                    PreparedStatement stm = cnx.prepareStatement(SalaCRUD.CMD_RECUPERAR)) {
                stm.clearParameters();
                stm.setString(1, id);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        resultado = new Sala(
                                rs.getString("id_Sala"),
                                rs.getString("total_Butacas")
                        );
                    }
                }
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(SalaDAO.class.getName()).log(Level.SEVERE, null, ex);
                return resultado;
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            return resultado;
        }
        return resultado;
    }

    public void eliminar(String p) throws Exception {
        PreparedStatement stm = Database.instance().prepareStatement(SalaCRUD.CMD_ELIMINAR);
        stm.setString(1, p);
        int count = Database.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("duplicado");
        }
    }

}
