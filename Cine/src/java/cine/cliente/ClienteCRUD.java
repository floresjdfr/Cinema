
package cine.cliente;

public class ClienteCRUD {
    protected static final String CMD_LISTAR
            = "SELECT id_Cliente, password, nombre, apellido, numero_Cuenta FROM Cliente "
            + "ORDER BY apellido, nombre;";
    
    protected static final String CMD_AGREGAR
            = "INSERT INTO Cliente (id_Cliente, password, nombre, apellido, numero_Cuenta) "
            + "VALUES (?, ?, ?, ?, ?); ";

    protected static final String CMD_RECUPERAR
            = "SELECT id_Cliente, password, nombre, apellido, numero_Cuenta FROM Cliente "
            + "WHERE id_Cliente = ?; ";
    
    protected static final String CMD_ACTUALIZAR
            = "UPDATE Cliente SET id_Cliente = ?, password = ?, nombre = ?, apellido = ?, numero_Cuenta = ?"
            + "WHERE id_Cliente = ?;";
    
    protected static final String CMD_ELIMINAR
            = "DELETE FROM Cliente "
            + "WHERE id_Cliente = ?; ";
    
}