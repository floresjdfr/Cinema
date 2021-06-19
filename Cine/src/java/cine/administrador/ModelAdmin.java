
package cine.administrador;

import java.util.HashMap;


public class ModelAdmin {
    
    private static ModelAdmin uniqueInstance;
    private Administrador admin;
    private AdministradorDAO dao;
    
    public static ModelAdmin instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ModelAdmin();
        }
        return uniqueInstance;
    }
    
    public ModelAdmin() {
        this.dao = new AdministradorDAO();
    }
    
   
    
    private void getAdministradorDB(String id) {
        admin = dao.recuperar(id);
    }

    //crear, obtener por XXXX, listar, eliminar*** 
    public void insertar(Administrador p) throws Exception {
        dao.crear(p);
    }
    
    public Administrador getAdmin(String cedula) throws Exception {
        getAdministradorDB(cedula);
        if (admin == null) {
            throw new Exception("Cliente no existe");
        }
        return admin;
    }
    
    public void eliminar(String id) throws Exception{
        dao.eliminar(id);
    }
    
}
