package cine.pelicula;

public class PeliculaCRUD {

    protected static final String CMD_LISTAR
            = "SELECT id_Pelicula, Nombre, duracion, descripcion , precio, estado FROM Pelicula "
            + "ORDER BY id_Pelicula;";

    protected static final String CMD_LISTAR_DISPINIBLES
            = "SELECT id_Pelicula, Nombre, duracion, descripcion, precio, estado FROM Pelicula "
            + "WHERE estado = 1 "
            + "ORDER BY id_Pelicula";

    protected static final String CMD_AGREGAR
            = "INSERT INTO Pelicula (Nombre, duracion, descripcion, precio) "
            + "VALUES (?, ?, ?, ?); ";

    protected static final String CMD_RECUPERAR
            = "SELECT id_Pelicula, Nombre, duracion, descripcion, precio, estado FROM Pelicula "
            + "WHERE id_Pelicula = ?; ";

    protected static final String CMD_ACTUALIZAR
            = "UPDATE Pelicula SET id_Pelicula = ?, Nombre = ?, duracion = ?, descripcion = ? , precio = ?, estado = ? "
            + "WHERE id_Pelicula = ?;";

    protected static final String CMD_ACTUALIZAR_ESTADO
            = "UPDATE Pelicula SET estado = ? "
            + "WHERE id_Pelicula = ?;";
    
    protected static final String CMD_ACTUALIZAR_ESTADO_CARTELERA
            = "UPDATE Cartelera SET estado = ? "
            + "WHERE id_Pelicula = ?;";
    /**
     * ****************************IMAGES*****************************************************************************************************
     */
    protected static final String CMD_LISTAR_IMAGES
            = "SELECT id, image FROM Images "
            + "ORDER BY id;";

    protected static final String CMD_AGREGAR_IMAGE
            = "INSERT INTO Images (id, image) "
            + "VALUES (?, ?); ";

    protected static final String CMD_RECUPERAR_IMAGE
            = "SELECT image FROM Images "
            + "WHERE id = ?; ";

}
