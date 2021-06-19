/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import cine.cartelera.Cartelera;
import cine.pelicula.Pelicula;
import cine.sala.Sala;
import cine.ticket.Ticket;
import cine.ticket.TicketListado;
import logic.Service;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("cartelera")
@PermitAll
public class Carteleras {
    
    String location = "/home/josedf/Documentos/Programacion IV/Proyecto II/Proyecto_II_Progra_IV/Cine/web/Images/";

    @GET
    @Path("peliculas")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Pelicula> listDisp() {
        return Service.instance().peliculasListDisp();
    }

    @GET
    @Path("carteleras")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Cartelera> listAllCartelera() {
        try {
            return Service.instance().getListaCarteleras();
        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }

    @GET
    @Path("tickets")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Ticket> listAllTicket() {
        try {
            return Service.instance().getListaTickets();
        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }

    @GET
    @Path("salas")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Sala> listAllSalas() {
        try {
            return Service.instance().getListaSalas();
        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }
    
    

    @GET
    @Path("{id}/imagen")
    @Produces("image/png")
    public Response getImge(@PathParam("id") String id_pelicula) throws IOException {
        try {
            File file = new File(location + id_pelicula);
            Response.ResponseBuilder response = Response.ok((Object) file);
            return response.build();

        } catch (Exception ex) {
            throw new NotAcceptableException();
        }
    }
    
}
