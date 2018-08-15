package ch.hearc.ig.micorr.rest.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
	 * L'accès à ce service se fait via l'URL suivant :
	 * 
	 * http://hostname/MiCorr-WebServices/artifacts/list?text=monTexte&Country=monCountry...
	 * 
	 * Tous les paramètres ne sont pas obligatoire. Il suffit de renseigner uniquement les 
	 * paramètres voulus. Les autres paramètres seront initialisés à null et seront ignorés
	 * par les différents services.
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
	@Path("/micorrArtefactList")
	@Produces(MediaType.APPLICATION_JSON)
	public Artefacts getMiCorrArtefactList(@QueryParam("text") String text, @QueryParam("country") String country, @QueryParam("metalFamily") String metalFamily, @QueryParam("corrosionForms") String corrosionForms, @QueryParam("environments") String environments) {
		
		services = new OntologyService();
		artefacts = new Artefacts();
		
		artefacts.setText(services.getProperties(text));
		artefacts.setCountry(services.getProperties(country));
		artefacts.setMetal(services.getProperties(metalFamily));
		artefacts.setCorrosion(services.getProperties(corrosionForms));
		artefacts.setEnvironment(services.getProperties(environments));
		
		if(artefacts.getText().getId() >= 0) {
			services.getParents(artefacts.getText());
			services.getChildren(artefacts.getText());
			services.getPropertyAssertions(artefacts.getText());
		}
		
		if(artefacts.getCountry().getId() >= 0) {
			services.getParents(artefacts.getCountry());
			services.getChildren(artefacts.getCountry());
			services.getPropertyAssertions(artefacts.getCountry());
		}
		
		if(artefacts.getMetal().getId() >= 0) {
			services.getParents(artefacts.getMetal());
			services.getChildren(artefacts.getMetal());
			services.getPropertyAssertions(artefacts.getMetal());
		}
		
		if(artefacts.getCorrosion().getId() >= 0) {
			services.getParents(artefacts.getCorrosion());
			services.getChildren(artefacts.getCorrosion());
			services.getPropertyAssertions(artefacts.getCorrosion());
		}
		
		if(artefacts.getEnvironment().getId() >= 0) {
			services.getParents(artefacts.getEnvironment());
			services.getChildren(artefacts.getEnvironment());
			services.getPropertyAssertions(artefacts.getEnvironment());
		}
		
		return artefacts;
		
	}

}
