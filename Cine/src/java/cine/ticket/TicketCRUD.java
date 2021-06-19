
package cine.ticket;


public class TicketCRUD {

    protected static final String CMD_LISTAR
            = "SELECT id_Ticket, numero_Butaca, id_Cliente, id_Cartelera FROM Ticket";
            //+ "WHERE id_Ticket = ?;";

    protected static final String CMD_AGREGAR
            = "INSERT INTO Ticket (id_Ticket, numero_Butaca, id_Cliente, id_Cartelera) "
            + "VALUES (?, ?, ?, ?);";

    protected static final String CMD_RECUPERAR
            = "SELECT id_Ticket, numero_Butaca, id_Cliente, id_Cartelera FROM Ticket "
            + "WHERE id_Ticket = ?; ";

    protected static final String CMD_ACTUALIZAR
            = "UPDATE Ticket SET id_Ticket = ?, numero_Butaca = ?, id_Cliente = ?, id_Cartelera = ?"
            + "WHERE codigo = ?;";

    protected static final String CMD_ELIMINAR
            = "DELETE FROM Ticket "
            + "WHERE id_Ticket = ?; ";

    protected static final String CMD_LISTAR_TICKET_CLIENTE 
            = "SELECT id_Ticket, numero_Butaca, id_Cliente, id_Cartelera FROM Ticket "
            + "WHERE id_Cliente = ?; ";
    
    protected static final String CMD_LISTAR_MEJORADO
            = " select t.id_Ticket, t.numero_Butaca, c.nombre, c.apellido,  p.Nombre, ca.id_Sala, ca.fecha_Funcion, ca.hora_Inicio from Ticket t, Cliente c, Pelicula p, Cartelera ca "
            + " where ? = t.id_Cliente and t.id_Cartelera = ca.id_Cartelera and ca.id_Pelicula = p.id_Pelicula and t.id_Cliente = c.id_Cliente; ";
    
    protected static final String CMD_RECUPERAR_TICKET
            = " select t.id_Ticket, t.numero_Butaca, c.nombre, c.apellido,  p.Nombre, ca.id_Sala, ca.fecha_Funcion, ca.hora_Inicio from Ticket t, Cliente c, Pelicula p, Cartelera ca "
            + " where ? = t.id_Ticket and t.id_Cartelera = ca.id_Cartelera and ca.id_Pelicula = p.id_Pelicula and t.id_Cliente = c.id_Cliente; ";
    

}


