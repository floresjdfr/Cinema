
package cine.sala;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class JSON_TO_SALA_PARSER {
 
    public Sala parseCJSON(String json) {
        Sala aux = null;
        try {
            Object obj = new JSONParser().parse(json);
            JSONObject jo = (JSONObject) obj;

            String sala = (String) jo.get("idSala");
            String asientos = (String) jo.get("asientos");
            aux = new Sala(sala,asientos);
            } catch (Exception ec) {

        }
        return aux;
    }
    
//
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
