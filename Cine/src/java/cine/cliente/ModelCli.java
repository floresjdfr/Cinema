package cine.cliente;


public class ModelCli {

    private static ModelCli uniqueInstance;
    private Cliente cliente;
    private ClienteDAO dao;

    public static ModelCli instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ModelCli();
        }
        return uniqueInstance;
    }

    public ModelCli() {
        this.dao = new ClienteDAO();
    }

    private void getClienteDB(String id) {
        cliente = dao.recuperar(id);
    }
    
    public void insertar(Cliente p) throws Exception {
        dao.crear(p);
    }

    public Cliente getCliente(String cedula) throws Exception {
        getClienteDB(cedula);
        if (cliente == null) {
            throw new Exception("Cliente no existe");
        }
        return cliente;
    }

    public void eliminar(String id) throws Exception {
        dao.eliminar(id);
    }

}
