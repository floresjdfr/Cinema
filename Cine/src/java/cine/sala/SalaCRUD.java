package cine.sala;



public class SalaCRUD {
    protected static final String CMD_LISTAR
            = "SELECT id_Sala, total_Butacas FROM Sala "
            + "ORDER BY id_Sala";
    
    protected static final String CMD_AGREGAR
            = "INSERT INTO Sala (id_Sala, total_Butacas) "
            + "VALUES (?, ?); ";

    protected static final String CMD_RECUPERAR
            = "SELECT id_Sala, total_Butacas FROM Sala "
            + "WHERE id_Sala = ?; ";
    
    protected static final String CMD_ACTUALIZAR
            = "UPDATE Sala SET id_Sala = ?, total_Butacas = ?"
            + "WHERE id_Sala = ?;";
    
    protected static final String CMD_ELIMINAR
            = "DELETE FROM Sala "
            + "WHERE id_Sala = ?; ";
    
}
