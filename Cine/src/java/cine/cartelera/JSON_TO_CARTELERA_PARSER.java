
package cine.cartelera;

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
}
