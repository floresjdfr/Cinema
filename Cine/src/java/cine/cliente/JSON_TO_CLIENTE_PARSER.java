
package cine.cliente;

import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;


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
}
