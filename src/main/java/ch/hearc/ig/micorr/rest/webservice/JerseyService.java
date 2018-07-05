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
	
	public JerseyService() {
		services = new OntologyService();
	}

	@GET
	@Path("{text}/{country}/{metalFamily}/{corrosionForms}/{environments}")
	@Produces(MediaType.APPLICATION_JSON)
	public Artefacts getMiCorrArtefactList(@PathParam("text") String text, @PathParam("country") String country, @PathParam("metalFamily") String metalFamily, @PathParam("corrosionForms") String corrosionForms, @PathParam("environments") String environments) {
		Artefacts list = new Artefacts();
		
		System.out.print("Text : "+ text);
		System.out.print("country : "+ country);
		System.out.print("metalFamily : "+ metalFamily);
		System.out.print("corrosionForms : "+ corrosionForms);
		System.out.print("environments : "+ environments);
		
		list.setArtefactList(services.getResearchProperties(text));
		
		//list.getArtefactList().add(new Artefact(1, "Test", "Test1", "Test2"));
		
		return list;
		
	}

}
