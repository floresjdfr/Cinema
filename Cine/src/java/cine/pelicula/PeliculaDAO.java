package cine.pelicula;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.Database;

import java.io.*;

public class PeliculaDAO {

    private final Database db;
    private static PeliculaDAO instancia;

    PeliculaDAO() {
        db = Database.instance();
    }

    public static PeliculaDAO obtenerInstancia() {
        if (instancia == null) {
            instancia = new PeliculaDAO();
        }
        return instancia;
    }

    public void insertImage(String id_Pelicula, InputStream image) throws Exception {
        int read = 0;

        PreparedStatement stm = Database.instance().prepareStatement(PeliculaCRUD.CMD_AGREGAR_IMAGE);

        stm.setString(1, id_Pelicula);
        stm.setBinaryStream(2, (InputStream) image);
        int count = Database.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("duplicado");
        }
    }

    public File getImage(String id_Pelicula) throws Exception {
        Image imagen = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            file = new File(id_Pelicula + ".png");
            fos = new FileOutputStream(file);
            byte imageBytes[] = new byte[10000];
            InputStream binaryStream;
            PreparedStatement ps = Database.instance().prepareStatement(PeliculaCRUD.CMD_RECUPERAR_IMAGE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                binaryStream = rs.getBinaryStream("image");
                while (binaryStream.read(imageBytes) > 0) {
                    fos.write(imageBytes);
                }

                return file;
            }
        } catch (Exception e) {

        }
        return file;
    }

    /*-------------------------------------------------------------------------------------------*/
    public void crear(Pelicula p) throws Exception {

        PreparedStatement stm = Database.instance().prepareStatement(PeliculaCRUD.CMD_AGREGAR);

        stm.setString(1, p.getNombre());
        stm.setString(2, p.getDuracion());
        stm.setString(3, p.getDescripcion());
        stm.setString(4, p.getPrecio());
        stm.setInt(5, 1);

        int count = Database.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("duplicado");
        }
    }

    public HashMap listarPeli() {
        Pelicula resultado = null;
        HashMap<String, Pelicula> peliculas = new HashMap<>();
        try {
            try (Connection cnx = db.getConnection();
                    PreparedStatement stm = cnx.prepareStatement(PeliculaCRUD.CMD_LISTAR)) {
                stm.clearParameters();
                try (ResultSet rs = stm.executeQuery()) {
                    while (rs.next()) {
                        resultado = new Pelicula(
                                rs.getString("id_Pelicula"),
                                rs.getString("Nombre"),
                                rs.getString("duracion"),
                                rs.getString("descripcion"),
                                rs.getString("precio"),
                                String.valueOf(rs.getInt("estado"))
                        );
                        peliculas.put(resultado.getId(), resultado);

                    }
                }
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(PeliculaDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
                return peliculas;
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            return peliculas;
        }
        return peliculas;

    }

    public HashMap listarPeliDisponibles() {
        Pelicula resultado = null;
        HashMap<String, Pelicula> peliculas = new HashMap<>();
        try {
            try (Connection cnx = db.getConnection();
                    PreparedStatement stm = cnx.prepareStatement(PeliculaCRUD.CMD_LISTAR_DISPINIBLES)) {
                stm.clearParameters();
                try (ResultSet rs = stm.executeQuery()) {
                    while (rs.next()) {
                        resultado = new Pelicula(
                                rs.getString("id_Pelicula"),
                                rs.getString("Nombre"),
                                rs.getString("duracion"),
                                rs.getString("descripcion"),
                                rs.getString("precio"),
                                String.valueOf(rs.getInt("estado"))
                        );
                        peliculas.put(resultado.getId(), resultado);

                    }
                }
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(PeliculaDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
                return peliculas;
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            return peliculas;
        }
        return peliculas;

    }

    public Pelicula recuperar(String id) {
        Pelicula resultado = null;
        try {
            try (Connection cnx = db.getConnection();
                    PreparedStatement stm = cnx.prepareStatement(PeliculaCRUD.CMD_RECUPERAR)) {
                stm.clearParameters();
                stm.setString(1, id);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        int estado = rs.getInt("estado");
                        resultado = new Pelicula(
                                rs.getString("id_Pelicula"),
                                rs.getString("Nombre"),
                                rs.getString("duracion"),
                                rs.getString("descripcion"),
                                rs.getString("precio"),
                                Integer.toString(rs.getInt("estado"))
                        );

                    }
                }
            } catch (URISyntaxException | IOException ex) {
                Logger.getLogger(PeliculaDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
                return resultado;
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
            return resultado;
        }
        return resultado;
    }
    
    public void actualizarEstado(String id, String estado) throws SQLException, Exception{ //Argumentos= id de la pelicula y nuevo estado de esta pelicula
        PreparedStatement stm = Database.instance().prepareStatement(PeliculaCRUD.CMD_ACTUALIZAR_ESTADO);
        stm.clearParameters();
        int state = Integer.parseInt(estado);
        stm.setInt(1, state);
        stm.setString(2, id);
        int count = Database.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("duplicado");
        }
    }
    public void actualizarEstadoCartelera(String id, String estado){ //Argumentos= id de la pelicula y nuevo estado de esta pelicula
      
        try{
        PreparedStatement stm = Database.instance().prepareStatement(PeliculaCRUD.CMD_ACTUALIZAR_ESTADO_CARTELERA);
        stm.clearParameters();
        int state = Integer.parseInt(estado);
        stm.setInt(1, state);
        stm.setString(2, id);
        int count = Database.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("duplicado");
        }
        } catch (Exception ex){
        
        }
    }

    public void actualizar(Pelicula p) throws SQLException, Exception {

        PreparedStatement stm = Database.instance().prepareStatement(PeliculaCRUD.CMD_ACTUALIZAR);

        //"UPDATE Pelicula SET id_Pelicula = ?, Nombre = ?, duracion = ?, descripcion = ? , precio = ?, estado = ?"
        stm.clearParameters();
        stm.setString(1, p.getId());
        stm.setString(2, p.getNombre());
        stm.setString(3, p.getDuracion());
        stm.setString(4, p.getDescripcion());
        stm.setString(5, p.getPrecio());
        stm.setString(6, p.getEstado());
        stm.setString(7, p.getId());
        int count = Database.instance().executeUpdate(stm);
        if (count == 0) {
            throw new Exception("duplicado");
        }
    }
}