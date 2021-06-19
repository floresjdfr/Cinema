
package cine.administrador;


public class AdministradorCRUD {
    protected static final String CMD_LISTAR
            = "SELECT id_Administrador, password FROM Administrador "
            + "ORDER BY id_Administrador";
    
    protected static final String CMD_AGREGAR
            = "INSERT INTO Administrador (id_Administrador, password) "
            + "VALUES (?, ?); ";

    protected static final String CMD_RECUPERAR
            = "SELECT id_Administrador, password FROM Administrador "
            + "WHERE id_Administrador = ?; ";
    
    protected static final String CMD_ACTUALIZAR
            = "UPDATE Administrador SET idAdministrador = ?, password = ?"
            + "WHERE id_Administrador = ?;";
    
    protected static final String CMD_ELIMINAR
            = "DELETE FROM Administrador "
            + "WHERE id_Administrador = ?; ";
    
}
