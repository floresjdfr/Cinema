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
import java.sql.*;
import java.util.Base64;

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
        //byte[] bytes = new byte[10000];
        //read = image.read(bytes);

        PreparedStatement stm = Database.instance().prepareStatement(PeliculaCRUD.CMD_AGREGAR_IMAGE);

        stm.setString(1, id_Pelicula);
        //stm.setBinaryStream(2, (InputStream) image, (int) (read));
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
            //Blob imageBlob;
            InputStream binaryStream;
            PreparedStatement ps = Database.instance().prepareStatement(PeliculaCRUD.CMD_RECUPERAR_IMAGE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //imageBlob = rs.getBlob("image");
                binaryStream = rs.getBinaryStream("image");
                while (binaryStream.read(imageBytes) > 0) {
                    fos.write(imageBytes);
                }

                return file;
            }
        } catch (Exception e) {

        }
        return file;
//        try {
//
//            byte imageBytes[];
//            Blob imageBlob;
//            InputStream binaryStream;
//            PreparedStatement ps = Database.instance().prepareStatement(PeliculaCRUD.CMD_RECUPERAR_IMAGE);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                imageBytes = rs.getBytes("image");
//                Image image = getToolkit().createImage(imageBytes);
//                Image img = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
//                ImageIcon icon = new ImageIcon(img);
//
//                //imageBlob = rs.getBlob("image");
//                //binaryStream = imageBlob.getBinaryStream(0, imageBlob.length());
//                //imageBytes = imageBlob.getBytes(1, (int) imageBlob.length());
//            }
//        } catch (Exception e) {
//
//        }
//        try {
//
//            PreparedStatement stm = Database.instance().prepareStatement(PeliculaCRUD.CMD_RECUPERAR_IMAGE);
//            stm.clearParameters();
//            stm.setString(1, id_Pelicula);
//            try (ResultSet result = stm.executeQuery()) {
//                if (result.next()) {
//                    imagen = new Image();
//                    Blob blob = result.getBlob("image");
//
//                    InputStream inputStream = blob.getBinaryStream();
//                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//                    byte[] buffer = new byte[10000];
//                    int bytesRead = -1;
//
//                    while ((bytesRead = inputStream.read(buffer)) != -1) {
//                        outputStream.write(buffer, 0, bytesRead);
//                    }
//
//                    byte[] imageBytes = outputStream.toByteArray();
//                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
//
//                    inputStream.close();
//                    outputStream.close();
//
//                    imagen.setBase64Image(base64Image);
//                }
//            } catch (Exception ex) {
//                Logger.getLogger(PeliculaDAO.class
//                        .getName()).log(Level.SEVERE, null, ex);
//                return imagen;
//            }
//
//        } catch (SQLException ex) {
//            throw ex;
//        }
        //       return imagen;
    }

    /*-------------------------------------------------------------------------------------------*/
    public void crear(Pelicula p) throws Exception {

        PreparedStatement stm = Database.instance().prepareStatement(PeliculaCRUD.CMD_AGREGAR);

        stm.setString(1, p.getNombre());
        stm.setString(2, p.getDuracion());
        stm.setString(3, p.getDescripcion());
        stm.setString(4, p.getPrecio());

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
/*

import java.io.*;  
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;  
import javax.servlet.http.*;  
import model.ConnectionManager;
public class DisplayImage extends HttpServlet { 
    public void doGet(HttpServletRequest request,HttpServletResponse response)  
             throws IOException  
    { 
    Statement stmt=null;
    String sql=null;
    BufferedInputStream bin=null;
    BufferedOutputStream bout=null;
    InputStream in =null;

    response.setContentType("image/jpeg");  
    ServletOutputStream out;  
    out = response.getOutputStream();  
    Connection conn = ConnectionManager.getConnection();

    int ID = Integer.parseInt(request.getParameter("ID"));
        try {
            stmt = conn.createStatement();
            sql = "SELECT * FROM IMAGETABLE WHERE ID="+ID+"";
            ResultSet result = stmt.executeQuery(sql);
            if(result.next()){
                in=result.getBinaryStream(3);//Since my data was in third column of table.
            }
            bin = new BufferedInputStream(in);  
            bout = new BufferedOutputStream(out);  
            int ch=0;   
            while((ch=bin.read())!=-1)  
                {  
                bout.write(ch);  
            }  

        } catch (SQLException ex) {
            Logger.getLogger(DisplayImage.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
        try{
            if(bin!=null)bin.close();  
            if(in!=null)in.close();  
            if(bout!=null)bout.close();  
            if(out!=null)out.close();
            if(conn!=null)conn.close();
        }catch(IOException | SQLException ex){
            System.out.println("Error : "+ex.getMessage());
        }
    }


    }  
}  

 */

 /*
final String dbURL = "jdbc:mysql://localhost:3306/portfolio";
    final String dbUser = "root";
    final String dbPass = "";

    Connection conn = null;
    Statement stmt = null;

    try {
        //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Class.forName("com.mysql.jdbc.Driver");

        conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
        System.out.println("db connected");
        stmt = (Statement) conn.createStatement();

        ResultSet rs1;
        rs1 = stmt.executeQuery("select profileImage from tbl_welcome where id = 1117");

        if (rs1.next()) {
            byte[] imgData = rs1.getBytes("profileImage");//Here....... r1.getBytes() extract byte data from resultSet 
            System.out.println(imgData);
            response.setHeader("expires", "0");
            response.setContentType("image/jpg");

            OutputStream os = response.getOutputStream(); // output with the help of outputStream 
            os.write(imgData);
            os.flush();
            os.close();

        }
    } catch (SQLException ex) {
        // String message = "ERROR: " + ex.getMessage();
        ex.printStackTrace();
    } finally {
        if (conn != null) {
            // closes the database connection
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    
 */
