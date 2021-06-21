package controller;

import cine.cliente.Cliente;
import cine.cliente.JSON_TO_CLIENTE_PARSER;
import cine.ticket.JSON_TO_TICKET_PARSER;
import cine.ticket.Ticket;
import cine.ticket.TicketListado;
import cine.usuario.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.PUT;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import logic.Service;

@Path("usuario")
@PermitAll
public class User {

    @Context
    HttpServletRequest request;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Usuario search() {
        Usuario s = new Usuario("sfdfsd", "sdfafsdsa");
        return s;
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario login(Usuario usuario) {
        HttpSession sesion = request.getSession(true);
        Usuario logged = null;
        try {
            logged = (Cliente) Service.instance().getCliente(usuario.getId());
        } catch (Exception ex) {
            try {
                logged = Service.instance().getAdmin(usuario.getId());
            } catch (Exception ex2) {

            }
        }
        if (logged == null) {
            throw new NotFoundException("Usuario no existe");
        }

        if (!logged.getPassword().equals(usuario.getPassword())) {
            throw new NotAcceptableException("Clave incorrecta");
        }
        request.getSession(true).setAttribute("Usuario", logged);
        return logged;
    }
    
    @GET 
    @Path("check-user")
    @Produces({MediaType.APPLICATION_JSON})
    @PermitAll
    public Usuario checkUser() {
        return (Usuario) request.getSession(true).getAttribute("Usuario"); 
    }

    @POST
    @Path("register")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario register(String json) {
        JSON_TO_CLIENTE_PARSER parser = new JSON_TO_CLIENTE_PARSER();//crea una clase para parsear el objeto
        Cliente cliente = parser.parseCJSON(json);//llamado a un metodo que crea un objeto cliete a mano a partir del string
        Usuario user = null;
        try {
            Service.instance().crearCliente(cliente);
            user = new Usuario(cliente.getId(), cliente.getPassword(), "CLIENTE");

        } catch (Exception ex) {
            throw new NotAcceptableException();
        }

        HttpSession sesion = request.getSession(true);
        Usuario logged = null;
        try {
            logged = (Cliente) Service.instance().getCliente(user.getId());
        } catch (Exception ex) {
            try {
                logged = Service.instance().getAdmin(user.getId());
            } catch (Exception ex2) {

            }
        }
        if (logged == null) {
            throw new NotFoundException("Usuario no existe");
        }

        if (!logged.getPassword().equals(user.getPassword())) {
            throw new NotAcceptableException("Clave incorrecta");
        }
        request.getSession(true).setAttribute("Usuario", logged);
        return logged;
    }

    @POST
    @Path("{idCartelera}/comprar")
    @RolesAllowed("CLIENTE")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<TicketListado> comprar(String json, @PathParam("idCartelera") String idCartelera) {//idCartelera
        
        try {
            HttpSession session = request.getSession(true);
            Usuario user= (Usuario) request.getSession().getAttribute("Usuario");
            JSON_TO_TICKET_PARSER parser = new JSON_TO_TICKET_PARSER();//crea una clase para parsear el objeto
            try {
                user = (Cliente) Service.instance().getCliente(user.getId());
            } catch (Exception ex) {
                try {
                    user = Service.instance().getAdmin(user.getId());
                } catch (Exception ex2) {

                }
            }
            List<Ticket> ticket = parser.parseCJSON(idCartelera, json, user);
            
            int ticketID = Service.instance().crearTickets(ticket);
            List<TicketListado> tic =  Service.instance().obtenerTicket(Integer.toString(ticketID));
            return tic;
        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
        @RolesAllowed("CLIENTE")
    public void update(Cliente cliente) {
        try {
        } catch (Exception ex) {
            throw new NotFoundException();
        }
    }

    @DELETE
    @Path("logout")
    @RolesAllowed({"CLIENTE", "ADMINISTRATOR"})
    public void logout() {
        HttpSession session = request.getSession(true);
        session.removeAttribute("Usuario");
        session.invalidate();
    }
    
    @GET 
    @Path("{id}/ticketsListado")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"CLIENTE"})
    public List<TicketListado> listadoTickets(@PathParam("id") String json) {
        try {
            List<TicketListado> tl = new ArrayList<>();
            tl =Service.instance().VersionMejorada(json);
            System.out.print(tl);
            return tl;
            
        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }
}
