package ch.hearc.ig.micorr.rest.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.hearc.ig.micorr.rest.business.Artefacts;
import ch.hearc.ig.micorr.rest.service.OntologyService;


@Path("/artifacts")
public class JerseyService {
	
	private OntologyService services;
	
	private Artefacts artefacts;
	
	public JerseyService() {
		
	}

	@GET
	@Path("{text}/{country}/{metalFamily}/{corrosionForms}/{environments}")
	@Produces(MediaType.APPLICATION_JSON)
	public Artefacts getMiCorrArtefactList(@PathParam("text") String text, @PathParam("country") String country, @PathParam("metalFamily") String metalFamily, @PathParam("corrosionForms") String corrosionForms, @PathParam("environments") String environments) {
		
		services = new OntologyService();
		
		System.out.println("Text : "+ text);
		System.out.println("country : "+ country);
		System.out.println("metalFamily : "+ metalFamily);
		System.out.println("corrosionForms : "+ corrosionForms);
		System.out.println("environments : "+ environments);
		
		artefacts.setText(services.getProperties(text));
		artefacts.setCountry(services.getProperties(country));
		artefacts.setMetal(services.getProperties(metalFamily));
		artefacts.setCorrosion(services.getProperties(corrosionForms));
		artefacts.setEnvironment(services.getProperties(environments));
		
		
		
		return artefacts;
		
	}

}
