package logic;

import cine.administrador.Administrador;
import cine.administrador.ModelAdmin;
import cine.cartelera.Cartelera;
import cine.cartelera.ModelCart;
import cine.cliente.Cliente;
import cine.cliente.ModelCli;
import cine.pelicula.Image;
import cine.pelicula.ModelPeli;
import cine.pelicula.Pelicula;
import cine.sala.ModelSala;
import cine.sala.Sala;
import cine.ticket.ModelTicket;
import cine.ticket.Ticket;
import cine.ticket.TicketListado;
import cine.usuario.Model;
import cine.usuario.Usuario;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class Service {

    private static Service uniqueInstance;
    HttpServletRequest request;

    public static Service instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Service();
        }
        return uniqueInstance;
    }

    //---------------------------------------------------Peliculas------------------------------------------------------------------
    public Pelicula getPelicula(String nombre) throws Exception {
        return ModelPeli.instance().getPelicula(nombre);
    }

    public void crearPelicula(Pelicula P) throws Exception {
        ModelPeli.instance().insertar(P);
    }

    public void eliminarPelicula(String id) throws Exception {
        ModelPeli.instance().eliminar(id);
    }

    public void activarPelicula(String id) throws Exception {
        ModelPeli.instance().activar(id);
    }

    public HashMap getListapeliculas() {
        return ModelPeli.instance().retornaLista();
    }

    public HashMap getListapeliculasDisp() {
        return ModelPeli.instance().retornaListaDisp();
    }

    public List<Pelicula> peliculasListAll() {
        return ModelPeli.instance().peliculasListAll();
    }

    public List<Pelicula> peliculasListDisp() {
        return ModelPeli.instance().peliculasListDisp();
    }

    public void insertarImagen(String id_Pelicula, InputStream image) throws Exception {
        ModelPeli.instance().insertarImagen(id_Pelicula, image);
    }

    public File getImagen(String id_Pelicula) throws Exception {

        return ModelPeli.instance().getImagen(id_Pelicula);
    }

    //------------------------------------------------------Salas--------------------------------------------------------------------
    public Sala getSala(String nombre) throws Exception {
        return ModelSala.instance().getSala(nombre);
    }

    public void crearSala(Sala P) throws Exception {
        ModelSala.instance().insertar(P);
    }

    public void eliminarSala(String id) throws Exception {
        ModelSala.instance().eliminar(id);
    }

    public List<Sala> getListaSalas() {
        return ModelSala.instance().retornaLista();
    }

    //------------------------------------------------------Cliente--------------------------------------------------------------------
    public Cliente getCliente(String id) throws Exception {
        return ModelCli.instance().getCliente(id);
    }

    public void crearCliente(Cliente P) throws Exception {
        ModelCli.instance().insertar(P);
    }

    public void eliminarCliente(String id) throws Exception {
        ModelCli.instance().eliminar(id);
    }

    //------------------------------------------------------Administrador-------------------------------------------------------------
    public Administrador getAdmin(String id) throws Exception {
        return ModelAdmin.instance().getAdmin(id);
    }

    public void crearAdmin(Administrador P) throws Exception {
        ModelAdmin.instance().insertar(P);
    }

    public void eliminarAdmin(String id) throws Exception {
        ModelAdmin.instance().eliminar(id);
    }

    //------------------------------------------------------Tickets--------------------------------------------------------------------
    public Ticket getTicket(String nombre) throws Exception {
        return ModelTicket.instance().getTicket(nombre);
    }

    public void crearTicket(Ticket P) throws Exception {
        ModelTicket.instance().insertar(P);
    }

    public int crearTickets(List<Ticket> LT) throws Exception {
        return ModelTicket.instance().insertarTickets(LT);
    }

    public void eliminarTicket(String id) throws Exception {
        ModelTicket.instance().eliminar(id);
    }

    public List<Ticket> getListaTickets() {
        return ModelTicket.instance().retornaArrayList();
    }

    public List<TicketListado> VersionMejorada(String json) {
        List<TicketListado> LTL = new ArrayList<>();
        LTL = ModelTicket.instance().mejorado(json);
        return LTL;
    }
    public List<TicketListado> obtenerTicket(String ticketNumber) {
        List<TicketListado> LTL = new ArrayList<>();
        LTL = ModelTicket.instance().obtenerTicket(ticketNumber);
        return LTL;
    }

    //------------------------------------------------------Cartelera------------------------------------------------------------------
    public Cartelera getCartelera(String nombre) throws Exception {
        return ModelCart.instance().getCart(nombre);
    }

    public void crearCartelera(Cartelera P) throws Exception {
        ModelCart.instance().insertar(P);
    }

    public void eliminarCartelera(String id) throws Exception {
        ModelCart.instance().eliminar(id);
    }

    public List<Cartelera> getListaCarteleras() {
        return ModelCart.instance().cartelerasGetArray();
    }

    public void login(Usuario usr) {
        request.getSession().setAttribute("user", usr);
    }

    public void logout() {
        request.getSession().removeAttribute("user");
    }

    private boolean isAuthenticated() {
        return request.getSession().getAttribute("user") != null;
    }

    public String getUserRole() {//Retorna el rol que tiene el usuario loggeado
        if (isAuthenticated()) {
            Usuario usuario = (Usuario) request.getSession().getAttribute("user");
            return usuario.getType();
        }
        return null;
    }
//    private static Service uniqueInstance;
//    HashMap<String,Pelicula> peliculas;
//    HashMap<String,Usuario> usuarios;
//    
//    
//    
//    public static Service instance() {
//        if (uniqueInstance == null) {
//            uniqueInstance = new Service();
//        }
//        return uniqueInstance;
//    }
//    private Service(){
//        peliculas = new HashMap<> ();
//        //String id, String nombre, String descripcion
//        peliculas.put("01", new Pelicula("01","Spider Man","The amazing spider-man","01:45"));
//        peliculas.put("02", new Pelicula("02","Matrix","God Keanu reeves","02:05"));
//        peliculas.put("03", new Pelicula("03","Matrix II","Super God Keanu Reeves","01:38"));
//        peliculas.put("04", new Pelicula("04","Sponge Bob","GOD KEANU REEVES","01:50"));
//        
//        
//        usuarios = new HashMap<> ();
//        usuarios.put("04", new Usuario("00","aaa"));
//        usuarios.put("04", new Usuario("01","bbb"));
//        usuarios.put("04", new Usuario("02","ccc"));
//        usuarios.put("04", new Usuario("03","ddd"));
//    }
//   
//    public List<Pelicula> peliculaListAll() {
//        return new ArrayList(peliculas.values());
//    }
//    public List<Usuario> usuarioListAll() {
//        return new ArrayList(usuarios.values());
//    }
//    public List<Pelicula> peliculaSearch(String nombre) {
//        List<Pelicula> result = new ArrayList<>();
//        for(Pelicula p:peliculas.values()){
//            if(p.getNombre().contains(nombre)) result.add(p);
//        }
//        return result;
//    } 
//    public Pelicula peliculaAdd(Pelicula pel)throws Exception {
//        if (peliculas.get(pel.getId())!=null){
//            throw new Exception ("406-pelicula ya existe");
//        }
//        else{
//            peliculas.put(pel.getId(),pel);
//            return pel;
//        }
//    }    
//    public void peliculaUpdate(Pelicula per)throws Exception {
//        if (peliculas.get(per.getId())==null){
//            throw new Exception ("404-pelicula no existe");
//        }
//        else{
//            peliculas.put(per.getId(), per);
//        }
//    }   
//    public void peliculaDelete(String id)throws Exception {
//        if (peliculas.get(id)==null){
//            throw new Exception ("404-pelicula no existe");
//        }
//        else{
//            peliculas.remove(id);
//        }
//    } 
//    public Pelicula peliculaEdit(String id)throws Exception {
//        if (peliculas.get(id)!=null){
//            return peliculas.get(id);     
//        }
//        else{
//            throw new Exception ("404-pelicula no existe");
//        }
//    }

}
