
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
    
}
