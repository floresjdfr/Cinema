
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
   
    
//            System.out.println(firstName);
//            System.out.println(lastName);
//
//            // getting age
//            long age = (long) jo.get("age");
//            System.out.println(age);
//
//            // getting address
//            Map address = ((Map) jo.get("address"));
//
//            // iterating address Map
//            Iterator<Map.Entry> itr1 = address.entrySet().iterator();
//            while (itr1.hasNext()) {
//                Map.Entry pair = itr1.next();
//                System.out.println(pair.getKey() + " : " + pair.getValue());
//            }
//
//            // getting phoneNumbers
//            JSONArray ja = (JSONArray) jo.get("phoneNumbers");
//
//            // iterating phoneNumbers
//            Iterator itr2 = ja.iterator();
//
//            while (itr2.hasNext()) {
//                itr1 = ((Map) itr2.next()).entrySet().iterator();
//                while (itr1.hasNext()) {
//                    Map.Entry pair = itr1.next();
//                    System.out.println(pair.getKey() + " : " + pair.getValue());
//                }
//            }
}
