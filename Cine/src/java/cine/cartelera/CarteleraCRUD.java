
package cine.cartelera;

public class CarteleraCRUD {

    protected static final String CMD_LISTAR
            = "SELECT id_Cartelera, fecha_Funcion, hora_Inicio, hora_Fin, id_Pelicula, id_Sala , estado FROM Cartelera "
            + "WHERE estado = 1 "
            +"and fecha_Funcion > current_date()";
    
    protected static final String CMD_LISTAR_PELICULA = "SELECT * from Cartelera WHERE pelicula = ?;";

    protected static final String CMD_AGREGAR
            = "INSERT INTO Cartelera (fecha_Funcion, hora_Inicio, hora_Fin, id_Pelicula, id_Sala, estado) "
            + "VALUES (?, ?, ?, ?, ?, ?); ";

    protected static final String CMD_RECUPERAR
            = "SELECT id_Cartelera, fecha_Funcion, hora_Inicio, hora_Fin, id_Pelicula, id_Sala FROM Cartelera "
            + "WHERE id_Cartelera = ?; ";

    protected static final String CMD_ACTUALIZAR
            = "UPDATE Cartelera SET id_Cartelera = ?, fecha_Funcion = ?, hora_Inicio = ?, hora_Fin = ?, id_Pelicula = ?, id_Sala = ?"
            + "WHERE id_Cartelera = ?;";

    protected static final String CMD_ELIMINAR
            = "DELETE FROM Cartelera "
            + "WHERE id_Cartelera = ?; ";

    protected static final String CMD_LISTAR_CARTELERA_PELICULA = ""
            + "SELECT * "
            + "FROM Cartelera "
            + "WHERE pelicula = ?;";
}