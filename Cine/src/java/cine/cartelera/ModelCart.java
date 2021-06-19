package cine.cartelera;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModelCart {

    private static ModelCart uniqueInstance;
    HashMap<String, Cartelera> carteleras;
    CarteleraDAO dao;

    public static ModelCart instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ModelCart();
        }
        return uniqueInstance;
    }

    public ModelCart() {
        this.dao = new CarteleraDAO();
        carteleras = new HashMap<>();
    }

    private void listaCarteleras() {
        cleanHash();
        carteleras = dao.listarCart();
    }

    public HashMap retornaLista() {
        listaCarteleras();
        return carteleras;
    }

    void cleanHash() {
        if (!carteleras.isEmpty()) {
            carteleras.clear();
        }
    }

    public List<Cartelera> cartelerasGetArray() {
        listaCarteleras();
        return new ArrayList(carteleras.values());
    }

    public void insertar(Cartelera p) throws Exception {
        dao.crear(p);
    }

    public Cartelera getCart(String nombre) throws Exception {
        listaCarteleras();
        Cartelera result = null;
        for (Cartelera p : carteleras.values()) {
            if (p.getId().contains(nombre)) {
                return p;
            }
        }
        if (result == null) {
            throw new Exception("Cartelera no existe");
        }
        return result;
    }

    public void eliminar(String id) throws Exception {
        dao.eliminar(id);
    }
}
