
package cine.cliente;

import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;



/**
 *
 * @author boyro
 */
public class JSON_TO_CLIENTE_PARSER {

    public JSON_TO_CLIENTE_PARSER() {
    }

    public Cliente parseCJSON(String json) {
        Cliente cliente = null;
        try {
            Object obj = new JSONParser().parse(json);
            JSONObject jo = (JSONObject) obj;

            String firstName = (String) jo.get("nombre");
            String lastName = (String) jo.get("apellidos");
            String id = (String) jo.get("id");
            String pass = (String) jo.get("password");
            String numero_cuenta = (String) jo.get("numero_cuenta");
            cliente = new Cliente(id,pass, firstName,lastName,numero_cuenta);
            } catch (Exception ec) {

        }
        return cliente;
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
