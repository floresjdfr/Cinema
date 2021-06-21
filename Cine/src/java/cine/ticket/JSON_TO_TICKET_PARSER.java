package cine.ticket;

import cine.usuario.Usuario;
import java.util.ArrayList;
import java.util.List;

public class JSON_TO_TICKET_PARSER {
    //idCartelera

    public List<Ticket> parseCJSON(String idCartelera, String json, Usuario u) {
        Ticket aux = null;
        ArrayList<Ticket> tickets = new ArrayList<>();
        try {

            String cartelera = idCartelera;
            String id, butaca = json, cliente;
            tickets.add(new Ticket(butaca, u.getId(), cartelera));
          
        } catch (Exception ec) {

        }
        return tickets;
    }
}
