package cine.sala;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelSala {

    private static ModelSala uniqueInstance;
    HashMap<String, Sala> salas;
    SalaDAO dao;

    public static ModelSala instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ModelSala();
        }
        return uniqueInstance;
    }

    public ModelSala() {
        this.dao = new SalaDAO();
        salas = new HashMap<>();
    }

    private void listaSalas() {
        cleanHash();
        salas = dao.listarSala();
    }

    void cleanHash() {
        if (!salas.isEmpty()) {
            salas.clear();
        }
    }

    public HashMap retornaHashMap() {
        listaSalas();
        return salas;
    }

    public List<Sala> retornaLista() {
        listaSalas();
        return new ArrayList(salas.values());
    }

    public void insertar(Sala p) throws Exception {
        dao.crear(p);
    }

    public Sala getSala(String nombre) throws Exception {
        listaSalas();
        Sala result = null;
        for (Sala p : salas.values()) {
            if (p.getSala().contains(nombre)) {
                return p;
            }
        }
        if (result == null) {
            throw new Exception("Sala no existe");
        }
        return result;
    }

    public void eliminar(String id) throws Exception {
        dao.eliminar(id);
    }

}
