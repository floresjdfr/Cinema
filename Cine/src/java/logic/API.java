package logic;

import controller.Administrador;
import controller.Carteleras;
import controller.User;
import filter.RestfulSecurityFilter;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

@ApplicationPath("api")
public class API extends Application {
    @Override
    public Set<Class<?>> getClasses() {

        HashSet<Class<?>> classes = new HashSet<>();
        classes.add(MultiPartFeature.class);
        System.out.println("added multipart feature");
        classes.add(Administrador.class);
        classes.add(Carteleras.class);
        classes.add(User.class);
        classes.add(RestfulSecurityFilter.class);
        
        return classes;
    }   
}
