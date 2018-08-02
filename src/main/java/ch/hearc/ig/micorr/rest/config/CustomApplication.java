package ch.hearc.ig.micorr.rest.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import ch.hearc.ig.micorr.rest.webservice.JerseyService;

/**
 * Classe de paramètres de l'application afin de lier les webservices
 * avec l'API Moxy de formattage des données en JSON
 * 
 * @author Jérôme Schneider
 *
 */
public class CustomApplication extends Application
{
    //Add Service APIs
    @Override
    public Set<Class<?>> getClasses()
    {
        Set<Class<?>> resources = new HashSet<Class<?>>();
 
        //register REST modules
        resources.add(JerseyService.class);
 
        //Manually adding MOXyJSONFeature
        resources.add(org.glassfish.jersey.moxy.json.MoxyJsonFeature.class);
 
        //Configure Moxy behavior
        resources.add(JsonMoxyConfigurationContextResolver.class);
 
        return resources;
    }
}