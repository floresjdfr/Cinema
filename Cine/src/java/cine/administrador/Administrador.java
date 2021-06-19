
package cine.administrador;

import cine.usuario.Usuario;


public class Administrador extends Usuario{

    public Administrador() {
    }

    public Administrador(String id, String pass) {
        super(id, pass,"ADMINISTRATOR");
    }
    
}
