
package cine.pelicula;

import cine.sala.Sala;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class JSON_TO_PELICULA_PARSER {
    
    public Pelicula parseCJSON(String json) {
        Pelicula aux = null;
        try {
            Object obj = new JSONParser().parse(json);
            JSONObject jo = (JSONObject) obj;

            String id = (String) jo.get("idPelicula");
            String nombre = (String) jo.get("pelicula");
            String descripcion = (String) jo.get("descripcion");
            String duracion = (String) jo.get("duracion");
            String precio = (String) jo.get("precio");
            String estado = (String) jo.get("estado");
            aux = new Pelicula(id,nombre,duracion,descripcion,precio, estado);
            } catch (Exception ec) {

        }
        return aux;
    }
   
    
}
