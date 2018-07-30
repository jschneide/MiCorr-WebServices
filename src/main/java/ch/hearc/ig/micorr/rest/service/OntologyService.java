package ch.hearc.ig.micorr.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.QuerySolution;

import ch.hearc.ig.micorr.rest.business.Artefact;
import ch.hearc.ig.micorr.rest.query.OntologyQuery;

public class OntologyService {

	private List<QuerySolution> querySolutionList;

	private OntologyQuery query;

	public OntologyService() {
		query = new OntologyQuery();
	}

	public Artefact getProperties(String search) {

		querySolutionList = new ArrayList<>();

		// - Recherche si la donnée saisie existe dans l'ontologie
		querySolutionList = query.getPropertiesDataQuery(search);
		
		Artefact artefact = null;

		if (!querySolutionList.isEmpty()) {
			QuerySolution data = (QuerySolution) querySolutionList.get(0);
			
			artefact = new Artefact(data.getLiteral("?artefactId").getInt(), data.getLiteral("?artefactName").getString(), data.getResource("?artefactType").getLocalName(), null, null);
		} else {
			artefact = new Artefact(0, "", "", null, null);
		}
		
		return artefact;
	}
	
	
		
}
