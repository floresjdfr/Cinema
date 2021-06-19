
package cine.usuario;

import java.util.Map;


public class Model {
    
     private static Model uniqueInstance;
     static Map<String,Usuario> usuarios;
    
    public static Model instance(){
        if (uniqueInstance == null){
            uniqueInstance = new Model();
        }
        return uniqueInstance;
    }

    public Model() {
        tmpUser = new Usuario();
    }

    public Model(Usuario usr) {
        this.tmpUser = usr;
    }

    public Usuario getUsr() {
        return tmpUser;
    }

    public void setUsr(Usuario usr) {
        this.tmpUser = usr;
    }
    
    private Usuario tmpUser;
    
    
     public static Usuario get(Usuario id)throws Exception{
        Usuario result = usuarios.get(id.getId());
        if (result==null) throw new Exception("Usuario no existe");
        return result;
    }   
}