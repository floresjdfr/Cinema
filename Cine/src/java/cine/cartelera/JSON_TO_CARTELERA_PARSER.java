
package cine.cartelera;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.sql.Date;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class JSON_TO_CARTELERA_PARSER {
    public Cartelera parseCJSON(String json) {
        Cartelera aux = null;
        try {
            Object obj = new JSONParser().parse(json);
            JSONObject jo = (JSONObject) obj;
            
            String fecha = (String) jo.get("fecha");
            String hi = (String) jo.get("horaInicio");
            String hf = (String) jo.get("horaFin");
            String idp = (String) jo.get("IdpeliC");
            String idc = (String) jo.get("IdSalaC");
            
            aux = new Cartelera(fecha,hi,hf,idp,idc);
            } catch (Exception ec) {
                System.out.print(ec.getMessage());
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
