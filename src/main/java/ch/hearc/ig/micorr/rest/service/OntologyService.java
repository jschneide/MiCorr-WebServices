package ch.hearc.ig.micorr.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.QuerySolution;

import ch.hearc.ig.micorr.rest.business.Artefact;
import ch.hearc.ig.micorr.rest.query.OntologyQuery;

/**
 * Classe contenant les différents services d'interrogation de l'ontologie.
 * Ces services permettent de trouver les propriétés, parents ou encore
 * assertions d'un artefact.
 * 
 * @author Jérôme Schneider
 *
 */
public class OntologyService {

	private List<QuerySolution> querySolutionList;

	private OntologyQuery query;

	public OntologyService() {
		query = new OntologyQuery();
	}

	/**
	 * Service de recherche des propriétés d'un artefact. Il renvoie l'id, 
	 * le nom et le type de l'artefact s'il a été trouvé dans l'ontologie.
	 * Sinon un résultat vide contenant un id à 0 et la recherche comme nom. 
	 * Si le paramètre search est null, aucune recherche n'est lancée et
	 * un artefact vide est retourné.
	 * 
	 * @param search l'artefact à rechercher dans l'ontologie
	 * @return Artefact contenant le résultats de la recherche. Si la recherche
	 * 		   n'a rien trouvée, un artefact vide contenant un id à 0 et le paramètre 
	 *         search comme nom est retourné. Si le paramètre search est null, aucune
	 *         recherche n'est lancée et un artefact vide est retourné.
	 *         
	 */
	public Artefact getProperties(String search) {

		querySolutionList = new ArrayList<>();

		Artefact artefact = null;
		
		// - Recherche si la donnée saisie existe dans l'ontologie
		if(search != null) {
			querySolutionList = query.getPropertiesDataQuery(search);
			
			if (!querySolutionList.isEmpty()) {
				QuerySolution data = (QuerySolution) querySolutionList.get(0);
				
				artefact = new Artefact(data.getLiteral("?artefactId").getInt(), data.getLiteral("?artefactName").getString(), data.getResource("?artefactType").getLocalName());
			} else {
				artefact = new Artefact(0, search, "");
			}
		}else {
			artefact = new Artefact(0, "", "");
		}
		
		return artefact;
	}
	
	/**
	 * Service de recherche des parents et des soeurs/frères d'un type
	 * d'artefact. Il permet de retrouver tous les éléments du même 
	 * niveau que la recherche de base ainsi que son/ses parent(s)
	 * les plus directs.
	 * 
	 * @param artefact l'artefact créé lors de la recherche des propriétés
	 * @return Artefact reçu en paramètre avec les informations
	 *         complémentaires concernants les parents et frères/soeurs
	 *         
	 */
	public Artefact getParents(Artefact artefact) {
		
		querySolutionList = new ArrayList<>();

		// - Recherche les soeurs du type de la données
		querySolutionList = query.getParentsDataQuery(artefact.getType());
		
		if (!querySolutionList.isEmpty()) {
			List<String> resSisters = new ArrayList<>();
			List<String> resParents = new ArrayList<>();
			
			for(int i = 0; i < querySolutionList.size(); i++) {
				resSisters.add(querySolutionList.get(i).getLiteral("?labelSister").getString());
				
				if(!resParents.contains(querySolutionList.get(i).getLiteral("?labelParent").getString())) {
					resParents.add(querySolutionList.get(i).getLiteral("?labelParent").getString());
				}
			}
			
			artefact.setSistersData(resSisters);
			artefact.setParentsData(resParents);
		}
		
		return artefact;
	}
	
	/**
	 * Services de recherches des assertions existantes dans l'ontologie pour un
	 * artefact données.
	 * 
	 * @param artefact l'artefact créé lors de la recherche des propriétés
	 * @return Artefact reçu en paramètre avec les informations
	 *         complémentaires concernants les assertions trouvées
	 */
	public Artefact getPropertyAssertions(Artefact artefact) {
		
		querySolutionList = new ArrayList<>();

		// - Recherche les assertion existant pour une donnée
		querySolutionList = query.getPropertyAssertionsDataQuery(artefact.getName());
		
		if (!querySolutionList.isEmpty()) {
			List<String> resAssertions = new ArrayList<>();
			
			for(int i = 0; i < querySolutionList.size(); i++) {
				resAssertions.add(querySolutionList.get(i).getLiteral("?labelAssertion").getString());
			}
			
			artefact.setAssertions(resAssertions);
		}
		
		return artefact;
	}
		
}
