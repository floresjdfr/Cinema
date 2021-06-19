
package cine.ticket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TicketListado {
    String id;
    String nombre;
    String apellido;
    String sala;
    String pelicula;
    String fecha;
    String hora;
    List<String> asiento;

    public TicketListado(String id, String nombre, String apellido, String sala, String pelicula, String fecha, String hora,String asiento) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sala = sala;
        this.pelicula = pelicula;
        this.fecha = fecha;
        this.hora = hora;
        this.asiento = loadSeats(asiento);
    }

    public TicketListado() {
        asiento = new ArrayList<String>();
    }
    
    private List<String> loadSeats(String asiento){//Parsea el string de asientos en un arreglo
        List<String> asientos = new ArrayList<>();
        asiento = asiento.replace("[", "");
        asiento = asiento.replace("]", "");
        asiento = asiento.replace("\"", "");//Borra el caracter '"' de la cadena
        String[] asientosArray = asiento.split(","); //Divido el array por las ","
        asientos = Arrays.asList(asientosArray);
        return asientos;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setAsiento(String asiento) {
        this.asiento = loadSeats(asiento);
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getSala() {
        return sala;
    }

    public String getPelicula() {
        return pelicula;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public List<String> getAsiento() {
        return asiento;
    }
    
    
}
