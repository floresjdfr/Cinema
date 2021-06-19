package cine.ticket;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.Database;

public class TicketDAO {

    private Database db;
    private static TicketDAO instancia;

    TicketDAO() {
        db = Database.instance();
    }

    public static TicketDAO obtenerInstancia() {
        if (instancia == null) {
            instancia = new TicketDAO();
        }
        return instancia;
    }

    public int crear(Ticket p) throws Exception {

        Connection cnx = db.getConnection();
        PreparedStatement stm = cnx.prepareStatement(TicketCRUD.CMD_AGREGAR, Statement.RETURN_GENERATED_KEYS);
        stm.setString(1, p.getId());
        stm.setString(2, p.getButaca());
        stm.setString(3, p.getCliente());
        stm.setString(4, p.getCartelera());
        int count = stm.executeUpdate();
        if (count == 0) {
            throw new Exception("duplicado");
        }
        int n = -1;
        ResultSet rs = stm.getGeneratedKeys();
        if (rs.next())
                n = rs.getInt(1);
        return n;
    }

    public HashMap listarTicket() {
        Ticket resultado = null;
        HashMap<String, Ticket> peliculas = new HashMap<>();
        try {
            try (Connection cnx = db.getConnection();
                    PreparedStatement stm = cnx.prepareStatement(TicketCRUD.CMD_LISTAR)) {
                stm.clearParameters();
                try (ResultSet rs = stm.executeQuery()) {
                    while (rs.next()) {
                        resultado = new Ticket(
                                rs.getString("id_Ticket"),
                                tokenizer(rs.getString("numero_Butaca")),
                                rs.getString("id_Cliente"),
                                rs.getString("id_Cartelera")
                        );
                        peliculas.put(resultado.getId(), resultado);
                    }
                }
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
                return peliculas;
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            return peliculas;
        }
        return peliculas;

    }

    private String tokenizer(String butacas) {
        String butaca = "";
        StringTokenizer multiTokenizer = new StringTokenizer(butacas, "://.[]'\"'");
        //StringTokenizer multiTokenizer = new StringTokenizer(json, "://.[]");
        while (multiTokenizer.hasMoreTokens()) {
            butaca += multiTokenizer.nextToken();
        }
        return butaca;
    }

    public Ticket recuperar(int id) {
        Ticket resultado = null;
        try {
            try (Connection cnx = db.getConnection();
                    PreparedStatement stm = cnx.prepareStatement(TicketCRUD.CMD_RECUPERAR)) {
                stm.clearParameters();
                stm.setInt(1, id);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        resultado = new Ticket(
                                rs.getString("id_Ticket"),
                                rs.getString("numero_Butaca"),
                                rs.getString("id_Cliente"),
                                rs.getString("id_Cartelera")
                        );
                    }
                }
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
                return resultado;
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            return resultado;
        }
        return resultado;
    }

    public void eliminar(String p) throws Exception {
        PreparedStatement stm = Database.instance().prepareStatement(TicketCRUD.CMD_ELIMINAR);
        stm.setString(1, p);
        int count = Database.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("duplicado");
        }
    }

    public HashMap listadoTickets(String id) {
        Ticket resultado = null;
        HashMap<String, Ticket> peliculas = new HashMap<>();
        int x = Integer.parseInt(id);

        try {
            try (Connection cnx = db.getConnection();
                    PreparedStatement stm = cnx.prepareStatement(TicketCRUD.CMD_LISTAR_TICKET_CLIENTE)) {
                stm.clearParameters();
                stm.setInt(1, x);
                try (ResultSet rs = stm.executeQuery()) {
                    while (rs.next()) {
                        resultado = new Ticket(
                                rs.getString("id_Ticket"),
                                rs.getString("numero_Butaca"),
                                rs.getString("id_Cliente"),
                                rs.getString("id_Cartelera")
                        );
                        peliculas.put(resultado.getId(), resultado);
                    }
                }
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
                return peliculas;
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            return peliculas;
        }
        return peliculas;
    }

    public HashMap listadoTicketsMejorado(String id) {
        TicketListado resultado = null;
        HashMap<String, TicketListado> peliculas = new HashMap<>();
        int x = Integer.parseInt(id);

        try {
            try (Connection cnx = db.getConnection();
                    PreparedStatement stm = cnx.prepareStatement(TicketCRUD.CMD_LISTAR_MEJORADO)) {
                stm.clearParameters();
                stm.setInt(1, x);
                try (ResultSet rs = stm.executeQuery()) {
                    while (rs.next()) {
                        resultado = new TicketListado(
                                rs.getString("id_Ticket"),
                                rs.getString("nombre"),
                                rs.getString("apellido"),
                                rs.getString("id_Sala"),
                                rs.getString(5),
                                rs.getString("fecha_Funcion"),
                                rs.getString("hora_Inicio"),
                                rs.getString("numero_Butaca")
                        );
                        peliculas.put(resultado.getId(), resultado);
                    }
                }
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
                return peliculas;
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            return peliculas;
        }
        return peliculas;
    }

    public HashMap obtenerTicket(String ticketNumber) {
        TicketListado resultado = null;
        HashMap<String, TicketListado> peliculas = new HashMap<>();
        int x = Integer.parseInt(ticketNumber);

        try {
            try (Connection cnx = db.getConnection();
                    PreparedStatement stm = cnx.prepareStatement(TicketCRUD.CMD_RECUPERAR_TICKET)) {
                stm.clearParameters();
                stm.setInt(1, x);
                try (ResultSet rs = stm.executeQuery()) {
                    while (rs.next()) {
                        resultado = new TicketListado(
                                rs.getString("id_Ticket"),
                                rs.getString("nombre"),
                                rs.getString("apellido"),
                                rs.getString("id_Sala"),
                                rs.getString(5),
                                rs.getString("fecha_Funcion"),
                                rs.getString("hora_Inicio"),
                                rs.getString("numero_Butaca")
                        );
                        peliculas.put(resultado.getId(), resultado);
                    }
                }
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
                return peliculas;
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            return peliculas;
        }
        return peliculas;
    }
}
