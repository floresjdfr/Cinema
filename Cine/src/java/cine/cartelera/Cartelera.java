
package cine.cartelera;

public class Cartelera {
    String id;
    String fecha_funcion;
    String hora_inicio;
    String hora_fin;
    String pelicula;
    String sala;

    public Cartelera() {
    }

    public Cartelera(String id, String fecha_funcion, String hora_inicio, String hora_fin, String pelicula, String sala) {
        this.id = id;
        this.fecha_funcion = fecha_funcion;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.pelicula = pelicula;
        this.sala = sala;
    }

    public Cartelera(String fecha_funcion, String hora_inicio, String hora_fin, String pelicula, String sala) {
        this.fecha_funcion = fecha_funcion;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.pelicula = pelicula;
        this.sala = sala;
    }

    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha_funcion() {
        return fecha_funcion;
    }

    public void setFecha_funcion(String fecha_funcion) {
        this.fecha_funcion = fecha_funcion;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(String hora_fin) {
        this.hora_fin = hora_fin;
    }

    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }
    
}
