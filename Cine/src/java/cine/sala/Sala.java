
package cine.sala;

public class Sala {
    String sala;
    String asientos;

    public Sala() {
    }

    public Sala(String sala, String asientos) {
        this.sala = sala;
        this.asientos = asientos;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getButacas() {
        return asientos;
    }

    public void setButacas(String asientoss) {
        this.asientos = asientos;
    }
    
}
