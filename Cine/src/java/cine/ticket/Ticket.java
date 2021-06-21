package cine.ticket;


public class Ticket {

    String id;
    String butaca;
    String cliente;
    String cartelera;

    public Ticket() {
    }

    public Ticket(String id, String butaca, String cliente, String cartelera) {
        this.id = id;
        this.butaca = butaca;
        this.cliente = cliente;
        this.cartelera = cartelera;
    }

    public Ticket(String butaca, String cliente, String cartelera) {
        this.id = null;
        this.butaca = butaca;
        this.cliente = cliente;
        this.cartelera = cartelera;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getButaca() {
        return butaca;
    }

    public void setButaca(String butaca) {
        this.butaca = butaca;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCartelera() {
        return cartelera;
    }

    public void setCartelera(String cartelera) {
        this.cartelera = cartelera;
    }

}
