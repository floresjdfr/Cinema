package cine.pelicula;

import java.io.File;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModelPeli {

    private static ModelPeli uniqueInstance;
    HashMap<String, Pelicula> peliculas;
    PeliculaDAO dao;

    public static ModelPeli instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ModelPeli();
        }
        return uniqueInstance;
    }

    public ModelPeli() {
        this.dao = new PeliculaDAO();
    }

    private void listaPeliculas() {
        peliculas = dao.listarPeli();
    }
    
    private void listaPeliculasDisponibles(){
        peliculas = dao.listarPeliDisponibles();
    }
    
    public HashMap retornaListaDisp(){
        cleanHash();
        listaPeliculasDisponibles();
        return peliculas;
    }
    public HashMap retornaLista() {
        cleanHash();
        listaPeliculas();
        return peliculas;
    }
    void cleanHash(){
    if(!peliculas.isEmpty())
        peliculas.clear();
    }
    public List<Pelicula> peliculasListAll() {
        listaPeliculas();
        return new ArrayList(peliculas.values());
    } 
    public List<Pelicula> peliculasListDisp(){
        //cleanHash();
        listaPeliculasDisponibles();
        return new ArrayList(peliculas.values());
    }
    //crear, obtener por XXXX, listar, eliminar*** 
    public void insertar(Pelicula p) throws Exception {
        dao.crear(p);
    }

    public Pelicula getPelicula(String nombre) throws Exception {
        listaPeliculas();
        Pelicula result = null;
        for (Pelicula p : peliculas.values()) {
            if (p.getNombre().contains(nombre)) {
                result = p;
                return result;
            }
        }
        if (result == null) {
            throw new Exception("Pelicula no existe");
        }
        return result;
    }

    public Pelicula getPeliculaID(String id) throws Exception {
        listaPeliculas();
        Pelicula result = null;
        for (Pelicula p : peliculas.values()) {
            if (p.getId().contains(id)) {
                result = p;
                return result;
            }
        }
        if (result == null) {
            throw new Exception("Pelicula no existe");
        }
        return result;
    }
    
    
    public void eliminar(String id) throws Exception {
        dao.actualizarEstado(id, "0");
        dao.actualizarEstadoCartelera(id,"0");
    }
    
    public void activar(String id) throws Exception{
        dao.actualizarEstado(id, "1");
        dao.actualizarEstadoCartelera(id,"1");
    }

    public void insertarImagen(String id_Pelicula, InputStream image) throws Exception {
        dao.insertImage(id_Pelicula, image);
    }
    public File getImagen(String id_Pelicula) throws Exception {
       return dao.getImage(id_Pelicula);
    }

}
