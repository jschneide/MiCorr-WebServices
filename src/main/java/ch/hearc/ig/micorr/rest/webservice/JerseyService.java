package ch.hearc.ig.micorr.rest.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.hearc.ig.micorr.rest.business.Artefacts;
import ch.hearc.ig.micorr.rest.service.OntologyService;

/**
 * Classe contenant les webservices mis à disposition pour l'interrogation
 * de l'ontologie MiCorr. Ces webservices retournent tous des informations au
 * format JSON.
 * 
 * @author Jérôme Schneider
 *
 */

@Path("/artifacts")
public class JerseyService {
	
	private OntologyService services;
	
	private Artefacts artefacts;
	
	public JerseyService() {
		
	}

	/**
	 * Webservices le plus complet. Il reçoit tous les paramètres de l'outil de recherche
	 * du site de MiCorr et retourne toutes les données liées à ces différentes mots.
	 * 
	 * @param text texte libre à rechercher dans l'ontologie
	 * @param country pays à rechercher dans l'ontologie
	 * @param metalFamily famille de métal à rechercher dans l'ontologie
	 * @param corrosionForms forme de corrosion à rechercher dans l'ontologie
	 * @param environments type d'environnement à rechercher dans l'ontologie
	 * @return retourne un Artefacts qui contient toutes les données trouvées au format JSON
	 * 
	 */
	@GET
	@Path("{text}/{country}/{metalFamily}/{corrosionForms}/{environments}")
	@Produces(MediaType.APPLICATION_JSON)
	public Artefacts getMiCorrArtefactList(@PathParam("text") String text, @PathParam("country") String country, @PathParam("metalFamily") String metalFamily, @PathParam("corrosionForms") String corrosionForms, @PathParam("environments") String environments) {
		
		services = new OntologyService();
		artefacts = new Artefacts();
		
		artefacts.setText(services.getProperties(text));
		artefacts.setCountry(services.getProperties(country));
		artefacts.setMetal(services.getProperties(metalFamily));
		artefacts.setCorrosion(services.getProperties(corrosionForms));
		artefacts.setEnvironment(services.getProperties(environments));
		
		if(artefacts.getText().getId() != 0) {
			services.getParents(artefacts.getText());
			services.getPropertyAssertions(artefacts.getText());
		}
		
		if(artefacts.getCountry().getId() != 0) {
			services.getParents(artefacts.getCountry());
			services.getPropertyAssertions(artefacts.getCountry());
		}
		
		if(artefacts.getMetal().getId() != 0) {
			services.getParents(artefacts.getMetal());
			services.getPropertyAssertions(artefacts.getMetal());
		}
		
		if(artefacts.getCorrosion().getId() != 0) {
			services.getParents(artefacts.getCorrosion());
			services.getPropertyAssertions(artefacts.getCorrosion());
		}
		
		if(artefacts.getEnvironment().getId() != 0) {
			services.getParents(artefacts.getEnvironment());
			services.getPropertyAssertions(artefacts.getEnvironment());
		}
		
		return artefacts;
		
	}

}
