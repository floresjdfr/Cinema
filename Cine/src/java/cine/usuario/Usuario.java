package cine.usuario;

public class Usuario {

    String id;
    String password;
    private String type;

    public Usuario() {
    }

    public Usuario(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public Usuario(String id, String password, String type) {
        this.id = id;
        this.password = password;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the type (tipo o roll)
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the (tipo o roll) to set
     */
    public void setType(String type) {
        this.type = type;
    }

}
