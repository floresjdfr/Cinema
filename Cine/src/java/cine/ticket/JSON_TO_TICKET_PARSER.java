/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cine.ticket;

import cine.usuario.Usuario;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
////import jdk.nashorn.internal.parser.JSONParser;
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author boyro
 */
public class JSON_TO_TICKET_PARSER {
    //idCartelera

    public List<Ticket> parseCJSON(String idCartelera, String json, Usuario u) {
        Ticket aux = null;
        ArrayList<Ticket> tickets = new ArrayList<>();
        try {

            String cartelera = idCartelera;
            //String id, butaca = "", cliente;
            String id, butaca = json, cliente;      
            // iterating seatsArray       
            //String str = "{ \"number\": [3-0, 0-4, 0-5, 4-6] }";
//            StringTokenizer multiTokenizer = new StringTokenizer(json, "://.[]'\"'");
//            while (multiTokenizer.hasMoreTokens()) {
//                butaca += multiTokenizer.nextToken();         
//            }
            tickets.add(new Ticket(butaca, u.getId(), cartelera));
          
        } catch (Exception ec) {

        }
        return tickets;
    }
}
