
package cine.cliente;

import cine.usuario.Usuario;


public class Cliente extends Usuario{

    private String apellidos;
    private String nombre;
    private String numero_cuenta;

    public Cliente() {
    }

    public Cliente(String id, String password, String nombre, String apellidos, String numero_cuenta) {
        super(id, password,"CLIENTE");
        this.nombre=nombre;
        this.apellidos=apellidos;
        this.numero_cuenta=numero_cuenta;
    }
        public Cliente( String apellidos, String id,  String nombre,String numero_cuenta,String password,String type) {
        super(id, password,type);
        this.apellidos=apellidos;
        this.nombre=nombre;
        this.numero_cuenta=numero_cuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellidos;
    }

    public void setApellido(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNumero_cuenta() {
        return numero_cuenta;
    }

    public void setNumero_cuenta(String numero_cuenta) {
        this.numero_cuenta = numero_cuenta;
    }
    
    
}
